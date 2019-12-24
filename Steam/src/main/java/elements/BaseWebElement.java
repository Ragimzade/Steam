package elements;

import base.BaseEntity;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class BaseWebElement extends BaseEntity {
    private Wait<WebDriver> wait;
    protected String name;
    protected By locator;

    protected BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        wait = new WebDriverWait(driver, config.getFluentWaitInSec())
                .pollingEvery(Duration.ofMillis(config.getFluentWaitInMill()))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected void waitForConditions(ExpectedCondition<List<WebElement>> conditions) {
        wait.until(conditions);
    }

    protected <T extends Function> void waitForCondition(T condition) {
        wait.until(condition);
    }

    protected WebElement getElement(By locator) {
        waitForPresent();
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    protected WebElement findElementByText(String text, By locator) {
        List<WebElement> webElements = getElements(locator);
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    public boolean isElementPresent() {
        try {
            log.info(String.format("Waiting for presence of '%s' element", name));
            waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
            waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
            log.info(String.format("Element '%s' present ", name));
            return true;
        } catch (TimeoutException e) {
            log.info(e.getMessage());
            return false;
        }
    }

    boolean waitForAbsent() {
        try {
            waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info(String.format("Element '%s' absent ", name));
            return true;
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return false;
        }
    }

    void hoverElement() {
        log.info(String.format("Hovering element '%s' ", name));
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(locator)).perform();
    }

    void waitForElementClickable() {
        waitForCondition(ExpectedConditions.elementToBeClickable(locator));
        log.info(String.format("Button '%s' is clickable", name));
    }

    protected void scrollToElement(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", webElement);
    }

    protected void waitForPresent() {
        log.info(String.format("Waiting for presence of element '%s' ", name));
        waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
        log.info(String.format("Element '%s' is present ", name));
    }

    protected String getText() {
        String text = getElement(locator).getAttribute("value");
        log.info(String.format("Getting text '%s' from '%s' element", text, name));
        return text;
    }

    public String getAttribute(String attribute) {
        log.info(String.format("Getting attribute '%s' from '%s' element", attribute, name));
        return getElement(locator).getAttribute(attribute);
    }
}


