package elements;

import browser.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;
import utils.Log;

import java.time.Duration;
import java.util.List;

public abstract class BaseWebElement {
    private FluentWait wait;
    private static ConfigFileReader config;
    static final Log log = Log.getInstance();
    WebDriver driver;
    String name;
    By locator;

    BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance();
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(config.getFluentWaitInSec()))
                .pollingEvery(Duration.ofMillis(config.getFluentWaitInMill()))
                .ignoring(NoSuchElementException.class);
    }




    void waitForConditions(ExpectedCondition<List<WebElement>> conditions) {
        wait.until(conditions);
    }

    void waitForCondition(ExpectedCondition<WebElement> conditions) {
        wait.until(conditions);
    }

    WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    protected boolean isElementPresent() {
        try {
            log.info(String.format("Waiting for presence of '%s' element", name));
            waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
            log.info(String.format("Element '%s' present ", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("Waited for '%s' seconds '%s'", config.getFluentWaitInSec(), name));
            return false;
        }
    }

    boolean waitForAbsent() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info(String.format("Element '%s' absent ", name));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    void hoverElement() {
        log.info(String.format("Hovering element '%s' ", name));
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(locator)).perform();
    }

    protected void scrollToMiddle() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,600)");
    }

    void scrollToElement(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", webElement);
    }


    void waitForPageLoaded() {
        new WebDriverWait(driver, config.getPageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}


