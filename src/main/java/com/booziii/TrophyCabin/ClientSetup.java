package com.booziii.TrophyCabin;

import com.booziii.TrophyCabin.Trophies.Trophies;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = "trophycabin", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(Trophies.DEER_FANCY_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.BISON_FANCY_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.RED_WOLF_FANCY_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.COD_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.SALMON_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.FANCY_SALMON_TROPHY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Trophies.FANCY_COD_TROPHY.get(), RenderType.cutout());
	}
}
