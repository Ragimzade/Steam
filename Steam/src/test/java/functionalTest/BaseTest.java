package functionalTest;

import browser.Browser;
import browser.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import steam.pages.MainPage;
import steam.pages.SteamDownloadPage;
import utils.ConfigFileReader;
import utils.Log;

import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTest {
    private Log log = Log.getInstance();
    protected MainPage mainPage;
    ConfigFileReader config;
    SteamDownloadPage steamDownloadPage;
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance(config.getBrowser());
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
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            Screenshot.takeScreenshot(driver);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(ITestResult result, Method m) throws IOException {

        log.info("Stop test " + m.getName());
    }
}
