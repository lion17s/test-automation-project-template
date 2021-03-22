package com.ta.core.testng.listeners;

import com.ta.core.driver.DriverFactory;
import com.ta.core.reporting.ReportingHelper;
import lombok.extern.log4j.Log4j2;
import org.testng.ISuite;
import org.testng.ITestResult;

import java.util.Map;
import java.util.UUID;

@Log4j2
public class UITestListener extends BaseTestListener {

    private Map<String, Object> capabilities;

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        capabilities = DriverFactory.getDriver().getCapabilities().asMap();
        ReportingHelper.startRecordingScreen(DriverFactory.getDriver(), false);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportingHelper.attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), false);
        ReportingHelper.attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), false);
        super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportingHelper.attachScreenshot(DriverFactory.getDriver(), "screenshot-" + UUID.randomUUID(), true);
        ReportingHelper.attachVideo(DriverFactory.getDriver(), "video-" + UUID.randomUUID(), false);
        super.onTestFailure(result);
    }

    @Override
    public void onFinish(ISuite suite) {
        ReportingHelper.attachEnvironmentInfo(capabilities);
        super.onFinish(suite);
    }

}
