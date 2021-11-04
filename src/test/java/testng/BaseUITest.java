package testng;

import ta.core.driver.DriverFactory;
import ta.core.env.Environment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseUITest extends BaseTest {

    @BeforeMethod(groups = "ui.test.example")
    public void setupDriver() {
        String driver = Environment.getCurrentEnvironment().get("driver");
        Map<String, Object> capabilities = Environment.getCurrentEnvironment().getOrDefault(driver, new HashMap<>());
        DriverFactory.setDriver(driver, capabilities);

    }

    @AfterMethod(groups = "ui.test.example")
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

}
