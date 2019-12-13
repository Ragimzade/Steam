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
import utils.JsonParse;

import java.io.IOException;
import java.util.List;

public class SteamTest extends BaseTest {
    private MainPage mainPage;

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"Steam Controller Friendly"}, {"Virtual Reality"}};
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
        loginPage.signIn(JsonParse.getLogin(), JsonParse.getPassword());
        Assert.assertTrue(loginPage.isMessageButtonPresent());
    }

    @Test
    public void downloadSteamTest() {
        SteamDownloadPage steamDownloadPage = mainPage.goToSteamDownloadPage();
        steamDownloadPage.downloadSteam();
        Assert.assertTrue(DownloadUtils.isFileDownloaded(config.getSteamFileName()));
    }

    @Test(dataProvider = "data-provider", dependsOnMethods = "loginTest")
    public void gameSearchTest(String data) {
        SoftAssert softAssert = new SoftAssert();
        GameCategoryPage gcPage = mainPage.goToCategoryByVisibleText(data);
        List<GameData> games = gcPage.getSeveralGameData(3);
        for (int i = 0; i < 3; i++) {
            mainPage.goToCategoryByVisibleText(data);
            GamePage gamePage = gcPage.goToGamePage(i);
            GameData game = gamePage.getGameData();
            String gamePageUrl = Browser.getCurrentUrl();
            games.get(i).compare(game);
            mainPage.findGame(games.get(i).getName());
            games.get(i).compare(game);
            String gameBySearchPageUrl = Browser.getCurrentUrl();
            softAssert.assertEquals(gamePageUrl, gameBySearchPageUrl,
                    "Urls of game page and game page by search are not equal");
        }
        softAssert.assertAll();
    }
}