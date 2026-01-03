package com.booziii.TrophyCabin.Trophies;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VoxelShapeRotation {

	public static VoxelShape rotate(VoxelShape shape, Direction from, Direction to) {
		VoxelShape result = shape;
		int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;

		for (int i = 0; i < times; i++) {
			result = rotateOnce(result);
		}

		return result;
	}

	private static VoxelShape rotateOnce(VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[]{Shapes.empty()};

		shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
			buffer[0] = Shapes.join(
				buffer[0],
				Shapes.box(
					1 - maxZ,
					minY,
					minX,
					1 - minZ,
					maxY,
					maxX
				),
				BooleanOp.OR
			);
		});

		return buffer[0];
	}
}
