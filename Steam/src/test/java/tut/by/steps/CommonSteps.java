package tut.by.steps;

import base.BaseEntity;
import tut.by.model.VacancyData;
import tut.by.pages.JobsPage;
import tut.by.pages.MainPage;
import tut.by.pages.SearchPage;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        return new SearchPage();
    }

    /**
     * Reads vacancies from page and writes them into "vacancyDataHashMap"
     *
     * @param searchValue value for vacancy searching
     * @see SearchPage#getAllVacancies()
     */
    public static void readVacancies(String searchValue) {
        SearchPage searchPage = new SearchPage();
        searchPage.searchForJobs(searchValue);
        List<VacancyData> vacancies = searchPage.getAllVacancies();
        vacancyDataHashMap.put(searchValue, vacancies);
    }

    /**
     * Gets vacancies if there are no vacancies in "vacancyDataHashMap" by specified searchValue
     *
     * @param searchValue value for vacancy searching
     * @see #readVacancies(String)
     */
    public static void readVacanciesIfRequire(String searchValue) {
        if (!vacancyDataHashMap.containsKey(searchValue)) {
            log.info(String.format("Getting vacancies for '%s' search value", searchValue));
            readVacancies(searchValue);
        }
    }

    /**
     * Get listed vacancies from vacancyDataHashMap by specified searchValue
     *
     * @param searchValue value for vacancy searching
     * @return list of VacancyData.class instances
     */
    public static List<VacancyData> getListedVacancies(String searchValue) {
        return Objects.requireNonNull(vacancyDataHashMap.get(searchValue));
    }
}
