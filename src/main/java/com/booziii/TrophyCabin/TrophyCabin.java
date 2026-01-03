package com.booziii.TrophyCabin;

import com.booziii.TrophyCabin.Armario.ModCapabilities;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TrophyCabin.MODID)
public class TrophyCabin {

	public static final String MODID = "trophycabin";

	public TrophyCabin(IEventBus bus) {
		ModRegistry.register(bus);
		bus.register(ModCapabilities.class);
		ModMenus.MENUS.register(bus);
	}
}
