package com.booziii.TrophyCabin;

import com.booziii.TrophyCabin.Armario.ArmarioBlock;
import com.booziii.TrophyCabin.Armario.ArmarioBlockEntity;
import com.booziii.TrophyCabin.BearRug.BearRugBlock;
import com.booziii.TrophyCabin.HuntingTable.HuntingTableBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.booziii.TrophyCabin.Trophies.Trophies;
import com.booziii.TrophyCabin.Trophies.TrophyItems;

public class ModRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(TrophyCabin.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(TrophyCabin.MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
			.create(Registries.BLOCK_ENTITY_TYPE, TrophyCabin.MODID);
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
			TrophyCabin.MODID);

	private static BlockBehaviour.Properties armarioProps() {
		return BlockBehaviour.Properties.of()
				.mapColor(MapColor.WOOD)
				.sound(SoundType.WOOD)
				.strength(1.5f);
	}

	public static final DeferredHolder<Block, ArmarioBlock> ARMARIO_GROOVED = BLOCKS.register("armario_grooved",
			() -> new ArmarioBlock(armarioProps()));

	public static final DeferredHolder<Block, ArmarioBlock> ARMARIO_PANEL = BLOCKS.register("armario_panel",
			() -> new ArmarioBlock(armarioProps()));

	public static final DeferredHolder<Item, BlockItem> ARMARIO_GROOVED_ITEM = ITEMS.register("armario_grooved",
			() -> new BlockItem(ARMARIO_GROOVED.get(), new Item.Properties()));

	public static final DeferredHolder<Item, BlockItem> ARMARIO_PANEL_ITEM = ITEMS.register("armario_panel",
			() -> new BlockItem(ARMARIO_PANEL.get(), new Item.Properties()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArmarioBlockEntity>> ARMARIO_BE = BLOCK_ENTITIES
			.register("armario",
					() -> BlockEntityType.Builder.of(
							ArmarioBlockEntity::new,
							ARMARIO_GROOVED.get(),
							ARMARIO_PANEL.get()).build(null));

	public static final DeferredHolder<Block, HuntingTableBlock> HUNTING_TABLE_BLOCK = BLOCKS.register("hunting_table",
			() -> new HuntingTableBlock(armarioProps()));

	public static final DeferredHolder<Item, BlockItem> HUNTING_TABLE_ITEM = ITEMS.register("hunting_table",
			() -> new BlockItem(HUNTING_TABLE_BLOCK.get(), new Item.Properties()));

	public static final DeferredHolder<Block, BearRugBlock> WHITE_BEAR_RUG_BLOCK = BLOCKS.register("white_bear_rug",
			BearRugBlock::new);

	public static final DeferredHolder<Block, BearRugBlock> BROWN_BEAR_RUG_BLOCK = BLOCKS.register("brown_bear_rug",
			BearRugBlock::new);

	public static final DeferredHolder<Block, BearRugBlock> BLACK_BEAR_RUG_BLOCK = BLOCKS.register("black_bear_rug",
			BearRugBlock::new);

	public static final DeferredHolder<Item, BlockItem> WHITE_BEAR_RUG_ITEM = ITEMS.register("white_bear_rug",
			() -> new BlockItem(WHITE_BEAR_RUG_BLOCK.get(), new Item.Properties()));

	public static final DeferredHolder<Item, BlockItem> BROWN_BEAR_RUG_ITEM = ITEMS.register("brown_bear_rug",
			() -> new BlockItem(BROWN_BEAR_RUG_BLOCK.get(), new Item.Properties()));

	public static final DeferredHolder<Item, BlockItem> BLACK_BEAR_RUG_ITEM = ITEMS.register("black_bear_rug",
			() -> new BlockItem(BLACK_BEAR_RUG_BLOCK.get(), new Item.Properties()));

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("main",
			() -> CreativeModeTab.builder()
					.icon(() -> ARMARIO_GROOVED_ITEM.get().getDefaultInstance())
					.title(Component.translatable("itemGroup.trophycabin"))
					.withTabsBefore(CreativeModeTabs.COMBAT)
					.displayItems((params, output) -> {
						output.accept(ARMARIO_GROOVED_ITEM.get());
						output.accept(ARMARIO_PANEL_ITEM.get());
						output.accept(HUNTING_TABLE_ITEM.get());
						output.accept(TrophyItems.DEER_FANCY_TROPHY.get());
						output.accept(TrophyItems.BISON_FANCY_TROPHY.get());
						output.accept(TrophyItems.RED_WOLF_FANCY_TROPHY.get());
						output.accept(WHITE_BEAR_RUG_ITEM.get());
						output.accept(BROWN_BEAR_RUG_ITEM.get());
						output.accept(BLACK_BEAR_RUG_ITEM.get());
						output.accept(TrophyItems.COD_TROPHY.get());
						output.accept(TrophyItems.SALMON_TROPHY.get());
						output.accept(TrophyItems.FANCY_COD_TROPHY.get());
						output.accept(TrophyItems.FANCY_SALMON_TROPHY.get());
						output.accept(TrophyItems.GOOSE_STATUE.get());
					})
					.build());

	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
		ITEMS.register(bus);
		BLOCK_ENTITIES.register(bus);
		TABS.register(bus);
		Trophies.BLOCKS.register(bus);
		TrophyItems.ITEMS.register(bus);
	}
}
