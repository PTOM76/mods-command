package net.pitan76.modscmd;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModUtil {

    @ExpectPlatform
    public static ModInfo getModInfo(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String getModName(String modId) {
        throw new AssertionError();
    }

    public static List<String> removeLoaderApi(Collection<String> modIds) {
        List<String> list = new ArrayList<>();

        for (String modId : modIds) {
            if (
                    modId.equals("minecraft")
                    || modId.equals("mixinextras")
                    || modId.equals("java")
                    || modId.startsWith("generated_")
                    || modId.equals("fabricloader")
                    || modId.equals("fabric-api-base")
                    || modId.equals("fabric-renderer-indigo")
                    || modId.equals("javafml")
                    || modId.equals("forge")
                    || modId.equals("neoforge")
                    || modId.equals("quiltloader")
            )
                continue;

            if (!modId.equals("fabric-api") &&
                    modId.startsWith("fabric-") && (modId.endsWith("-api")
                    || modId.endsWith("-v0")
                    || modId.endsWith("-v1")
                    || modId.endsWith("-v2")
                    || modId.endsWith("-v3")
                    || modId.endsWith("-v4")
                    || modId.endsWith("-v5")
            ))
                continue;


            list.add(modId);
        }

        return list;
    }
}
