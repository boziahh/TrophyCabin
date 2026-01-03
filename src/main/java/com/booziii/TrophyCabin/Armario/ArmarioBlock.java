package com.booziii.TrophyCabin.Armario;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ArmarioBlock extends BaseEntityBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final IntegerProperty FRONT_TEXTURE = IntegerProperty.create("front_texture", 0, 6);

	public ArmarioBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(FRONT_TEXTURE, 0));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FRONT_TEXTURE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockPos pos = ctx.getClickedPos();
		Level level = ctx.getLevel();
		BlockState state = this.defaultBlockState()
				.setValue(FACING, ctx.getHorizontalDirection().getOpposite());
		return updateFrontTexture(level, pos, state);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		updateSelfAndNeighbors(level, pos, state);
	}

	private void updateSelfAndNeighbors(Level level, BlockPos pos, BlockState state) {
		level.setBlock(pos, updateFrontTexture(level, pos, state), 3);
		for (Direction dir : Direction.values()) {
			BlockPos neighbor = pos.relative(dir);
			BlockState neighborState = level.getBlockState(neighbor);
			if (neighborState.is(this)) {
				level.setBlock(neighbor, updateFrontTexture(level, neighbor, neighborState), 3);
			}
		}
	}

	private BlockState updateFrontTexture(Level level, BlockPos pos, BlockState state) {
		Direction facing = state.getValue(FACING);
		Direction right = facing.getClockWise();

		for (int ox = 0; ox < 2; ox++) {
			for (int oy = 0; oy < 3; oy++) {
				BlockPos start = pos.offset(-ox * right.getStepX(), -oy, -ox * right.getStepZ());
				if (is2x3(level, start, facing, right)) {
					return state.setValue(FRONT_TEXTURE, get2x3Index(start, pos, right));
				}
			}
		}
		return state.setValue(FRONT_TEXTURE, 0);
	}

	private boolean is2x3(Level level, BlockPos start, Direction facing, Direction right) {
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 3; y++) {
				BlockPos p = start.offset(x * right.getStepX(), y, x * right.getStepZ());
				BlockState bs = level.getBlockState(p);
				if (!bs.is(this) || bs.getValue(FACING) != facing)
					return false;
			}
		}
		return true;
	}

	private int get2x3Index(BlockPos start, BlockPos current, Direction right) {
		int localY = current.getY() - start.getY();
		int localX;

		if (right == Direction.EAST)
			localX = current.getX() - start.getX();
		else if (right == Direction.WEST)
			localX = start.getX() - current.getX();
		else if (right == Direction.SOUTH)
			localX = current.getZ() - start.getZ();
		else
			localX = start.getZ() - current.getZ();

		if (localX == 0)
			return localY == 2 ? 1 : localY == 1 ? 2 : 3;

		if (localX == 1)
			return localY == 2 ? 4 : localY == 1 ? 5 : 6;

		return 0;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ArmarioBlockEntity(pos, state);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hit) {
		if (level.isClientSide)
			return InteractionResult.SUCCESS;

		BlockEntity be = level.getBlockEntity(pos);
		if (be instanceof ArmarioBlockEntity armario) {
			level.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
			player.openMenu(armario);
		}
		return InteractionResult.CONSUME;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			if (!level.isClientSide) {
				BlockEntity be = level.getBlockEntity(pos);
				if (be instanceof ArmarioBlockEntity armario)
					Containers.dropContents(level, pos, armario);
			}
			updateNeighbors(level, pos);
			super.onRemove(state, level, pos, newState, isMoving);
		}
	}

	private void updateNeighbors(Level level, BlockPos pos) {
		for (Direction dir : Direction.values()) {
			BlockPos neighbor = pos.relative(dir);
			BlockState neighborState = level.getBlockState(neighbor);
			if (neighborState.is(this)) {
				level.setBlock(neighbor, updateFrontTexture(level, neighbor, neighborState), 3);
			}
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
}
