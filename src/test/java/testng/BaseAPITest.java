package testng;

import com.ta.core.env.Environment;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public abstract class BaseAPITest extends BaseTest {

    @BeforeSuite(groups = "api.test.example", dependsOnMethods = "setupEnvironment")
    public void setupAPIClientEnvironment() {
        RestAssured.baseURI = Environment.get().getString("base.uri");
    }

}
