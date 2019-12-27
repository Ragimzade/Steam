package browser;

import base.BaseEntity;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DateUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for taking screenshots
 */
public class Screenshot extends BaseEntity {
    /**
     * Takes screenshot and attachs it to report
     *
     * @param driver WebDriver
     * @return screenshot
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachScreenshotToReport(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Takes screenshot and saves it to screenshot folder
     *
     * @param driver WebDriver
     */
    public static void takeScreenshot(WebDriver driver) {
        Path screenPath = Paths.get("screenshots_from_tests", DateUtil.getTimeStamp().concat(".png"));
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotPng = new File(screenPath.toString());
            FileUtils.copyFile(screenshotFile, screenshotPng);
            log.info(String.format("Saving screenshot '%s' ", screenshotPng.getName()));
        } catch (IOException ex) {
            log.error("There was a problem while trying to make screenshot ", ex);
        }
    }

}
