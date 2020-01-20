package steam.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import elements.TextArea;
import elements.TextField;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public LoginPage() {
        assertPageIsOpened(loginTextArea);
    }

    private final TextField loginField = new TextField(By.name("username"), "login");
    private final TextField passwordField = new TextField(By.name("password"), "password");
    private final Button signIn = new Button(By.xpath("//button[@type='submit']"), "signIn");
    private final Button messages = new Button(By.id("header_notification_link"), "messages");
    private final TextArea loginTextArea = new TextArea(By.xpath("//div[@class='loginbox_content' and contains(.,'Sign')]"), "loginTextArea");

    /**
     * Signs in with specified login and password
     *
     * @param login    user login
     * @param password user password
     */
    public void signIn(String login, String password) {
        loginField.typeValue(login);
        passwordField.typeValue(password);
        signIn.isElementPresent();
        signIn.click();
    }

    /**
     * Verifies if user is logged in
     *
     * @return true if logged in
     */
    public boolean isMessageButtonPresent() {
        return messages.isElementPresent();
    }

}
