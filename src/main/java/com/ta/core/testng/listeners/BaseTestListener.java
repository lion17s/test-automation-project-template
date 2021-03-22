package com.ta.core.testng.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.reporters.XMLReporter;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;


@Log4j2
public class BaseTestListener extends XMLReporter implements IReporter, ITestListener, ISuiteListener, IAnnotationTransformer {

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
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
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
        log.debug(suite.getName() + " execution finished");
    }

}
