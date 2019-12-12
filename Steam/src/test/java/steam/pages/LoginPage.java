package steam.pages;

import base.BasePage;
import elements.Button;
import elements.TextArea;
import elements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public LoginPage() {
        assertPageIsOpened(loginTextArea);
    }

    private final TextField loginField = new TextField(By.name("username"), "login");
    private final TextField passwordField = new TextField(By.name("password"), "password");
    private final Button signIn = new Button(By.xpath("//button[@type='submit']"), "signIn");
    private final Button messages = new Button(By.id("header_notification_link"), "messages");
    private final TextArea loginTextArea = new TextArea(By.xpath("//div[@class='loginbox_content' and contains(.,'Sign')]"), "loginTextArea");

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
