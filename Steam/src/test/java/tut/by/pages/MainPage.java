package tut.by.pages;

import base.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import tut.by.enums.Sections;

import static tut.by.enums.Sections.Job;

public class MainPage extends BasePage {
    private final String TOP_BAR_MENU = "//li[@class='topbar__li']//a[@class='topbar__link' and contains(.,'%s')]";
    private final Button vacanciesButton = new Button(By.xpath("//div[@data-hh-tab-id='searchVacancy']"), "vacanciesButton");

    /**
     * Navigates to Jobs page
     *
     * @return instance of JobPage class
     * @see #navigateTo(Sections)
     */
    public JobsPage goToJobsPage() {
        navigateTo(Job);
        return new JobsPage();
    }

    /**
     * Navigates to specified sections
     *
     * @param section specified section (from enum 'Sections')
     * @see Sections
     */
    public void navigateTo(Sections section) {
        new Button(By.xpath(String.format(TOP_BAR_MENU, section.getSection())), String.format("%sButton", section))
                .scrollAndClick();
    }

    /**
     * Checks if Search page is opened
     *
     * @return true if Search page is opened
     */
    public boolean isSearchPageOpened() {
        return vacanciesButton.isElementPresent();
    }
}
