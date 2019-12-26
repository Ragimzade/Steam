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
 * Abstract class from which most classes are inherited.
 * Describes the highest level methods
 */
public abstract class BaseEntity {
    protected static final Log log = Log.getInstance();
    protected WebDriver driver = Browser.getDriver();
    protected static ConfigFileReader config = ConfigFileReader.getInstance();

    /**
     * Gets delay with specified timeout and delay
     *
     * @param timeout time out in seconds
     * @param delay   delay in milliseconds
     * @return delay
     */
    protected static ConditionFactory getDelay(int timeout, int delay) {
        return await().atMost(timeout, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Gets delay with default values
     *
     * @see #getDelay(int, int)
     */
    protected static ConditionFactory getDelay() {
        return getDelay(5, 1000);
    }

    /**
     * Waits till the list with specified condition is present
     *
     * @param timeout      time out in seconds
     * @param delay        delay in milliseconds
     * @param errorMessage specified error message
     * @param supplier     condition to meet
     * @param <T>          Type of object for waiting
     * @return List with <T> type
     * @throws AssertionError if condition is not met
     * @see #getDelay(int, int)
     */
    public static <T> List<T> waitForList(int timeout, int delay, String errorMessage, Callable<List<T>> supplier) {
        try {
            return getDelay(timeout, delay).until(supplier, not(empty()));
        } catch (ConditionTimeoutException ex) {
            throw new AssertionError(errorMessage, ex);
        }
    }

}
