package steam.pages;

import elements.Table;
import org.openqa.selenium.By;

public class VirtualRealityPage extends PageBase {

    private final Table table = new Table(By.xpath("//div[@id='tab_content_NewReleases']"), "gameTable");


    public GamePage goToGamePage(int numberOfGame) {
        table.getRows().get(numberOfGame).click();
        return new GamePage();
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
