package com.cha0ticw.anvil_repair_rework.rework;

import com.cha0ticw.anvil_repair_rework.ModTags;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class RepairRework {
    public RepairRework(IEventBus modBus) { NeoForge.EVENT_BUS.addListener(RepairRework::onAnvilUpdate); }

    private static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (right.isEmpty()) return;

        boolean isKit = right.is(ModTags.Items.REPAIR_KITS);

        if (!isKit && left.getItem().isValidRepairItem(left, right)) {
            event.setCanceled(true);
            return;
        }

        if (!left.isDamaged()) {
            event.setCanceled(true);
            return;
        }

        int max = left.getMaxDamage();
        int damage = left.getDamageValue();
        int remainingpct = (max - damage) * 100 / max;

        int needed = remainingpct >= 66 ? 1
                   : remainingpct >= 22 ? 2
                   : 3;

    }
}
