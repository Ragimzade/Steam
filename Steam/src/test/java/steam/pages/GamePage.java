package steam.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import steam.model.GameData;
import steam.table_manager.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamePage extends BasePage {
    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
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
    private final TextArea winTextAres = new TextArea(By.xpath("//span[@class='platform_img win']"), "winTextArea");
    private static final String FREE_GAME = "Free To Play";

    /**
     * Navigates to GamePage using serial number of the game
     *
     * @return instance of GamePage class
     */
    public GameData getGameData() {
        return new GameData(getGameName(), getGamePrice(), getGameDiscount(), getAllPlatforms());
    }

    /**
     * Gets name of the game
     *
     * @return name
     */
    public String getGameName() {
        return gameTitle.getText();
    }

    /**
     * Gets game's discount
     *
     * @return discount if exists and null if doesn't
     */
    public String getGameDiscount() {
        try {
            if (getGamePrice().equals(FREE_GAME)) {
                return null;
            }
            return discountArea.getText();
        } catch (TimeoutException ex) {
            return null;
        }
    }

    /**
     * Gets list game's of platforms
     *
     * @return list with platforms
     */
    private List<String> getPlatforms() {
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

    /**
     * Gets list of game's platforms and adds to list windows platform if exists
     *
     * @return list of platforms including windows platform
     */
    public List<String> getAllPlatforms() {
        List<String> allPlatform;
        if (getPlatforms() != null) {
            allPlatform = getPlatforms();
        } else {
            allPlatform = new ArrayList<>();
        }
        allPlatform.add(winTextAres.getAttribute("class"));
        return allPlatform;
    }

    /**
     * Gets price of the game
     *
     * @return price
     */
    public String getGamePrice() {
        String price = priceArea.getText();
        if (!price.equals("Free To Play")) {
            return price.replaceAll("[^0-9$.,]", "");
        } else return price;
    }

}
