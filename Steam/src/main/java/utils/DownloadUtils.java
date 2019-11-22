package utils;

import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class DownloadUtils {

    private static final Log log = Log.getInstance();

    public static boolean isFileDownloaded() throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir") + "\\downloads\\SteamSetup.exe");
        Files.deleteIfExists(filePath);
        try {
            getDelay()
                    .until(() -> filePath.toFile().exists());
            log.info("File " + filePath.toFile().getName() + " is downloaded");
            Files.deleteIfExists(filePath);
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