package com.cha0ticw.reworkedenchantingrepairing;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.function.Supplier;

public class Config {
    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_ENCHANTING = "enchanting";
    public static Supplier<Boolean> DISABLE_ANVIL_ENCHANTMENT_COMBINE;

    public static final String CATEGORY_REPAIRING = "repairing";
    public static Supplier<Boolean> ENABLE_MENDING_RUNE;
    public static Supplier<Integer> MENDING_RUNE_USES;
    public static Supplier<Integer> XP_COST_PER_KIT;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_ENCHANTING);
        DISABLE_ANVIL_ENCHANTMENT_COMBINE = COMMON_BUILDER.comment("Disables the ability to combine enchantments in an anvil.")
                .define("disable_anvil_enchantment_combine", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_REPAIRING);
        ENABLE_MENDING_RUNE = COMMON_BUILDER.comment("Makes repairing gear use the Mending Rune instead of vanilla materials.")
                .define("enable_repair_kits", true);
        MENDING_RUNE_USES = COMMON_BUILDER.comment("How many uses the Mending Rune has.")
                .defineInRange("mending_rune_uses", 30, 1, 1000);
        XP_COST_PER_KIT = COMMON_BUILDER.comment("XP levels charged per Mending Rune use. If you set this to 10, a tool with less than 33% durability will cost 30 levels, since the Mending Rune is used 3 times.")
                        .defineInRange("xp_cost_per_kit", 0, 0, 10);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
