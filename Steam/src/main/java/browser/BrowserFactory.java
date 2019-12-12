package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    private BrowserFactory() {
    }

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
