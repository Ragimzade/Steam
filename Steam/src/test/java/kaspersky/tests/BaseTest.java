package kaspersky.tests;

import base_entity.BaseEntity;
import browser.Browser;
import browser.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;

import java.lang.reflect.Method;

public class BaseTest extends BaseEntity {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance();
        Browser.openKasperskyBaseUrl();
    }

    @AfterSuite
    public void tearDown() {
        Browser.quit();
    }



    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m) {
        log.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            Screenshot.takeScreenshot(driver);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(ITestResult result, Method m) {
        log.info("Stop test " + m.getName());
    }
}
