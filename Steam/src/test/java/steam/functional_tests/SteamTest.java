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

    private static final int NUMBER_OF_GAMES = 3;

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{ {"Virtual Reality"}};
    }
//{"Steam Controller Friendly"},
    @BeforeMethod(alwaysRun = true)
    public void goToMainPage() {
        mainPage = new MainPage();
        mainPage.selectEnglishLanguage();
        mainPage.goToMainPage();
    }

    @Test
    public void loginTest() throws IOException, ParseException {
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

    @Test(dataProvider = "data-provider")
    public void gameSearchTest(String data) {
        SoftAssert softAssert = new SoftAssert();
        GameCategoryPage gcPage = mainPage.goToCategoryByVisibleText(data);
        List<GameData> games = gcPage.getSeveralGameData(3);

        GamePage gamePage = gcPage.goToGamePage(0);
        String firstGamePage = Browser.getCurrentUrl();
        softAssert.assertEquals(gamePage.getGameName(), games.get(0).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(0).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(0).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), games.get(0).getPlatforms());
        mainPage.findGame(games.get(0).getName());
        softAssert.assertEquals(gamePage.getGameName(), games.get(0).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(0).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(0).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), (games.get(0).getPlatforms()));
        String firstGameBySearchPage = Browser.getCurrentUrl();
        softAssert.assertEquals(firstGamePage, firstGameBySearchPage);

        mainPage.openGamesTab();
        mainPage.goToCategoryByVisibleText(data);
        gcPage.goToGamePage(1);
        String secondGamePage = Browser.getCurrentUrl();
        softAssert.assertEquals(gamePage.getGameName(), games.get(1).getName());
        softAssert.assertEquals(gamePage.getGameName(), games.get(1).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(1).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(1).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), (games.get(1).getPlatforms()));
        mainPage.findGame(games.get(1).getName());
        softAssert.assertEquals(gamePage.getGameName(), games.get(1).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(1).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(1).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), (games.get(1).getPlatforms()));
        String secondGameSearchPage = Browser.getCurrentUrl();
        softAssert.assertEquals(secondGamePage, secondGameSearchPage);

        mainPage.openGamesTab();
        mainPage.goToCategoryByVisibleText(data);
        gcPage.goToGamePage(2);
        String thirdGamePage = Browser.getCurrentUrl();
        softAssert.assertEquals(gamePage.getGameName(), games.get(2).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(2).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(2).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), (games.get(2).getPlatforms()));
        mainPage.findGame(games.get(2).getName());
        softAssert.assertEquals(gamePage.getGameName(), games.get(2).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(2).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(2).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), (games.get(2).getPlatforms()));
        String thirdGameSearchPage = Browser.getCurrentUrl();
        softAssert.assertEquals(thirdGamePage, thirdGameSearchPage);
        softAssert.assertAll();
    }

}
