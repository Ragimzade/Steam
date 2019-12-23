package api.test;


import api.endpoint.EndPoints;
import api.model.CurrencyDescription;
import api.model.CurrencyRates;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Log;

import java.io.IOException;
import java.util.Map;


public class ApiTests extends Rest {
    private Log log = Log.getInstance();
    private static final String BASE_URL = "http://www.nbrb.by/API/ExRates";
    public static final int EXPECTED_RECORDS_QUANTITY = 225;

    @Test
    public void apiTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        Response listOfCurrenciesResponse = RestAssured.
                given()
                .baseUri(BASE_URL)
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(EndPoints.ALL_CURRENCIES)
                .then()
                .statusCode(200).contentType(ContentType.JSON).extract().response();
        int recordsQuantity = listOfCurrenciesResponse.path("Cur_ID.size()");
        softAssert.assertEquals(recordsQuantity, EXPECTED_RECORDS_QUANTITY, "Quantity of records is wrong");

        Map<String, String> usdCur = listOfCurrenciesResponse.path("find{it.Cur_Abbreviation == 'USD'}");
        Gson gson = new Gson();
        String json = gson.toJson(usdCur);
        ObjectMapper obj = new ObjectMapper();
        CurrencyDescription usdDescription = obj.readValue(json, CurrencyDescription.class);

        Response usdDescriptionResponse = RestAssured.
                given()
                .baseUri(BASE_URL)
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(EndPoints.USD_CURRENCY)
                .then()
                .statusCode(STATUS_CODE_OK).contentType(APPLICATION_JSON).extract().response();
        CurrencyDescription currencyDescription = usdDescriptionResponse.getBody().as(CurrencyDescription.class);
        softAssert.assertEquals(usdDescription, currencyDescription, "Descriptions are not equal");

        Response usdRateForTodayResponse = RestAssured.
                given()
                .baseUri(BASE_URL)
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(EndPoints.USD_CURRENCY_FOR_TODAY)
                .then()
                .statusCode(STATUS_CODE_OK).contentType(APPLICATION_JSON).extract().response();
        CurrencyRates usd = usdRateForTodayResponse.getBody().as(CurrencyRates.class);
        log.info(usd.toString());

        Response usdRateForPeriodResponse = RestAssured.
                given()
                .baseUri(BASE_URL)
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get(EndPoints.CURRENCIES_FOR_PERIOD)
                .then()
                .statusCode(STATUS_CODE_OK).contentType(APPLICATION_JSON).extract().response();
        String maxRate = usdRateForPeriodResponse.path("max{it.Cur_OfficialRate}.Date");
        log.info(String.format("Max rate was on date %s", maxRate));
        String minRate = usdRateForPeriodResponse.path("min{it.Cur_OfficialRate}.Date");
        log.info(String.format("Min rate was on date %s", minRate));
        softAssert.assertAll();
    }


}