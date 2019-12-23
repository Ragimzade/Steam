package kaspersky.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NavigationMenuTabs {
    Summary("Summary"),
    Devices("Devices"),
    Licenses("Licenses"),
    Downloads("Downloads"),
    Store("Store");

    private String tab;
}


