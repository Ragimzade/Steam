package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

public class Button extends BaseWebElement {

    public Button(By locator, String name) {
        super(locator, name);
    }

    private static final Log log = Log.getInstance();


    public void click() {
        log.info(String.format("Clicking button '%s'", name));
        waitForElementClickable();
        getElement(locator).click();
        log.info(String.format("Clicking button '%s': success", name));
    }

    public boolean isButtonOnPage() {
        try {
            isElementPresent();
            log.info(String.format("Button '%s' is on page", name));
            return true;
        } catch (NoSuchElementException | TimeoutException ex) {
            log.info(String.format("Button '%s' is absent", name));
            return false;
        }
    }

    public boolean waitForAbsentButton() {
        return waitForAbsent();
    }

    public void hoverButton() {
        hoverElement();
    }

    public String getText() {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info(String.format("TextArea '%s' is visible", name));
        log.info(String.format("Getting text from '%s' TextArea", name));
        return getElement(locator).getText();
    }

    public void clickByVisibleText(String text) {
        log.info(String.format("Clicking button '%s' by text '%s'", name, text));
        waitForElementClickable();
        findElementByText(text).click();
        log.info(String.format("Clicking button '%s'  by text '%s': success", name, text));
    }

}
