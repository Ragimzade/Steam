package utils;


import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestData {
    public static final String CREDS_FILE_PATH = "src/main/resources/creds.json";
    static JSONParser parser = new JSONParser();

    @SneakyThrows
    public static String getValue(String key) {
        Path filepath = Paths.get(CREDS_FILE_PATH);
        Object obj = parser.parse(new FileReader(String.valueOf(filepath)));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
