package steam.model;


import lombok.*;
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
        softAssert.assertEquals(game.getName(), this.name, "Names are different");
        softAssert.assertEquals(game.getPrice(), this.price, "Prices are not equal");
        softAssert.assertEquals(game.getDiscount(), this.discount, "Names of games are not equal");
        softAssert.assertEquals(game.getPlatforms(), this.platforms, "Platforms of games are not equal");
        softAssert.assertAll();
    }

}