package base;

import browser.Browser;
import elements.BaseWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage extends BaseEntity {

    public BasePage() {
    }

    protected void assertPageIsOpened(BaseWebElement element) {

        if (element.isElementPresent()) {
            log.info(String.format("Page '%s' is opened", getClass().getName().substring(16)));
        } else {
            log.error(String.format("Page '%s' is not opened", getClass().getName()));
            throw new AssertionError(element + " not found");
        }
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void switchToiFrame(By locator) {
        driver.switchTo().frame(find(locator));
    }

    protected void waitForPageIsLoaded() {
        Browser.waitForPageLoaded();
    }
}
