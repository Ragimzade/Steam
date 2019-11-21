package elements;

import browser.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.Log;

import java.time.Duration;
import java.util.List;

public abstract class BaseWebElement {
    private FluentWait wait;
    private static final int WAIT_DURATION_IN_SEC = 7;
    private static final int WAIT_DURATION_IN_MILL = 100;
    static final Log log = Log.getInstance();
    WebDriver driver;
    String name;
    By locator;

    BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        driver = Browser.getInstance();
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(WAIT_DURATION_IN_SEC))
                .pollingEvery(Duration.ofMillis(WAIT_DURATION_IN_MILL))
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

    boolean isElementPresent() {
        try {
            log.info(String.format("Waiting for presence of '%s' element", name));
            waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
            log.info(String.format("Element '%s' present ", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("waited for '%s' seconds '%s'", WAIT_DURATION_IN_SEC, name));
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
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(locator)).perform();
    }

    public void scrollToMiddle() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)");
    }

    public void scrollToElement(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", webElement);
    }

}


