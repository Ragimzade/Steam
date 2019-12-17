package base;

import browser.Browser;
import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.Log;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.concurrent.Callable;
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

    public static <T> List<T> waitForList(int timeout, int delay, Callable<List<T>> supplier) {
        try {
            return getDelay(timeout, delay).until(supplier, not(empty()));
        } catch (ConditionTimeoutException ex) {
            throw new AssertionError(String.format("Message is not found in folder exception:: %s", ex.getMessage()));
        }
    }

}
