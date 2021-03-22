package testng;

import com.ta.core.env.Environment;
import com.ta.core.testng.listeners.APITestListener;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(APITestListener.class)
public abstract class BaseAPITest extends BaseTest {

    @BeforeSuite(groups = "api.example.test")
    public void setupAPIClientEnvironment() {
        RestAssured.baseURI = Environment.get().getString("base.uri");
    }

}
