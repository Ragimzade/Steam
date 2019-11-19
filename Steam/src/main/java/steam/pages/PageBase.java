package steam.pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;


public class PageBase {
    protected ConfigFileReader config = ConfigFileReader.getInstance();
     WebDriver driver;

    public PageBase() {

    }
}
