package org.example;

public class RectangleJSONStyleFactory implements JSONStyleFactory {
    @Override
    public JSONExplorer createExplorer() {
        return new RectangleJSONExplorer();
    }
}
