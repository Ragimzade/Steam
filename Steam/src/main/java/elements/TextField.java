package elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class TextField extends BaseWebElement {

    private static final Logger log = Logger.getLogger(TextField.class);

    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void typeValue(String value) {
        log.info(String.format("Setting text '%s' to '%s' TextField", value, name));
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

    public String getText() {
        String text = getElement(locator).getAttribute("value");
        log.info(String.format("Getting text '%s' from '%s' TextField", text, name));
        return text;
    }
}
