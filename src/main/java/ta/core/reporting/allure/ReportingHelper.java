package ta.core.reporting.allure;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class ReportingHelper {

    @SuppressWarnings("rawtypes")
    private static String stopRecordingScreen(WebDriver driver) {
        String output = "";
        if (driver instanceof AndroidDriver) {
            log.debug("stop recording android screen");
            output = ((AndroidDriver) driver).stopRecordingScreen();
        } else if (driver instanceof IOSDriver) {
            log.debug("stop recording ios screen");
            output = ((IOSDriver) driver).stopRecordingScreen();
        }
        log.debug("screen recording stopped");
        return output;
    }

    @SuppressWarnings("rawtypes")
    public static void startRecordingScreen(WebDriver driver, boolean shouldRecord) {
        if (driver instanceof AndroidDriver && shouldRecord) {
            log.debug("start recording android screen");
            ((AndroidDriver) driver).startRecordingScreen();
            log.debug("android screen recording started");
        } else if (driver instanceof IOSDriver && shouldRecord) {
            log.debug("start recording ios screen");
            ((IOSDriver) driver).startRecordingScreen();
            log.debug("ios screen recording started");
        }
    }

    public static void attachVideo(WebDriver driver, String name, boolean shouldAttach) {
        if (shouldAttach) {
            var output = stopRecordingScreen(driver);
            log.debug("attaching video");
            Allure.addAttachment(name, new ByteArrayInputStream(Base64.decodeBase64(output)));
            log.debug("video attached");
        }
    }

    public static void attachScreenshot(WebDriver driver, String name, boolean shouldAttach) {
        if (driver != null && shouldAttach) {
            var screenshotByteArray = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            log.debug("attaching screenshot");
            Allure.addAttachment(name, new ByteArrayInputStream(screenshotByteArray));
            log.debug("screenshot attached");
        }
    }

    @SneakyThrows
    public static void attachEnvironmentInfo(Map<String, Object> capabilities) {
        if (capabilities != null) {
            log.debug("attaching environment info from capabilities \n{}", capabilities);
            var output = new FileOutputStream("build/allure-results/environment.properties");
            var properties = new Properties();
            capabilities.forEach((key, value) -> properties.setProperty(key, value.toString()));
            properties.store(output, "Environment info");
            log.debug("environment info attached");
        }
    }

}
