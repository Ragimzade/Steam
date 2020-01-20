package tut.by.pages;

import base.BasePage;
import elements.BaseWebElement;
import elements.Button;
import elements.TextArea;
import org.openqa.selenium.By;

public class JobsPage extends BasePage {

    private final Button findButton = new Button(By.className("supernova-search-submit-text"), "findButton");
    private final TextArea jobsHeaderTextArea = new TextArea(By.className("supernova-dashboard-header"), "jobsHeaderTextArea");

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public JobsPage() {
        assertPageIsOpened(jobsHeaderTextArea);
    }

    /**
     * Navigates to Search page
     *
     * @return instance of SearchPage class
     */
    public SearchPage goToSearchPage() {
        findButton.click();
        return new SearchPage();
    }
}
