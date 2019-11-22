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

    public static synchronized ConfigFileReader getInstance() {
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

    public int getFluentWaitInSec() {
        String waitInSec = prop.getProperty("FluentWaitInSec");
        if (waitInSec != null)
            return Integer.parseInt(waitInSec);
        else throw new RuntimeException("FluentWaitInSec is null");
    }

    public int getFluentWaitInMill() {
        String waitInMill = prop.getProperty("FluentWaitInMill");
        if (waitInMill != null)
            return Integer.parseInt(waitInMill);
        else throw new RuntimeException("FluentWaitInMill is null");
    }


    public String getBrowserDownloadPath() {
        String BrowserDownloadPath = prop.getProperty("BrowserDownloadPath");
        if (BrowserDownloadPath != null)
            return BrowserDownloadPath;
        else throw new RuntimeException("BrowserDownloadPath is null");
    }

}
