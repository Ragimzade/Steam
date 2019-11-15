package elements;

import browser.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;

public abstract class BaseWebElement {
    protected WebDriverWait wait;
    private static final int WAIT_TIME = 5;
    private static final Logger log = Logger.getLogger(Button.class);
    private ConfigFileReader config = ConfigFileReader.getInstance();
    protected WebDriver driver;
    protected String name;
    protected By locator;

    public BaseWebElement(By locator, String name) {
        this.name = name;
        this.locator = locator;
        driver = Browser.getInstance(config.getBrowser("FireFox"));
        wait = new WebDriverWait(driver, WAIT_TIME);
    }

    protected WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    protected boolean isElementPresent(int timeOut) {
        try {
            log.info(String.format("Waiting for presence of '%s' element", name));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
            log.info(String.format("Element '%s' present ", name));
            return true;
        } catch (TimeoutException e) {
            log.info(String.format("waited for '%s' seclonds '%s'", timeOut, name));
            return false;
        }
    }
}
