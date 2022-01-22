package ta.core.testng.listeners;

import ta.core.driver.DriverFactory;
import ta.core.env.Environment;
import ta.core.reporting.allure.ReportingHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.HasCapabilities;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.XMLReporter;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
public class TestListener extends XMLReporter
        implements IReporter, ITestListener, ISuiteListener, IAnnotationTransformer {

    private Map<String, Object> capabilities;
    private boolean shouldAlwaysAttachScreenshot;
    private boolean shouldAlwaysAttachVideo;
    private boolean shouldAttachScreenshot;
    private boolean shouldAttachVideo;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzerListener.class);
    }

    @Override
    public void onStart(ISuite suite) {
        log.info("{} execution started", suite.getName());
        super.getConfig().setGenerateTestResultAttributes(true);
        RestAssured.filters(new AllureRestAssured());
        if (log.isDebugEnabled()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{} STARTED", result.getMethod().getMethodName());
        if (DriverFactory.getDriver() != null) {
            capabilities = ((HasCapabilities) DriverFactory.getDriver()).getCapabilities().asMap();
            shouldAlwaysAttachScreenshot = Environment.getCurrentEnvironment().getOrDefault("alwaysAttachScreenshot", false);
            shouldAlwaysAttachVideo = Environment.getCurrentEnvironment().getOrDefault("alwaysAttachVideo", false);
            shouldAttachScreenshot = Environment.getCurrentEnvironment().getOrDefault("attachScreenshot", shouldAlwaysAttachScreenshot);
            shouldAttachVideo = Environment.getCurrentEnvironment().getOrDefault("attachVideo", shouldAlwaysAttachVideo);
        }
        ReportingHelper.startRecordingScreen(DriverFactory.getDriver(), shouldAttachVideo);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("{} PASSED", result.getMethod().getMethodName());
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), shouldAlwaysAttachScreenshot);
        ReportingHelper
                .attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), shouldAlwaysAttachVideo);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("{} FAILED", result.getMethod().getMethodName());
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), shouldAttachScreenshot);
        ReportingHelper
                .attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), shouldAttachVideo);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} SKIPPED", result.getMethod().getMethodName());
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        super.generateReport(xmlSuites, suites, outputDirectory);
    }

    @Override
    public void onFinish(ISuite suite) {
        ReportingHelper.attachEnvironmentInfo(capabilities);
        log.info("{} execution finished", suite.getName());
    }

}
