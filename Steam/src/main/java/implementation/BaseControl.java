package implementation;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseControl {
    private static final Logger log = Logger.getLogger(Button.class);

    protected WebDriver driver;

    protected String name;

    protected By locator;

    public BaseControl(WebDriver driver, By locator, String name) {
        this.driver = driver;
        this.name = name;
        this.locator = locator;
    }

    protected WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    protected boolean isElementPresent(int timeOut) {
        try {
            log.info(String.format("Waiting for presence of '%s'", name));
            new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
            log.info(String.format("Success '%s'", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("waited for '%s' seclonds '%s'", timeOut, name));
            return false;
        }
    }
}
