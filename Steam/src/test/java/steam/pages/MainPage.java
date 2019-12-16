package steam.pages;

import base.BasePage;
import elements.Button;
import elements.TextArea;
import elements.TextField;
import org.openqa.selenium.By;


public class MainPage extends BasePage {

    private final Button loginButton = new Button(By.className("global_action_link"), "LoginButton");
    private final Button steamButton = new Button(By.className("logo"), "steamButton");
    private final Button installSteamButton = new Button(By.className("header_installsteam_btn_content"), "installSteamButton");
    private final Button gamesTabButton = new Button(By.id("genre_tab"), "gamesTabButton");
    private final TextField searchField = new TextField(By.name("term"), "searchField");
    private final Button searchSuggest = new Button(By.xpath("//div[@id='search_suggestion_contents']//div[@class='match_name']"), "searchSuggest");
    private final Button languagesPopUp = new Button(By.xpath("//span[@id='language_pulldown']"), "languagesPopUp");
    private final Button englishButton = new Button(By.xpath("//a[@class='popup_menu_item tight' and contains(.,'English')]"), "englishButton");
    private final TextArea featuredTextArea = new TextArea(By.xpath("//div[@class='home_title' and contains(.,'Featured')]"), "featuredTextArea");
    private final Button categoriesButton = new Button(By.xpath("//div[@id='genre_flyout']//a[@class='popup_menu_item']"), "categoriesButton");

    public MainPage() {
        //  assertPageIsOpened(featuredTextArea);
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

    private void openGamesTab() {
        gamesTabButton.hoverButton();
    }

    public GameCategoryPage goToCategoryByVisibleText(String categoryName) {
        openGamesTab();
        categoriesButton.clickByVisibleText(categoryName);
        return new GameCategoryPage();
    }

    public GamePage findGame(String value) {
        searchField.typeValue(value);
        searchSuggest.clickByVisibleText(value);
        return new GamePage();
    }

    private boolean isEnglishLangSelected() {
        return languagesPopUp.getText().equals("language");
    }

    private void openLanguagesPopUp() {
        languagesPopUp.click();
    }

    public void selectEnglishLanguage() {
        if (!isEnglishLangSelected()) {
            openLanguagesPopUp();
            englishButton.click();
        }
    }

}
