package browser;

import base_entity.BaseEntity;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DateUtil;

import java.io.File;

public class Screenshot extends BaseEntity {


    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    public static void takeScreenshot(WebDriver driver) {
        String filePath = "screenshots_from_tests/" + DateUtil.getCurrentDate();
        try {
            int serialNumber = 1;
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File file = new File(filePath + ".png");
            for (int i = 0; i < serialNumber; i++) {
                if (file.exists()) {
                    file = new File(filePath + "(" + serialNumber + ")" + ".png");
                    serialNumber++;
                }
            }
            FileUtils.copyFile(scrFile, file);
            log.info(String.format("Saving screenshot '%s' to directory :: %s", file.getName(), filePath));
        } catch (Exception ex) {
            log.error("There was a problem when trying to make screenshot. Exception:: " + ex.getMessage());
        }
    }

}
