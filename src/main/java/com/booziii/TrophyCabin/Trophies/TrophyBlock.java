package com.booziii.TrophyCabin.Trophies;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrophyBlock extends HorizontalDirectionalBlock {

	public static final MapCodec<TrophyBlock> CODEC = BlockBehaviour.simpleCodec(TrophyBlock::new);

	private VoxelShape north = Shapes.empty();
	private VoxelShape south = Shapes.empty();
	private VoxelShape east = Shapes.empty();
	private VoxelShape west = Shapes.empty();

	public TrophyBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD));
		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	public TrophyBlock setShape(VoxelShape baseShape) {
		north = baseShape;
		south = VoxelShapeRotation.rotate(baseShape, Direction.NORTH, Direction.SOUTH);
		east = VoxelShapeRotation.rotate(baseShape, Direction.NORTH, Direction.EAST);
		west = VoxelShapeRotation.rotate(baseShape, Direction.NORTH, Direction.WEST);
		return this;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	private VoxelShape shapeFor(BlockState state) {
		return switch (state.getValue(FACING)) {
			case SOUTH -> south;
			case EAST -> east;
			case WEST -> west;
			default -> north;
		};
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shapeFor(state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shapeFor(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
