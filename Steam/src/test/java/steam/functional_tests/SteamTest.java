package steam.functional_tests;

import browser.Browser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.model.GameData;
import steam.pages.*;
import utils.DownloadUtils;
import utils.JsonParser;

import java.io.IOException;

public class SteamTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToMainPage() {
        mainPage = new MainPage();
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
        Assert.assertTrue(DownloadUtils.isFileDownloaded());
    }

    @Test
    public void gameSearchTest() {
        mainPage.openGamesTab();
        VirtualRealityPage vrPage = mainPage.goToVirtualRealityPage();
        System.out.println(vrPage.getPlatforms(0));
        GameData firstGame = new GameData()
                .setName(vrPage.getName(0))
                .setDiscount(vrPage.getDiscount(0))
                .setPrice(vrPage.getPrice(0))
                .setPlatforms(vrPage.getPlatforms(0));
        GameData secondGame = new GameData()
                .setName(vrPage.getName(1))
                .setDiscount(vrPage.getDiscount(1))
                .setPrice(vrPage.getPrice(1))
                .setPlatforms(vrPage.getPlatforms(1));
        GameData thirdGame = new GameData()
                .setName(vrPage.getName(2))
                .setDiscount(vrPage.getDiscount(2))
                .setPrice(vrPage.getPrice(2))
                .setPlatforms(vrPage.getPlatforms(2));

        GamePage gamePage = vrPage.goToGamePage(0);
        String firstGamePage = Browser.getCurrentUrl();
        Assert.assertEquals(gamePage.getGameName(), firstGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), firstGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), firstGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),firstGame.getPlatforms());
        mainPage.findGame(firstGame.getName());
        Assert.assertEquals(gamePage.getGameName(), firstGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), firstGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), firstGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),(firstGame.getPlatforms()));
        String firstGameBySearchPage = Browser.getCurrentUrl();
        Assert.assertEquals(firstGamePage, firstGameBySearchPage);

        mainPage.openGamesTab();
        mainPage.goToVirtualRealityPage();
        vrPage.goToGamePage(1);
        String secondGamePage = Browser.getCurrentUrl();
        Assert.assertEquals(gamePage.getGameName(), secondGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), secondGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), secondGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),(secondGame.getPlatforms()));
        mainPage.findGame(secondGame.getName());
        Assert.assertEquals(gamePage.getGameName(), secondGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), secondGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), secondGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),(secondGame.getPlatforms()));
        String secondGameSearchPage = Browser.getCurrentUrl();
        Assert.assertEquals(secondGamePage, secondGameSearchPage);

        mainPage.openGamesTab();
        mainPage.goToVirtualRealityPage();
        vrPage.goToGamePage(2);
        String thirdGamePage = Browser.getCurrentUrl();
        Assert.assertEquals(gamePage.getGameName(), thirdGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), thirdGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), thirdGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),(thirdGame.getPlatforms()));
        mainPage.findGame(thirdGame.getName());
        Assert.assertEquals(gamePage.getGameName(), thirdGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), thirdGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), thirdGame.getPrice());
        Assert.assertEquals(gamePage.getPlatforms(),(thirdGame.getPlatforms()));
        String thirdGameSearchPage = Browser.getCurrentUrl();
        Assert.assertEquals(thirdGamePage, thirdGameSearchPage);
    }

}
