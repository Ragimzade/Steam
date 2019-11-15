package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigFileReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BrowserFactory {

    private static WebDriver driver;
    private static BrowserFactory instance;
    private static ConfigFileReader config= ConfigFileReader.getInstance();;


    private BrowserFactory() {
    }

    public static WebDriver initDriver(String browser) {
        if (browser.equals("BrowserType.FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            profile.setPreference("browser.download.dir", config.getDownloadPath());
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setProfile(profile);
            return new FirefoxDriver(firefoxOptions);
        } else if (browser.equals("BrowserType.CHROME")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--safebrowsing-disable-download-protection");
            chromeOptions.addArguments("--allow-unchecked-dangerous-downloads[3]");
            chromeOptions.addArguments("--safebrowsing-disable-extension-blacklist");
            chromeOptions.addArguments("disable-infobars");
            chromeOptions.addArguments("--safebrowsing-disable");
            // chromeOptions.addArguments("download.default_directory", downloadFilepath);
            chromeOptions.setCapability(org.openqa.selenium.chrome.ChromeOptions.CAPABILITY, chromeOptions);
            chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            chromeOptions.setAcceptInsecureCerts(true);


            return new ChromeDriver(chromeOptions);


        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        throw new RuntimeException();
    }
}
