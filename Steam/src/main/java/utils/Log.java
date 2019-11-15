package utils;

import org.apache.log4j.Logger;

public final class Log {
    private static Log instance;
    private static final Logger logger = Logger.getLogger("Logging");

    private Log() {
    }

    public static synchronized Log getInstance() {
        if (instance == null)
            instance = new Log();

        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}