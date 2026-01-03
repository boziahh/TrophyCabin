package com.booziii.TrophyCabin;

import com.booziii.TrophyCabin.HuntingTable.HuntingTableScreen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.api.distmarker.Dist;

@EventBusSubscriber(value = Dist.CLIENT, modid = TrophyCabin.MODID)
public class ModClient {
	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(ModMenus.HUNTING_TABLE.get(), HuntingTableScreen::new);
	}
}
