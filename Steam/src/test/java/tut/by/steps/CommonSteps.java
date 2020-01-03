package tut.by.steps;

import base.BaseEntity;
import tut.by.model.VacancyData;
import tut.by.pages.JobsPage;
import tut.by.pages.MainPage;
import tut.by.pages.SearchPage;

import java.util.HashMap;
import java.util.List;

public class CommonSteps extends BaseEntity {

    /**
     * Static HashMap<String, List<VacancyData>> field with stored vacancies
     */
    public static HashMap<String, List<VacancyData>> vacancyDataHashMap = new HashMap<>();

    /**
     * Navigates to Search Page if the page is not opened
     *
     * @return instance of SearchPage class
     */
    public static SearchPage goToSearchPageIfNotOpened() {
        MainPage mainPage = new MainPage();
        if (!mainPage.isSearchPageOpened()) {
            log.info("Opening SearchPage");
            JobsPage jobsPage = mainPage.goToJobsPage();
            jobsPage.goToSearchPage();
            return new SearchPage();
        }
        return null;
    }

    /**
     * Reads vacancies from page and writes them into "vacancyDataHashMap"
     *
     * @param searchValue value for vacancy searching
     * @see SearchPage#getAllVacancies()
     */
    public static void getVacancies(String searchValue) {
        SearchPage searchPage = new SearchPage();
        searchPage.searchForJobs(searchValue);
        List<VacancyData> vacancies = searchPage.getAllVacancies();
        vacancyDataHashMap.put(searchValue, vacancies);
    }

    /**
     * Gets vacancies if there are no vacancies in "vacancyDataHashMap" on specified searchValue
     *
     * @param searchValue value for vacancy searching
     * @see #getVacancies(String)
     */
    public static void getVacanciesIfRequire(String searchValue) {
        if (!vacancyDataHashMap.containsKey(searchValue)) {
            log.info(String.format("Getting vacancies for '%s' search value", searchValue));
            getVacancies(searchValue);
        }
    }
}
