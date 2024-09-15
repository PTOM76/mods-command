package net.pitan76.modscmd.command;

import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.ConfigCommand;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.StringCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;
import net.pitan76.mcpitanlib.api.event.StringCommandEvent;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.core.datafixer.Pair;
import net.pitan76.modscmd.ModInfo;
import net.pitan76.modscmd.ModUtil;

import java.util.Collection;

public class ModsCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        super.init(settings);

        addArgumentCommand("help", new LiteralCommand() {

            @Override
            public void execute(ServerCommandEvent e) {
                String help = "§a/mods§f: Show all mods\n" +
                        "§a/mods help§f: Show help\n" +
                        "§a/mods info <modId>§f: Show mod info\n" +
                        "§a/mods normal <viewType>§f: Show normal mods\n" +
                        "§a/mods all <viewType>§f: Show all mods\n" +
                        "\n" +
                        "§a  viewType§f: name, id";
                e.sendSuccess(TextUtil.literal(help), false);
            }
        });

        addArgumentCommand("info", new LiteralCommand() {

            @Override
            public void init(CommandSettings settings) {
                super.init(settings);

                addArgumentCommand("modId", new StringCommand() {

                    @Override
                    public void execute(StringCommandEvent e) {
                        String modId = e.getValue();
                        ModInfo modInfo = ModUtil.getModInfo(modId);
                        if (modInfo == null) {
                            e.sendSuccess(TextUtil.literal("Mod ID: §a" + modId + "§f, Mod Name: §aUnknown"), false);
                            return;
                        }

                        StringBuilder sb = new StringBuilder();
                        sb.append("§e[§9Mod Info§e]§r\n");
                        sb.append("§aMod ID: §f").append(modInfo.getId()).append("\n");
                        sb.append("§aMod Name: §f").append(modInfo.getName()).append("\n");
                        sb.append("§aMod Version: §f").append(modInfo.version).append("\n");
                        if (modInfo.description != null && !modInfo.description.isEmpty())
                            sb.append("§aMod Description: §f").append(modInfo.description).append("\n");
                        if (modInfo.urls != null && !modInfo.urls.isEmpty()) {
                            sb.append("§aMod URLs: §f").append("\n");
                            for (Pair<String, String> url : modInfo.urls) {
                                sb.append("-§b").append(url.getA()).append(": §f").append(url.getB()).append("\n");
                            }
                        }
                        if (modInfo.authors != null && !modInfo.authors.isEmpty()) {
                            sb.append("§aMod Authors: §f").append("\n");
                            for (String author : modInfo.authors) {
                                sb.append("-§b").append(author).append("\n");
                            }
                        }

                        e.sendSuccess(TextUtil.literal(sb.toString()), false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "modId";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                e.sendSuccess(TextUtil.literal("Usage: /mods info <modId>"), false);
            }
        });

        addArgumentCommand("normal", new LiteralCommand() {

            @Override
            public void init(CommandSettings settings) {
                super.init(settings);

                addArgumentCommand("viewType", new StringCommand() {

                    @Override
                    public void execute(StringCommandEvent e) {
                        String viewType = e.getValue();
                        Collection<String> modIds = PlatformUtil.getModIds();
                        modIds = ModUtil.removeLoaderApi(modIds);

                        StringBuilder sb = new StringBuilder();
                        sb.append("Mods (").append(modIds.size()).append("): ");

                        for (String modId : modIds) {
                            String modName = ModUtil.getModName(modId);
                            if (modName == null || modName.isEmpty())
                                modName = modId;

                            if (viewType.equals("name"))
                                sb.append("§a").append(modName).append("§f, ");
                            else
                                sb.append("§a").append(modId).append("§f, ");
                        }
                        sb.delete(sb.length() - 2, sb.length());

                        e.sendSuccess(TextUtil.literal(sb.toString()), false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "viewType";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                Collection<String> modIds = PlatformUtil.getModIds();
                modIds = ModUtil.removeLoaderApi(modIds);

                StringBuilder sb = new StringBuilder();
                sb.append("Mods (").append(modIds.size()).append("): ");

                for (String modId : modIds) {
                    String modName = ModUtil.getModName(modId);
                    if (modName == null || modName.isEmpty())
                        modName = modId;

                    sb.append("§a").append(modName).append("§f, ");
                }
                sb.delete(sb.length() - 2, sb.length());

                e.sendSuccess(TextUtil.literal(sb.toString()), false);
            }
        });

        addArgumentCommand("all", new LiteralCommand() {

            @Override
            public void init(CommandSettings settings) {
                super.init(settings);

                addArgumentCommand("viewType", new StringCommand() {

                    @Override
                    public void execute(StringCommandEvent e) {
                        String viewType = e.getValue();
                        Collection<String> modIds = PlatformUtil.getModIds();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Mods (").append(modIds.size()).append("): ");

                        for (String modId : modIds) {
                            String modName = ModUtil.getModName(modId);
                            if (modName == null || modName.isEmpty())
                                modName = modId;

                            if (viewType.equals("name"))
                                sb.append("§a").append(modName).append("§f, ");
                            else
                                sb.append("§a").append(modId).append("§f, ");
                        }
                        sb.delete(sb.length() - 2, sb.length());

                        e.sendSuccess(TextUtil.literal(sb.toString()), false);
                    }

                    @Override
                    public String getArgumentName() {
                        return "viewType";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent e) {
                Collection<String> modIds = PlatformUtil.getModIds();
                StringBuilder sb = new StringBuilder();
                sb.append("Mods (").append(modIds.size()).append("): ");

                for (String modId : modIds) {
                    String modName = ModUtil.getModName(modId);
                    if (modName == null || modName.isEmpty())
                        modName = modId;

                    sb.append("§a").append(modName).append("§f, ");
                }
                sb.delete(sb.length() - 2, sb.length());

                e.sendSuccess(TextUtil.literal(sb.toString()), false);
            }
        });
    }

    @Override
    public void execute(ServerCommandEvent e) {
        Collection<String> modIds = PlatformUtil.getModIds();
        modIds = ModUtil.removeLoaderApi(modIds);

        StringBuilder sb = new StringBuilder();
        sb.append("Mods (").append(modIds.size()).append("): ");

        for (String modId : modIds) {
            String modName = ModUtil.getModName(modId);
            if (modName == null || modName.isEmpty())
                modName = modId;

            sb.append("§a").append(modName).append("§f, ");
        }
        sb.delete(sb.length() - 2, sb.length());

        e.sendSuccess(TextUtil.literal(sb.toString()), false);
    }
}
