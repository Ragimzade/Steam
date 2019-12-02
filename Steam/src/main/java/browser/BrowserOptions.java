package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.ConfigFileReader;

import java.util.HashMap;

public class BrowserOptions {

    private static ConfigFileReader config = ConfigFileReader.getInstance();

    static ChromeOptions getChromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.prompt_for_download", "false");
        chromePreferences.put("download.default_directory", System.getProperty("user.dir") + config.getBrowserDownloadPath());
        chromePreferences.put("download.directory_upgrade", "true");
        chromePreferences.put("safebrowsing.enabled", "true");
        chromeOptions.addArguments("--lang=eng");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setExperimentalOption("prefs", chromePreferences);

        return chromeOptions;
    }

    static FirefoxOptions getFirefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "eng");
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        profile.setPreference("browser.download.dir", System.getProperty("user.dir") + config.getBrowserDownloadPath());
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        return firefoxOptions;
    }
}
