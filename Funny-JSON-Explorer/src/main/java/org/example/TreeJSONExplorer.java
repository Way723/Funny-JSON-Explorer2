//package org.example;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TreeJSONExplorer implements JSONExplorer {
//
//    private IconFamilyConfig iconFamilyConfig;
//
//    private String icon;
//
//    @Override
//    public void display(String json) {
//        System.out.println("以树形样式显示 JSON");
//        JSONObject jsonObject = new JSONObject(json); // 解析 JSON 字符串为 JSONObject
//        List<Boolean> list = new ArrayList<>(); // 用于跟踪树的层级结构
//        displayJsonObject(jsonObject, 0, list); // 显示 JSON 结构
//    }
//
//
//    @Override
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    @Override
//    public void setIconFamilyConfig(IconFamilyConfig iconFamilyConfig) {
//        this.iconFamilyConfig = iconFamilyConfig;
//    }
//
//    // 递归显示 JSONComponent
//    private void displayJsonObject(JSONObject jsonObject, int indent, List<Boolean> list) {
//        for (String key : jsonObject.keySet()) {
//            Object value = jsonObject.get(key);
//            boolean isObject = value instanceof JSONObject;
//            boolean isArray = value instanceof JSONArray;
//            boolean hasNext = hasNext(jsonObject, key);
//
//            // 打印缩进和连接线
//            printIndent(indent, list);
//            System.out.print(!hasNext ? "└─" : "├─");
//            list.add(hasNext);
//            // 如果值是对象或数组，则只打印键，否则打印键值对
//            if (isObject || isArray) {
//                if (isObject && ((JSONObject) value).keySet().size() == 0) {
//                    System.out.println(iconFamilyConfig.getLeafIcon(icon) + key);
//                } else {
//                    System.out.println(iconFamilyConfig.getNodeIcon(icon) + key);
//                }
//
//            } else {
//                System.out.println(iconFamilyConfig.getLeafIcon(icon) + key + ": " + value);
//            }
//
//
//            // 递归处理对象和数组
//            if (isObject) {
//                displayJsonObject((JSONObject) value, indent + 1, list);
//            } else if (isArray) {
//                displayJsonArray((JSONArray) value, indent + 1, list);
//            }
//            list.remove(indent);
//        }
//    }
//
//    // 递归显示 JSONArray
//    private void displayJsonArray(JSONArray jsonArray, int indent, List<Boolean> list) {
//        for (int i = 0; i < jsonArray.length(); i++) {
//            Object value = jsonArray.get(i);
//            boolean isObject = value instanceof JSONObject;
//            boolean isArray = value instanceof JSONArray;
//            boolean hasNext = i < jsonArray.length() - 1;
//
//            // 打印缩进和连接线
//            printIndent(indent, list);
//            System.out.print(!hasNext ? "└─" : "├─");
//            list.add(hasNext);
//            // 递归处理对象和数组，否则直接打印值
//            if (isObject) {
//                displayJsonObject((JSONObject) value, indent + 1, list);
//            } else if (isArray) {
//                displayJsonArray((JSONArray) value, indent + 1, list);
//            } else {
//                System.out.println(iconFamilyConfig.getLeafIcon(icon) + value);
//            }
//            list.remove(indent);
//        }
//    }
//
//    // 打印缩进
//    private void printIndent(int indent, List<Boolean> list) {
//        for (int i = 0; i < indent; i++) {
//            if (list.get(i)) {
//                System.out.print("│  ");
//            } else {
//                System.out.print("   ");
//            }
//        }
//    }
//
//    // 检查键在 JSONObject 中是否有下一个键
//    private boolean hasNext(JSONObject jsonObject, String key) {
//        int index = 0;
//        for (String nextKey : jsonObject.keySet()) {
//            if (nextKey.equals(key)) {
//                return index < jsonObject.length() - 1;
//            }
//            index++;
//        }
//        return false;
//    }
//}


package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TreeJSONExplorer implements JSONExplorer {

    private IconFamilyConfig iconFamilyConfig;

    private String icon;

    @Override
    public void display(String json) {
        System.out.println("以树形样式显示 JSON");
        JSONObject jsonObject = new JSONObject(json); // 解析 JSON 字符串为 JSONObject
        List<Boolean> list = new ArrayList<>(); // 用于跟踪树的层级结构
        displayJsonObject(jsonObject, 0, list); // 显示 JSON 结构
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public void setIconFamilyConfig(IconFamilyConfig iconFamilyConfig) {
        this.iconFamilyConfig = iconFamilyConfig;
    }

    // 递归显示 JSONComponent
    private void displayJsonObject(JSONObject jsonObject, int indent, List<Boolean> list) {
        JSONIterator iterator = new JSONObjectIterator(jsonObject);
        while (iterator.hasNext()) {
            String key = iterator.nextKey();
            Object value = iterator.nextValue();
            boolean isObject = value instanceof JSONObject;
            boolean isArray = value instanceof JSONArray;
            boolean hasNext = iterator.hasNext();

            // 打印缩进和连接线
            printIndent(indent, list);
            System.out.print(!hasNext ? "└─" : "├─");
            list.add(hasNext);
            // 如果值是对象或数组，则只打印键，否则打印键值对
            if (isObject || isArray) {
                if (isObject && ((JSONObject) value).keySet().size() == 0) {
                    System.out.println(iconFamilyConfig.getLeafIcon(icon) + key);
                } else {
                    System.out.println(iconFamilyConfig.getNodeIcon(icon) + key);
                }

            } else {
                System.out.println(iconFamilyConfig.getLeafIcon(icon) + key + ": " + value);
            }

            // 递归处理对象和数组
            if (isObject) {
                displayJsonObject((JSONObject) value, indent + 1, list);
            } else if (isArray) {
                displayJsonArray((JSONArray) value, indent + 1, list);
            }
            list.remove(list.size() - 1);
        }
    }

    // 递归显示 JSONArray
    private void displayJsonArray(JSONArray jsonArray, int indent, List<Boolean> list) {
        JSONIterator iterator = new JSONArrayIterator(jsonArray);
        while (iterator.hasNext()) {
            Object value = iterator.nextValue();
            boolean isObject = value instanceof JSONObject;
            boolean isArray = value instanceof JSONArray;
            boolean hasNext = iterator.hasNext();

            // 打印缩进和连接线
            printIndent(indent, list);
            System.out.print(!hasNext ? "└─" : "├─");
            list.add(hasNext);
            // 递归处理对象和数组，否则直接打印值
            if (isObject) {
                displayJsonObject((JSONObject) value, indent + 1, list);
            } else if (isArray) {
                displayJsonArray((JSONArray) value, indent + 1, list);
            } else {
                System.out.println(iconFamilyConfig.getLeafIcon(icon) + value);
            }
            list.remove(list.size() - 1);
        }
    }

    // 打印缩进
    private void printIndent(int indent, List<Boolean> list) {
        for (int i = 0; i < indent; i++) {
            if (list.get(i)) {
                System.out.print("│  ");
            } else {
                System.out.print("   ");
            }
        }
    }

    // 检查键在 JSONObject 中是否有下一个键
    private boolean hasNext(JSONObject jsonObject, String key) {
        int index = 0;
        for (String nextKey : jsonObject.keySet()) {
            if (nextKey.equals(key)) {
                return index < jsonObject.length() - 1;
            }
            index++;
        }
        return false;
    }
}
