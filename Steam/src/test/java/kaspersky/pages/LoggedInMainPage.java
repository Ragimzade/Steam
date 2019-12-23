package kaspersky.pages;

import base.BasePage;
import elements.Button;
import kaspersky.enums.NavigationMenuTabs;
import org.openqa.selenium.By;

public class LoggedInMainPage extends BasePage {
    private static final String NAVIGATION_MENU = "//ul[@class='main-menu js-priority-menu is-priority']//li//a[contains(@href,'%s')]";
    private final Button notificationButton = new Button(By.xpath("//button[@class='w-notifications__bell js-notifications']"), "notificationButton");
    private final Button accountButton = new Button(By.xpath("(//span[@class='menu-item__text'])[3]"), "accountButton");
    private final Button signOutButton = new Button(By.xpath("(//span[@class='w-accountMenu__text'])[3]"), "signOutButton");

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

    public void logOut() {
        accountButton.click();
        signOutButton.click();
    }

    public String getUserLogin() {
        return accountButton.getText();
    }

    public boolean isAccountButtonPresent() {
        return accountButton.isButtonOnPage();
    }

    public boolean isOnLoggedInMainPage() {
        return notificationButton.isButtonOnPage();
    }
}
