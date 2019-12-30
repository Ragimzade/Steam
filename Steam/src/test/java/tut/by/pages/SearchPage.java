package tut.by.pages;

import base.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tut.by.model.VacancyData;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {
    protected final Button nextButton = new Button(By.xpath("//a[@data-qa='pager-next']"), "nextButton");

    public List<VacancyData> getVacanciesOnPage() {
        List<WebElement> vacancies = findElements(By.xpath("//div[@class='vacancy-serp ']/div[contains(@data-qa,'vacancy')]"));
        List<VacancyData> vacancyData = new ArrayList<>();
        for (WebElement vacancy : vacancies) {
            String header = vacancy.findElement(By.xpath(".//a[contains(@data-qa,'vacancy-title')]")).getText();
            String description = vacancy.findElement(By.xpath(".//div[@class='g-user-content']")).getText();
            VacancyData data = new VacancyData(header, description);
            vacancyData.add(data);
        }
        return vacancyData;
    }

    public List<VacancyData> getAllVacancies() {
        List<VacancyData> allVacancies = getVacanciesOnPage();
        while (nextButton.isElementPresent()) {
            nextButton.click();
            List<VacancyData> vacanciesOnPage = getVacanciesOnPage();
            allVacancies.addAll(vacanciesOnPage);
        }
        return allVacancies;
    }

}
