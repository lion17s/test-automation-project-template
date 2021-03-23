package com.ta.core.testng.listeners;

import com.ta.core.driver.DriverFactory;
import com.ta.core.env.Environment;
import com.ta.core.reporting.allure.ReportingHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.log4j.Log4j2;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.XMLReporter;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.mapper.ObjectMapperType.GSON;


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
        log.debug(suite.getName() + " execution started");
        super.getConfig().setGenerateTestResultAttributes(true);
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(GSON));
        RestAssured.filters(new AllureRestAssured());
        if (log.isDebugEnabled()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " STARTED");
        ReportingHelper.startRecordingScreen(DriverFactory.getDriver(), false);
        if (DriverFactory.getDriver() != null) {
            capabilities = DriverFactory.getDriver().getCapabilities().asMap();
            shouldAlwaysAttachScreenshot = Environment.getBooleanOrDefault("alwaysAttachScreenshot", false);
            shouldAlwaysAttachVideo = Environment.getBooleanOrDefault("alwaysAttachVideo", false);
            shouldAttachScreenshot = Environment.getBooleanOrDefault("attachScreenshot", shouldAlwaysAttachScreenshot);
            shouldAttachVideo = Environment.getBooleanOrDefault("attachVideo", shouldAlwaysAttachVideo);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " PASSED");
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), shouldAlwaysAttachScreenshot);
        ReportingHelper
                .attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), shouldAlwaysAttachVideo);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " FAILED");
        ReportingHelper
                .attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), shouldAttachScreenshot);
        ReportingHelper
                .attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), shouldAttachVideo);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " SKIPPED");
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        super.generateReport(xmlSuites, suites, outputDirectory);
    }

    @Override
    public void onFinish(ISuite suite) {
        ReportingHelper.attachEnvironmentInfo(capabilities);
        log.debug(suite.getName() + " execution finished");
    }

}
