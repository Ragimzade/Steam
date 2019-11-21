package steam.functional_tests;

import com.google.gson.internal.bind.util.ISO8601Utils;
import elements.Table;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.model.GameData;
import steam.pages.GamePage;
import steam.pages.MainPage;
import steam.pages.VirtualRealityPage;

public class SteamTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void goToMainPage() {
        mainPage = new MainPage();
        mainPage.goToMainPage();
    }


//    @Test
//    public void loginTest() throws IOException, ParseException {
//        LoginPage loginPage = mainPage.goToLoginPage();
//        loginPage.signIn(JsonParser.getLogin(), JsonParser.getPassword());
//        Assert.assertTrue(loginPage.isMessageButtonPresent());
//    }

//    @Test
//    public void downloadSteamTest() throws IOException {
//        SteamDownloadPage steamDownloadPage = mainPage.goToSteamDownloadPage();
//        steamDownloadPage.downloadSteam();
//        Assert.assertTrue(DownloadUtils.isFileDownloaded());
//
//    }

    @Test
    public void gameSearchTest() throws InterruptedException {
        mainPage.openGamesTab();
        VirtualRealityPage vrPage = mainPage.goToVirtualRealityPage();
        GameData firstGame = new GameData()
                .setName(vrPage.getName(0))
                .setDiscount(vrPage.getDiscount(0))
                .setPrice(vrPage.getPrice(0));
        GamePage gamePage = vrPage.goToGamePage(0);
        Assert.assertEquals(gamePage.getGameName(), firstGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), firstGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), firstGame.getPrice());
        mainPage.findGame(firstGame.getName());

        mainPage.openGamesTab();
        mainPage.goToVirtualRealityPage();
        GameData secondtGame = new GameData()
                .setName(vrPage.getName(1))
                .setDiscount(vrPage.getDiscount(1))
                .setPrice(vrPage.getPrice(1));
        vrPage.goToGamePage(1);
        Assert.assertEquals(gamePage.getGameName(), secondtGame.getName());
        Assert.assertEquals(gamePage.getDiscount(), secondtGame.getDiscount());
        Assert.assertEquals(gamePage.getGamePrice(), secondtGame.getPrice());
        mainPage.findGame(secondtGame.getName());


    }
}
