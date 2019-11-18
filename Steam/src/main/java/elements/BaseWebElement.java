package elements;

import browser.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.ConfigFileReader;
import utils.Log;

import java.time.Duration;

public abstract class BaseWebElement {
    protected FluentWait wait;
    private static final int WAIT_DURATION_IN_SEC = 7;
    private static final int WAIT_DURATION_IN_MILL = 100;
    private static final Log log = Log.getInstance();
    private ConfigFileReader config = ConfigFileReader.getInstance();
    protected WebDriver driver;
    protected String name;
    protected By locator;

    public BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        driver = Browser.getInstance(config.getBrowser("Chrome"));
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(WAIT_DURATION_IN_SEC)).pollingEvery(Duration.ofMillis(WAIT_DURATION_IN_MILL))
                .ignoring(NoSuchElementException.class);
    }

    WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    boolean isElementPresent() {
        try {
            log.info(String.format("Waiting for presence of '%s' element", name));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            log.info(String.format("Element '%s' present ", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("waited for '%s' seclonds '%s'", WAIT_DURATION_IN_SEC, name));
            return false;
        }
    }
}
