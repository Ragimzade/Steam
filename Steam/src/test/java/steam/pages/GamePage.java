package steam.pages;

import base.BasePage;
import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import steam.model.GameData;
import steam.table_manager.Table;

import java.util.List;
import java.util.stream.Collectors;

public class GamePage extends BasePage {

    public GamePage() {
        assertPageIsOpened(communityHub);
    }

    private final TextArea gameTitle = new TextArea(By.className("apphub_AppName"), "gameTitle");
    private final TextArea discountArea = new TextArea(By
            .xpath("//div[@class='game_area_purchase_game']//div[contains(@class,'pct')]"), "discountArea");
    private final TextArea priceArea = new TextArea(By
            .xpath("//div[@class='game_purchase_action_bg']//div[contains(@class,'final') or contains(@class,'purchase_price')]"), "priceArea");
    private final Table platformsBlock = new Table(By.xpath("/div[@class='block responsive_apppage_details_left']"), "platforms");
    private final Button communityHub = new Button(By.className("apphub_OtherSiteInfo"), "communityHub");

    public GameData getGameData() {
        return new GameData(getGameName(), getGamePrice(), getGameDiscount(), getPlatforms());
    }

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
        try {
            log.info(String.format("'%s'Getting list of platforms", getClass()));
            List<WebElement> platforms = platformsBlock.getElements(
                    By.xpath("//div[contains(@class,'details_block vrsupport') and contains(.,'Input')]/preceding-sibling::div[contains(@class,'game_area')]"));
            return platforms.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (TimeoutException ex) {
            return null;
        }
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
