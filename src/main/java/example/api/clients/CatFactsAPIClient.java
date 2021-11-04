package example.api.clients;

import ta.core.api.BaseAPIClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class CatFactsAPIClient extends BaseAPIClient<CatFactsAPIClient> {

    @Step
    public CatFactsAPIClient getRandomFact() {
        setResponse(RestAssured.get("/fact"));
        return this;
    }

}
