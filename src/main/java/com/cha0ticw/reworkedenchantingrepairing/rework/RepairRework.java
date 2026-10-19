package com.cha0ticw.reworkedenchantingrepairing.rework;

import com.cha0ticw.reworkedenchantingrepairing.Config;
import com.cha0ticw.reworkedenchantingrepairing.item.RepairKits;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.entity.player.AnvilRepairEvent;

public class RepairRework {
    public RepairRework(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(RepairRework::onAnvilUpdate);
        NeoForge.EVENT_BUS.addListener(RepairRework::onAnvilRepair);
    }

    public static int fullRepairCost(ItemStack gear) {
        int max = gear.getMaxDamage();
        int damage = gear.getDamageValue();
        int remainingPct = (max - damage) * 100 / max;

        return      remainingPct >= 66 ? 1
                :   remainingPct >= 22 ? 2
                :   3;
    }

    public static int usesRequired(ItemStack gear, ItemStack kit) {
        int needed = fullRepairCost(gear);
        int remainingUses = Math.max(0, kit.getMaxDamage() - kit.getDamageValue());
        return Math.min(remainingUses, needed);
    }

    private static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (!Config.ENABLE_REPAIR_KITS.get()) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (right.isEmpty() || !left.isDamageableItem()) return;

        //check if repair item is a repair kit
        if (!(right.getItem() instanceof RepairKits kit)) {
            if (left.getItem().isValidRepairItem(left, right)) {
                event.setCanceled(true);
            }
            return;
        }
        //check if the kit is the right material
        if (!left.getItem().isValidRepairItem(left, kit.getMaterialStack())) {
            event.setCanceled(true);
            return;
        }

        //ignore items with full durability
        if (!left.isDamaged()) {
            event.setCanceled(true);
            return;
        }

        int needed = RepairRework.fullRepairCost(left);
        int used = RepairRework.usesRequired(left, right);

        if (used <= 0) {
            event.setCanceled(true);
            return;
        }

        int damage = left.getDamageValue();
        int restore = (damage * used + needed - 1) / needed;

        ItemStack out = left.copy();
        out.setDamageValue(Math.max(0, damage - restore));

        //renames while repairing
        String name = event.getName();
        if (name != null && !name.isBlank()) {
            out.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        }

        event.setOutput(out);
        event.setMaterialCost(used);
        event.setCost((long) used * Config.XP_COST_PER_KIT.get());
    }

    //return damaged kit
    private static void onAnvilRepair(AnvilRepairEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (!(right.getItem() instanceof RepairKits)) return;
        if (!right.isDamageableItem()) return;

        int used = RepairRework.usesRequired(left, right);

        ItemStack returned = right.copy();
        returned.setCount(1);
        returned.setDamageValue(right.getDamageValue() + used);

        if (returned.getDamageValue() >= returned.getMaxDamage()) {
            player.drop(returned, false);
        }
    }
}
