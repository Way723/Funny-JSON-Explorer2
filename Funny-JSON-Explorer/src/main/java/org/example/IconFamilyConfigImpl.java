package org.example;

import java.util.HashMap;
import java.util.Map;

public class IconFamilyConfigImpl implements IconFamilyConfig {
    private Map<String, String> nodeIcons = new HashMap<>();
    private Map<String, String> leafIcons = new HashMap<>();

    @Override
    public void addIconFamily(String name, String nodeIcon, String leafIcon) {
        nodeIcons.put(name, nodeIcon);
        leafIcons.put(name, leafIcon);
    }

    @Override
    public String getNodeIcon(String name) {
        return nodeIcons.get(name);
    }

    @Override
    public String getLeafIcon(String name) {
        return leafIcons.get(name);
    }
}
