package pages;

import browser.Browser;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import utils.ConfigFileReader;

import java.io.File;
import java.time.Duration;

public class SteamDownloadPage {
    private final Button downloadSteamButton = new Button(By.className("about_install_steam_link"), "downloadSteamButton");
    private FluentWait wait;

    public void downloadSteam() {
        downloadSteamButton.click();

    }


    public SteamDownloadPage() {
        ConfigFileReader config = ConfigFileReader.getInstance();
        WebDriver driver = Browser.getInstance(config.getBrowser("Chrome"));
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(100));
    }


    public boolean download(String downloadPath) {
        File folder = new File(downloadPath);
        File[] listOfFiles = folder.listFiles();
        boolean found = false;

        wait.until(x -> folder.exists());

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println(fileName);
                if (fileName.startsWith(fileName)) {
                    found = true;
                }
            }

        }
        return found;
    }

}
