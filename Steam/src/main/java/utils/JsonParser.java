package utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    static JSONParser parser = new JSONParser();

    public static String getLogin() throws IOException, ParseException {
        return getValue("login");
    }

    public static String getPassword() throws IOException, ParseException {
        return getValue("password");
    }

    private static String getValue(String key) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\creds.json"));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
