package steam.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import elements.DropDown;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import steam.model.GameData;
import steam.table_manager.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameCategoryPage extends BasePage {

    private static final String YEAR_OF_BIRTH = "1960";
    private static final int QUANTITY_OF_ROWS = 3;

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public GameCategoryPage() {
        assertPageIsOpened(newReleasesButton);
    }

    private final Table table = new Table(By.xpath("//div[@id='tab_content_NewReleases']"), "gameTable");
    private final Button newReleasesButton = new Button(By.id("tab_select_NewReleases"), "newReleasesButton");
    private final TextArea ageValidationArea = new TextArea(By.className("main_content_ctn"), "ageValidationArea");
    private final DropDown ageDropDown = new DropDown(By.name("ageYear"), "ageDropDown");
    private final Button viewPageButton = new Button(By.xpath("//span[contains(.,'View Page')]"), "viewPageButton");

    /**
     * Navigates to GamePage using serial number of the game
     *
     * @param numberOfGame serial number
     * @return instance of GamePage class
     */
    public GamePage goToGamePage(int numberOfGame) {
        table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame).click();
        validateAgeIfRequire(YEAR_OF_BIRTH);
        return new GamePage();
    }

    /**
     * Verifies the presence of age validation form.
     * Validates age if form present
     *
     * @param text age for validate
     */
    public void validateAgeIfRequire(String text) {
        if (ageValidationArea.isElementPresent()) {
            ageDropDown.selectByText(text);
            viewPageButton.click();
        }
    }

    /**
     * Creates instance of GameData class with defined fields
     *
     * @param numberOfGame serial number of the game
     * @return GameData instance
     */
    public GameData getGameData(int numberOfGame) {
        return new GameData(getName(numberOfGame), getPrice(numberOfGame),
                getDiscount(numberOfGame), getAllPlatforms(numberOfGame));
    }

    /**
     * Returns a list with specified quantity of the games
     *
     * @param quantityOfGames required quantity of the games
     * @return list with games
     */
    public List<GameData> getSeveralGameData(int quantityOfGames) {
        List<GameData> games = new ArrayList<>();
        for (int i = 0; i < quantityOfGames; i++) {
            games.add(getGameData(i));
        }
        return games;
    }

    /**
     * Gets name of the game
     *
     * @param numberOfGame serial number of the game
     * @return name
     */
    public String getName(int numberOfGame) {
        return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElement(By.xpath(".//div[@class='tab_item_name']")).getText();
    }

    /**
     * Gets price of the game
     *
     * @param numberOfGame serial number of the game
     * @return price
     */
    public String getPrice(int numberOfGame) {
        try {
            return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_final_price']")).getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    /**
     * Gets game's discount
     *
     * @param numberOfGame serial number of the game
     * @return discount if exists and null if doesn't
     */
    public String getDiscount(int numberOfGame) {
        try {
            return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_pct']")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Gets list of game's platforms
     *
     * @param numberOfGame serial number of the game
     * @return list with platforms
     */
    private List<String> getPlatforms(int numberOfGame) {
        log.info(String.format("'%s'Getting list of platforms", getClass().getName()));
        List<WebElement> platforms = table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElements(By.xpath(".//div[@class='tab_item_details']//span[@title]"));
        return platforms.stream()
                .map(p -> p.getAttribute("title"))
                .collect(Collectors.toList());
    }

    /**
     * Gets list of platforms and adds to list windows platform if exists
     *
     * @param numberOfGame serial number of the game
     * @return list of platforms
     * @see #getPlatforms(int)
     */
    public List<String> getAllPlatforms(int numberOfGame) {
        List<String> allPlatform = getPlatforms(numberOfGame);
        String winPlatform = table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElement(By.xpath(".//span[@class='platform_img win']")).getAttribute("class");
        allPlatform.add(winPlatform);
        return allPlatform;
    }

}

