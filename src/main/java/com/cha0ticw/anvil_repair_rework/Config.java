package com.cha0ticw.anvil_repair_rework;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.function.Supplier;

public class Config {
    private static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_ENCHANTING = "enchanting";
    public static Supplier<Boolean> DISABLE_ANVIL_ENCHANTMENT_COMBINE;

    public static final String CATEGORY_REPAIRING = "repairing";
    public static Supplier<Boolean> ENABLE_REPAIR_KITS;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_ENCHANTING);
        DISABLE_ANVIL_ENCHANTMENT_COMBINE = COMMON_BUILDER.comment("Disables the ability to combine enchantments in an anvil.")
                .define("disable_anvil_enchantment_combine", true);

        COMMON_BUILDER.push(CATEGORY_REPAIRING);
        ENABLE_REPAIR_KITS = COMMON_BUILDER.comment("Makes repairing gear use repair kits instead of vanilla materials.")
                .define("enable_repair_kits", true);
    }
}
