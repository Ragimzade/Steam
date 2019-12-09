package steam.model;


import lombok.Data;
import org.testng.asserts.SoftAssert;
import steam.pages.GamePage;

import java.util.List;

@Data
public class GameData {
    private String name;
    private String price;
    private String discount;
    private List<String> platforms;

    public GameData setPrice(String price) {
        this.price = price;
        return this;
    }

    public GameData setPlatforms(List<String> platforms) {
        this.platforms = platforms;
        return this;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public GameData setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public GameData setName(String name) {
        this.name = name;
        return this;
    }

    public void compare(SoftAssert softAssert, List<GameData> games, int gameNumber, GamePage gamePage) {
        softAssert.assertEquals(gamePage.getGameName(), games.get(gameNumber).getName());
        softAssert.assertEquals(gamePage.getGameDiscount(), games.get(gameNumber).getDiscount());
        softAssert.assertEquals(gamePage.getGamePrice(), games.get(gameNumber).getPrice());
        softAssert.assertEquals(gamePage.getPlatforms(), games.get(gameNumber).getPlatforms());
    }

}