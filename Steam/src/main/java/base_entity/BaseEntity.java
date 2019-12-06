package base_entity;

import browser.Browser;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.Log;

public class BaseEntity {
    protected static Log log = Log.getInstance();
    protected WebDriver driver = Browser.getInstance();
    protected static ConfigFileReader config = ConfigFileReader.getInstance();

}
