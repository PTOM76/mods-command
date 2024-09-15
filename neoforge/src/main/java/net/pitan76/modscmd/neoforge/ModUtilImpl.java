package net.pitan76.modscmd.neoforge;

import net.neoforged.fml.loading.FMLLoader;
import net.pitan76.modscmd.ModInfo;

import java.util.List;

public class ModUtilImpl {
    public static ModInfo getModInfo(String modId) {
        List<net.neoforged.fml.loading.moddiscovery.ModInfo> modInfos = FMLLoader.getLoadingModList().getMods();
        if (modInfos.isEmpty()) return new ModInfo();
        for (net.neoforged.fml.loading.moddiscovery.ModInfo modInfo : modInfos) {
            if (!modInfo.getModId().equals(modId))
                continue;

            return new ModInfo(modId, modInfo.getDisplayName(), modInfo.getVersion().getQualifier());
        }

        return new ModInfo();
    }

    public static String getModName(String modId) {
        List<net.neoforged.fml.loading.moddiscovery.ModInfo> modInfos = FMLLoader.getLoadingModList().getMods();
        if (modInfos.isEmpty()) return "";
        for (net.neoforged.fml.loading.moddiscovery.ModInfo modInfo : modInfos) {
            if (!modInfo.getModId().equals(modId))
                continue;

            return modInfo.getDisplayName();
        }

        return "";
    }
}
