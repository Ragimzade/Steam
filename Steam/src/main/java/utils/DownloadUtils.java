package utils;

import base_entity.BaseEntity;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtils extends BaseEntity {

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

    private static Path getPath(String fileName) {
        return Paths.get(System.getProperty("user.dir"), config.getBrowserDownloadPath(), fileName);
    }

    public static void deleteDirectory(String fileName) {
        try {
            log.info(String.format("deleting directory '%s' ", getPath(fileName)));
            System.out.println(getPath(fileName));
            Files.deleteIfExists(getPath(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Something gone wrong, try again");
        }
    }


}