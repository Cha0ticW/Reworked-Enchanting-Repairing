package com.cha0ticw.anvil_repair_rework;


import com.cha0ticw.anvil_repair_rework.item.RepairKits;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import java.util.logging.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AnvilRepairReworkMod.MOD_ID)
public class AnvilRepairReworkMod {
    public static final String MOD_ID = "anvil_repair_rework";
    public static final Logger LOGGER = (Logger) LogManager.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public AnvilRepairReworkMod(IEventBus modEventBus, ModContainer modContainer) {
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        RepairKits.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANVIL_REPAIR_REWORK_TAB = CREATIVE_MODE_TABS.register("anvil_repair_rework_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.anvil_repair_rework.repair_rework_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(RepairKits.WOODEN_REPAIR_KIT);
                output.accept(RepairKits.STONE_REPAIR_KIT);
                output.accept(RepairKits.COPPER_REPAIR_KIT);
                output.accept(RepairKits.IRON_REPAIR_KIT);
                output.accept(RepairKits.GOLDEN_REPAIR_KIT);
                output.accept(RepairKits.DIAMOND_REPAIR_KIT);
                output.accept(RepairKits.NETHERITE_REPAIR_KIT);
            }).build());

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Anvil Repair Rework is starting");
    }
}