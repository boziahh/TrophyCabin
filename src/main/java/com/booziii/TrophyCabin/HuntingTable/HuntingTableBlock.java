package com.booziii.TrophyCabin.HuntingTable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class HuntingTableBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<TableHalf> HALF = EnumProperty.create("half", TableHalf.class);
	private static final VoxelShape SHAPE = Shapes.or(
			Block.box(0, 0, 0, 16, 2, 16),
			Block.box(4, 2, 4, 12, 12, 12),
			Block.box(0, 12, 0, 16, 14, 16));

	public HuntingTableBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(HALF, TableHalf.LEFT));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction facing = ctx.getHorizontalDirection().getOpposite();
		BlockPos pos = ctx.getClickedPos();
		Level level = ctx.getLevel();
		BlockPos rightPos = pos.relative(facing.getClockWise());

		if (!level.getBlockState(rightPos).canBeReplaced(ctx))
			return null;

		level.setBlock(rightPos, defaultBlockState()
				.setValue(FACING, facing)
				.setValue(HALF, TableHalf.RIGHT), 3);

		return defaultBlockState()
				.setValue(FACING, facing)
				.setValue(HALF, TableHalf.LEFT);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hit) {
		if (level.isClientSide)
			return InteractionResult.SUCCESS;

		var provider = new SimpleMenuProvider(
				(id, inv, p) -> new HuntingTableMenu(id, inv, p),
				Component.translatable("container.trophycabin.hunting_table"));

		((ServerPlayer) player).openMenu(provider);
		return InteractionResult.CONSUME;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return state.getValue(HALF) == TableHalf.LEFT ? SHAPE : SHAPE;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.is(newState.getBlock())) {
			Direction facing = state.getValue(FACING);
			BlockPos otherPos = state.getValue(HALF) == TableHalf.LEFT
					? pos.relative(facing.getClockWise())
					: pos.relative(facing.getCounterClockWise());

			if (level.getBlockState(otherPos).is(this))
				level.removeBlock(otherPos, false);
		}
		super.onRemove(state, level, pos, newState, moved);
	}

	enum TableHalf implements StringRepresentable {
		LEFT,
		RIGHT;

		@Override
		public String getSerializedName() {
			return name().toLowerCase();
		}
	}

}
