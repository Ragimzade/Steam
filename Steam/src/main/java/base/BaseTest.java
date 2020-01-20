package base;

import browser.Browser;
import browser.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;

import java.lang.reflect.Method;

public class BaseTest extends BaseEntity {

    /**
     * Sets up browser, config and opens base url before each test suite
     */
    @BeforeSuite
    public void setUp() {
        config = ConfigFileReader.getInstance();
        driver = Browser.getDriver();
        Browser.openBaseUrl();
    }

    /**
     * Closes browser when test suite is finished
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Browser.quit();
    }

    /**
     * Logs test method name before test method start
     *
     * @param m method
     */
    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m) {
        log.info("Start test " + m.getName());
    }

    /**
     * Takes screenshots if test method is failed
     *
     * @param testResult test result
     * @see Screenshot#attachScreenshotToReport(WebDriver)
     * @see Screenshot#takeScreenshot(WebDriver)
     */
    @AfterMethod(alwaysRun = true)
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Screenshot.takeScreenshot(driver);
            Screenshot.attachScreenshotToReport(driver);
        }
    }

    /**
     * Logs test method name when test method is finished
     *
     * @param m method
     */
    @AfterMethod(alwaysRun = true)
    public void logTestStop(ITestResult result, Method m) {
        log.info("Stop test " + m.getName());
    }
}
