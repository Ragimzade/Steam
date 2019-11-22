package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

public class Button extends BaseWebElement {

    public Button(By locator, String name) {
        super(locator, name);
    }

    private static final Log log = Log.getInstance();

    void waitForElementClickable() {
        waitForCondition(ExpectedConditions.elementToBeClickable(locator));
        log.info(String.format("Button '%s' is clickable", name));
    }

    public void click() {
        log.info(String.format("Clicking button '%s'", name));
        waitForElementClickable();
        getElement(locator).click();
        log.info(String.format("Clicking button '%s': success", name));
    }

    public boolean isButtonOnPage() {
        try {
            log.info(String.format("Waiting for presence of '%s' button", name));
            return isElementPresent();
        } catch (NoSuchElementException ex) {
            log.info(String.format("Button '%s' is not found on the page", name));
            return false;
        }
    }

    public boolean waitForAbsentButton() {
        return waitForAbsent();
    }

    public void hoverButton() {
        hoverElement();
    }
}