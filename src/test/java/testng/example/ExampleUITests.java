package testng.example;

import org.testng.annotations.Test;
import testng.BaseUITest;

import static example.ui.pages.GoogleSearchPage.openGoogleSearchPage;

public class ExampleUITests extends BaseUITest {

    @Test(groups = "ui.test.example")
    public void verifyCanPerformSearch() {
        openGoogleSearchPage()
                .inputQuery("weather");
    }

}
