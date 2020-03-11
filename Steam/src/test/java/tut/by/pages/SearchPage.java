package tut.by.pages;

import base.BasePage;
import browser.Browser;
import elements.BaseWebElement;
import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tut.by.model.VacancyData;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {
    private final Button nextButton = new Button(By.xpath("//a[@data-qa='pager-next']"), "nextButton");
    private final Button vacanciesButton = new Button(By.xpath("//div[@data-hh-tab-id='searchVacancy']"), "vacanciesButton");
    private final TextField searchTextField = new TextField(By.xpath("//input[@data-qa='search-input']"), "searchTextField");
    private final Button findButton = new Button(By.className("supernova-search-submit-text"), "findButton");

    /**
     * Base constructor
     *
     * @see BasePage#assertPageIsOpened(BaseWebElement)
     */
    public SearchPage() {
        assertPageIsOpened(vacanciesButton);
    }

    /**
     * Searches for jobs by specified value
     *
     * @param searchValue value for search
     */
    public void searchForJobs(String searchValue) {
        searchTextField.typeValue(searchValue);
        findButton.click();
    }

    /**
     * Reads vacancies on current single page and creates a list with instances of VacancyData class
     *
     * @return list of VacancyData class instances
     * @see #getVacancy(WebElement)
     */
    private List<VacancyData> getVacanciesOnPage() {
        List<WebElement> vacancies = findElements(By.xpath("//div[@class='vacancy-serp']/div[contains(@class,'vacancy')]"));

        return vacancies.stream()
                .map(this::getVacancy)
                .collect(Collectors.toList());
    }

    /**
     * Creates vacancy from specified web element
     *
     * @param vacancy WebElement
     * @return instance of VacancyData class
     */
    private VacancyData getVacancy(WebElement vacancy) {
        String header = vacancy.findElement(By.xpath(".//a[contains(@data-qa,'vacancy-title')]")).getText();
        String description = vacancy.findElement(By.xpath(".//div[@class='g-user-content']")).getText();
        String pageUrl = Browser.getCurrentUrl();
        return new VacancyData(header, description, pageUrl);
    }

    /**
     * Reads vacancies from all pages using pagination button
     *
     * @return list with vacancies
     */
    public List<VacancyData> getAllVacancies() {
        List<VacancyData> allVacancies = getVacanciesOnPage();
        while (nextButton.isElementPresent()) {
            nextButton.click();
            List<VacancyData> vacanciesOnPage = getVacanciesOnPage();
            vacanciesOnPage.stream().collect(Collectors.toCollection(() -> allVacancies));
        }
        return allVacancies;
    }
}
