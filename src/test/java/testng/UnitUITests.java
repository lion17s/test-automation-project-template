package testng;

import org.testng.annotations.Test;

import static experiment.google.GoogleSearchPage.openGoogleSearchPage;

public class UnitUITests extends BaseUITest {

    @Test
    public void verifyCanPerformSearch() {
        openGoogleSearchPage()
                .inputQuery("weather");
    }

}
