package net.pitan76.modscmd.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.pitan76.modscmd.ModsCommandMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModsCommandMod.MOD_ID)
public class ModsCommandForge {
    public ModsCommandForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EventBuses.registerModEventBus(ModsCommandMod.MOD_ID, bus);
        new ModsCommandMod();
    }
}