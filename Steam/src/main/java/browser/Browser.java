package browser;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;

import static browser.BrowserFactory.initDriver;

public class Browser {
    private static WebDriver driver;
    private static ConfigFileReader config = ConfigFileReader.getInstance();

    private Browser() {
    }

    public static synchronized WebDriver getInstance(String browser) {
        if (driver == null) {
            driver = initDriver(browser);
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

    public static void openBaseUrl() {
        driver.get(config.getBaseUrl());
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }

    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public static void safeAlertAccept() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {

        }
    }
}
