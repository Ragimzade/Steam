package utils;

import base_entity.BaseEntity;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtils extends BaseEntity {

    private final static String FILE_NAME = "Steam.exe";
    public static final int TIMEOUT = 20;
    public static final int DELAY = 20;

    public static boolean isFileDownloaded(String filename) throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir"), config.getBrowserDownloadPath(), filename);
        Files.deleteIfExists(filePath);
        try {
            getDelay(TIMEOUT, DELAY)
                    .until(() -> filePath.toFile().exists());
            log.info("File " + filePath.toFile().getName() + " is downloaded");
            return true;
        } catch (TimeoutException ex) {
            log.error("File is not downloaded");
            return false;
        }
    }


}