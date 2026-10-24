package com.cha0ticw.reworkedenchantingrepairing.block.entity.container;

import com.cha0ticw.reworkedenchantingrepairing.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class ReworkedEnchantingTableMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;

    // Client-side: called by the MenuType factory, reads what the server wrote
    public ReworkedEnchantingTableMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, ContainerLevelAccess.create(inv.player.level(), buf.readBlockPos()));
    }

    // Server-side: used by your MenuProvider
    public ReworkedEnchantingTableMenu(int id, Inventory inv, ContainerLevelAccess access) {
        super(ModMenuTypes.ENCHANTING_TABLE.get(), id);
        this.access = access;
        // add slots here later
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY; // implement shift-click once you have slots
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, Blocks.ENCHANTING_TABLE);
    }
}
