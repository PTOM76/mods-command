package net.pitan76.modscmd.output;

import net.pitan76.easyapi.FileControl;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.modscmd.ModInfo;
import net.pitan76.modscmd.ModUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ModListOutput {
    public static final File FOLDER = new File(PlatformUtil.getGameFolderAsFile(), "modlist");

    public static final String[] SUPPORT_FILE_TYPE = new String[] {
            "json",
            "csv",
            "txt",
            "md",
            "yml",
            "xml",
            "html",
            "xlsx",
    };

    public static Result output(String fileType, String fileName) {
        if (Arrays.stream(SUPPORT_FILE_TYPE).noneMatch(s -> s.equals(fileType)))
            return Result.NOT_SUPPORT_FILE_TYPE;

        List<ModInfo> modInfos = new ArrayList<>();

        Collection<String> modIds = PlatformUtil.getModIds();
        for (String modId : modIds) {
            ModInfo modInfo = ModUtil.getModInfo(modId);
            modInfos.add(modInfo);
        }

        File file = new File(FOLDER, fileName + "." + fileType);
        if (fileType.equals("json")) {
            // JSONを出力する、modInfosをJSONに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("[\n");
            for (ModInfo modInfo : modInfos) {
                builder.append("  {\n");
                builder.append("    \"ID\": \"").append(modInfo.getId()).append("\",\n");
                builder.append("    \"Name\": \"").append(modInfo.getName()).append("\",\n");
                builder.append("    \"Version\": \"").append(modInfo.version).append("\",\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("    \"Authors\": [\n");
                    for (String author : modInfo.authors) {
                        builder.append("      \"").append(author).append("\",\n");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                    builder.append("\n    ],\n");
                }

                builder.append("    \"Description\": \"").append(modInfo.description).append("\"\n");
                builder.append("  },\n");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("\n]");

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("csv")) {
            // CSVを出力する、modInfosをCSVに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("ID,Name,Version,Authors,Description\n");
            for (ModInfo modInfo : modInfos) {
                builder.append(modInfo.getId()).append(",");
                builder.append(modInfo.getName()).append(",");
                builder.append(modInfo.version).append(",");

                if (!modInfo.authors.isEmpty()) {
                    for (String author : modInfo.authors) {
                        builder.append(author).append(", ");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                }
                builder.append(",");

                builder.append(modInfo.description).append("\n");
            }

            FileControl.fileWriteContents(file, builder.toString());

        }
        if (fileType.equals("txt")) {
            // TXTを出力する、modInfosをTXTに変換して出力する
            StringBuilder builder = new StringBuilder();
            for (ModInfo modInfo : modInfos) {
                builder.append("ID: ").append(modInfo.getId()).append("\n");
                builder.append("Name: ").append(modInfo.getName()).append("\n");
                builder.append("Version: ").append(modInfo.version).append("\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("Authors: ");
                    for (String author : modInfo.authors) {
                        builder.append(author).append(", ");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                    builder.append("\n");
                }

                builder.append("Description: ").append(modInfo.description).append("\n");
                builder.append("\n");
            }

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("md")) {
            // MDを出力する、modInfosをMDに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("# Mod List\n");
            for (ModInfo modInfo : modInfos) {
                builder.append("### ").append(modInfo.getName()).append("\n");
                builder.append("- ID: ").append(modInfo.getId()).append("\n");
                builder.append("- Version: ").append(modInfo.version).append("\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("- Authors: \n");
                    for (String author : modInfo.authors) {
                        builder.append("  - ").append(author).append("\n");
                    }
                }

                builder.append("- Description: ").append(modInfo.description).append("\n");
                builder.append("\n");
            }

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("yml")) {
            // YMLを出力する、modInfosをYMLに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("modList:\n");
            for (ModInfo modInfo : modInfos) {
                builder.append("  - id: ").append(modInfo.getId()).append("\n");
                builder.append("    name: ").append(modInfo.getName()).append("\n");
                builder.append("    version: ").append(modInfo.version).append("\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("    authors:\n");
                    for (String author : modInfo.authors) {
                        builder.append("      - ").append(author).append("\n");
                    }
                }

                builder.append("    description: ").append(modInfo.description).append("\n");
                builder.append("\n");
            }

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("xml")) {
            // XMLを出力する、modInfosをXMLに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            builder.append("<modList>\n");
            for (ModInfo modInfo : modInfos) {
                builder.append("  <mod>\n");
                builder.append("    <id>").append(modInfo.getId()).append("</id>\n");
                builder.append("    <name>").append(modInfo.getName()).append("</name>\n");
                builder.append("    <version>").append(modInfo.version).append("</version>\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("    <authors>\n");
                    for (String author : modInfo.authors) {
                        builder.append("      <author>").append(author).append("</author>\n");
                    }
                    builder.append("    </authors>\n");
                }

                builder.append("    <description>").append(modInfo.description).append("</description>\n");
                builder.append("  </mod>\n");
            }
            builder.append("</modList>");

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("html")) {
            // HTMLを出力する、modInfosをHTMLに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("<!DOCTYPE html>\n");
            builder.append("<html>\n");
            builder.append("<head>\n");
            builder.append("  <title>Mod List</title>\n");
            builder.append("</head>\n");
            builder.append("<body>\n");
            builder.append("  <h1>Mod List</h1>\n");
            builder.append("  <table>\n");
            builder.append("    <tr>\n");
            builder.append("      <th>ID</th>\n");
            builder.append("      <th>Name</th>\n");
            builder.append("      <th>Version</th>\n");
            builder.append("      <th>Authors</th>\n");
            builder.append("      <th>Description</th>\n");
            builder.append("    </tr>\n");
            for (ModInfo modInfo : modInfos) {
                builder.append("    <tr>\n");
                builder.append("      <td>").append(modInfo.getId()).append("</td>\n");
                builder.append("      <td>").append(modInfo.getName()).append("</td>\n");
                builder.append("      <td>").append(modInfo.version).append("</td>\n");

                if (!modInfo.authors.isEmpty()) {
                    builder.append("      <td>\n");
                    for (String author : modInfo.authors) {
                        builder.append("        ").append(author).append("<br>\n");
                    }
                    builder.append("      </td>\n");
                }

                builder.append("      <td>").append(modInfo.description).append("</td>\n");
                builder.append("    </tr>\n");
            }
            builder.append("  </table>\n");
            builder.append("</body>\n");
            builder.append("</html>");

            FileControl.fileWriteContents(file, builder.toString());
        }
        if (fileType.equals("xlsx")) {
            // XLSXを出力する、modInfosをXLSXに変換して出力する
            StringBuilder builder = new StringBuilder();
            builder.append("ID\tName\tVersion\tAuthors\tDescription\n");
            for (ModInfo modInfo : modInfos) {
                builder.append(modInfo.getId()).append("\t");
                builder.append(modInfo.getName()).append("\t");
                builder.append(modInfo.version).append("\t");

                if (!modInfo.authors.isEmpty()) {
                    for (String author : modInfo.authors) {
                        builder.append(author).append(", ");
                    }
                    builder.delete(builder.length() - 2, builder.length());
                }
                builder.append("\t");

                builder.append(modInfo.description).append("\n");
            }

            FileControl.fileWriteContents(file, builder.toString());
        }


        return Result.FAILED;
    }

    public enum Result {
        SUCCESS,
        NOT_SUPPORT_FILE_TYPE,
        FAILED
    }
}
