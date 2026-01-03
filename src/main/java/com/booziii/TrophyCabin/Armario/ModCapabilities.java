package com.booziii.TrophyCabin.Armario;

import com.booziii.TrophyCabin.ModRegistry;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {

	@SubscribeEvent
	public static void register(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(
				Capabilities.ItemHandler.BLOCK,
				ModRegistry.ARMARIO_BE.get(),
				(be, side) -> ((ArmarioBlockEntity) be).getItemHandler(side));
	}
}
