package net.pitan76.modscmd.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.pitan76.mcpitanlib.core.datafixer.Pair;
import net.pitan76.modscmd.ModInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModUtilImpl {
    public static ModInfo getModInfo(String modId) {
        Optional<ModContainer> optional = FabricLoader.getInstance().getModContainer(modId);
        if (!optional.isPresent()) return new ModInfo();

        ModContainer modContainer = optional.get();
        ModMetadata metadata = modContainer.getMetadata();

        List<String> authors = new ArrayList<>();
        List<Pair<String, String>> urls = new ArrayList<>();

        String version = metadata.getVersion().getFriendlyString();
        metadata.getAuthors().forEach(author -> authors.add(author.getName()));
        metadata.getContact().asMap().forEach((type, url) -> urls.add(new Pair<>(type, url)));

        return new ModInfo(modId, metadata.getName(), version, metadata.getDescription(), authors, urls);
    }

    public static String getModName(String modId) {
        Optional<ModContainer> optional = FabricLoader.getInstance().getModContainer(modId);
        if (!optional.isPresent()) return "";

        ModContainer modContainer = optional.get();
        ModMetadata metadata = modContainer.getMetadata();

        return metadata.getName();
    }
}
