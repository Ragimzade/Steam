package implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextField extends BaseControl {
    public TextField(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    public void typeValue(String value) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }


}
