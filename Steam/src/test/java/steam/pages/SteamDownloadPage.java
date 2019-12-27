package steam.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import org.openqa.selenium.By;


public class SteamDownloadPage extends BasePage {
    private final Button downloadSteamButton = new Button(By.className("about_install_steam_link"), "downloadSteamButton");

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public SteamDownloadPage() {
        assertPageIsOpened(downloadSteamButton);
    }

    /**
     * Downloads Steam app
     */
    public void downloadSteam() {
        downloadSteamButton.click();
    }

}