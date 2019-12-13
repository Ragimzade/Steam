package kaspersky.pages;

import base.BasePage;
import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class DownloadPage extends BasePage {

    private final TextArea downloadHeader = new TextArea(By.xpath("//h2[contains(@class, 'u-title') and contains(.,'Trial')]"), "downloadHeader");
    private final Button sendButton = new Button(By.xpath("//button[@data-at-selector='installerSendSelfBtn']"), "sendButton");
    private final Button OSTabButtons = new Button(By.className("u-osTile__title"), "OSTabButtons");
    private final Button okButton = new Button(By.xpath("//button[contains(text(),'OK')]"), "okButton");
    private final Button captchaBlockButton = new Button(By.xpath("//div[contains(@class,'kl-captcha')]//iframe"), "captchaBlockButton");
    private final By productDescription = By.xpath("./ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//div[@data-at-selector='serviceShortDescription']");
    private final By iframe = By.xpath("(//iframe[@title='recaptcha challenge'])[2]");
    private final Button iframeVerifyButton = new Button(By.xpath("//div[@id='rc-imageselect']//*[@id='recaptcha-verify-button']"), "iframeVerifyButton");

    public DownloadPage() {
        assertPageIsOpened(downloadHeader);
    }

    public void sendAppToMySelf(String product) {
        sendProductToMySelf(product);
        sendButton.click();
        if (okButton.isButtonOnPage()) {
            okButton.click();
        }
    }

    public void goToSelectedOsTab(String os) {
        OSTabButtons.clickByVisibleText(os);
    }

    private WebElement getProductBlockByName(String text) {
        List<WebElement> webElements = findElements(
                By.xpath("//div[@class='w-downloadProgramCard a-padding-x-sm']//child::div/div/div/div[2]"));
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    private String getProductDescription(String productName) {
        WebElement product = getProductBlockByName(productName).findElement((productDescription));
        return product.getText();
    }

    public void sendProductToMySelf(String productName) {
        WebElement product = getProductBlockByName(productName).findElement
                (By.xpath(".//ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//button/div[2]"));
        product.click();
    }

    public boolean isProductHasCorrectDescription(String productName, String correctDescription) {
        String description = getProductDescription(productName);
        if (description.equals(correctDescription)) {
            log.info("Descriptions match");
            return true;
        } else {
            log.info(String.format("Found description: '%s'; expected description: '%s' ", description, correctDescription));
            return false;
        }
    }

}
