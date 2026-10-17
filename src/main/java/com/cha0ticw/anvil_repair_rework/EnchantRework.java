package com.cha0ticw.anvil_repair_rework;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class EnchantRework {
    public EnchantRework(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(EnchantRework::onAnvilUpdate);
    }

    private static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack right = event.getRight();

        if (right.isEmpty()) return;

        if (hasEnchants(right) && hasEnchants(event.getLeft())) {
            event.setCanceled(true);
        }
    }

    private static boolean hasEnchants(ItemStack stack) {
        ItemEnchantments applied = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        ItemEnchantments stored = stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY);
        return !applied.isEmpty() || !stored.isEmpty();
    }
}
