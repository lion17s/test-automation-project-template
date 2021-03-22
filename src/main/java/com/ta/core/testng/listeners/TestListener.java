package com.ta.core.testng.listeners;

import com.ta.core.driver.DriverFactory;
import com.ta.core.reporting.allure.ReportingHelper;
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


@Log4j2
public class UITestListener extends XMLReporter implements IReporter, ITestListener, ISuiteListener, IAnnotationTransformer {

    private Map<String, Object> capabilities;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzerListener.class);
    }

    @Override
    public void onStart(ISuite suite) {
        log.debug(suite.getName() + " execution started");
        super.getConfig().setGenerateTestResultAttributes(true);
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " STARTED");
        capabilities = DriverFactory.getDriver().getCapabilities().asMap();
        ReportingHelper.startRecordingScreen(DriverFactory.getDriver(), false);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportingHelper.attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), false);
        ReportingHelper.attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), false);
        log.info(result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportingHelper.attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), true);
        ReportingHelper.attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), false);
        log.info(result.getMethod().getMethodName() + " FAILED");
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
