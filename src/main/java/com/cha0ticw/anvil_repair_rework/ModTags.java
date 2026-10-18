package com.cha0ticw.anvil_repair_rework;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> REPAIR_KITS = createTag("repair_kits");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AnvilRepairReworkMod.MOD_ID, name));
        }
    }
}
