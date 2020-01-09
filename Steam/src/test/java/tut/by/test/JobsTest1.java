package tut.by.test;

import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tut.by.model.JobsSearchData;
import tut.by.model.VacancyData;
import tut.by.steps.CommonSteps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static tut.by.steps.CommonSteps.vacancyDataHashMap;

public class JobsTest1 extends BaseTest {
    private static final String JOBS_TEST_DATA_FILEPATH = "src/main/resources/jobs_search_data.json";

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
    public void searchVacanciesByHeadlineTest(JobsSearchData searchData) {
        SoftAssert softAssert = new SoftAssert();
        CommonSteps.goToSearchPageIfNotOpened();
        CommonSteps.getVacanciesIfRequire(searchData.getSearchValue());
        List<VacancyData> set = vacancyDataHashMap.get(searchData.getSearchValue());
        for (VacancyData vacancyData : set) {
            softAssert.assertTrue(searchData.getKeyWords().stream().anyMatch(vacancyData.getHeadline()::contains),
                    String.format("Vacancy '%s' doesn't contain any of key words: ", vacancyData.getHeadline()));
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "testDataFromJSON")
    public void searchVacanciesByHeadlineAndDescriptionTest(JobsSearchData searchData) {
        SoftAssert softAssert = new SoftAssert();
        CommonSteps.goToSearchPageIfNotOpened();
        CommonSteps.getVacanciesIfRequire(searchData.getSearchValue());
        List<VacancyData> set = vacancyDataHashMap.get(searchData.getSearchValue());
        for (VacancyData vacancyData : set) {
            String vacancyDescriptionAndHeadline = vacancyData.getDescription() + vacancyData.getHeadline();
            softAssert.assertTrue(searchData.getKeyWords().stream().anyMatch(vacancyDescriptionAndHeadline::contains),
                    String.format("Vacancy '%s' doesn't contain any of key words: ", vacancyData.getHeadline()));
        }
        softAssert.assertAll();
    }
}
