package com.support;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends InitializeDriver implements ITestListener {

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    ExtentReports reports = ExtentReporting.getExtentInstance();

    @Override
    public void onTestStart(ITestResult result) {
        test = reports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        String testMethodName = result.getMethod().getMethodName();

        try {
           WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getSuperclass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
            extentTest.get().addScreenCaptureFromPath(getScreenshot(testMethodName, driver), testMethodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }
}
