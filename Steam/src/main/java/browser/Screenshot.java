package browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DateUtil;
import utils.Log;

import java.io.File;

public class Screenshot {
    protected static Log log = Log.getInstance();

    public static void takeScreenshot(WebDriver driver) {
        String filePath = "screenshots_from_tests/" + DateUtil.getCurrentDateFormatDost();
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
            log.error("There was a problem when trying to make screenshot, please try again later... Exception:: " + ex.getMessage());
        }
    }

}
