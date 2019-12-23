package kaspersky.enums;

public enum NavigationMenuTabs {
    Summary("Summary"),
    Devices("Devices"),
    Licenses("Licenses"),
    Downloads("Downloads"),
    Store("Store");

    private String tab;

    NavigationMenuTabs(String category) {
        this.tab = category;
    }

    public String getTab() {
        return tab;
    }
}


