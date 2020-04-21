package com.neojson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JSON path parser based on json-simple library.
 */
public class NeoJson {

    private String json;
    public NeoJson(String json) {
        this.json = json;
    }

    public <T> T parse(String path) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(json);
        Object val = obj;

        if (path.equals("")) {
            return (T) val;
        }

        String[] arr = path.split("\\.");
        for (int i = 0; i < arr.length; i++) {
            String field = arr[i];
            boolean isRequired =  field.contains("?");
            field = field.replace("?", "");

            Pattern pattern = Pattern.compile("\\[(\\d?)\\]");
            Matcher matcher = pattern.matcher(field);
            if (matcher.find()) {
                // array
                var list = (JSONArray) val;
                var index = Integer.valueOf(matcher.group(1));
                val = list.get(index);
            } else {
                // object
                val = ((JSONObject)val).get(field);
            }

            if (val == null && !isRequired) {
                throw new RuntimeException(String.format("%s is null", field));
            }
        }

        return val != null ? (T) val: null;
    }

}
