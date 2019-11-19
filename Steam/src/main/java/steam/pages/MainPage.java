package steam.pages;

import elements.Button;
import org.openqa.selenium.By;


public class MainPage extends PageBase {

    private final Button loginButton = new Button(By.className("global_action_link"), "LoginButton");
    private final Button steamButton = new Button(By.className("logo"), "steamButton");
    private final Button installSteamButton = new Button(By.className("header_installsteam_btn_content"), "installSteamButton");


    public MainPage() {
    }

    public void goToMainPage() {
        steamButton.click();
    }

    public SteamDownloadPage goToSteamDownloadPage() {
        installSteamButton.click();
        return new SteamDownloadPage();
    }


    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage();
    }
}
