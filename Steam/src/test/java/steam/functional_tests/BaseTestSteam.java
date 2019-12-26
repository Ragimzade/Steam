
package steam.functional_tests;

import base.BaseTest;
import browser.Browser;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import utils.DownloadUtils;

public class BaseTestSteam extends BaseTest {
    protected static final String STEAM_FILE_NAME = "SteamSetup.exe";


    @Override
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        config = ConfigFileReader.getInstance();
        DownloadUtils.deleteDirectory(STEAM_FILE_NAME);
        driver = Browser.getDriver();
        Browser.openBaseUrl();
    }

}