package com.cha0ticw.reworkedenchantingrepairing.rework;

import com.cha0ticw.reworkedenchantingrepairing.Config;
import com.cha0ticw.reworkedenchantingrepairing.item.MendingRuneItem;
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

        boolean isRune = right.is(MendingRuneItem.MENDING_RUNE.get());

        //check if repair item is a mending rune
        if (!isRune) {
            if (left.getItem().isValidRepairItem(left, right)) {
                event.setCanceled(true);
            }
            return;
        }

        //ignore items with full durability
        if (!left.isDamaged()) {
            event.setCanceled(true);
            return;
        }

        int needed = RepairRework.fullRepairCost(left);
        int used = RepairRework.usesRequired(left, right);

        //rune has no durability
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

    //return damaged rune
    private static void onAnvilRepair(AnvilRepairEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (!(right.is(MendingRuneItem.MENDING_RUNE.get()))) return;
        if (!right.isDamageableItem()) return;

        int used = RepairRework.usesRequired(left, right);

        ItemStack returned;
        int runeDamage = right.getDamageValue() + used;

        if (runeDamage >= right.getMaxDamage()) {
            //return Empty Mending Rune when used up
            returned = new ItemStack(MendingRuneItem.EMPTY_MENDING_RUNE.get());
        } else {
            returned = right.copy();
            returned.setCount(1);
            returned.setDamageValue(runeDamage);
        }

        if (!player.getInventory().add(returned)) {
            player.drop(returned, false);
        }
    }
}
