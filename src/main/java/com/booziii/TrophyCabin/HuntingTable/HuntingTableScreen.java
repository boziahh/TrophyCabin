package com.booziii.TrophyCabin.HuntingTable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class HuntingTableScreen extends AbstractContainerScreen<HuntingTableMenu> {

    private static final ResourceLocation BG =
            ResourceLocation.fromNamespaceAndPath("trophycabin", "textures/gui/hunting_table.png");

    private static final ResourceLocation SCROLL_TEX =
            ResourceLocation.fromNamespaceAndPath("trophycabin", "textures/gui/scroll.png");

    private int scrollOffset = 0;

    private static final int ROW_HEIGHT = 27;
    private static final int VISIBLE_ROWS = 4;

    private static final int SCROLL_X_OFFSET = 158;
    private static final int SCROLL_Y_OFFSET = 18;
    private static final int SCROLL_TRACK_HEIGHT = 106;
    private static final int SCROLL_THUMB_WIDTH = 10;
    private static final int SCROLL_THUMB_HEIGHT = 13;

    public HuntingTableScreen(HuntingTableMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        imageWidth = 176;
        imageHeight = 222;
    }

    @Override
    protected void renderBg(GuiGraphics g, float pt, int mx, int my) {
        g.blit(BG, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int baseX = leftPos + 20;
        int baseY = topPos + 20;

        for (int row = 0; row < VISIBLE_ROWS; row++) {
            int questIndex = row + scrollOffset;
            if (questIndex >= menu.getQuestCount()) break;

            Quest q = menu.getQuest(questIndex);
            int y = baseY + row * ROW_HEIGHT;

            Component mobName = Component.translatable(
                    "entity." + q.targetId().getNamespace() + "." + q.targetId().getPath());
            g.drawString(font, mobName, baseX, y, 0xFFFFFF, false);

            g.drawString(
                    font,
                    menu.getQuestProgress(questIndex) + "/" + q.goal(),
                    baseX,
                    y + 10,
                    0xFFFFFF,
                    false
            );

            ItemStack cost = stackFrom(q.costItem(), q.costCount());
            ItemStack reward = stackFrom(q.rewardItem(), q.rewardCount());

            g.renderItem(cost, baseX + 56, y - 2);
            g.renderItemDecorations(font, cost, baseX + 56, y - 2);

            g.drawString(font, "=", baseX + 80, y + 4, 0xFFFFFF, false);

            g.renderItem(reward, baseX + 92, y - 2);
            g.renderItemDecorations(font, reward, baseX + 92, y - 2);
        }

        renderScrollbar(g);
    }

    private void renderScrollbar(GuiGraphics g) {
        int maxScroll = Math.max(0, menu.getQuestCount() - VISIBLE_ROWS);
        if (maxScroll <= 0) return;

        float ratio = (float) scrollOffset / maxScroll;
        int thumbY = (int) (ratio * (SCROLL_TRACK_HEIGHT - SCROLL_THUMB_HEIGHT));

        g.blit(
                SCROLL_TEX,
                leftPos + SCROLL_X_OFFSET,
                topPos + SCROLL_Y_OFFSET + thumbY,
                0,
                0,
                SCROLL_THUMB_WIDTH,
                SCROLL_THUMB_HEIGHT,
                SCROLL_THUMB_WIDTH,
                SCROLL_THUMB_HEIGHT
        );
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double dx, double dy) {
        int maxScroll = Math.max(0, menu.getQuestCount() - VISIBLE_ROWS);

        if (dy > 0) scrollOffset--;
        if (dy < 0) scrollOffset++;

        scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
        return true;
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        int relX = (int) (mx - leftPos);
        int relY = (int) (my - topPos);

        int baseX = 8;
        int baseY = 18;

        for (int row = 0; row < VISIBLE_ROWS; row++) {
            int questIndex = row + scrollOffset;
            if (questIndex >= menu.getQuestCount()) break;

            int y = baseY + row * ROW_HEIGHT;

            if (relX >= baseX && relX <= baseX + 131 && relY >= y && relY <= y + 25) {
                minecraft.gameMode.handleInventoryButtonClick(
                        menu.containerId,
                        HuntingTableMenu.CLAIM_BUTTON_BASE + questIndex
                );
                return true;
            }
        }

        return super.mouseClicked(mx, my, button);
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        renderBackground(g, mx, my, pt);
        super.render(g, mx, my, pt);

        int relX = mx - leftPos;
        int relY = my - topPos;

        int baseX = 8;
        int baseY = 18;

        for (int row = 0; row < VISIBLE_ROWS; row++) {
            int questIndex = row + scrollOffset;
            if (questIndex >= menu.getQuestCount()) break;

            int y = baseY + row * ROW_HEIGHT;

            if (relX >= baseX && relX <= baseX + 131 && relY >= y && relY <= y + 25) {
                Quest q = menu.getQuest(questIndex);

                ItemStack cost = stackFrom(q.costItem(), q.costCount());
                ItemStack reward = stackFrom(q.rewardItem(), q.rewardCount());

                g.renderTooltip(
                        font,
                        List.of(
                                Component.literal("Quest").withStyle(ChatFormatting.GOLD),
                                Component.literal("Requires: ")
                                        .append(Component.literal(
                                                menu.getQuestProgress(questIndex)
                                                        + "/" + q.goal() + " "))
                                        .append(getEntityName(q.targetId()))
                                        .append(Component.literal(" kills to unlock.")),
                                Component.literal("Cost: ")
                                        .append(cost.getHoverName())
                                        .append(Component.literal(" (consumed)").withStyle(ChatFormatting.RED)),
                                Component.literal("Reward: ")
                                        .append(reward.getHoverName())
                                        .withStyle(ChatFormatting.GREEN)
                        ),
                        Optional.empty(),
                        mx,
                        my
                );
                break;
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        g.drawString(font, title.getString(), 8, 6, 0x404040, false);
        g.drawString(font, playerInventoryTitle.getString(), 8, imageHeight - 96 + 2, 0x404040, false);
    }

    private static ItemStack stackFrom(ResourceLocation id, int count) {
        Item item = BuiltInRegistries.ITEM.get(id);
        if (item == null) return ItemStack.EMPTY;
        return new ItemStack(item, count);
    }

    private static Component getEntityName(ResourceLocation id) {
        return Component.translatable(
                "entity." + id.getNamespace() + "." + id.getPath()
        );
    }
}
