package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class RectangleJSONExplorer implements JSONExplorer {

    private IconFamilyConfig iconFamilyConfig;

    private String icon;
    private Boolean isFirst = true;

    @Override
    public void display(String json) {
        System.out.println("以矩形样式显示 JSON");
        JSONObject jsonObject = new JSONObject(json);

        displayJsonObject(new JSONObjectIterator(jsonObject), 0);
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public void setIconFamilyConfig(IconFamilyConfig iconFamilyConfig) {
        this.iconFamilyConfig = iconFamilyConfig;
    }

    // 递归显示 JSONIterator
    private void displayJsonObject(JSONIterator iterator, int indent) {
        while (iterator.hasNext()) {
            String key = iterator.nextKey();
            Object value = iterator.nextValue();
            boolean isObject = value instanceof JSONObject;
            boolean isArray = value instanceof JSONArray;
            boolean hasNext = iterator.hasNext();
            Boolean isLast = !(isObject || isArray) && !hasNext;

            // 打印缩进和连接线
            printIndent(indent, isLast);
            if (isFirst) {
                System.out.print("┌─ ");
            } else if (isLast) {
                System.out.print("└─ ");
            } else {
                System.out.print("├─ ");
            }

            // 如果值是对象或数组，则只打印键，否则打印键值对
            if (!(isObject || isArray)) {
                System.out.print(iconFamilyConfig.getLeafIcon(icon) + key + ": " + value);
                // 计算需要补齐的长度
                int paddingLength = 50 - key.length() - value.toString().length() - indent * 3 - 2;
                for (int i = 0; i < paddingLength; i++) {
                    System.out.print("─");
                }
                if (isFirst) {
                    System.out.print("─┐");
                } else if (isLast) {
                    System.out.print("─┘");
                } else {
                    System.out.print("─┤");
                }
                System.out.println();
            } else {
                if (isObject && ((JSONObject) value).keySet().size() == 0) {
                    System.out.print(iconFamilyConfig.getLeafIcon(icon) + key);
                } else {
                    System.out.print(iconFamilyConfig.getNodeIcon(icon) + key);
                }
                // 计算需要补齐的长度
                int paddingLength = 50 - key.length() - indent * 3;
                for (int i = 0; i < paddingLength; i++) {
                    System.out.print("─");
                }
                if (isFirst) {
                    System.out.print("─┐");
                } else if (isLast) {
                    System.out.print("─┘");
                } else {
                    System.out.print("─┤");
                }
                System.out.println();
            }
            isFirst = false;

            // 递归处理对象和数组
            if (isObject) {
                displayJsonObject(new JSONObjectIterator((JSONObject) value), indent + 1);
            } else if (isArray) {
                displayJsonArray(new JSONArrayIterator((JSONArray) value), indent + 1);
            }
        }
    }

    // 递归显示 JSONArrayIterator
    private void displayJsonArray(JSONIterator iterator, int indent) {
        while (iterator.hasNext()) {
            String key = iterator.nextKey();
            Object value = iterator.nextValue();
            boolean isObject = value instanceof JSONObject;
            boolean isArray = value instanceof JSONArray;
            boolean hasNext = iterator.hasNext();
            boolean isLast = !(isObject || isArray) && !hasNext;

            // 打印缩进和连接线
            printIndent(indent, isLast);
            if (isFirst) {
                System.out.print("┌─ ");
            } else if (isLast) {
                System.out.print("└─ ");
            } else {
                System.out.print("├─ ");
            }

            // 递归处理对象和数组，否则直接打印值
            if (isObject) {
                displayJsonObject(new JSONObjectIterator((JSONObject) value), indent + 1);
            } else if (isArray) {
                displayJsonArray(new JSONArrayIterator((JSONArray) value), indent + 1);
            } else {
                System.out.print(value);
                // 计算需要补齐的长度
                int paddingLength = 50 - value.toString().length() - indent * 3;
                for (int j = 0; j < paddingLength; j++) {
                    System.out.print("─");
                }
                if (isFirst) {
                    System.out.print("─┐");
                } else if (isLast) {
                    System.out.print("─┘");
                } else {
                    System.out.print("─┤");
                }
                System.out.println();
            }
        }
    }

    // 打印缩进
    private void printIndent(int indent, Boolean isLast) {
        if (!isLast) {
            for (int i = 0; i < indent; i++) {
                System.out.print("│  ");
            }
        } else {
            for (int i = 0; i < indent; i++) {
                System.out.print("└──");
            }
        }
    }
}
