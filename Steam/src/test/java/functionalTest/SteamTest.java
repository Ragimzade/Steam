package functionalTest;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pages.LoginPage;
import steam.pages.MainPage;
import steam.pages.SteamDownloadPage;
import utils.DownloadUtils;
import utils.JsonParser;

import java.io.IOException;

public class SteamTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void sdfs() {
        mainPage = new MainPage();
        mainPage.goToMainPage();
    }


    @Test
    public void loginTest() throws IOException, ParseException {

        LoginPage loginPage = mainPage.goToLoginPage();
        loginPage.signIn(JsonParser.readLogin(), JsonParser.readPassword());

        Assert.assertTrue(loginPage.isMessageButtonPresent());
        loginPage.absentButton();
    }

    @Test
    public void downloadSteamTest() throws IOException {
        MainPage mainPage = new MainPage();
        mainPage.goToMainPage();
        SteamDownloadPage steamDownloadPage = mainPage.goToSteamDownloadPage();
        steamDownloadPage.downloadSteam();
        Assert.assertTrue(DownloadUtils.isFileDownloaded());


    }
}
