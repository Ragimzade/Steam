package ft;

import browser.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class SteamTest extends TestBase {


    @Test
    public void loginTest() {
        mainPage.goToMainPage();
        mainPage.goToLoginPage();
        loginPage.signIn(config.getLogin(), config.getPassword());
        Assert.assertTrue(loginPage.isMessageButtonPresent());
    }

    @Test
    public void downloadSteamTest() {
        mainPage.goToMainPage();
        mainPage.goToSteamDownloadPage();
        steamDownloadPage.downloadSteam();


        Assert.assertTrue(steamDownloadPage.download(config.getDownloadPath()));


    }
}
