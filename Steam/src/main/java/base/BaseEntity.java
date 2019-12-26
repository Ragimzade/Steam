package base;

import browser.Browser;
import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

/**
 * Abstract class from which most classes inherit.
 * Describes the highest level methods
 */
public abstract class BaseEntity {
    protected static final Log log = Log.getInstance();
    protected WebDriver driver = Browser.getDriver();
    protected static ConfigFileReader config = ConfigFileReader.getInstance();

    /**
     *
     * @param timeout
     * @param delay
     * @return
     */
    protected static ConditionFactory getDelay(int timeout, int delay) {
        return await().atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(delay, TimeUnit.MILLISECONDS);
    }

    protected static ConditionFactory getDelay() {
        return getDelay(5, 1000);
    }

    public static <T> List<T> waitForList(int timeout, int delay, String errorMessage, Callable<List<T>> supplier) {
        try {
            return getDelay(timeout, delay).until(supplier, not(empty()));
        } catch (ConditionTimeoutException ex) {
            throw new AssertionError(errorMessage, ex);
        }
    }

}
