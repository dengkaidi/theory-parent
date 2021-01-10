package com.dkd.theory.rpc.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.naming.OperationNotSupportedException;

/**
 * @author dkd
 */
public final class JsonTool {

    private JsonTool() throws Exception {
        throw new IllegalAccessException("tool class can't construct");
    }

    private static Gson gson = null;
    static {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
