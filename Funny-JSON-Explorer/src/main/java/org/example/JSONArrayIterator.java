package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONArrayIterator implements JSONIterator {
    private JSONArray jsonArray;
    private int currentIndex;

    public JSONArrayIterator(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < jsonArray.length();
    }

    @Override
    public String nextKey() {
        return String.valueOf(currentIndex); // JSONArray没有key，用索引代替
    }

    @Override
    public Object nextValue() {
        return jsonArray.get(currentIndex++);
    }
}