package kaspersky.pages;

import base_entity.BaseEntity;
import browser.Browser;
import elements.BaseWebElement;
import org.openqa.selenium.WebDriver;
import utils.Log;

public class BasePage extends BaseEntity {
    WebDriver driver = Browser.getInstance();

    public BasePage() {
    }

    protected void assertPageIsOpened(BaseWebElement element) {

        if (element.isElementPresent()) {
            log.info(String.format("Page '%s' is opened", getClass().getName()));
        } else {
            log.error(String.format("Page '%s' is not opened", getClass().getName()));
            throw new AssertionError(element + " not found");
        }
    }
}
