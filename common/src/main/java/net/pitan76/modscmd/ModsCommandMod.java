package net.pitan76.modscmd;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.modscmd.command.ModsCommand;

public class ModsCommandMod extends CommonModInitializer {
    public static final String MOD_ID = "modscmd";
    public static final String MOD_NAME = "Mods Command";

    public static ModsCommandMod INSTANCE;
    public static CompatRegistryV2 registry;

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        CommandRegistry.register("mods", new ModsCommand());
    }

    // ----
    public static CompatIdentifier _id(String path) {
        return CompatIdentifier.of(MOD_ID, path);
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }
}