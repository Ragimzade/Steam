package steam.model;


import lombok.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode

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

    public void compare(GameData game) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(game.getName(), this.name);
        softAssert.assertEquals(game.getPrice(), this.price, "Prices are not equal");
        softAssert.assertEquals(game.getDiscount(), this.discount, "Names of games are not equal");
        softAssert.assertEquals(game.getPlatforms(), this.platforms, "Platforms of games are not equal");

    }


}