package tut.by.test;

import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tut.by.model.JobsSearchData;
import tut.by.model.VacancyData;
import tut.by.pages.JobsPage;
import tut.by.pages.MainPage;
import tut.by.pages.SearchPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class JobsTest extends BaseTest {
    private static final String JOBS_TEST_DATA_FILEPATH = "src/main/resources/JobsSearchData.json";
    private JobsPage jobsPage;

    @BeforeMethod
    public void goToJobsPage() {
        MainPage mainPage = new MainPage();
        jobsPage = mainPage.goToJobsPage();
    }

    @DataProvider()
    public Iterator<Object[]> testDataFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(JOBS_TEST_DATA_FILEPATH)));
        StringBuilder JSON = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            JSON.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<JobsSearchData> data = gson.fromJson(JSON.toString(), new TypeToken<List<JobsSearchData>>() {
        }.getType());
        return data.stream().map((o) -> new Object[]{o}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "testDataFromJSON")
    public void searchVacanciesTest(JobsSearchData searchData) {
        SoftAssert softAssert = new SoftAssert();
        SearchPage searchPage = jobsPage.searchForJobs("Qa automation");
        List<VacancyData> automationVacancies = searchPage.getAllVacancies();
        for (VacancyData data : automationVacancies) {
            softAssert.assertTrue(searchData.getKeyWords().parallelStream().anyMatch(data.getHeadline()::contains),
                    String.format("Headline: '%s' \r\n Description: '%s'", data.getHeadline(), data.getDescription()));
        }
        softAssert.assertAll();
    }
}
