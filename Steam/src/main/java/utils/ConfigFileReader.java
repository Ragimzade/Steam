package utils;

import javax.mail.Session;
import java.io.IOException;
import java.util.Objects;
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
        String browser = prop.getProperty("Browser", "CHROME");
        Objects.requireNonNull(browser, "Set property browser in config");
        return browser;
    }

    public String getBaseUrl() {
        String url = prop.getProperty("BaseUrl");
        Objects.requireNonNull(url, "Set property BaseUrl in config");
        return url;
    }

    public int getFluentWaitInSec() {
        String waitInSec = prop.getProperty("FluentWaitInSec", "5");
        Objects.requireNonNull(waitInSec, "Set property FluentWaitInSec in config");
        return Integer.parseInt(waitInSec);
    }

    public int getFluentWaitInMill() {
        String waitInMill = prop.getProperty("FluentWaitInMill", "1000");
        Objects.requireNonNull(waitInMill, "Set property FluentWaitInMill in config");
        return Integer.parseInt(waitInMill);
    }

    public String getBrowserDownloadPath() {
        String browserDownloadPath = prop.getProperty("BrowserDownloadPath");
        Objects.requireNonNull(browserDownloadPath, "Set property BrowserDownloadPath in config");
        return browserDownloadPath;
    }

    public int getPageLoadTimeout() {
        String pageLoadTimeout = prop.getProperty("PageLoadTimeout", "30");
        Objects.requireNonNull(pageLoadTimeout, "Set property pageLoadTimeout in config");
        return Integer.parseInt(pageLoadTimeout);
    }

    public Session createMailSession() throws IOException {
        prop = new Properties();
        prop.load(ConfigFileReader.class.getClassLoader().getResourceAsStream("smtp.properties"));
        return Session.getDefaultInstance(prop, null);
    }

    public String getSmtpHost() {
        String smtpHost = prop.getProperty("SmtpHost");
        Objects.requireNonNull(smtpHost, "Set property SmtpHost in config");
        return smtpHost;
    }

    public String getSmtpProtocol() {
        String smtpProtocol = prop.getProperty("SmtpProtocol");
        Objects.requireNonNull(smtpProtocol, "Set property SmtpProtocol is config");
        return smtpProtocol;
    }

}
