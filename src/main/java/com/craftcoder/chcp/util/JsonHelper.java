package com.craftcoder.chcp.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <E> E toObject(Object obj, Class<E> clazz) throws RuntimeException {
        try {
            if (obj instanceof String) {
                if (clazz == String.class) {
                    return (E) obj;
                } else {
                    return MAPPER.readValue((String) obj, clazz);
                }
            } else if (obj instanceof JsonNode) {
                return MAPPER.readValue(obj.toString(), clazz);
            } else {
                return MAPPER.readValue(MAPPER.writeValueAsString(obj), clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E> List<E> toList(Object obj, Class<E> clazz) throws RuntimeException {
        JavaType type = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            if (obj instanceof String) {
                return MAPPER.readValue((String) obj, type);
            } else if (obj instanceof JsonNode) {
                return MAPPER.readValue(obj.toString(), type);
            } else {
                return MAPPER.readValue(MAPPER.writeValueAsString(obj), type);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode toJson(Object obj) throws RuntimeException {
        if (obj instanceof String) {
            try {
                return MAPPER.readTree((String) obj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return MAPPER.valueToTree(obj);
        }
    }

}
