package steam.pages;

import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.table_manager.Table;

import java.util.ArrayList;
import java.util.List;

public class GamePage extends PageBase {
    public GamePage() {
        assertPageIsOpened(communityHub);
    }

    private final TextArea gameTitle = new TextArea(By.className("apphub_AppName"), "gameTitle");
    private final TextArea discountArea = new TextArea(By
            .xpath("//div[@class='game_purchase_action_bg']//div[contains(@class,'pct')]"), "discountArea");
    private final TextArea priceArea = new TextArea(By
            .xpath("//div[@class='game_purchase_action_bg']//div[contains(@class,'final') or contains(@class,'purchase_price')]"), "priceArea");
    private final Table platformsBlock = new Table(By.xpath("/div[@class='block responsive_apppage_details_left']"), "platforms");
    private final Button communityHub = new Button(By.className("apphub_OtherSiteInfo"), "communityHub");

    public String getGameName() {
        return gameTitle.getText();
    }

    public String getGameDiscount() {
        try {
            return discountArea.getText();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<String> getPlatforms() {
        log.info(String.format("'%s'Getting list of platforms", getClass()));
        List<WebElement> platforms = platformsBlock.getElements(
                By.xpath("(//div[@class='block responsive_apppage_details_left'])//a[@class='name' and contains(@href,'support')]"));
        List<String> platformsToString = new ArrayList<>();
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).getText().equals("HTC Vive")) {
                platformsToString.add(platforms.get(i).getText());
            }
            if (platforms.get(i).getText().equals("Valve Index")) {
                platformsToString.add(platforms.get(i).getText());
            }
            if (platforms.get(i).getText().equals("Oculus Rift")) {
                platformsToString.add(platforms.get(i).getText());
            }
            if (platforms.get(i).getText().equals("Windows Mixed Reality")) {
                platformsToString.add(platforms.get(i).getText());
            }
            log.info(String.format("Platforms: '%s'", platformsToString));
        }
        return platformsToString;

    }

    public String getGamePrice() {
        try {
            String price = priceArea.getText();
            if (!price.equals("Free")) {
                return price.substring(0, price.length() - 4);
            } else return price;
        } catch (Exception ex) {
            return null;
        }
    }

}
