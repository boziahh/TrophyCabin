package com.booziii.TrophyCabin;

import com.booziii.TrophyCabin.HuntingTable.HuntingTableMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU,
            TrophyCabin.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<HuntingTableMenu>> HUNTING_TABLE = MENUS.register(
            "hunting_table",
            () -> IMenuTypeExtension.create(HuntingTableMenu::new));
}
