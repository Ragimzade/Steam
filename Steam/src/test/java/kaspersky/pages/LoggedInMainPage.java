package kaspersky.pages;

import base.BasePage;
import elements.Button;
import kaspersky.enums.NavigationMenuTabs;
import org.openqa.selenium.By;

public class LoggedInMainPage extends BasePage {
    private static final String NAVIGATION_MENU = "//ul[@class='main-menu js-priority-menu is-priority']//li//a[contains(@href,'%s')]";
    private final Button notificationButton = new Button(By.xpath("//button[@class='w-notifications__bell js-notifications']"), "notificationButton");

    public LoggedInMainPage() {
        assertPageIsOpened(notificationButton);
    }

    public DownloadPage goToDownloadPage() {
        navigate(NavigationMenuTabs.Downloads);
        return new DownloadPage();
    }

    private void navigate(NavigationMenuTabs tab) {
        new Button(By.xpath(String.format(NAVIGATION_MENU, tab)), String.format("%s Menu Button", tab)).click();
    }


}
