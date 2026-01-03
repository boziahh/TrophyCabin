package com.booziii.TrophyCabin.Trophies;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Trophies {
	public class BlockShapeHelper {
		public static VoxelShape box(double x1, double y1, double z1, double x2, double y2, double z2) {
			return Block.box(x1, y1, z1, x2, y2, z2);
		}

		public static VoxelShape combine(VoxelShape... shapes) {
			VoxelShape result = Shapes.empty();
			for (VoxelShape shape : shapes)
				result = Shapes.or(result, shape);
			return result;
		}
	}

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, "trophycabin");

	public static final DeferredHolder<Block, Block> DEER_FANCY_TROPHY = BLOCKS.register(
			"deer_fancy_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(2, 0, 13, 14, 3, 16),
									BlockShapeHelper.box(0, 3, 13, 16, 16, 16),
									BlockShapeHelper.box(5, 5, 6, 11, 10, 13),
									BlockShapeHelper.box(6, 6, 3, 10, 9, 6),
									BlockShapeHelper.box(2, 7, 11, 5, 10, 12),
									BlockShapeHelper.box(11, 7, 11, 14, 10, 12))));

	public static final DeferredHolder<Block, Block> BISON_FANCY_TROPHY = BLOCKS.register(
			"bison_fancy_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(-2.5, 9, 6, 2.5, 12, 7),
									BlockShapeHelper.box(0, 3, 13, 16, 16, 16),
									BlockShapeHelper.box(2, 0, 13, 14, 3, 16),
									BlockShapeHelper.box(2.5, 2, 2, 13.5, 14, 15),
									BlockShapeHelper.box(13, 10, 8, 16, 20, 11),
									BlockShapeHelper.box(13.5, 9, 6, 18.5, 12, 7),
									BlockShapeHelper.box(4.5, 2, -3, 11.5, 10, 2),
									BlockShapeHelper.box(0, 10, 8, 3, 20, 11))));

	public static final DeferredHolder<Block, Block> RED_WOLF_FANCY_TROPHY = BLOCKS.register(
			"red_wolf_fancy_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(0, 3, 13, 16, 16, 16),
									BlockShapeHelper.box(2, 0, 13, 14, 3, 16),
									BlockShapeHelper.box(4, 4.5, 7, 12, 10, 13),
									BlockShapeHelper.box(6, 4.5, 4, 10, 7, 7),
									BlockShapeHelper.box(4, 10.5, 11, 7, 14, 12),
									BlockShapeHelper.box(9, 10.5, 11, 12, 14, 12))));
	public static final DeferredHolder<Block, Block> GOOSE_STATUE = BLOCKS.register(
			"goose_statue",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(1.5F, 6.0F)
							.sound(SoundType.STONE)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(9.75, 5, 10, 13.25, 9, 13),
									BlockShapeHelper.box(10.75, 0.2, 10.75, 12.25, 6.2, 12.25),
									BlockShapeHelper.box(9.25, 0, 6.75, 13.75, 1, 12.5),

									BlockShapeHelper.box(2.75, 5, 10, 6.25, 9, 13),
									BlockShapeHelper.box(3.75, 0.2, 10.75, 5.25, 6.2, 12.25),
									BlockShapeHelper.box(2.25, 0, 6.75, 6.75, 1, 12.5),

									BlockShapeHelper.box(6.5, 8.04804, 16.15301, 9.5, 11.04804, 20.15301),
									BlockShapeHelper.box(5.5, 7.54804, 12.15301, 10.5, 12.54804, 17.15301),

									BlockShapeHelper.box(4, 7, 4, 12, 15, 13),

									BlockShapeHelper.box(6.5, 17.04458, 6.58152, 9.5, 25.04458, 9.58152),
									BlockShapeHelper.box(6.5, 22.09458, 0.93152, 9.5, 23.09458, 5.23152),
									BlockShapeHelper.box(6.5, 22.99458, 1.03152, 9.5, 24.29458, 5.33152),
									BlockShapeHelper.box(6.875, 24.19458, 1.13152, 9.125, 25.49458, 5.33152),
									BlockShapeHelper.box(6, 22.04458, 4.83152, 10, 26.29458, 8.83152),

									BlockShapeHelper.box(6, 11, 4, 10, 18, 8))));

	public static final DeferredHolder<Block, Block> COD_TROPHY = BLOCKS.register(
			"cod_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(0, 3, 15, 16, 13, 16),
									BlockShapeHelper.box(4, 6, 13, 11, 10, 15),
									BlockShapeHelper.box(14, 7.0008, 12.9992, 15, 10.0008, 14.9992),
									BlockShapeHelper.box(11, 6, 13, 14, 10, 15),
									BlockShapeHelper.box(10, 6, 11, 12, 7, 13),
									BlockShapeHelper.box(-2, 6, 14, 4, 10, 14),
									BlockShapeHelper.box(7, 5, 14, 9, 6, 14),
									BlockShapeHelper.box(6, 10, 14, 12, 11, 14))));

	public static final DeferredHolder<Block, Block> SALMON_TROPHY = BLOCKS.register(
			"salmon_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(0, 3, 15, 16, 13, 16),
									BlockShapeHelper.box(14, 5, 11, 16, 5, 13),
									BlockShapeHelper.box(5, 10, 14, 10, 12, 14),
									BlockShapeHelper.box(-6, 5.5, 14, 0, 10.5, 14),
									BlockShapeHelper.box(8, 5.5, 12.5, 16, 10.5, 15.5),
									BlockShapeHelper.box(0, 5.5, 12.5, 8, 10.5, 15.5),
									BlockShapeHelper.box(16, 6.5, 13, 19, 10.5, 15),
									BlockShapeHelper.box(14, 5.86703, 12.50752, 16, 5.86703, 14.50752),
									BlockShapeHelper.box(14, 5.86703, 13.49258, 16, 5.86703, 15.49258))));

	public static final DeferredHolder<Block, Block> FANCY_SALMON_TROPHY = BLOCKS.register(
			"fancy_salmon_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(0, 3, 13, 16, 16, 16),
									BlockShapeHelper.box(2, 0, 13, 14, 3, 16),
									BlockShapeHelper.box(14, 6, 9, 16, 6, 11),
									BlockShapeHelper.box(5, 11, 12, 10, 13, 12),
									BlockShapeHelper.box(-6, 6.5, 12, 0, 11.5, 12),
									BlockShapeHelper.box(8, 6.5, 10.5, 16, 11.5, 13.5),
									BlockShapeHelper.box(0, 6.5, 10.5, 8, 11.5, 13.5),
									BlockShapeHelper.box(16, 7.5, 11, 19, 11.5, 13),
									BlockShapeHelper.box(14, 6.86703, 10.50752, 16, 6.86703, 12.50752),
									BlockShapeHelper.box(14, 6.86703, 11.49258, 16, 6.86703, 13.49258))));
	public static final DeferredHolder<Block, Block> FANCY_COD_TROPHY = BLOCKS.register(
			"fancy_cod_trophy",
			() -> new TrophyBlock(
					BlockBehaviour.Properties.of()
							.mapColor(MapColor.WOOD)
							.strength(2.0F)
							.noOcclusion())
					.setShape(
							BlockShapeHelper.combine(
									BlockShapeHelper.box(0, 3, 13, 16, 16, 16),
									BlockShapeHelper.box(2, 0, 13, 14, 3, 16),
									BlockShapeHelper.combine(
											BlockShapeHelper.box(4, 7, 11, 11, 11, 13),
											BlockShapeHelper.combine(
													BlockShapeHelper.box(14, 8.0008, 10.9992, 15, 11.0008, 12.9992),
													BlockShapeHelper.box(11, 7, 11, 14, 11, 13)),
											BlockShapeHelper.box(10, 7, 9, 12, 8, 11),
											BlockShapeHelper.box(10, 7, 13, 12, 8, 15),
											BlockShapeHelper.box(-2, 7, 12, 4, 11, 12),
											BlockShapeHelper.box(7, 6, 12, 9, 7, 12),
											BlockShapeHelper.box(6, 11, 12, 12, 12, 12)))));

}
