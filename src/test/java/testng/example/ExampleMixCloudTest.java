package testng.example;

import example.ui.pages.MixCloudPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testng.BaseUITest;

import java.util.Random;

public class ExampleMixCloudTest extends BaseUITest {

    @DataProvider(parallel = true)
    private static Object[][] showProvider() {
        return new Object[][]{
                {"/progressive-mix-10/"},
                {"/progressive-mix-9/"}
        };
    }

    @Test(groups = "ui.test.example", dataProvider = "showProvider")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void verifyCanListenToShow(String showUrl) throws InterruptedException {
        int minutesToListen = new Random().ints(1, 60).findFirst().getAsInt();
        System.out.println("Minutest to listen " + minutesToListen);
        MixCloudPage
                .openShowPage(showUrl)
                .playShow()
                .keepListeningFor(minutesToListen);
    }

}
