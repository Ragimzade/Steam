package elements;

import org.openqa.selenium.By;

public class TextArea extends BaseWebElement {

    public TextArea(By locator, String name) {
        super(locator, name);
    }

    public String getText() {
        String text = getElement(locator).getText();
        log.info(String.format("Getting text '%s' from '%s' TextArea", text, name));
        return text;
    }

    public boolean waitForAbsentTextArea() {
        return waitForAbsent();
    }

}
