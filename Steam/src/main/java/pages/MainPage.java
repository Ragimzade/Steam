package pages;

import browser.Browser;
import implementation.Button;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;


public class MainPage extends PageBase {
    protected ConfigFileReader config = new ConfigFileReader();
    private static final Logger logger = Logger.getLogger(MainPage.class);

    private final Button loginButton = new Button(driver,By.className("global_action_link"), "LoginButton");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goToMainPage() {
        Browser.navigateTo(config.getBaseUrl());
    }

    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }
}
