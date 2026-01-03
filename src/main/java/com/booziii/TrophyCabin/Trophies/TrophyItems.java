package com.booziii.TrophyCabin.Trophies;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;

public class TrophyItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, "trophycabin");

	public static final DeferredHolder<Item, Item> DEER_FANCY_TROPHY = ITEMS.register(
			"deer_fancy_trophy",
			() -> new BlockItem(Trophies.DEER_FANCY_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> BISON_FANCY_TROPHY = ITEMS.register(
			"bison_fancy_trophy",
			() -> new BlockItem(Trophies.BISON_FANCY_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> RED_WOLF_FANCY_TROPHY = ITEMS.register(
			"red_wolf_fancy_trophy",
			() -> new BlockItem(Trophies.RED_WOLF_FANCY_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> GOOSE_STATUE = ITEMS.register(
			"goose_statue",
			() -> new BlockItem(Trophies.GOOSE_STATUE.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> SALMON_TROPHY = ITEMS.register(
			"salmon_trophy",
			() -> new BlockItem(Trophies.SALMON_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> COD_TROPHY = ITEMS.register(
			"cod_trophy",
			() -> new BlockItem(Trophies.COD_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> FANCY_COD_TROPHY = ITEMS.register(
			"fancy_cod_trophy",
			() -> new BlockItem(Trophies.FANCY_COD_TROPHY.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> FANCY_SALMON_TROPHY = ITEMS.register(
			"fancy_salmon_trophy",
			() -> new BlockItem(Trophies.FANCY_SALMON_TROPHY.get(), new Item.Properties()));
}
