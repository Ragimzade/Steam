package elements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.Log;

import java.time.Duration;

public class Button extends BaseWebElement {

    public Button(By locator, String name) {
        super(locator, name);
    }

    private static final Log log = Log.getInstance();

    public boolean isClickable() {
        try {
            log.info(String.format("Button '%s' is clickable", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("Button '%s' is NOT clickable", name));
            return false;
        }
    }

    public void click() {

        log.info(String.format("Clicking button '%s'", name));
        if (isClickable()) {
            getElement(locator).click();
            log.info(String.format("Clicking button '%s': success", name));
        } else {
            log.error(String.format("Button '%s' is not clickable", name));
        }
    }

    public boolean isButtonOnPage() {
        return isElementPresent();
    }



}
