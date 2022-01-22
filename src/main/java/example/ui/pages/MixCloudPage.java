package example.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ta.core.driver.DriverFactory;
import ta.core.ui.BaseUIElement;

public class MixCloudPage extends BaseUIElement {

    @FindBy(className = "play-button-wrap")
    private WebElement playButton;

    @Step
    public static MixCloudPage openShowPage(String showUrl) {
        DriverFactory.getDriver().get("https://www.mixcloud.com/lion17s" + showUrl);
        return new MixCloudPage();
    }

    @Step
    public MixCloudPage playShow() {
        playButton.click();
        return this;
    }

    @Step
    public void keepListeningFor(long minutes) throws InterruptedException {
        Thread.sleep(minutes * 60 * 1000);
    }

}
