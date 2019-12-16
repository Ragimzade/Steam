package api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


public class ApiTests {
    private static final String BASE_URL = "http://www.nbrb.by/API/ExRates";

    public static Response doGetRequest(String endpoint) {
        return
                given().log().all().baseUri(BASE_URL).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void apiTest() throws IOException {
        Response response = doGetRequest(EndPoints.ALL_CURRENCIES);
        int size = response.path("Cur_ID.size()");
        Assert.assertEquals(size, 225);

        Map<String, String> usdCur = response.path("find{it.Cur_Abbreviation == 'USD'}");
        Gson gson = new Gson();
        String json = gson.toJson(usdCur);
        ObjectMapper obj = new ObjectMapper();
        CurrencyDescription m = obj.readValue(json, CurrencyDescription.class);

        Response response123 = doGetRequest(EndPoints.USD_CURRENCY);
        CurrencyDescription currencyDescription = response123.getBody().as(CurrencyDescription.class);
        System.out.println(currencyDescription.toString());
        Assert.assertEquals(m, currencyDescription);

        Response response2 = doGetRequest(EndPoints.USD_CURRENCY);
        CurrencyRates usd = response2.getBody().as(CurrencyRates.class);
        System.out.println(usd);

        Response response3 = doGetRequest(EndPoints.CURRENCIES_FOR_PERIOD);
//        float max = response3.path("Date.max{it.Cur_OfficialRate}");
        float min = response3.path("{it.Cur_OfficialRate}");
//        System.out.println(max);
        System.out.println(min);

    }


}