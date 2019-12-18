
package steam.functional_tests;

import base.BaseTest;
import browser.Browser;
import org.testng.annotations.BeforeSuite;
import utils.DownloadUtils;

public class BaseTestSteam extends BaseTest {

    @Override
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        DownloadUtils.deleteDirectory(config.getSteamFileName());
        driver = Browser.getInstance();
        Browser.openBaseUrl();
    }

}