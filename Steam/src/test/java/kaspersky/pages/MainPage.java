package kaspersky.pages;

import elements.Button;
import elements.TextField;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class MainPage extends BasePage {

    private final Button loginButton = new Button(By.xpath("//button[contains(.,'Sign in') and contains(@class,'btn')]"), "loginButton");
    private final TextField emailField = new TextField(By.cssSelector("input.u-inputField__control[type='email']"), "emailField");
    private final TextField passwordField = new TextField(By.xpath("(//input[@type='password'])[1]"), "passwordField");
    private final Button signInButton = new Button(By.xpath("//button[@data-omniture-cta-name='Sign in']"), "signInButton");

    public MainPage() {
    }

    public void openLoginPopUp() {
        loginButton.click();
    }

    public LoggedInMainPage Login(String email, String password) throws IOException, ParseException {
        openLoginPopUp();
        emailField.typeValue(email);
        passwordField.typeValue(password);
        signInButton.click();
        return new LoggedInMainPage();
    }

}
