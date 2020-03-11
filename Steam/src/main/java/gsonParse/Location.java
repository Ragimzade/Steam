package gsonParse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Location {
    private String id;
    private List<AddressData> addresses;
    private List<String> images;
    private String timezone;

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class AddressData {
        private String line1;
        private City city;
        private State state;
        private Country country;
        private GPS gps;
        private String id;
        private boolean active;

        @ToString
        public static class State {
            private String name;

        }

        @ToString
        public static class City {
            private String name;
        }

        @ToString
        public static class Country {
            private String name;
        }

        @ToString
        public static class GPS {
            private List<Double> coordinates;
        }
    }
}
