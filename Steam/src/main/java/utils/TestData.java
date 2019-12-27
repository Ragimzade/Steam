package utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for getting data for tests from json file
 */
public class TestData {
    public static final String CREDS_JSON_FILE_PATH = "src/main/resources/creds.json";
    static JSONParser parser = new JSONParser();

    /**
     * Gets specified value as string from json file
     *
     * @param key key to read from file
     * @return string value
     * @throws IOException    if file is not found
     * @throws ParseException when unexpected error occurs while parsing
     */

    public static String getValue(String key) throws IOException, ParseException {
        Path filepath = Paths.get(CREDS_JSON_FILE_PATH);
        Object obj = parser.parse(new FileReader(String.valueOf(filepath)));
        JSONObject jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(key);
    }
}
