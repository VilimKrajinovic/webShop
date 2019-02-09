package com.vkraji.webShop.models.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
    public static String extractIdFromJson(String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonElement jsonId = jsonObject.get("selected");
        String selected = jsonId.toString();
        return selected;
    }
}
