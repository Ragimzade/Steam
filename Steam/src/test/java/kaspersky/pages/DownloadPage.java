package kaspersky.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class DownloadPage extends BasePage {

    private final TextArea downloadHeaderTextArea = new TextArea(By.xpath("//h2[@data-at-selector='downloadBlockTrialAppsTitle']"), "downloadHeader");
    private final Button sendButton = new Button(By.xpath("//button[@data-at-selector='installerSendSelfBtn']"), "sendButton");
    private final Button OSTabButtons = new Button(By.className("u-osTile__title"), "OSTabButtons");
    private final Button okButton = new Button(By.xpath("//button[contains(text(),'OK')]"), "okButton");
    private final TextArea iframeTextArea = new TextArea(By.xpath("(//iframe[@title='recaptcha challenge'])[2]"), "iframe");
    private static final int TIMEOUT_IN_SECONDS = 120;
    private static final int DELAY_IN_MILLIS = 1000;

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public DownloadPage() {
        assertPageIsOpened(downloadHeaderTextArea);
    }

    /**
     * Sends application to user's mailbox
     *
     * @param product name of apllication to send
     */
    public void sendAppToMySelf(String product) {
        sendProductToMySelf(product);
        sendButton.click();
        clickOkButtonIfPresent();
        waitForCaptchaValidation();
    }

    /**
     * Clicks OK button if it appears
     */
    private void clickOkButtonIfPresent() {
        if (okButton.isElementPresent()) {
            okButton.click();
        }
    }

    /**
     * Navigates to specified OS tab
     *
     * @param os name of OS
     */
    public void goToSelectedOsTab(String os) {
        OSTabButtons.clickByVisibleText(os);
    }

    /**
     * Verifies if captcha is present and waits while validation
     */
    private void waitForCaptchaValidation() {
        if (iframeTextArea.isElementPresent()) {
            log.info("waiting for absent of iframe");
            getDelay(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS).until(iframeTextArea::waitForAbsent);
            sendButton.click();
            clickOkButtonIfPresent();
        }
    }

    /**
     * Gets product's block by specified name
     *
     * @param productName specified name
     * @return matching WebElement
     * @throws NoSuchElementException if there is no matching WebElement
     */
    private WebElement getProductBlockByName(String productName) {
        List<WebElement> webElements = findElements(
                By.xpath("//div[@class='w-downloadProgramCard a-padding-x-sm']//child::div/div/div/div[2]"));
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing " + productName));
    }

    /**
     * Gets product description as string by specified product name
     *
     * @param productName product name
     * @return description of specified products
     * @see #getProductBlockByName
     */
    private String getProductDescription(String productName) {
        WebElement product = getProductBlockByName(productName)
                .findElement((By.xpath("./ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//div[@data-at-selector='serviceShortDescription']")));
        return product.getText();
    }

    /**
     * Finds product by name and clicks the button to send it to user's email
     *
     * @param productName product name
     * @see #getProductBlockByName
     */
    public void sendProductToMySelf(String productName) {
        WebElement product = getProductBlockByName(productName).findElement
                (By.xpath(".//ancestor::div[@class='w-downloadProgramCard a-padding-x-sm']//button/div[2]"));
        product.click();
    }

    /**
     * Gets product's description and verifies if it's equal with specified description
     *
     * @param productName        product name
     * @param correctDescription specified description
     * @return true if descriptions are equal
     */
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
