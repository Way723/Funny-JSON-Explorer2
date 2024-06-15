package org.example;

public interface IconFamilyConfig {
    public void addIconFamily(String name, String nodeIcon, String leafIcon);
    public String getNodeIcon(String name);
    public String getLeafIcon(String name);
}
