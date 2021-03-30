package testng;

import com.ta.core.driver.DriverFactory;
import com.ta.core.env.Environment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseUITest extends BaseTest {

    @BeforeMethod(groups = "ui.test.example")
    public void setupDriver() {
        String driver = Environment.get().getString("driver");
        Map<String, Object> capabilities = Environment.getValueOrDefault(driver, new HashMap<>());
        DriverFactory.setDriver(driver, capabilities);

    }

    @AfterMethod(groups = "ui.test.example")
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

}
