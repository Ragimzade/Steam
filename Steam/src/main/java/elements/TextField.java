package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class describes webElement text field
 */
public class TextField extends BaseWebElement {

    public TextField(By locator, String name) {
        super(locator, name);
    }

    /**
     * Types value into text field
     *
     * @param value value to type to
     * @see BaseWebElement#waitForCondition(ExpectedCondition)
     */
    public void typeValue(String value) {
        log.info(String.format("Setting text '%s' to '%s' TextField", value, name));
        waitForCondition(ExpectedConditions.elementToBeClickable(locator));
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

}
