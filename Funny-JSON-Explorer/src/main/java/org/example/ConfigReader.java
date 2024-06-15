package org.example;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigReader {
    private JSONObject config;

    public ConfigReader(String configFilePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(configFilePath)));
            this.config = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getConfig() {
        return this.config;
    }
}