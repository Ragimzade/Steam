package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;

public class PageBase {
    protected ConfigFileReader config = new ConfigFileReader();
    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }


}
