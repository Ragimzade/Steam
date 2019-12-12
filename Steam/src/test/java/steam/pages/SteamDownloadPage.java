package steam.pages;

import base.BasePage;
import elements.Button;
import org.openqa.selenium.By;


public class SteamDownloadPage extends BasePage {
    private final Button downloadSteamButton = new Button(By.className("about_install_steam_link"), "downloadSteamButton");

    public SteamDownloadPage() {
        assertPageIsOpened(downloadSteamButton);
    }

    public void downloadSteam() {
        downloadSteamButton.click();
    }

}