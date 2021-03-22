package testng;

import com.ta.core.env.Environment;
import org.testng.annotations.*;

public abstract class BaseTest {

    @Parameters("env")
    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment(@Optional(value = "desktop.chrome") String env) {
        Environment.setEnvironment(env);
    }

}
