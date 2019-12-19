
package steam.functional_tests;

import base.BaseTest;
import browser.Browser;
import org.testng.annotations.BeforeSuite;

public class BaseTestSteam extends BaseTest {
    protected static final String STEAM_FILE_NAME = "SteamSetup.exe";


    @Override
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        driver = Browser.getDriver();
        Browser.openBaseUrl();
    }

}