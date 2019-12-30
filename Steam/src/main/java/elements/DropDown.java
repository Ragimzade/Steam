package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Class describes webElement drop-down
 */
public class DropDown extends BaseWebElement {

    /**
     * Constructor
     *
     * @param locator DropDown locator
     * @param name    DropDown name
     */
    public DropDown(By locator, String name) {
        super(locator, name);
    }

    /**
     * Selects the value of drop-down menu by matching text
     *
     * @param text text
     * @see BaseWebElement#waitForPresent()
     */
    public void selectByText(String text) {
        waitForPresent();
        log.info(String.format("Selecting value with text '%s'", text));
        new Select(getElement(locator)).selectByVisibleText(text);
    }
}
