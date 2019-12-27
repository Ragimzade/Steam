package base;

import browser.Browser;
import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Parent class of all page classes.
 * Describes methods that are common for all pages
 */
public class BasePage extends BaseEntity {
    /**
     * Base constructor
     */
    public BasePage() {
    }

    /**
     * Verifies if page opened using check for certain WebElement presence
     *
     * @param element WebElement which is expected to present on page
     * @throws AssertionError if page is not opened (if certain WebElement is not present)
     */
    protected void assertPageIsOpened(BaseWebElement element) {
        if (element.isElementPresent()) {
            log.info(String.format("Page '%s' is opened", getClass().getName()));
        } else {
            throw new AssertionError(element + " not found");
        }
    }

    /**
     * Finds an element
     *
     * @param locator element's locator
     * @return WebElement
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Finds list of WebElements
     *
     * @param locator element's locator
     * @return list of WebElements
     */
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Switches to iframe
     *
     * @param locator iframe's locator
     */
    protected void switchToiFrame(By locator) {
        driver.switchTo().frame(find(locator));
    }

    /**
     * Waits till page is loaded
     *
     * @see Browser#waitForPageLoaded()
     */
    protected void waitForPageIsLoaded() {
        Browser.waitForPageLoaded();
    }
}
