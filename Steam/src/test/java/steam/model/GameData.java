package steam.model;


import java.util.List;
import java.util.Objects;

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

    public String getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", platforms=" + platforms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameData gameData = (GameData) o;
        return Objects.equals(name, gameData.name) &&
                Objects.equals(price, gameData.price) &&
                Objects.equals(discount, gameData.discount) &&
                Objects.equals(platforms, gameData.platforms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, discount, platforms);
    }
}
