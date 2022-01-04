package com.dollarbarberhouse.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.Base64;
import java.util.List;
import java.util.Map;

public class HelpUtils {

    public static String decodeBase64Str(String base64) {
        return new String(Base64.getDecoder().decode(base64));
    }

    public static String encodeBase64Str(String json) {
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    public static <T> T getObjectFromEncodedStr(String encodedStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        String decodedStr = decodeBase64Str(encodedStr);
        try {
            return mapper.readValue(decodedStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getObjectFromStr(String str, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> getObjectListFromStr(String str, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String stringifyJson(Map json) {
        return new JSONObject(json).toString();
    }
}
