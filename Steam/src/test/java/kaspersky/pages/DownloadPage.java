package kaspersky.pages;

import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class DownloadPage extends BasePage {

    private final TextArea downloadHeader = new TextArea(By.xpath("//h2[contains(@class, 'u-title') and contains(.,'Trial')]"),
            "downloadHeader");
    private final Button sendToMySelf = new Button(By.xpath("(//div[@class='u-button__text'])[2]"), "sendToMySelf");
    private final Button sendButton = new Button(By.xpath("//button[@data-at-selector='installerSendSelfBtn']"), "sendButton");
    private final Button OSTabButtons = new Button(By.className("u-osTile__title"), "OSTabButtons");
    private final Button okButton = new Button(By.xpath("//button[contains(text(),'OK')]"), "okButton");

    public DownloadPage() {
        assertPageIsOpened(downloadHeader);
    }

    public void sendAppToMySelf(String OSName, String product, String description) {
        OSTabButtons.clickByVisibleText(OSName);
        isProductHasCorrectDescription(product, description);
        sendProductToMySelf(product);
        sendButton.click();
        if (isOkButtonPresent()) {
            okButton.click();
        }
    }


    private WebElement getProductBlockByName(String text) {
        List<WebElement> webElements = driver.findElements(
                By.xpath("//div[@class='w-downloadProgramCard a-padding-x-sm']//child::div/div/div/div[2]"));
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + text));
    }

    private String getProductDescription(String productName) {
        WebElement product = getProductBlockByName(productName).findElement
                (By.xpath("./ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//div[@data-at-selector='serviceShortDescription']"));
        return product.getText();
    }

    public void sendProductToMySelf(String productName) {
        WebElement product = getProductBlockByName(productName).findElement
                (By.xpath(".//ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//button/div[2]"));
        product.click();
    }

    public boolean isProductHasCorrectDescription(String productName, String correctDescription) {
        String description = getProductDescription(productName);
        return description.equals(correctDescription);
    }

    public void clickOkButton() {
        okButton.click();
    }

    public boolean isOkButtonPresent() {
        try {
            return okButton.isButtonOnPage();
        } catch (TimeoutException ex) {
            return false;
        }
    }
}
