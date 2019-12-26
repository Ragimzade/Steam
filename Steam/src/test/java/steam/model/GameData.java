package steam.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class GameData {
    public String name;
    public String price;
    public String discount;
    public List<String> platforms;

    public void compare(GameData game) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(game.getName(), this.name,
                String.format("Names are different %s %s", game.getName(), this.name));
        softAssert.assertEquals(game.getPrice(), this.price,
                String.format("Prices are not equal %s %s ", game.getPrice(), this.price));
        softAssert.assertEquals(game.getDiscount(), this.discount,
                String.format("Discounts of games are not equal %s %s ", game.getDiscount(), this.discount));
        softAssert.assertEquals(game.getPlatforms(), this.platforms,
                String.format("Platforms of game %s are not equal %s %s", game.getName(), game.getPlatforms(), this.platforms));
        softAssert.assertAll();
    }
}