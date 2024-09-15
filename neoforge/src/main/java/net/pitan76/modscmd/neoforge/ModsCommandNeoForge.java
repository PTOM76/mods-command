package net.pitan76.modscmd.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.pitan76.modscmd.ModsCommandMod;

@Mod(ModsCommandMod.MOD_ID)
public class ModsCommandNeoForge {
    public ModsCommandNeoForge(ModContainer modContainer) {
        IEventBus bus = modContainer.getEventBus();

        new ModsCommandMod();
    }
}