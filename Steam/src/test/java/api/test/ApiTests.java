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
    public static final int EXPECTED_RECORDS_QUANTITY = 225;

    public static Response doGetRequest(String endpoint) {
        return
                given().log().all().baseUri(BASE_URL).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void apiTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        Response listOfCurrenciesResponse = doGetRequest(EndPoints.ALL_CURRENCIES);
        int recordsQuantity = listOfCurrenciesResponse.path("Cur_ID.size()");
        softAssert.assertEquals(recordsQuantity, EXPECTED_RECORDS_QUANTITY, "Quantity of records is wrong");

        Map<String, String> usdCur = listOfCurrenciesResponse.path("find{it.Cur_Abbreviation == 'USD'}");
        Gson gson = new Gson();
        String json = gson.toJson(usdCur);
        ObjectMapper obj = new ObjectMapper();
        CurrencyDescription usdDescription = obj.readValue(json, CurrencyDescription.class);

        Response usdDescriptionResponse = doGetRequest(EndPoints.USD_CURRENCY);
        CurrencyDescription currencyDescription = usdDescriptionResponse.getBody().as(CurrencyDescription.class);
        softAssert.assertEquals(usdDescription, currencyDescription, "Descriptions are not equal");

        Response usdRateForTodayResponse = doGetRequest(EndPoints.USD_CURRENCY_FOR_TODAY);
        CurrencyRates usd = usdRateForTodayResponse.getBody().as(CurrencyRates.class);
        System.out.println(usd);

        Response usdRateForPeriodResponse = doGetRequest(EndPoints.CURRENCIES_FOR_PERIOD);
        String maxRate = usdRateForPeriodResponse.path("max{it.Cur_OfficialRate}.Date");
        log.info(String.format("Max rate was on date %s", maxRate));
        String minRate = usdRateForPeriodResponse.path("min{it.Cur_OfficialRate}.Date");
        log.info(String.format("Min rate was on date %s", minRate));
        softAssert.assertAll();
    }


}