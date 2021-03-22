package testng.example;

import example.api.clients.CatFactsAPIClient;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import testng.BaseAPITest;

public class ExampleAPITests extends BaseAPITest {

    @Test(groups = "api.test.example")
    public void verifyCanGetRandomCatsFact() {
        new CatFactsAPIClient()
                .getRandomFact()
                .verifyStatusCode(HttpStatus.SC_OK);
    }

}
