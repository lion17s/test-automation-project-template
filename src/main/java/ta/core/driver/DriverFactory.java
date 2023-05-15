package ta.core.driver;

import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import lombok.extern.slf4j.Slf4j;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@SuppressWarnings("unused")
public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_INSTANCE = new ThreadLocal<>();

    private static String getPlatformNameFromCapabilities(Map<String, Object> capabilities) {
        log.info("getting platform name capability");
        var platformName = capabilities.getOrDefault("platformName", "").toString();
        log.info("platform name is <{}>", platformName);
        return platformName;
    }

    @SneakyThrows
    private static URL getURLFromCapabilities(Map<String, Object> capabilities) {
        log.info("getting url from capabilities");
        var url = new URL(capabilities.getOrDefault("hub", "").toString());
        log.info("url is <{}>", url);
        return url;
    }

    private static WebDriver decorateDriver(WebDriver driver) {
        log.debug("decorating driver");
        var listener = new WebDriverListener() {
            @Override
            public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
                var param = (null != args) ? Arrays.toString(args) : "";
                log.debug(method.getName() + " " + param);
            }
        };
        var decoratedDriver = new EventFiringDecorator<>(listener).decorate(driver);
        log.debug("driver decorated");
        return decoratedDriver;
    }

    private static AppiumDriver initAppiumDriver(Map<String, Object> capabilities) {
        log.info("initializing appium driver");
        var url = getURLFromCapabilities(capabilities);
        var platformName = getPlatformNameFromCapabilities(capabilities);
        var desiredCapabilities = new DesiredCapabilities(capabilities);
        if (platformName.equalsIgnoreCase(Platform.ANDROID.name())) {
            var androidDriver = new AndroidDriver(url, desiredCapabilities);
            log.debug("appium android driver initialized with capabilities: {}\n{}", url, desiredCapabilities);
            return androidDriver;
        } else if (platformName.equalsIgnoreCase(Platform.IOS.name())) {
            var iosDriver = new IOSDriver(url, desiredCapabilities);
            log.debug("appium ios driver initialized with capabilities: {}\n{}", url, desiredCapabilities);
            return iosDriver;
        } else {
            throw new ExceptionInInitializerError("missing platformName capability");
        }
    }

    private static WebDriver initRemoteWebDriver(Map<String, Object> capabilities) {
        if (!getURLFromCapabilities(capabilities).toString().isEmpty()) {
            var url = getURLFromCapabilities(capabilities);
            var remoteWebDriver = new RemoteWebDriver(url, new DesiredCapabilities(capabilities));
            log.debug("remote web driver initialized with capabilities: {}\n{}", url, capabilities);
            return decorateDriver(remoteWebDriver);
        } else {
            throw new ExceptionInInitializerError("missing hub capability");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static WebDriver initWebDriver(String driverName, Map<String, Object> capabilities) {
        WebDriver driver;
        if (driverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            var chromeOptions = new ChromeOptions();
            chromeOptions.addArguments((ArrayList) capabilities.getOrDefault("args", new ArrayList<>()));
            driver = new ChromeDriver(chromeOptions);
            log.debug("chrome driver initialized with options:\n{}", chromeOptions);
            return decorateDriver(driver);
        } else if (driverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            var firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments((ArrayList) capabilities.getOrDefault("args", new ArrayList<>()));
            driver = new FirefoxDriver(firefoxOptions);
            log.debug("firefox driver initialized with options:\n{}", firefoxOptions);
            return decorateDriver(driver);
        } else if (driverName.equalsIgnoreCase("safari")) {
            var safariOptions = new SafariOptions();
            capabilities.forEach(safariOptions::setCapability);
            driver = new SafariDriver(safariOptions);
            log.debug("safari driver initialized with options:\n{}", safariOptions);
            return decorateDriver(driver);
        } else if (driverName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            var edgeOptions = new EdgeOptions();
            capabilities.forEach(edgeOptions::setCapability);
            driver = new EdgeDriver(edgeOptions);
            log.debug("edge driver initialized with options:\n{}", edgeOptions);
            return decorateDriver(driver);
        } else {
            throw new ExceptionInInitializerError("missing driver capability");
        }
    }

    public static void setDriver(String driverName, Map<String, Object> capabilities) {
        WebDriver driver = null;
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
            case "firefox":
            case "safari":
            case "edge": {
                driver = initWebDriver(driverName, capabilities);
                break;
            }
        }
        DRIVER_INSTANCE.set(driver);
    }

    public static WebDriver getDriver() {
        return DRIVER_INSTANCE.get();
    }

    public static WebDriverWait getDriverWait(int timeOutInSeconds) {
        return new WebDriverWait(DRIVER_INSTANCE.get(), Duration.ofSeconds(timeOutInSeconds));
    }

    public static void quitDriver() {
        if (DRIVER_INSTANCE.get() != null) {
            DRIVER_INSTANCE.get().quit();
            DRIVER_INSTANCE.remove();
        }
    }

}
