package com.cha0ticw.reworkedenchantingrepairing.rework

import com.cha0ticw.reworkedenchantingrepairing.Config;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class VanillaRepairRework {
    public VanillaRepairRework(IEventBus modBus) {
        Neoforge.EVENT_BUS.addListener(VanillaRepairRework::onAnvilUpdate);
    }

    public static int fullRepairCost(ItemStack gear) {
        int max = gear.getMaxDamage();
        int damage = gear.getDamageValue();
        int remainingpct = (max - damage) * 100 / max;

        return      remainingpct >= 66 ? 1;
                :   remainingpct >= 33 ? 2;
                :   3;
    }

        private static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (Config.ENABLE_MENDING_RUNE.get()) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (right.isEmpty() || !left.isDamageableItem()) return;

        //ignore items with full durability
        if (!left.isDamaged()) {
            event.setCanceled(true);
            return;
        }

        int needed = VanillaRepairRework.fullRepairCost(left);
        int damage = left.getDamageValue();
        int restore = (damage * needed - 1) / needed;

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
