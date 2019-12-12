package base;

import browser.Browser;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.Log;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class BaseEntity {
    protected static Log log = Log.getInstance();
    protected WebDriver driver = Browser.getInstance();
    protected static ConfigFileReader config = ConfigFileReader.getInstance();

    protected static ConditionFactory getDelay(int timeout, int delay) {
        return await().atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(delay, TimeUnit.MILLISECONDS);
    }
}
