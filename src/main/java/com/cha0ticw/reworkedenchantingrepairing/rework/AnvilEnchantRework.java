package com.cha0ticw.reworkedenchantingrepairing.rework;

import com.cha0ticw.reworkedenchantingrepairing.Config;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class AnvilEnchantRework {
    public AnvilEnchantRework(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(AnvilEnchantRework::onAnvilUpdate);
    }

    private static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (!Config.DISABLE_ANVIL_ENCHANTMENT_COMBINE.get()) return;

        ItemStack right = event.getRight();
        //checks if the right input is empty, if yes it does nothing
        if (right.isEmpty()) return;
        //checks if both inputs are enchanted, will block if yes
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
