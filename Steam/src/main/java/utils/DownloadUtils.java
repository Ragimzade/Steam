package utils;

import base.BaseEntity;
import org.openqa.selenium.TimeoutException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtils extends BaseEntity {
    public static final int TIMEOUT_IN_SECONDS = 20;
    public static final int DELAY_IN_MILLIS = 70;

    public static boolean isFileDownloaded(String filename) {
        Path filePath = getPath(filename);
        try {
            getDelay(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS)
                    .until(() -> filePath.toFile().exists() && filename.endsWith(filename.substring(10)));
            log.info("File " + filePath.toFile().getName() + " is downloaded");
            return true;
        } catch (TimeoutException ex) {
            log.error("File is not downloaded", ex);
            return false;
        }
    }

    private static Path getPath(String filename) {
        return Paths.get(System.getProperty("user.dir"), config.getBrowserDownloadPath(), filename);
    }


}