package elements;

import base_entity.BaseEntity;
import browser.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class BaseWebElement extends BaseEntity {
    private FluentWait wait;
    protected String name;
    protected By locator;


    protected BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(config.getFluentWaitInSec()))
                .pollingEvery(Duration.ofMillis(config.getFluentWaitInMill()))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    <T extends Function> void waitForCondition(T condition) {
        wait.until(condition);
    }

    protected WebElement getElement(By locator) {
        waitForPresent();
        return driver.findElement(locator);
    }

    protected List<WebElement> getElements(By locator) {
        waitForPresent();
        return driver.findElements(locator);
    }

    protected WebElement findElementByText(String text) {
        List<WebElement> webElements = getElements(locator);
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    public boolean isElementPresent() {
        waitForPresent();
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

    protected boolean waitForAbsent() {
        try {
            waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info(String.format("Element '%s' absent ", name));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void hoverElement() {
        log.info(String.format("Hovering element '%s' ", name));
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(locator)).perform();
    }

    protected void waitForElementClickable() {
        waitForCondition(ExpectedConditions.elementToBeClickable(locator));
        log.info(String.format("Element '%s' is clickable", name));
    }

    protected void scrollToMiddle() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + Browser.getWindowSize() + ")");
    }

    protected void scrollToElement(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", webElement);
    }


    protected void waitForPageLoaded() {
        new WebDriverWait(driver, config.getPageLoadTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForPresent() {
        log.info(String.format("Waiting for presence of element '%s' ", name));
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info(String.format("Element '%s' is present ", name));
    }

    protected String getText() {
        String text = getElement(locator).getAttribute("value");
        log.info(String.format("Getting text '%s' from '%s' element", text, name));
        return text;
    }
}


