package gsonParse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import static gsonParse.JsonParser.readJsonFile;

public class Program {
    private static final String JOBS_TEST_DATA_FILEPATH = "src/main/resources/addresses.json";

    public static void main(String[] args) throws IOException {
        StringBuilder JSON = readJsonFile(JOBS_TEST_DATA_FILEPATH);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Location location = gson.fromJson(JSON.toString(), Location.class);
        String jsonOutput = gson.toJson(location);
        System.out.println(jsonOutput);
    }
}
