package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONObjectIterator implements JSONIterator {
    private Iterator<String> keys;
    private JSONObject jsonObject;
    private String currentKey;

    public JSONObjectIterator(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.keys = jsonObject.keys();
    }

    @Override
    public boolean hasNext() {
        return keys.hasNext();
    }

    @Override
    public String nextKey() {
        currentKey = keys.next();
        return currentKey;
    }

    @Override
    public Object nextValue() {
        return jsonObject.get(currentKey);
    }
}