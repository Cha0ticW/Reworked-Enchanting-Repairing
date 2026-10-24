package com.cha0ticw.reworkedenchantingrepairing;


import com.cha0ticw.reworkedenchantingrepairing.registry.ModItems;
import com.cha0ticw.reworkedenchantingrepairing.registry.ModMenuTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import java.util.logging.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ReworkedEnchantingRepairing.MOD_ID)
public class ReworkedEnchantingRepairing {
    public static final String MOD_ID = "reworked_enchanting_repairing";
    public static final Logger LOGGER = (Logger) LogManager.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public ReworkedEnchantingRepairing(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        NeoForge.EVENT_BUS.register(this);

        ModItems.ITEMS.register(modEventBus);
        ModMenuTypes.MENU_TYPE.register(modEventBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.EMPTY_MENDING_RUNE);
            event.accept(ModItems.MENDING_RUNE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Reworked Enchanting & Repairing is starting!");
    }
}