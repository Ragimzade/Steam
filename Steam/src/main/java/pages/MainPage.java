package pages;

import browser.Browser;
import elements.Button;
import org.openqa.selenium.By;


public class MainPage extends PageBase {

    private final Button loginButton = new Button(By.className("global_action_link"), "LoginButton");
    private final Button steamButton = new Button(By.className("logo"), "steamButton");
    private final Button installSteamButton = new Button(By.className("header_installsteam_btn_content"), "installSteamButton");


    public MainPage() {
    }

    public void openBaseUrl() {
        Browser.navigateTo(config.getBaseUrl());
    }

    public void goToMainPage() {
        steamButton.click();
    }

    public void goToSteamDownloadPage() {
        installSteamButton.click();
    }



    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage();
    }
}
