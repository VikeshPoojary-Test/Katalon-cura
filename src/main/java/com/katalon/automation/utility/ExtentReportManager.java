package com.katalon.automation.utility;

import com.katalon.automation.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager extends BasePage {

    static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    static ExtentReports extentReports;
    static String extentReportName = null;

    public static ExtentReports getReport(String testCaseName) {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            setupExtentReport();
        }
        return extentReports;
    }

    public static ExtentReports setupExtentReport() {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        String filePath=System.getProperty("user.dir")+"/src/test/result/"+date.replace("-","_")+".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Vikesh S D");

        reporter.config().setReportName("KATALON TEST Gegression");
        reporter.config().setTheme(Theme.DARK);

        return extentReports;
    }

    public static String setExtentReportName(String testCaseName) {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportName = testCaseName + "_" + date;
        return extentReportName;
    }

    public static void flushReport() {
        extentReports.flush();
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        ExtentTest test = extentReports.createTest(name, description);
        extentTest.set(test);
        return getTest();
    }

//    public synchronized static void attachImage(){
//        getTest().addScreenCaptureFromPath(getScreenshotDestinationPath());
//    }

    public synchronized static void log(String message){
        getTest().info(message);
    }

    public synchronized static void pass(String message){
        getTest().pass(message);
    }

    public synchronized static void fail(String message){
        getTest().fail(message);
    }
}

