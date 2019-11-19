package steam.pages;

import elements.Button;
import org.openqa.selenium.By;


public class SteamDownloadPage extends PageBase {
    private final Button downloadSteamButton = new Button(By.className("about_install_steam_link"), "downloadSteamButton");

    public void downloadSteam() {
        downloadSteamButton.click();
    }



}