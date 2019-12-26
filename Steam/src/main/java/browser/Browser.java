package browser;

import base.BaseEntity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static browser.BrowserFactory.initDriver;

public class Browser extends BaseEntity {
    private static volatile WebDriver driver;

    private Browser() {
    }

    public static WebDriver getDriver() {
        WebDriver localInstance = driver;
        if (localInstance == null) {
            synchronized (WebDriver.class) {
                localInstance = driver;
                if (localInstance == null) {
                    driver = localInstance = initDriver(config.getBrowser());
                }
            }
        }
        return localInstance;
    }

    /**
     * Quit the browser
     */
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void waitForPageLoaded() {
        log.info("Waiting for page to load");
        new WebDriverWait(driver, config.getPageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void scrollToMiddle() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(String.format("window.scrollBy(0, %s)", Browser.getWindowSize() / 2));
    }

    public static int getWindowSize() {
        return (driver.manage().window().getSize().getHeight());
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
