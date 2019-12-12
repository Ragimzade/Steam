package utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonParser {
    static JSONParser parser = new JSONParser();

    public static String getLogin() throws IOException, ParseException {
        return getValue("login");
    }

    public static String getPassword() throws IOException, ParseException {
        return getValue("password");
    }

    public static String getKasperskyLogin() throws IOException, ParseException {
        return getValue("kasperskiLogin");
    }

    public static String getKasperskyPassword() throws IOException, ParseException {
        return getValue("kasperskyPassword");
    }

    private static String getValue(String key) throws IOException, ParseException {
        Path filepath = Paths.get("src/main/resources/creds.json");
        Object obj = parser.parse(new FileReader(String.valueOf(filepath)));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
