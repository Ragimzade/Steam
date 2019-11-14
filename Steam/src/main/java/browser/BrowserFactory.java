package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

public class BrowserFactory {

    private static WebDriver driver;
    private static BrowserFactory instance;

    private BrowserFactory() {
    }

    public static WebDriver initDriver(String browser) {
        if (browser.equals("ff")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browser.equals("BrowserType.CHROME")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        throw new RuntimeException();
    }

    public static BrowserFactory getInstance() {
        if (instance == null) {
            instance = new BrowserFactory();
        }
        return instance;
    }

}
