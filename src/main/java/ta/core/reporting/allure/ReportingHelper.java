package ta.core.reporting.allure;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ReportingHelper {

    private static String stopRecordingScreen(WebDriver driver) {
        String output = "";
        if (driver instanceof AndroidDriver) {
            log.info("stop recording android screen");
            output = ((AndroidDriver) driver).stopRecordingScreen();
        } else if (driver instanceof IOSDriver) {
            log.info("stop recording ios screen");
            output = ((IOSDriver) driver).stopRecordingScreen();
        }
        log.info("screen recording stopped");
        return output;
    }

    public static void startRecordingScreen(WebDriver driver, boolean shouldRecord) {
        if (driver instanceof AndroidDriver && shouldRecord) {
            log.info("start recording android screen");
            ((AndroidDriver) driver).startRecordingScreen();
            log.info("android screen recording started");
        } else if (driver instanceof IOSDriver && shouldRecord) {
            log.info("start recording ios screen");
            ((IOSDriver) driver).startRecordingScreen();
            log.info("ios screen recording started");
        }
    }

    public static void attachVideo(WebDriver driver, String name, boolean shouldAttach) {
        if (shouldAttach) {
            var output = stopRecordingScreen(driver);
            log.info("attaching video");
            Allure.addAttachment(name, new ByteArrayInputStream(Base64.decodeBase64(output)));
            log.info("video attached");
        }
    }

    public static void attachScreenshot(WebDriver driver, String name, boolean shouldAttach) {
        if (driver != null && shouldAttach) {
            var screenshotByteArray = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            log.info("attaching screenshot");
            Allure.addAttachment(name, new ByteArrayInputStream(screenshotByteArray));
            log.info("screenshot attached");
        }
    }

    @SneakyThrows
    public static void attachEnvironmentInfo(Map<String, Object> capabilities) {
        if (capabilities != null) {
            log.info("attaching environment info from capabilities <{}>", capabilities);
            var output = new FileOutputStream("build/allure-results/environment.properties");
            var properties = new Properties();
            capabilities.forEach((key, value) -> properties.setProperty(key, value.toString()));
            properties.store(output, "Environment info");
            log.info("environment info attached");
        }
    }

}
