package browser;

import base.BaseEntity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static browser.BrowserFactory.initDriver;

public class Browser extends BaseEntity {
    private static volatile WebDriver driver;

    /**
     * Private constructor for not instantiating Browser
     */
    private Browser() {
    }

    /**
     * Creates and returns instance of WebDriver
     *
     * @return WebDriver
     */
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

    /**
     * Waits till page is loaded
     */
    public static void waitForPageLoaded() {
        log.info("Waiting for page to load");
        new WebDriverWait(driver, config.getPageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Scrolls the page to the middle
     */
    public static void scrollToMiddle() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(String.format("window.scrollBy(0, %s)", Browser.getWindowSize() / 2));
    }

    /**
     * @return Browser's windows size
     */
    public static int getWindowSize() {
        return (driver.manage().window().getSize().getHeight());
    }

    /**
     * Navigates to Base URL
     */
    public static void openBaseUrl() {
        driver.get(config.getBaseUrl());
    }

    /**
     * Navigates to specified URL
     *
     * @param url url to navigate to
     */
    public static void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * @return current URL
     */
    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Refreshes the page
     */
    public static void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Maximizes the window
     */
    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Accepts alert if present
     */
    public static void safeAlertAccept() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            log.info(e.getMessage());
        }
    }

}
