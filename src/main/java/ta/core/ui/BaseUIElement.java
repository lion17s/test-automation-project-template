package ta.core.ui;

import ta.core.driver.DriverFactory;
import ta.core.env.Environment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.Duration;

public abstract class BaseUIElement {

    private static final int WAIT_TIME_SECONDS = Environment.getCurrentEnvironment().getOrDefault("driverWait", 3);

    public BaseUIElement() {
        if (DriverFactory.getDriver() instanceof AppiumDriver) {
            PageFactory.initElements(new AppiumFieldDecorator(
                    DriverFactory.getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS)), this);
        } else {
            PageFactory.initElements(new AjaxElementLocatorFactory(
                    DriverFactory.getDriver(), WAIT_TIME_SECONDS), this);
        }
    }

}
