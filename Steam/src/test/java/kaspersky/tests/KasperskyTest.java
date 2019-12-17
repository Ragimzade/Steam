package kaspersky.tests;

import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kaspersky.model.ProductData;
import kaspersky.pages.DownloadPage;
import kaspersky.pages.LoggedInMainPage;
import kaspersky.pages.MainPage;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonParse;
import utils.MailUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class KasperskyTest extends BaseTest {
    private LoggedInMainPage loggedInMainPage;
    private DownloadPage downloadPage;

    @BeforeSuite
    public void beforeSuite() throws IOException, ParseException {
        MailUtils.deleteAllMessages();
        MainPage mainPage = new MainPage();
        loggedInMainPage = mainPage.Login(JsonParse.getKasperskyLogin2(), JsonParse.getKasperskyPassword());
        downloadPage = loggedInMainPage.goToDownloadPage();
    }

    @DataProvider()
    public Iterator<Object[]> testDataFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/product.json")));
        StringBuilder JSON = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            JSON.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ProductData> data = gson.fromJson(JSON.toString(), new TypeToken<List<ProductData>>() {
        }.getType());
        return data.stream().map((o) -> new Object[]{o}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "testDataFromJSON")
    public void sendEmailTest(ProductData product) {
        SoftAssert softAssert = new SoftAssert();
        downloadPage.goToSelectedOsTab(product.getOs());
        softAssert.assertTrue(downloadPage.isProductHasCorrectDescription(product.getProduct(), product.getDescription()),
                "Product description is not correct");
        downloadPage.sendAppToMySelf(product.getProduct());
        softAssert.assertTrue(MailUtils.isMailHasCorrectSubject(product.getEmailSubject()),
                "Email subject is not correct");
        softAssert.assertTrue(MailUtils.getTextFromMessage(product.getEmailSubject()).contains(product.getEmailLink()),
                "Email doesn't contain correct link");
        softAssert.assertAll();
    }


    @BeforeClass()
    public void deleteMessages() {
        MailUtils.deleteAllMessages();
    }
}