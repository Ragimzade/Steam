package kaspersky.steps;

import kaspersky.pages.LoggedInMainPage;
import kaspersky.pages.MainPage;

public class CommonSteps {
    /**
     * Logs in if require
     *
     * @return instance of LoggedInMainPage class
     * @see MainPage#login(String, String)
     */
    public static LoggedInMainPage doLogin(String email, String password) {
        MainPage mainPage = new MainPage();
        if (!mainPage.isSignedIn()) {
            return mainPage.login(email, password);
        } else return new LoggedInMainPage();
    }

}
