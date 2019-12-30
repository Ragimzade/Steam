package tut.by.pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;

public class JobsPage {
    protected final TextField searchTextField = new TextField(By.xpath("//input[@data-qa='search-input']"), "searchTextField");
    protected final Button findButton = new Button(By.className("supernova-search-submit-text"), "findButton");

    public SearchPage searchForJobs(String searchValue) {
        searchTextField.typeValue(searchValue);
        findButton.click();
        return new SearchPage();
    }

}
