package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JSONContext{
    private JSONStyleFactory styleFactory;

    private IconFamilyConfig iconFamilyConfig;

    private String icon;

    private String style;

    JSONExplorer explorer;
//
    public JSONContext() {
        // 默认使用树形风格工厂
        this.styleFactory = new TreeJSONStyleFactory();

    }

    public void setStyle(String style) {
        this.style = style;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void display(String content) {
        buildstyle();
        buildIcon();
        this.explorer = styleFactory.createExplorer();
        explorer.setIcon(icon);
        explorer.setIconFamilyConfig(iconFamilyConfig);


        explorer.display(content);
    }

    public void buildstyle() {
        if ("tree".equals(style)) {
            this.styleFactory = new TreeJSONStyleFactory();
        } else if ("rectangle".equals(style)) {
            this.styleFactory = new RectangleJSONStyleFactory();
        }
    }



    public void buildIcon() {
        this.iconFamilyConfig = new IconFamilyConfigImpl();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 从配置文件中读取JSON数据
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json");
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // 遍历JSON数据，添加图标族信息
            for (JsonNode node : jsonNode) {
                String name = node.get("name").asText();
                String nodeIcon = node.get("nodeIcon").asText();
                String leafIcon = node.get("leafIcon").asText();
                iconFamilyConfig.addIconFamily(name, nodeIcon, leafIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



