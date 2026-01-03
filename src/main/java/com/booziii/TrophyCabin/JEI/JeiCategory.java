package com.booziii.TrophyCabin.JEI;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import com.booziii.TrophyCabin.HuntingTable.Quest;

public class JeiCategory implements IRecipeCategory<Quest> {

	public static final RecipeType<Quest> TYPE = new RecipeType<>(
			ResourceLocation.fromNamespaceAndPath("trophycabin", "hunting_quests"),
			Quest.class);

	private final IDrawable background;
	private final IDrawable icon;

	public JeiCategory(IGuiHelper guiHelper) {
		background = guiHelper.createBlankDrawable(160, 60);
		icon = guiHelper.createDrawableItemStack(
				new ItemStack(
						BuiltInRegistries.ITEM.get(
								ResourceLocation.fromNamespaceAndPath("trophycabin", "hunting_table"))));
	}

	@Override
	public RecipeType<Quest> getRecipeType() {
		return TYPE;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("container.trophycabin.hunting_table");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, Quest quest, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 10, 28)
				.addItemStack(new ItemStack(
						BuiltInRegistries.ITEM.get(quest.costItem()),
						quest.costCount()));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 28)
				.addItemStack(new ItemStack(
						BuiltInRegistries.ITEM.get(quest.rewardItem()),
						quest.rewardCount()));
	}

	@Override
	public void draw(
			Quest quest,
			IRecipeSlotsView slots,
			GuiGraphics g,
			double mouseX,
			double mouseY) {
		var font = Minecraft.getInstance().font;

		Component line = Component.literal("Requires ")
				.append(Component.literal(String.valueOf(quest.goal())))
				.append(Component.literal(" "))
				.append(Component.translatable(
						"entity." + quest.targetId().getNamespace() + "." + quest.targetId().getPath()))
				.append(Component.literal(" kills"));

		g.drawString(
				font,
				line,
				10,
				18,
				0xFFFFFF,
				false);
	}

}
