package kaspersky.tests;

import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kaspersky.model.ProductData;
import kaspersky.pages.DownloadPage;
import kaspersky.pages.LoggedInMainPage;
import kaspersky.steps.CommonSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.MailUtils;
import utils.TestData;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class KasperskyTest extends BaseTest {

    public static final String PRODUCT_JSON_FILE_PATH = "src/main/resources/product.json";

    @DataProvider()
    public Iterator<Object[]> testDataFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(PRODUCT_JSON_FILE_PATH)));
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
    public void sendEmailTest(ProductData product) throws MessagingException {
        Date sendTime = new Date();
        LoggedInMainPage loggedInMainPage = CommonSteps.doLogin(
                TestData.getValue("kasperskyLogin"), TestData.getValue("kasperskyPassword"));
        DownloadPage downloadPage = loggedInMainPage.goToDownloadPage();
        SoftAssert softAssert = new SoftAssert();
        downloadPage.goToSelectedOsTab(product.getOs());
        softAssert.assertTrue(downloadPage.isProductHasCorrectDescription(product.getProduct(), product.getDescription()),
                "Product description is not correct");
        downloadPage.sendAppToMySelf(product.getProduct());
        softAssert.assertEquals(MailUtils.getMessageSubject(product.getEmailSubject(), sendTime), product.getEmailSubject(),
                "Email subject is not correct");
        softAssert.assertTrue(MailUtils.getMessageContent(product.getEmailSubject(), sendTime).contains(product.getEmailLink()),
                "Email doesn't contain correct link");
        softAssert.assertAll();
    }
}