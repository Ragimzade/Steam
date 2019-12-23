package kaspersky.steps;

import kaspersky.pages.LoggedInMainPage;
import kaspersky.pages.MainPage;
import utils.TestData;

public class CommonSteps {

    public static LoggedInMainPage login(String email, String password) {
        MainPage mainPage = new MainPage();
        if (!mainPage.isSignedIn()) {
            return mainPage.login(TestData.getValue(email), TestData.getValue(password));
        } else return new LoggedInMainPage();
    }

}
