package steam.pages;

import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VirtualRealityPage extends PageBase {

    private final Table table = new Table(By.xpath("//div[@id='tab_content_NewReleases']"), "gameTable");

    public GamePage goToGamePage(int numberOfGame) {
        table.getRows().get(numberOfGame).click();
        return new GamePage();
    }

    public List<String> getPlatforms(int numberOfGame) {
        log.info(String.format("'%s'Getting list of platforms", getClass()));
        List<WebElement> list = table.getRows().get(numberOfGame)
                .findElements(By.xpath(".//div[@class='tab_item_details']//span[@title]"));
        List<String> platforms = new ArrayList<>();
        for (WebElement element : list) {
            platforms.add(element.getAttribute("title"));
        }
        log.info(String.format("Platforms: '%s'", platforms));
        return platforms;
    }


    public String getName(int numberOfGame) {
        return table.getRows().get(numberOfGame).findElement(By.xpath(".//div[@class='tab_item_name']")).getText();
    }

    public String getPrice(int numberOfGame) {
        try {
            return table.getRows().get(numberOfGame).findElement(By.xpath(".//div[@class='discount_final_price']")).getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getDiscount(int numberOfGame) {
        try {
            return table.getRows().get(numberOfGame).findElement(By.xpath(".//div[@class='discount_pct']")).getText();
        } catch (Exception e) {
            return null;
        }
    }
}
