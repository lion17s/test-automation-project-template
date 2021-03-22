package testng;

import com.ta.core.driver.DriverFactory;
import com.ta.core.env.Environment;
import com.ta.core.testng.listeners.UITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.HashMap;

@Listeners(UITestListener.class)
public abstract class BaseUITest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setupDriver() {
        String driver = Environment.get().getString("driver");
        DriverFactory.setDriver(driver, Environment.getObjectOrDefault(driver, new HashMap<>()));

    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

}
