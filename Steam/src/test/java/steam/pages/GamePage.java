package steam.pages;

import elements.TextArea;
import org.openqa.selenium.By;

public class GamePage extends PageBase {

    private final TextArea gameTitle = new TextArea(By.className("apphub_AppName"), "gameTitle");
    private final TextArea discountArea = new TextArea(By.xpath("//div[@class='game_purchase_action_bg']//div[contains(@class,'pct')]"), "discountArea");
    private final TextArea priceArea = new TextArea(By.xpath("//div[@class='game_purchase_action_bg']//div[contains(@class,'final')]"), "priceArea");

    public String getGameName() {
        return gameTitle.getText();
    }

    public String getDiscount() {
        try {
            return discountArea.getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getGamePrice() {

        try {
            String price = priceArea.getText();
            return price.substring(0, price.length() - 4);
        } catch (Exception ex) {
            return null;
        }

    }

}
