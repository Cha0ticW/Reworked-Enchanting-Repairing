package com.cha0ticw.reworkedenchantingrepairing.item;

import com.cha0ticw.reworkedenchantingrepairing.ReworkedEnchantingRepairing;
import com.cha0ticw.reworkedenchantingrepairing.Config;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RepairKits extends Item {
    private final Supplier<ItemStack> material;

    public RepairKits(Properties props, Supplier<ItemStack> material) {
        super(props);
        this.material = material;
    }

    private static void modifyDefault(ModifyDefaultComponentsEvent event) {
        event.modify(RepairKits.WOODEN_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.WOODEN_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.STONE_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.STONE_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.COPPER_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.COPPER_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.IRON_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.IRON_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.GOLDEN_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.GOLDEN_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.DIAMOND_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.DIAMOND_REPAIR_KIT_USES.get()));
        event.modify(RepairKits.NETHERITE_REPAIR_KIT.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.NETHERITE_REPAIR_KIT_USES.get()));
    }

    public ItemStack getMaterialStack() {
        return material.get();
    }
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ReworkedEnchantingRepairing.MOD_ID);

    public static final DeferredItem<RepairKits> WOODEN_REPAIR_KIT = ITEMS.register("wooden_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.OAK_PLANKS)));
    public static final DeferredItem<RepairKits> STONE_REPAIR_KIT = ITEMS.register("stone_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.COBBLESTONE)));
    public static final DeferredItem<RepairKits> COPPER_REPAIR_KIT = ITEMS.register("copper_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.COPPER_INGOT)));
    public static final DeferredItem<RepairKits> IRON_REPAIR_KIT = ITEMS.register("iron_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.IRON_INGOT)));
    public static final DeferredItem<RepairKits> GOLDEN_REPAIR_KIT = ITEMS.register("golden_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.GOLD_INGOT)));
    public static final DeferredItem<RepairKits> DIAMOND_REPAIR_KIT = ITEMS.register("diamond_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.DIAMOND)));
    public static final DeferredItem<RepairKits> NETHERITE_REPAIR_KIT = ITEMS.register("netherite_repair_kit",
            () -> new RepairKits(new Item.Properties().durability(1), () -> new ItemStack(Items.NETHERITE_INGOT)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
