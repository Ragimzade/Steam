package tut.by.pages;

import base.BasePage;
import elements.Button;
import org.openqa.selenium.By;
import tut.by.enums.Sections;

import static tut.by.enums.Sections.Job;

public class MainPage extends BasePage {
    private final String TOP_BAR_MENU = "//li[@class='topbar__li']//a[@class='topbar__link' and contains(.,'%s')]";

    public JobsPage goToJobsPage() {
        navigate(Job);
        return new JobsPage();
    }

    public void navigate(Sections section) {
        new Button(By.xpath(String.format(TOP_BAR_MENU, section.getSection())), String.format("%sButton", section)).scrollAndClick();
    }
}
