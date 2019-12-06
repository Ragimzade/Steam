package steam.functional_tests;

import browser.Browser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steam.model.GameData;
import steam.pages.*;
import utils.DownloadUtils;
import utils.JsonParser;

import java.io.IOException;
import java.util.List;

public class SteamTest extends BaseTest {
    private MainPage mainPage;

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"Steam Controller Friendly"}, {"Virtual Reality"}, {"Action"}};
    }

    @BeforeMethod(alwaysRun = true)
    public void goToMainPage() {
        mainPage = new MainPage();
        mainPage.goToMainPage();
    }

    @Test
    public void loginTest() throws IOException, ParseException {
        mainPage.selectEnglishLanguage();
        LoginPage loginPage = mainPage.goToLoginPage();
        loginPage.signIn(JsonParser.getLogin(), JsonParser.getPassword());
        Assert.assertTrue(loginPage.isMessageButtonPresent());
    }

    @Test
    public void downloadSteamTest() throws IOException {
        SteamDownloadPage steamDownloadPage = mainPage.goToSteamDownloadPage();
        steamDownloadPage.downloadSteam();
        Assert.assertTrue(DownloadUtils.isFileDownloaded("SteamSetup.exe"));
    }

    @Test(priority = 1, dataProvider = "data-provider")
    public void gameSearchTest(String data) {
        SoftAssert softAssert = new SoftAssert();
        GameCategoryPage gcPage = mainPage.goToCategoryByVisibleText(data);
        List<GameData> games = gcPage.getSeveralGameData(3);
        for (int i = 0; i < 3; i++) {
            mainPage.goToCategoryByVisibleText(data);
            GamePage gamePage = gcPage.goToGamePage(i);
            String firstGamePage = Browser.getCurrentUrl();
            games.get(i).compare(softAssert, games, i, gamePage);
            mainPage.findGame(games.get(i).getName());
            games.get(i).compare(softAssert, games, i, gamePage);
            String firstGameBySearchPage = Browser.getCurrentUrl();
            softAssert.assertEquals(firstGamePage, firstGameBySearchPage);
        }
    }

}