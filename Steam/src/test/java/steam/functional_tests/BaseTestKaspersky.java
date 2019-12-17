
package steam.functional_tests;

import base.BaseTest;
import browser.Browser;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import utils.DownloadUtils;

public class BaseTestKaspersky extends BaseTest {

    @Override
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        DownloadUtils.deleteDirectory(config.getSteamFileName());
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance();
        Browser.openBaseUrl();
    }

}