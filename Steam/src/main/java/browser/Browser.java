package browser;

import base_entity.BaseEntity;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import static browser.BrowserFactory.initDriver;

public class Browser extends BaseEntity {
    private static WebDriver driver;
    
    private Browser() {
    }

    public static synchronized WebDriver getInstance() {
        if (driver == null) {
            driver = initDriver(config.getBrowser());
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void openKasperskyBaseUrl() {
        driver.get(config.getKasperskyBaseUrl());
    }

    public static int getWindowSize() {
        return (driver.manage().window().getSize().getHeight()) / 2;
    }

    public static void openBaseUrl() {
        driver.get(config.getBaseUrl());
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
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
