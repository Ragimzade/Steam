package kaspersky.pages;

import base.BasePage;
import elements.Button;
import elements.TextArea;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import utils.JsonParse;

import java.io.IOException;

public class LoggedInMainPage extends BasePage {

    private final TextArea accountName = new TextArea(
            By.xpath("//span[@class='menu-item__text' and contains(.,'" + JsonParse.getKasperskyLogin() + "')]"), "accountName");
    private final Button downloadTab = new Button(By.xpath("//a[@title='Downloads']"), "downloadTab");

    public LoggedInMainPage() throws IOException, ParseException {
        assertPageIsOpened(accountName);
    }

    public DownloadPage goToDownloadPage() {
        downloadTab.click();
        return new DownloadPage();
    }
}
