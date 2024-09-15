package net.pitan76.modscmd;

import net.pitan76.mcpitanlib.core.datafixer.Pair;

import java.util.ArrayList;
import java.util.List;

public class ModInfo {
    public final String id;
    public String name = "";
    public String version = "";
    public String description = "";
    public List<String> authors = new ArrayList<>();

    // type => url
    public List<Pair<String, String>> urls = new ArrayList<>();

    public ModInfo(String id) {
        this.id = id;
    }

    public ModInfo() {
        this.id = null;
    }

    public boolean isEmpty() {
        return id == null || id.isEmpty();
    }

    public ModInfo(String id, String name, String version, String description, List<String> authors, List<Pair<String, String>> urls) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.urls = urls;
    }

    public ModInfo(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
