package org.example;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.apache.commons.cli.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option jsonFile = new Option("f", "file", true, "JSON file path");
        jsonFile.setRequired(true);
        options.addOption(jsonFile);

        Option styleOption = new Option("s", "style", true, "Display style (tree/rectangle)");
        styleOption.setRequired(true);
        options.addOption(styleOption);

        Option iconFamilyOption = new Option("i", "iconFamily", true, "Icon family (poker/love)");
        iconFamilyOption.setRequired(true);
        options.addOption(iconFamilyOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            String jsonFilePath = cmd.getOptionValue("file");
            String style = cmd.getOptionValue("style");
            String iconFamily = cmd.getOptionValue("iconFamily");

            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            //
            JSONContext context = new JSONContext();

            context.setStyle(style);
            context.setIcon(iconFamily);


            context.display(jsonContent);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("fje", options);

            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


