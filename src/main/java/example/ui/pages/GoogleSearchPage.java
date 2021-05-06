package example.ui.pages;

import ta.core.driver.DriverFactory;
import ta.core.ui.BaseUIElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage extends BaseUIElement {

    @FindBy(name = "q")
    private WebElement searchInputField;

    @Step
    public static GoogleSearchPage openGoogleSearchPage() {
        DriverFactory.getDriver().get("https://google.com");
        return new GoogleSearchPage();
    }

    @Step
    public void inputQuery(String query) {
        searchInputField.sendKeys(query);
    }

}
