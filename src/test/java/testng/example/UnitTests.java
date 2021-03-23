package testng.example;

import com.ta.core.env.Environment;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UnitTests {

    @Test
    public void unitTest0() {
        Environment.setEnvironment("iphone.12.pro.max.safari");
        Map<String, Object> output = Environment.getValueOrDefault("appium", new HashMap<>());
        System.out.println(output);
    }

}
