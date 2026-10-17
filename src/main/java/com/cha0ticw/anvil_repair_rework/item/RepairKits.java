package com.cha0ticw.anvil_repair_rework.item;

import com.cha0ticw.anvil_repair_rework.AnvilRepairReworkMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RepairKits {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnvilRepairReworkMod.MOD_ID);

    public static final DeferredItem<Item> WOODEN_REPAIR_KIT = ITEMS.register("wooden_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STONE_REPAIR_KIT = ITEMS.register("stone_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_REPAIR_KIT = ITEMS.register("copper_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_REPAIR_KIT = ITEMS.register("iron_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_REPAIR_KIT = ITEMS.register("golden_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_REPAIR_KIT = ITEMS.register("diamond_repair_kit",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_REPAIR_KIT = ITEMS.register("netherite_repair_kit",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
