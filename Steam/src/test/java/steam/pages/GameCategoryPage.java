package steam.pages;

import base.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.model.GameData;
import steam.table_manager.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameCategoryPage extends BasePage {

    private static final int QUANTITY_OF_ROWS = 3;

    public GameCategoryPage() {
        assertPageIsOpened(newReleasesButton);
    }

    private final Table table = new Table(By.xpath("//div[@id='tab_content_NewReleases']"), "gameTable");
    private final Button newReleasesButton = new Button(By.id("tab_select_NewReleases"), "newReleasesButton");

    public GamePage goToGamePage(int numberOfGame) {
        table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame).click();
        return new GamePage();
    }

    public GameData getGameData(int numberOfGame) {
        return new GameData(getName(numberOfGame), getPrice(numberOfGame),
                getDiscount(numberOfGame), getAllPlatforms(numberOfGame));
    }

    public List<GameData> getSeveralGameData(int quantityOfGames) {
        List<GameData> games = new ArrayList<>();
        for (int i = 0; i < quantityOfGames; i++) {
            games.add(getGameData(i));
        }
        return games;
    }

    public String getName(int numberOfGame) {
        return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElement(By.xpath(".//div[@class='tab_item_name']")).getText();
    }

    public String getPrice(int numberOfGame) {
        try {
            return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_final_price']")).getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getDiscount(int numberOfGame) {
        try {
            return table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_pct']")).getText();
        } catch (Exception e) {
            return null;
        }
    }

    private List<String> getPlatforms(int numberOfGame) {
        log.info(String.format("'%s'Getting list of platforms", getClass().getName()));
        List<WebElement> platforms = table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElements(By.xpath(".//div[@class='tab_item_details']//span[@title]"));
        return platforms.stream()
                .map(p -> p.getAttribute("title"))
                .collect(Collectors.toList());
    }

    public List<String> getAllPlatforms(int numberOfGame) {
        List<String> allPlatform = getPlatforms(numberOfGame);
        String winPlatform = table.getSelectedRows(QUANTITY_OF_ROWS).get(numberOfGame)
                .findElement(By.xpath(".//span[@class='platform_img win']")).getAttribute("class");
        allPlatform.add(winPlatform);
        return allPlatform;
    }

}

