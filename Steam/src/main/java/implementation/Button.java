package implementation;

import model.IButton;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends BaseControl implements IButton {


    public Button(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    private static final Logger log = Logger.getLogger(Button.class);

    public boolean isClickable(int timeout) {

        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
            log.info(String.format("Button '%s' is clickable", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("Button '%s' is NOT clickable", name));
            return false;
        }
    }

    public void click() {
        log.info(String.format("Clicking button '%s'", name));
        if (isClickable(1)) {
            getElement(locator).click();
            log.info("success");
        } else {
            log.error(String.format("Buttoun '%s' is not clickable", name));
        }
    }

    public boolean isButtonOnPage() {
        return isElementPresent(3);
    }

    public void sendKeys() {

    }

}
