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

public class Screenshot extends BaseEntity {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachScreenshotToReport(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void takeScreenshot(WebDriver driver) {
        String screenShot = "screenshots_from_tests/" + DateUtil.getTimeStamp();
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotPng = new File(screenShot + ".png");
            FileUtils.copyFile(screenshotFile, screenshotPng);
            log.info(String.format("Saving screenshot '%s' to directory :: %s", screenshotPng.getName(), screenShot));
        } catch (IOException ex) {
            log.error(String.format("There was a problem when trying to make screenshot. Exception:: %s", ex.getMessage()));
        }
    }

}
