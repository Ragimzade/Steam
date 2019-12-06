package steam.pages;

import browser.Browser;
import elements.Button;
import elements.TextArea;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;


public class MainPage extends PageBase {

    private final Button loginButton = new Button(By.className("global_action_link"), "LoginButton");
    private final Button steamButton = new Button(By.className("logo"), "steamButton");
    private final Button installSteamButton = new Button(By.className("header_installsteam_btn_content"), "installSteamButton");
    private final Button gameCategoryButton = new Button(By.xpath("//a[@class='popup_menu_item']"), "gameCategoryButton");
    private final Button gamesTabButton = new Button(By.id("genre_tab"), "gamesTabButton");
    private final TextField searchField = new TextField(By.name("term"), "searchField");
    private final Button searchButton = new Button(By.xpath("//a[@id='store_search_link']//img"), "searchButton");
    private final Button searchSuggest = new Button(By.xpath("//div[@id='search_suggestion_contents']//div[@class='match_name']"), "searchSuggest");
    private final Button languagesPopUp = new Button(By.xpath("//span[@id='language_pulldown']"), "languagesPopUp");
    private final Button englishButton = new Button(By.xpath("//a[@class='popup_menu_item tight' and contains(.,'English')]"), "englishButton");
    private final TextArea featuredTextArea = new TextArea(By.xpath("//div[@class='home_title' and contains(.,'Featured')]"), "featuredTextArea");
    private WebDriver driver = Browser.getInstance();

    public MainPage() {
        //   assertPageIsOpened(featuredTextArea);
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

    public GameCategoryPage goToCategoryByVisibleText(String categoryName) {
        openGamesTab();
        clickByVisibleText(categoryName);
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


    protected WebElement findElementByText(String text) {
        List<WebElement> webElements = driver.findElements(By.xpath("//div[@id='genre_flyout']//a[@class='popup_menu_item']"));
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    protected void clickByVisibleText(String text) {
        findElementByText(text).click();
    }

}
