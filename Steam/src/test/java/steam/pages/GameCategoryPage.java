package steam.pages;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.model.GameData;
import steam.table_manager.Table;

import java.util.ArrayList;
import java.util.List;

public class GameCategoryPage extends PageBase {

    public GameCategoryPage() {
        assertPageIsOpened(newReleasesButton);
    }

    private final Table table = new Table(By.xpath("//div[@id='tab_content_NewReleases']"), "gameTable");
    private final Button newReleasesButton = new Button(By.id("tab_select_NewReleases"), "newReleasesButton");

    public GamePage goToGamePage(int numberOfGame) {
        table.getSelectedRows(3).get(numberOfGame).click();
        return new GamePage();
    }

    public GameData getGameData(int numberOfGame) {
        return new GameData()
                .setName(getName(numberOfGame))
                .setPlatforms(getPlatforms(numberOfGame))
                .setDiscount(getDiscount(numberOfGame))
                .setPrice(getPrice(numberOfGame));
    }

    public List<GameData> getSeveralGameData(int quantityOfGames) {
        List<GameData> games = new ArrayList<>();
        for (int i = 0; i < quantityOfGames; i++) {
            games.add(getGameData(i));
        }
        return games;
    }

    public String getName(int numberOfGame) {
        return table.getSelectedRows(3).get(numberOfGame)
                .findElement(By.xpath(".//div[@class='tab_item_name']")).getText();
    }

    public String getPrice(int numberOfGame) {
        try {
            return table.getSelectedRows(3).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_final_price']")).getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getDiscount(int numberOfGame) {
        try {
            return table.getSelectedRows(3).get(numberOfGame)
                    .findElement(By.xpath(".//div[@class='discount_pct']")).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getPlatforms(int numberOfGame) {
        log.info(String.format("'%s'Getting list of platforms", getClass()));
        List<WebElement> list = table.getSelectedRows(3).get(numberOfGame)
                .findElements(By.xpath(".//div[@class='tab_item_details']//span[@title]"));
        List<String> platforms = new ArrayList<>();
        for (WebElement element : list) {
            platforms.add(element.getAttribute("title"));
        }
        log.info(String.format("Platforms: '%s'", platforms));
        return platforms;
    }
}