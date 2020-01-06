package utils;

import org.apache.log4j.Logger;

/**
 * Class for logging
 */
public final class Log {
    private static Log instance;
    private static final Logger logger = Logger.getLogger("Logging");

    /**
     * Private constructor for not instantiating
     */
    private Log() {
    }

    /**
     * @return Log class instance
     */
    public static synchronized Log getInstance() {
        if (instance == null)
            instance = new Log();
        return instance;
    }

    /**
     * Prints info lvl message
     *
     * @param message message to print
     */
    public void info(String message) {
        logger.info(String.format("--------==[ %1$s ]==--------", message));
    }

    /**
     * Prints error lvl message with specified message and exception
     *
     * @param message specified message
     * @param ex      exception message
     */
    public void error(String message, Throwable ex) {
        logger.error(message, ex);
    }

    /**
     * Prints error lvl message with specified message a
     *
     * @param message specified message
     */
    public void error(String message) {
        logger.error(message);
    }
}