package testng.example;

import org.testng.annotations.Test;
import testng.BaseUITest;

import static example.pages.GoogleSearchPage.openGoogleSearchPage;

public class ExampleUITests extends BaseUITest {

    @Test
    public void verifyCanPerformSearch() {
        openGoogleSearchPage()
                .inputQuery("weather");
    }

}
