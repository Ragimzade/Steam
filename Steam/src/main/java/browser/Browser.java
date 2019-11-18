package browser;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import static browser.BrowserFactory.initDriver;

public class Browser {
    private static WebDriver driver;

    private Browser() {
    }

    public static WebDriver getInstance(String browser) {
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

    public static void navigateTo(final String url) {
        driver.get(url);
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
