package com.ta.core.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.mac.Mac2Driver;
import io.appium.java_client.mac.Mac2Element;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

@Log4j2
@SuppressWarnings("unused")
public class DriverFactory {

    private static final ThreadLocal<RemoteWebDriver> DRIVER_INSTANCE = new ThreadLocal<>();

    private static String getPlatformNameFromCapabilities(Map<String, Object> capabilities) {
        return capabilities.getOrDefault("platformName", "").toString();
    }

    @SneakyThrows
    private static URL getURLFromCapabilities(Map<String, Object> capabilities) {
        return new URL(capabilities.getOrDefault("hub", "").toString());
    }

    private static AppiumDriver<?> initAppiumDriver(Map<String, Object> capabilities) {
        URL url = getURLFromCapabilities(capabilities);
        String platformName = getPlatformNameFromCapabilities(capabilities);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(capabilities);
        if (platformName.equalsIgnoreCase(Platform.ANDROID.name())) {
            return new AndroidDriver<MobileElement>(url, desiredCapabilities);
        } else if (platformName.equalsIgnoreCase(Platform.IOS.name())) {
            return new IOSDriver<MobileElement>(url, desiredCapabilities);
        } else if (platformName.equalsIgnoreCase(Platform.WINDOWS.name())) {
            return new WindowsDriver<WindowsElement>(url, desiredCapabilities);
        } else if (platformName.equalsIgnoreCase(Platform.MAC.name())) {
            return new Mac2Driver<Mac2Element>(url, desiredCapabilities);
        } else {
            throw new ExceptionInInitializerError("missing <platformName> capability");
        }
    }

    private static RemoteWebDriver initRemoteWebDriver(Map<String, Object> capabilities) {
        if (!getURLFromCapabilities(capabilities).toString().isEmpty()) {
            return new RemoteWebDriver(getURLFromCapabilities(capabilities), new DesiredCapabilities(capabilities));
        } else {
            throw new ExceptionInInitializerError("missing <hub> capability");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static RemoteWebDriver initWebDriver(String driverName, Map<String, Object> capabilities) {
        if (driverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            capabilities.forEach(chromeOptions::setCapability);
            chromeOptions.addArguments((ArrayList) capabilities.getOrDefault("arguments", new ArrayList<>()));
            return new ChromeDriver(chromeOptions);
        } else if (driverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            capabilities.forEach(firefoxOptions::setCapability);
            firefoxOptions.addArguments((ArrayList) capabilities.getOrDefault("arguments", new ArrayList<>()));
            return new FirefoxDriver(firefoxOptions);
        } else {
            throw new ExceptionInInitializerError("missing <driver> capability");
        }
    }

    public static void setDriver(String driverName, Map<String, Object> capabilities) {
        RemoteWebDriver driver = null;
        switch (driverName.toLowerCase()) {
            case "appium": {
                driver = initAppiumDriver(capabilities);
                break;
            }
            case "remote": {
                driver = initRemoteWebDriver(capabilities);
                break;
            }
            case "chrome":
            case "firefox": {
                driver = initWebDriver(driverName, capabilities);
                break;
            }
        }
        DRIVER_INSTANCE.set(driver);
    }

    public static RemoteWebDriver getDriver() {
        return DRIVER_INSTANCE.get();
    }

    public static WebDriverWait getDriverWait(int timeOutInSeconds) {
        return new WebDriverWait(DRIVER_INSTANCE.get(), timeOutInSeconds);
    }

    public static void quitDriver() {
        if (DRIVER_INSTANCE.get() != null) {
            DRIVER_INSTANCE.get().quit();
            DRIVER_INSTANCE.remove();
        }
    }

}
