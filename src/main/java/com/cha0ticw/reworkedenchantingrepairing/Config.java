package com.cha0ticw.reworkedenchantingrepairing;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.function.Supplier;

public class Config {
    public static ModConfigSpec COMMON_CONFIG;

    public static final String CATEGORY_ENCHANTING = "enchanting";
    public static Supplier<Boolean> DISABLE_ANVIL_ENCHANTMENT_COMBINE;

    public static final String CATEGORY_REPAIRING = "repairing";
    public static Supplier<Boolean> ENABLE_REPAIR_KITS;
    public static Supplier<Integer> XP_COST_PER_KIT;

    public static final String CATEGORY_REPAIR_KITS = "repair_kits";
    public static Supplier<Integer> WOODEN_REPAIR_KIT_USES;
    public static Supplier<Integer> STONE_REPAIR_KIT_USES;
    public static Supplier<Integer> COPPER_REPAIR_KIT_USES;
    public static Supplier<Integer> IRON_REPAIR_KIT_USES;
    public static Supplier<Integer> GOLDEN_REPAIR_KIT_USES;
    public static Supplier<Integer> DIAMOND_REPAIR_KIT_USES;
    public static Supplier<Integer> NETHERITE_REPAIR_KIT_USES;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_ENCHANTING);
        DISABLE_ANVIL_ENCHANTMENT_COMBINE = COMMON_BUILDER.comment("Disables the ability to combine enchantments in an anvil.")
                .define("disable_anvil_enchantment_combine", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_REPAIRING);
        ENABLE_REPAIR_KITS = COMMON_BUILDER.comment("Makes repairing gear use repair kits instead of vanilla materials.")
                .define("enable_repair_kits", true);
        XP_COST_PER_KIT = COMMON_BUILDER.comment("XP levels charged per repair kit use.")
                        .defineInRange("xp_cost_per_kit", 0, 0, 10);

        COMMON_BUILDER.push(CATEGORY_REPAIR_KITS);
        WOODEN_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Wooden Repair Kit has.")
                .defineInRange("wooden_repair_kit_uses", 30, 1, 1000);
        STONE_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Stone Repair Kit has.")
                .defineInRange("stone_repair_kit_uses", 30, 1, 1000);
        COPPER_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Copper Repair Kit has.")
                .defineInRange("copper_repair_kit_uses", 30, 1, 1000);
        IRON_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Iron Repair Kit has.")
                .defineInRange("iron_repair_kit_uses", 30, 1, 1000);
        GOLDEN_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Golden Repair Kit has.")
                .defineInRange("golden_repair_kit_uses", 30, 1, 1000);
        DIAMOND_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Diamond Repair Kit has.")
                .defineInRange("diamond_repair_kit_uses", 30, 1, 1000);
        NETHERITE_REPAIR_KIT_USES = COMMON_BUILDER.comment("How many uses the Netherite Repair Kit has.")
                .defineInRange("netherite_repair_kit_uses", 30, 1, 1000);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
