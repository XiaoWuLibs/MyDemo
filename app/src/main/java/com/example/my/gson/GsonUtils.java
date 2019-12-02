package com.example.my.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by  wsl
 * on 2019/11/27 14:56
 */
public class GsonUtils {
    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    /**
     * Object转json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    /**
     * Json转List集合
     */
    public static <T> List<T> jsonToList(String json, Class<T> clz) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new com.google.gson.JsonParser().parse(json).getAsJsonArray();
        Gson mGson = new Gson();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }


    /**
     * Json转换成Map的List集合对象
     */
    public static <T> List<Map<String, T>> toListMap(String json, Class<T> clz) {
        Type type = new TypeToken<List<Map<String, T>>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转Map对象
     */
    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        Type type = new TypeToken<Map<String, T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
