package com.cha0ticw.reworkedenchantingrepairing.registry;

import com.cha0ticw.reworkedenchantingrepairing.ReworkedEnchantingRepairing;
import com.cha0ticw.reworkedenchantingrepairing.block.entity.container.ReworkedEnchantingTableMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(Registries.MENU, ReworkedEnchantingRepairing.MOD_ID);

    public static final Supplier<MenuType<ReworkedEnchantingTableMenu>> ENCHANTING_TABLE = MENU_TYPE
            .register("reworked_enchanting_table", () -> IMenuTypeExtension.create(ReworkedEnchantingTableMenu::new));
}
