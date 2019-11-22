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
        log.info(String.format("Getting text from '%s' TextArea", name));
        return getElement(locator).getText();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

}
