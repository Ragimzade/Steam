package ft;

import browser.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;
import pages.MainPage;
import pages.SteamDownloadPage;
import utils.ConfigFileReader;

import java.lang.reflect.Method;

public class TestBase {
    private static final Logger logger = Logger.getLogger(TestBase.class);
    MainPage mainPage;
    LoginPage loginPage;
    ConfigFileReader config;
    WebDriver driver;
    SteamDownloadPage steamDownloadPage;
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        config = ConfigFileReader.getInstance();
        mainPage = new MainPage();
        loginPage = new LoginPage();
        mainPage.openBaseUrl();
        steamDownloadPage = new SteamDownloadPage();
    }


    @AfterSuite
    public void tearDown() {
        Browser.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m) {
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }
}
