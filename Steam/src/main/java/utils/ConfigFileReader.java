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
        Objects.requireNonNull(browser, "browser is null");
        return browser;
    }

    public String getBaseUrl() {
        String url = prop.getProperty("BaseUrl", "https://steampowered.com/");
        Objects.requireNonNull(url, "url is null");
        return url;
    }

    public int getFluentWaitInSec() {
        String waitInSec = prop.getProperty("FluentWaitInSec","4");
        Objects.requireNonNull(waitInSec, "FluentWaitInSec is null");
        return Integer.parseInt(waitInSec);
    }

    public int getFluentWaitInMill() {
        String waitInMill = prop.getProperty("FluentWaitInMill","1000");
        Objects.requireNonNull(waitInMill, "FluentWaitInMill is null");
        return Integer.parseInt(waitInMill);
    }

    public String getBrowserDownloadPath() {
        String browserDownloadPath = prop.getProperty("BrowserDownloadPath");
        Objects.requireNonNull(browserDownloadPath, "BrowserDownloadPath is null");
        return browserDownloadPath;
    }

    public String getSteamFileName() {
        String steamFileName = prop.getProperty("SteamFileName");
        Objects.requireNonNull(steamFileName, "steamFileName is null");
        return steamFileName;
    }

    public int getPageLoadTimeout() {
        String pageLoadTimeout = prop.getProperty("PageLoadTimeout", "30");
        Objects.requireNonNull(pageLoadTimeout, "pageLoadTimeout is null");
        return Integer.parseInt(pageLoadTimeout);
    }

    public Session createMailSession() throws IOException {
        prop = new Properties();
        prop.load(ConfigFileReader.class.getClassLoader().getResourceAsStream("smtp.properties"));
        return Session.getDefaultInstance(prop, null);
    }

    public String getSmtpHost() {
        String smtpHost = prop.getProperty("SmtpHost");
        Objects.requireNonNull(smtpHost, "smtpHost in config");
        return smtpHost;
    }

    public String getSmtpProtocol() {
        String smtpProtocol = prop.getProperty("SmtpProtocol");
        Objects.requireNonNull(smtpProtocol, "SmtpProtocol is null");
        return smtpProtocol;
    }

}
