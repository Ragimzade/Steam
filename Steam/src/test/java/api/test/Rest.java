package api.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class Rest {
    protected static final String AUTHORIZATION = "Authorization";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String ACCEPT = "Accept";
    protected static final int STATUS_CODE_OK = 200;

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setAccept(APPLICATION_JSON)
            .setContentType(APPLICATION_JSON)
            .log(LogDetail.ALL)
            .build();

}
