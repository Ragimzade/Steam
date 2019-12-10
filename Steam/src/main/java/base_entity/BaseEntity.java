package base_entity;

import browser.Browser;

import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.Log;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class BaseEntity {
    protected static ConfigFileReader config;
    protected WebDriver driver;
    protected static Log log = Log.getInstance();

    public BaseEntity() {
        config = ConfigFileReader.getInstance();
        driver = Browser.getInstance();
    }

    protected static ConditionFactory getDelay(int timeout, int delay) {
        return await().atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(delay, TimeUnit.MILLISECONDS);
    }

}
