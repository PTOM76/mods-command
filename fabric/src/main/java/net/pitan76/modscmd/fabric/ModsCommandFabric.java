package net.pitan76.modscmd.fabric;

import net.pitan76.modscmd.ModsCommandMod;
import net.fabricmc.api.ModInitializer;

public class ModsCommandFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new ModsCommandMod();
    }
}