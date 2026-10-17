package com.cha0ticw.anvil_repair_rework;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import java.util.logging.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AnvilRepairReworkMod.MOD_ID)
public class AnvilRepairReworkMod {
    public static final String MOD_ID = "anvil_repair_rework";
    public static final Logger LOGGER = (Logger) LogManager.getLogger();
}