package pages;

import implementation.Button;
import implementation.TextField;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private static final Logger logger = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    private final TextField loginField = new TextField(driver, By.name("username"), "login");
    private final TextField passwordField = new TextField(driver, By.xpath(".//input[@type='password']"), "password");
    private final Button signIn = new Button(driver, By.xpath(".//button[@type='submit']"), "signin");
    private final Button messages = new Button(driver, By.xpath("//div[@id='header_notification_link']"), "messages");

    public void signIn(String login, String password) {
        loginField.typeValue(login);
        passwordField.typeValue(password);
        signIn.click();
    }

    public boolean isMessageButtonPresent() {
        return messages.isButtonOnPage();
    }
}
