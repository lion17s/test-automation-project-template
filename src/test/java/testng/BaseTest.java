package testng;

import ta.core.env.Environment;
import ta.core.testng.listeners.TestListener;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public abstract class BaseTest {

    @Parameters("env")
    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment(@Optional(value = "desktop.chrome") String env) {
        Environment.setEnvironment(env);
    }

}
