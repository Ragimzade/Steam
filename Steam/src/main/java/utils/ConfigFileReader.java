package utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static ConfigFileReader instance;
    private Properties prop;

    public ConfigFileReader() {
        prop = new Properties();
        try {
            prop.load(ConfigFileReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigFileReader getInstance() {
        if (instance == null) {
            instance = new ConfigFileReader();
        }
        return instance;
    }

    public String getBrowser() {
        String browser = prop.getProperty("Browser");
        if (browser != null)
            return browser;
        else throw new RuntimeException("browser is null");
    }

    public String getBaseUrl() {
        String url = prop.getProperty("BaseUrl");
        if (url != null)
            return url;
        else throw new RuntimeException("url is null");
    }


    public String getLogin() {
        return prop.getProperty("Login");
    }

    public String getPassword() {
        return prop.getProperty("Password");
    }
}
