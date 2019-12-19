package utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestData {
    static JSONParser parser = new JSONParser();

    public static String getValue(String key) throws IOException, ParseException {
        Path filepath = Paths.get("src/main/resources/creds.json");
        Object obj = parser.parse(new FileReader(String.valueOf(filepath)));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
