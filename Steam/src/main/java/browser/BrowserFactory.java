package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Class for building browsers with specified type
 */
public class BrowserFactory {
    /**
     * Private constructor for not instantiating BrowserFactory
     */
    private BrowserFactory() {
    }

    /**
     * Initializes specified type of browser
     *
     * @param browsers type of browser
     * @return browser with options
     */
    public static WebDriver initDriver(String browsers) {
        WebDriver driver = null;
        switch (browsers) {
            case "FIREFOX":
                FirefoxOptions firefoxOptions = BrowserOptions.getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;
            case "CHROME":
                ChromeOptions chromeOptions = BrowserOptions.getChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
        }
        return driver;
    }
}
