package api.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import utils.DateUtil;

public class EndPoint {
    protected static final String BASE_URL = "http://www.nbrb.by/API/ExRates";
    protected static final String AUTHORIZATION = "Authorization";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String ACCEPT = "Accept";
    protected static final int STATUS_CODE_OK = 200;
    protected static final String USD_CURRENCY = "/Currencies/145";
    protected static final String ALL_CURRENCIES = "/Currencies";
    protected static final String CURRENCIES_FOR_PERIOD = "Rates/Dynamics/145?startDate=2019-1-1&endDate=2019-12-1";
    protected static final String USD_CURRENCY_FOR_TODAY = String.format("Rates/143?onDate=%s", DateUtil.getCurrentDate());

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setAccept(APPLICATION_JSON)
            .setContentType(APPLICATION_JSON)
            .log(LogDetail.ALL)
            .build();

}
