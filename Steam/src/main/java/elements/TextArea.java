package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextArea extends BaseWebElement {

    public TextArea(By locator, String name) {
        super(locator, name);
    }

    public String getText() {
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info(String.format("TextArea '%s' is visible", name));
        String text = getElement(locator).getText();
        log.info(String.format("Getting text '%s' from '%s' TextArea", text, name));
        return text;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

}
