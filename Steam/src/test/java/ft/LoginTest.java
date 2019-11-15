package ft;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FileDownload;

public class LoginTest extends TestBase {


    //    @Test
//    public void loginTest() {
//        mainPage.goToMainPage();
//        mainPage.goToLoginPage();
//        loginPage.signIn(config.getLogin(), config.getPassword());
//        Assert.assertTrue(loginPage.isMessageButtonPresent());
//    }
////
    @Test
    public void downloadSteamTest() throws InterruptedException {
        FileDownload download = new FileDownload();
        mainPage.goToMainPage();
        mainPage.goToInstallPage();
        mainPage.downloadSteam();
        Assert.assertTrue(fileDownload.isFileDownloaded(config.getDownloadPath()));
    }
}
