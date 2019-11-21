package steam.pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;


public class MainPage extends PageBase {

    private final Button loginButton = new Button(By.className("global_action_link"), "LoginButton");
    private final Button steamButton = new Button(By.className("logo"), "steamButton");
    private final Button installSteamButton = new Button(By.className("header_installsteam_btn_content"), "installSteamButton");
    private final Button virtualRealityButton = new Button(By.xpath("//a[@class='popup_menu_item' and contains(.,'Virtual')]"), "virtualRealityButton");
    private final Button gamesTabButton = new Button(By.id("genre_tab"), "gamesTabButton");
    private final TextField searchField = new TextField(By.name("term"), "searchField");
    private final Button searchButton = new Button(By.xpath("//a[@id='store_search_link']//img"), "searchButton");
    private final Button searchSuggest = new Button(By.xpath("//div[@id='searchterm_options'] //div//div//a"), "searchSuggest");


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

    public void openGamesTab() {
        gamesTabButton.hoverButton();
    }

    public VirtualRealityPage goToVirtualRealityPage() {
        virtualRealityButton.click();
        return new VirtualRealityPage();
    }

    public GamePage findGame(String value) {
        searchField.typeValue(value);
        searchSuggest.click();
        return new GamePage();
    }
}
