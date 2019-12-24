package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends BaseWebElement {

    public DropDown(By locator, String name) {
        super(locator, name);
    }

    public void selectByText(String text) {
        waitForPresent();
        log.info(String.format("Selecting value with text '%s'", text));
        new Select(getElement(locator)).selectByVisibleText(text);
    }
}
