package net.pitan76.modscmd.forge;

import net.minecraftforge.fml.loading.FMLLoader;
import net.pitan76.modscmd.ModInfo;

import java.util.List;

public class ModUtilImpl {
    public static ModInfo getModInfo(String modId) {
        List<net.minecraftforge.fml.loading.moddiscovery.ModInfo> modInfos = FMLLoader.getLoadingModList().getMods();
        if (modInfos.isEmpty()) return new ModInfo();
        for (net.minecraftforge.fml.loading.moddiscovery.ModInfo modInfo : modInfos) {
            if (!modInfo.getModId().equals(modId))
                continue;

            return new ModInfo(modId, modInfo.getDisplayName(), modInfo.getVersion().getQualifier());
        }

        return new ModInfo();
    }

    public static String getModName(String modId) {
        List<net.minecraftforge.fml.loading.moddiscovery.ModInfo> modInfos = FMLLoader.getLoadingModList().getMods();
        if (modInfos.isEmpty()) return "";
        for (net.minecraftforge.fml.loading.moddiscovery.ModInfo modInfo : modInfos) {
            if (!modInfo.getModId().equals(modId))
                continue;

            return modInfo.getDisplayName();
        }

        return "";
    }
}
