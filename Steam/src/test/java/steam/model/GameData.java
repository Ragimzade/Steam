package steam.model;


import lombok.Data;

import java.util.List;
import java.util.Objects;

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

}