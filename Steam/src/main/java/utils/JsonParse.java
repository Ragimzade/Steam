package utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParse {
    static JSONParser parser = new JSONParser();

    public static String getLogin() {
        return getValue("login");
    }

    public static String getPassword() {
        return getValue("password");
    }

    public static String getKasperskyLogin() {
        return getValue("kasperskiLogin");
    }

    public static String getKasperskyLogin2() {
        return getValue("kasperskiLogin2");
    }

    public static String getKasperskyLPassword() {
        return getValue("kasperskyPassword");
    }

    private static String getValue(String key) {
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\creds.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
