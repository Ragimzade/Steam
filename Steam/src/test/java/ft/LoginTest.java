package ft;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginTest() {
        mainPage.goToMainPage();
        mainPage.goToLoginPage();
        loginPage.signIn(config.getLogin("Login"),config.getPassword("Password"));
        Assert.assertTrue(loginPage.isMessageButtonPresent());
    }
}
