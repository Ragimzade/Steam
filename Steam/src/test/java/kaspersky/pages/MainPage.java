package kaspersky.pages;

import base.BasePage;
import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private final Button loginButton = new Button(By.xpath("//button[contains(.,'Sign in') and contains(@class,'btn')]"), "loginButton");
    private final TextField emailField = new TextField(By.cssSelector("input.u-inputField__control[type='email']"), "emailField");
    private final TextField passwordField = new TextField(By.xpath("(//input[@type='password'])[1]"), "passwordField");
    private final Button signInButton = new Button(By.xpath("//button[@data-omniture-cta-name='Sign in']"), "signInButton");
    private final Button notificationButton = new Button(By.xpath("//button[@class='w-notifications__bell js-notifications']"), "notificationButton");

    /**
     * Base constructor
     */
    public MainPage() {
    }

    /**
     * Opens login pop-up
     */
    public void openLoginPopUp() {
        loginButton.click();
    }

    /**
     * Signs in with specified login and password
     * @param email user login
     * @param password user password
     * @return instance of LoggedInMainPage class
     */
    public LoggedInMainPage login(String email, String password) {
        openLoginPopUp();
        emailField.typeValue(email);
        passwordField.typeValue(password);
        signInButton.click();
        return new LoggedInMainPage();
    }

    /**
     * Verifies if user is signed in
     * @return true if signed in
     */
    public boolean isSignedIn() {
        return notificationButton.isElementPresent();
    }


}
