package org.example;

public class TreeJSONStyleFactory implements JSONStyleFactory {
    @Override
    public JSONExplorer createExplorer() {
        return new TreeJSONExplorer();
    }
}
