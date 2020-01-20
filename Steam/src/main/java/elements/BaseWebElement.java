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

/**
 * Abstract class. Describes base element on page
 * Contains methods that are common for all elements
 */
public abstract class BaseWebElement extends BaseEntity {
    private Wait<WebDriver> wait;
    protected String name;
    protected By locator;

    /**
     * Base constructor
     *
     * @param locator Element locator
     * @param name    Element name
     */
    protected BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        wait = new WebDriverWait(driver, config.getFluentWaitInSec())
                .pollingEvery(Duration.ofMillis(config.getFluentWaitInMill()))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Waits till the specified condition is met
     *
     * @param condition Condition we are waiting for
     * @param <T>       Object for waiting
     * @throws TimeoutException if the specified condition is not met
     */
    protected <T> void waitForCondition(ExpectedCondition<T> condition) {
        wait.until(condition);
    }

    /**
     * Returns WebElement using parameter locator
     *
     * @param locator Element locator
     * @return WebElement
     * @see #waitForPresent()
     */
    protected WebElement getElement(By locator) {
        waitForPresent();
        return driver.findElement(locator);
    }

    /**
     * Returns list of  WebElements using parameter locator
     *
     * @param locator Element locator
     * @return List<WebElement>
     */
    public List<WebElement> getElements(By locator) {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    /**
     * Finds the element using visible text
     *
     * @param text    Text
     * @param locator Element's locator
     * @return WebElement containing specified text
     * @throws NoSuchElementException if there is no matching element
     */
    protected WebElement findElementByText(String text, By locator) {
        List<WebElement> webElements = getElements(locator);
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    /**
     * Waits and verifies the presence of element
     *
     * @return true if present
     */
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

    /**
     * Waits till the element is absent
     *
     * @return true if absent
     */
    public boolean isElementAbsent() {
        try {
            waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info(String.format("Element '%s' absent ", name));
            return true;
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * Mouse over the element
     */
    public void hoverElement() {
        log.info(String.format("Hovering element '%s' ", name));
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(locator)).perform();
    }

    /**
     * Waits till element is clickable
     *
     * @throws TimeoutException if the specified element is not clickable
     * @see #waitForCondition(ExpectedCondition)
     */
    public void waitForElementClickable() {
        waitForCondition(ExpectedConditions.elementToBeClickable(locator));
        log.info(String.format("Button '%s' is clickable", name));

    }

    /**
     * Scrolls page to the specified element
     *
     * @param webElement element to scroll to
     */
    public void scrollToElement(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", webElement);
    }

    public void scrollToElement() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", getElement(locator));
    }

    /**
     * Waits till element is present
     *
     * @throws TimeoutException if element doesn't appear
     */
    public void waitForPresent() {
        log.info(String.format("Waiting for presence of element '%s' ", name));
        waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
        log.info(String.format("Element '%s' is present ", name));
    }

    /**
     * @return element's text(attribute value)
     */
    public String getAttributeText() {
        String text = getElement(locator).getAttribute("value");
        log.info(String.format("Getting text '%s' from '%s' element", text, name));
        return text;
    }

    /**
     * @return element's text (inner text)
     * @see #waitForCondition(ExpectedCondition)
     */
    public String getText() {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info(String.format("Element '%s' is visible", name));
        log.info(String.format("Getting text from '%s' element", name));
        return getElement(locator).getText();
    }

    /**
     * Returns the specified attribute's text
     *
     * @param attribute attribute from which to get value
     * @return the specified attribute's text
     */
    public String getAttribute(String attribute) {
        log.info(String.format("Getting attribute '%s' from '%s' element", attribute, name));
        return getElement(locator).getAttribute(attribute);
    }

    /**
     * @return element's name
     */
    public String getName() {
        return name;
    }
}


