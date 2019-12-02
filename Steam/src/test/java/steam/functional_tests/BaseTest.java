package steam.functional_tests;

import browser.Browser;
import browser.Screenshot;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import utils.Log;

import java.lang.reflect.Method;


public class BaseTest {
    private Log log = Log.getInstance();
    ConfigFileReader config;
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance();
        Browser.openBaseUrl();
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
            Screenshot.saveScreenshotPNG(driver);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void logTestStop(ITestResult result, Method m) {
        log.info("Stop test " + m.getName());
    }
}
