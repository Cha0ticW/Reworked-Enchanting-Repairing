package com.cha0ticw.reworkedenchantingrepairing.registry;

import com.cha0ticw.reworkedenchantingrepairing.Config;
import com.cha0ticw.reworkedenchantingrepairing.ReworkedEnchantingRepairing;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems extends Item {
    public ModItems(Properties props) {
        super(props);
    }

    public static void modifyDefault(ModifyDefaultComponentsEvent event) {
        event.modify(ModItems.MENDING_RUNE.get(),
                builder -> builder.set(DataComponents.MAX_DAMAGE, Config.MENDING_RUNE_USES.get()));
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ReworkedEnchantingRepairing.MOD_ID);

    public static final DeferredItem<ModItems> MENDING_RUNE = ITEMS.register("mending_rune",
            () -> new ModItems(new Item.Properties().durability(1)));

    public static final DeferredItem<ModItems> EMPTY_MENDING_RUNE = ITEMS.register("empty_mending_rune",
            () -> new ModItems(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
