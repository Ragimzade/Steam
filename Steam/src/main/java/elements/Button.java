package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

import java.util.List;

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

    public String getText() {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info(String.format("TextArea '%s' is visible", name));
        log.info(String.format("Getting text from '%s' TextArea", name));
        return getElement(locator).getText();
    }

    public void clickByVisibleText(String text) {
        log.info(String.format("Clicking button '%s'", name));
        findElementByText(text, locator).click();
    }

}
