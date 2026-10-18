package com.cha0ticw.anvil_repair_rework.rework;

import com.cha0ticw.anvil_repair_rework.Config;
import com.cha0ticw.anvil_repair_rework.ModTags;
import com.cha0ticw.anvil_repair_rework.item.RepairKits;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class RepairRework {
    public RepairRework(IEventBus modBus) { NeoForge.EVENT_BUS.addListener(RepairRework::onAnvilUpdate); }

    private static void onAnvilUpdate(AnvilUpdateEvent event) {
        if (!Config.ENABLE_REPAIR_KITS.get()) return;

        Player player = event.getPlayer();
        if (player.level().isClientSide) return;

        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (right.isEmpty() || !left.isDamageableItem()) return;
        //check if repair item is a repair kit
        if (!(right.getItem() instanceof RepairKits)) return;
        if (!right.isDamageableItem()) return;
        //ignore items with full durability
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
