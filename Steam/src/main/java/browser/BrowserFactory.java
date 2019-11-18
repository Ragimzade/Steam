package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigFileReader;

import java.util.HashMap;

public class BrowserFactory {


    private static ConfigFileReader config = ConfigFileReader.getInstance();


    private BrowserFactory() {
    }

    public static WebDriver initDriver(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                profile.setPreference("browser.download.dir", config.getDownloadPath());
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                HashMap<String, Object> chromePreferences = new HashMap<>();
                chromePreferences.put("profile.default_content_settings.popups", 0);
                chromePreferences.put("download.prompt_for_download", "false");
                chromePreferences.put("download.default_directory", config.getDownloadPath());
                chromePreferences.put("download.directory_upgrade", "true");
                chromePreferences.put("safebrowsing.enabled", "true");
                chromeOptions.setExperimentalOption("prefs", chromePreferences);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
        }
        return driver;
    }
}
