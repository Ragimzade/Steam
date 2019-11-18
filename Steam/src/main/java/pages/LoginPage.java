package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends PageBase {

    public LoginPage() {
    }

    private final TextField loginField = new TextField(By.name("username"), "login");
    private final TextField passwordField = new TextField(By.xpath(".//input[@type='password']"), "password");
    private final Button signIn = new Button(By.xpath(".//button[@type='submit']"), "signin");
    private final Button messages = new Button(By.xpath("//div[@id='header_notification_link']"), "messages");

    public void signIn(String login, String password) {
        loginField.typeValue(login);
        loginField.getText();
        passwordField.typeValue(password);
        passwordField.getText();
        signIn.isButtonOnPage();
        signIn.click();
    }

    public boolean isMessageButtonPresent() {
        return messages.isButtonOnPage();
    }
}
