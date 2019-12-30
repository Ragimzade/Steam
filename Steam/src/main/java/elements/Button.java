package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Class describes webElement button
 */
public class Button extends BaseWebElement {

    /**
     * Constructor
     *
     * @param locator Button locator
     * @param name    Button name
     */
    public Button(By locator, String name) {
        super(locator, name);
    }

    /**
     * Clicks the button
     *
     * @see BaseWebElement#waitForElementClickable()
     */
    public void click() {
        waitForElementClickable();
        clickWithAttempts();
    }

    public void scrollAndClick() {
        scrollToElement();
        waitForElementClickable();
        clickWithAttempts();
    }

    /**
     * Clicks the button by visible text
     *
     * @see BaseWebElement#findElementByText(String, By)
     */
    public void clickByVisibleText(String text) {
        log.info(String.format("Clicking button '%s'", name));
        findElementByText(text, locator).click();
    }

    /**
     * Clicks the button with several attempts
     *
     * @see BaseWebElement#getElement(By)
     */
    private void clickWithAttempts() {
        int attempts = 0;
        while (attempts < 4) {
            try {
                getElement(locator).click();
                return;
            } catch (StaleElementReferenceException e) {
                log.info("StaleException. Attempt: " + attempts);
            }
            attempts++;
        }
    }
}
