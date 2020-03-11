package gsonParse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser {

    public static StringBuilder readJsonFile(String jsonPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(jsonPath)));
        StringBuilder JSON = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            JSON.append(line);
            line = reader.readLine();
        }
        return JSON;
    }
}
