package com.neojson;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class NeoJsonTest {

    static String json1 = "[\n" +
            "            {\n" +
            "                \"employee\": {\n" +
            "                    \"firstName\": \"Lokesh\",\n" +
            "                    \"lastName\": \"Gupta\",\n" +
            "                    \"website\": \"howtodoinjava.com\",\n" +
            "                    \"age\": 34\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"employee\": {\n" +
            "                    \"firstName\": \"Brian\",\n" +
            "                    \"lastName\": \"Schultz\",\n" +
            "                    \"website\": \"example.com\"\n" +
            "                }\n" +
            "            }\n" +
            "        ]";


    static String json2 = "            {\n" +
            "                \"tool\":\n" +
            "                {\n" +
            "                    \"jsonpath\":\n" +
            "                    {\n" +
            "                        \"creator\":\n" +
            "                        {\n" +
            "                            \"name\": \"Jayway Inc.\",\n" +
            "                            \"location\":\n" +
            "                            [\n" +
            "                                \"Malmo\",\n" +
            "                                \"San Francisco\",\n" +
            "                                \"Helsingborg\"\n" +
            "                            ]\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"books\":\n" +
            "                [\n" +
            "                    {\n" +
            "                        \"title\": \"Beginning JSON\",\n" +
            "                        \"price\": 49.99\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"title\": \"JSON at Work\",\n" +
            "                        \"price\": 29.99\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n";

    @Test
    public void parse() throws ParseException {
        String val = parse(json2,"tool.jsonpath.creator.name");
        assertNotNull(val);
    }

    @Test
    public void parse_arr() throws ParseException {
        JSONArray arr = parse(json2,"tool.jsonpath.creator.location");
        assertNotNull(arr);
    }

    @Test
    public void deep_parse() throws ParseException {
        String val = parse(json1,"[0].employee.firstName");
        assertNotNull(val);
    }

    @Test(expected = RuntimeException.class)
    public void deep_parse_null() throws ParseException {
        String val = parse(json1,"[0].employee.unknown");
        assertNotNull(val);
    }

    @Test
    public void deep_parse_optional() throws ParseException {
        String val = parse(json1,"[0].employee.?firstname");
        assertNull(val);
    }

    @Test
    public void deep_parse_mid_array() throws ParseException {
        String val = parse(json2,"books.[0].title");
        assertNotNull(val);
    }

    @Test
    public void root_array() throws ParseException {
        JSONArray val = parse(json1, "");
        assertEquals(2, val.size());
    }

    private <T> T parse(String json, String path) throws ParseException {
        return new NeoJson(json).parse(path);
    }

}
