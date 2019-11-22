package steam.pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends PageBase {

    public LoginPage() {
    }

    private final TextField loginField = new TextField(By.name("username"), "login");
    private final TextField passwordField = new TextField(By.name("password"), "password");
    private final Button signIn = new Button(By.xpath("//button[@type='submit']"), "signIn");
    private final Button messages = new Button(By.id("header_notification_link"), "messages");

    public void signIn(String login, String password) {
        loginField.typeValue(login);
        passwordField.typeValue(password);
        signIn.isButtonOnPage();
        signIn.click();
    }

    public boolean isMessageButtonPresent() {
        return messages.isButtonOnPage();
    }

}
