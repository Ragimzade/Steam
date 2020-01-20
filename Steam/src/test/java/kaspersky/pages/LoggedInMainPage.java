package kaspersky.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import kaspersky.enums.NavigationMenuTabs;
import org.openqa.selenium.By;

public class LoggedInMainPage extends BasePage {
    private static final String NAVIGATION_MENU = "//ul[@class='main-menu js-priority-menu is-priority']//li//a[contains(@href,'%s')]";
    private final Button notificationButton = new Button(By.xpath("//button[@class='w-notifications__bell js-notifications']"), "notificationButton");
    private final Button accountButton = new Button(By.xpath("(//span[@class='menu-item__text'])[3]"), "accountButton");
    private final Button signOutButton = new Button(By.xpath("(//span[@class='w-accountMenu__text'])[3]"), "signOutButton");

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public LoggedInMainPage() {
        assertPageIsOpened(notificationButton);
    }

    /**
     * Navigates to Download page
     *
     * @return instance of DownloadPage class
     */
    public DownloadPage goToDownloadPage() {
        navigate(NavigationMenuTabs.Downloads);
        return new DownloadPage();
    }

    /**
     * Navigates to specified tab
     *
     * @param tab specified tab (from enum 'NavigationMenuTabs')
     * @see NavigationMenuTabs
     */
    private void navigate(NavigationMenuTabs tab) {
        new Button(By.xpath(String.format(NAVIGATION_MENU, tab)), String.format("%s Menu Button", tab)).click();
    }

    /**
     * Logs out
     */
    public void logOut() {
        accountButton.click();
        signOutButton.click();
    }

    /**
     * Get user login
     *
     * @return user login
     */
    public String getUserLogin() {
        return accountButton.getText();
    }

    /**
     * Verifies if account button is present
     *
     * @return returns true if present
     */
    public boolean isAccountButtonPresent() {
        return accountButton.isElementPresent();
    }

    /**
     * Verifies if user is on LoggedInMain Page
     *
     * @return true if is on the page
     */
    public boolean isOnLoggedInMainPage() {
        return notificationButton.isElementPresent();
    }
}
