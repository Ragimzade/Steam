package browser;

import base_entity.BaseEntity;
import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

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

    public static int getWindowSize() {
        return (driver.manage().window().getSize().getHeight()) / 2;
    }

    public static void openBaseUrl() {
        driver.get(config.getBaseUrl());
    }

    public static <T extends WebElement> void switchToFrame(T element) {
        driver.switchTo().frame(element);
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
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
