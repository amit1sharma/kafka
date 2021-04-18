package com.example.producer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asharma on 12/26/14.
 */
public class JSONConverter {
    public static Map convertPojoToJSONObject(Object o) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Map jsonModel = new JSONObject();
        JSONParser parser = new JSONParser();
        jsonModel = (Map)parser.parse(mapper.writeValueAsString(o));
        return jsonModel;
    }
    public static List convertListOfPojoToJSONArrayObjects(List list) throws IOException, ParseException {
        List arr = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        arr = (List)parser.parse(mapper.writeValueAsString(list));
        return arr;
    }
    public static <T> T convertJSONObjectToPojo(JSONObject jsonObject, Class<T> aClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (T)mapper.readValue(jsonObject.toJSONString(),aClass);
    }
    public static <T> List<T> convertListOfJSONObjectToPojo(List jsonObjects, Class<T> aClass) throws IOException {
        List<T> list = new ArrayList<T>();
        for(Object jsonObject : jsonObjects) {
            list.add(convertJSONObjectToPojo(new JSONObject((Map)jsonObject),aClass));
        }
        return list;
    }
}
