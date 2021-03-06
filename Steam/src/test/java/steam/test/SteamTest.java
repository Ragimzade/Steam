package steam.test;

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
import utils.TestData;

import java.io.IOException;
import java.util.List;

import static steam.test_data.Categories.ACTION;
import static steam.test_data.Categories.ADVENTURE;

public class SteamTest extends BaseTestSteam {
    private static final int QUANTITY_OF_GAMES = 3;
    private MainPage mainPage;

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{ADVENTURE}, {ACTION}};
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
        loginPage.signIn(TestData.getValue("login"), TestData.getValue("password"));
        Assert.assertTrue(loginPage.isMessageButtonPresent());
    }

    @Test
    public void downloadSteamTest() {
        SteamDownloadPage steamDownloadPage = mainPage.goToSteamDownloadPage();
        steamDownloadPage.downloadSteam();
        Assert.assertTrue(DownloadUtils.isFileDownloaded(STEAM_FILE_NAME));
    }

    @Test(dataProvider = "data-provider")
    public void gameSearchTest(String data) {
        SoftAssert softAssert = new SoftAssert();
        GameCategoryPage gcPage = mainPage.goToCategoryByVisibleText(data);
        List<GameData> games = gcPage.getSeveralGameData(QUANTITY_OF_GAMES);
        for (int i = 0; i < QUANTITY_OF_GAMES; i++) {
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