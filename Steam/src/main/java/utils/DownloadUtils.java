package utils;

import base_entity.BaseEntity;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class DownloadUtils extends BaseEntity {

    public static boolean isFileDownloaded(String filename) throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir"), config.getBrowserDownloadPath(), filename);
        Files.deleteIfExists(filePath);
        try {
            getDelay()
                    .until(() -> filePath.toFile().exists());
            log.info("File " + filePath.toFile().getName() + " is downloaded");
            return true;
        } catch (TimeoutException ex) {
            log.error("File is not downloaded");
            return false;
        }
    }

    private static ConditionFactory getDelay() {
        return await().atMost(20, TimeUnit.SECONDS)
                .ignoreExceptions()
                .pollDelay(20, TimeUnit.MILLISECONDS);
    }
}