package com.booziii.TrophyCabin.JEI;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import com.booziii.TrophyCabin.HuntingTable.Quest;

@JeiPlugin
public class plugin implements IModPlugin {

	private static final ResourceLocation UID =
		ResourceLocation.fromNamespaceAndPath("trophycabin", "jei");

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(
			new JeiCategory(registration.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(
			JeiCategory.TYPE,
			Quest.ALL
		);
	}
}
