package api.test;


import api.endpoint.EndPoints;
import api.model.CurrencyDescription;
import api.model.CurrencyRates;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Log;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class ApiTests {
    private Log log = Log.getInstance();
    private static final String BASE_URL = "http://www.nbrb.by/API/ExRates";

    public static Response doGetRequest(String endpoint) {
        return
                given().log().all().baseUri(BASE_URL).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void apiTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        Response response = doGetRequest(EndPoints.ALL_CURRENCIES);
        int recordsQuantity = response.path("Cur_ID.size()");
        softAssert.assertEquals(recordsQuantity, 223, "Quantity of records is wrong");

        Map<String, String> usdCur = response.path("find{it.Cur_Abbreviation == 'USD'}");
        Gson gson = new Gson();
        String json = gson.toJson(usdCur);
        ObjectMapper obj = new ObjectMapper();
        CurrencyDescription usdDescription = obj.readValue(json, CurrencyDescription.class);

        Response response2 = doGetRequest(EndPoints.USD_CURRENCY);
        CurrencyDescription currencyDescription = response2.getBody().as(CurrencyDescription.class);

        softAssert.assertEquals(usdDescription, currencyDescription, "Descriptions are not equal");

        Response response3 = doGetRequest(EndPoints.USD_CURRENCY_FOR_TODAY);
        CurrencyRates usd = response3.getBody().as(CurrencyRates.class);
        System.out.println(usd);

        Response response4 = doGetRequest(EndPoints.CURRENCIES_FOR_PERIOD);
        String maxRate = response4.path("max{it.Cur_OfficialRate}.Date");
        log.info(String.format("Max rate was on date %s", maxRate));
        String minRate = response4.path("min{it.Cur_OfficialRate}.Date");
        log.info(String.format("Min rate was on date %s", minRate));
        softAssert.assertAll();
    }


}