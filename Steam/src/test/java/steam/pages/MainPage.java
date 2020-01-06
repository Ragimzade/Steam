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

    /**
     * Base constructor
     */
    public MainPage() {
    }

    /**
     * Navigates to main page
     */
    public void goToMainPage() {
        steamButton.click();
    }

    /**
     * Navigates to Steam Download page
     *
     * @return instance of SteamDownloadPage class
     */
    public SteamDownloadPage goToSteamDownloadPage() {
        installSteamButton.click();
        return new SteamDownloadPage();
    }

    /**
     * Navigates to Login page
     *
     * @return instance of LoginPage class
     */
    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage();
    }

    /**
     * Opens games tab
     */
    private void openGamesTab() {
        gamesTabButton.hoverElement();
    }

    /**
     * Navigates to Game Category page by category name
     *
     * @param categoryName category name
     * @return instance of GameCategoryPage class
     */
    public GameCategoryPage goToCategoryByVisibleText(String categoryName) {
        openGamesTab();
        categoriesButton.clickByVisibleText(categoryName);
        return new GameCategoryPage();
    }

    /**
     * Find games using search filed by game's name
     *
     * @param value game's name
     * @return instance of GamePage class
     */
    public GamePage findGame(String value) {
        searchField.typeValue(value);
        searchSuggest.clickByVisibleText(value);
        return new GamePage();
    }

    /**
     * Verifies if english language is selected
     *
     * @return true if english language is selected
     */
    private boolean isEnglishLangSelected() {
        return languagesPopUp.getText().equals("language");
    }

    /**
     * Opens language pop-up
     */
    private void openLanguagesPopUp() {
        languagesPopUp.click();
    }

    /**
     * Verifies if english language is selected and selects it if not
     */
    public void selectEnglishLanguage() {
        if (!isEnglishLangSelected()) {
            openLanguagesPopUp();
            englishButton.click();
        }
    }
}
