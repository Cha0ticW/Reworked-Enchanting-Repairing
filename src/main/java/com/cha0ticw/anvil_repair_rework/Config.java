package com.cha0ticw.anvil_repair_rework;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec.BooleanValue DISABLE_ANVIL_ENCHANTING_COMBINE;

    public Config(){
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }

    static {
        DISABLE_ANVIL_ENCHANTING_COMBINE = flag("disableAnvilCombine", true, "Disables the ability to combine enchantments in an anvil.");
    }

    private static ModConfigSpec.BooleanValue flag(String key, boolean defaultValue, String comment) {
        return BUILDER.comment(comment).define(key, defaultValue);
    }
}
