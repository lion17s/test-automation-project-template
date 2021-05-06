package example.api.clients;

import ta.core.api.BaseAPIClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class CatFactsAPIClient extends BaseAPIClient<CatFactsAPIClient> {

    private static final String BASE_PATH = "/facts";

    @Step
    public CatFactsAPIClient getRandomFact() {
        setResponse(RestAssured.given().basePath(BASE_PATH).get("/random"));
        return this;
    }

}
