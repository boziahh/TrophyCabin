package com.booziii.TrophyCabin.HuntingTable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import java.util.List;

import com.booziii.TrophyCabin.ModMenus;

public class HuntingTableMenu extends AbstractContainerMenu {

    public static final int CLAIM_BUTTON_BASE = 100;

    private final List<Quest> quests;
    private final ContainerData data;
    private final ServerPlayer serverPlayer;

    public HuntingTableMenu(int id, Inventory inv, RegistryFriendlyByteBuf buf) {
        this(id, inv, inv.player);
    }

    public HuntingTableMenu(int id, Inventory inv, Player player) {
        super(ModMenus.HUNTING_TABLE.get(), id);

        this.quests = Quest.ALL;
        this.serverPlayer = player instanceof ServerPlayer sp ? sp : null;
        this.data = new SimpleContainerData(quests.size());
        this.addDataSlots(data);

        int startX = 8;
        int startY = 140;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, startX + col * 18, startY + 58));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (serverPlayer == null)
            return;

        for (int i = 0; i < quests.size(); i++) {
            data.set(i, getKillCount(serverPlayer, quests.get(i).targetId()));
        }
    }

    private static int getKillCount(ServerPlayer player, ResourceLocation entityId) {
        EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(entityId);
        if (type == null)
            return 0;
        return player.getStats().getValue(Stats.ENTITY_KILLED.get(type));
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        int index = id - CLAIM_BUTTON_BASE;
        if (index < 0 || index >= quests.size())
            return false;
        if (!(player instanceof ServerPlayer sp))
            return false;

        Quest q = quests.get(index);

        if (getKillCount(sp, q.targetId()) < q.goal())
            return false;
        if (!hasItem(sp, q.costItem(), q.costCount()))
            return false;

        removeItem(sp, q.costItem(), q.costCount());

        Item rewardItem = BuiltInRegistries.ITEM.get(q.rewardItem());
        if (rewardItem == null)
            return false;

        ItemStack reward = new ItemStack(rewardItem, q.rewardCount());
        if (!sp.getInventory().add(reward)) {
            sp.drop(reward, false);
        }
        SoundEvent sound = BuiltInRegistries.SOUND_EVENT.get(
                ResourceLocation.fromNamespaceAndPath("minecraft", "block.note_block.pling"));
        if (sound != null) {
            sp.level().playSound(
                    null,
                    sp.blockPosition(),
                    sound,
                    SoundSource.PLAYERS,
                    1.0F,
                    2F);
        }

        return true;
    }

    private boolean hasItem(ServerPlayer player, ResourceLocation id, int count) {
        Item item = BuiltInRegistries.ITEM.get(id);
        if (item == null)
            return false;

        int needed = count;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(item)) {
                needed -= stack.getCount();
                if (needed <= 0)
                    return true;
            }
        }
        return false;
    }

    private void removeItem(ServerPlayer player, ResourceLocation id, int count) {
        Item item = BuiltInRegistries.ITEM.get(id);
        if (item == null)
            return;

        int remaining = count;
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.is(item))
                continue;
            int take = Math.min(stack.getCount(), remaining);
            stack.shrink(take);
            remaining -= take;
            if (remaining <= 0)
                return;
        }
    }

    public int getQuestCount() {
        return quests.size();
    }

    public Quest getQuest(int i) {
        return quests.get(i);
    }

    public int getQuestProgress(int i) {
        return data.get(i);
    }
}
