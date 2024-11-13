package TestNGListenerPackage;


import Interface.WebDriverProviderMTEST;
import LongTermLoanPackage.Main.LTBaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ExtentReportListenerMTEST implements ITestListener {

  private static ExtentReports extentReportsMTEST;
  private static ExtentTest extentTestMTEST;
  private static String suiteNameMTEST;
  private static String timestampMTEST;
  private static String reportFileNameMTEST;
  private static ExtentSparkReporter htmlReporterMTEST;
  private static final ThreadLocal<ExtentTest> extenttestMTEST = new ThreadLocal<>();



  /*static {

    // Create a unique report file name with timestamp
    String timestamp = new SimpleDateFormat("MMM-yyyy/dd-MM-yyyy HH.mm a").format(new Date());
    String reportFileName = "test-output/extent-reports/" + timestamp + ".html";

    ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFileName);
    String cssPath="C:/Users/rohit.mathur/IdeaProjects/Lending/customfile-custome.css";
    // Configure the reporter
    System.out.println("Applying CSS from: " + cssPath);
    System.out.println("CSS Path: " + new File(cssPath).exists());
    //htmlReporter.config().setCss(cssPath); // Ensure this path is correct
    //htmlReporter.config().setCss("body { background-color: #FF0000; }");  // Directly inject CSS for testing

    htmlReporter.config().setTheme(Theme.DARK); // Use Theme.DARK for a dark theme
    htmlReporter.config().setDocumentTitle("Automation Test Report");
    htmlReporter.config().setReportName("Functional Test Report");

    extentReports = new ExtentReports();
    extentReports.attachReporter(htmlReporter);

    // Set system info
    extentReports.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
    extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
    extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
    extentReports.setSystemInfo("User Home", System.getProperty("user.home"));
    extentReports.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
    extentReports.setSystemInfo("Architecture", System.getProperty("os.arch"));
    extentReports.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
    extentReports.setSystemInfo("Total Memory (MB)", String.valueOf(Runtime.getRuntime().totalMemory() / (1024 * 1024)));
    extentReports.setSystemInfo("Free Memory (MB)", String.valueOf(Runtime.getRuntime().freeMemory() / (1024 * 1024)));
  }*/
  private static String browserMTEST;
  Test testAnnotationMTEST;
  String testNameMTEST;
  ExtentTest testMTEST;
  String authorMTEST, categoryMTEST, deviceMTEST;
  long timestampMillis = System.currentTimeMillis();
  Date date = new Date(timestampMillis);
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH.mm a");
  WebDriver driverMTEST;
  String humanReadableTimestampMTEST, filenameMTEST;
  File destinationFileMTEST, screenshotMTEST;
  private String deviceBrowserMTEST = "", deviceBrowserVersionMTEST = "";

  @Override
  public void onStart(ITestContext contextMTEST) {
    // Code to execute when the test suite starts
    // Generate the report file name with the test suite name and timestamp
    if (extentReportsMTEST == null) {
      suiteNameMTEST = contextMTEST.getSuite().getName(); // Get the test suite name
      timestampMTEST = new SimpleDateFormat("MMM-yyyy/dd-MM-yyyy HH.mm a").format(new Date()); // Format timestampMTEST
      reportFileNameMTEST = "test-output/extent-reports/" + suiteNameMTEST + "_" + timestampMTEST + ".html";

      htmlReporterMTEST = new ExtentSparkReporter(reportFileNameMTEST);
     /* String cssPath = "C:/Users/rohit.mathur/IdeaProjects/Lending/customfile-custome.css";

      // Configure the reporter
      System.out.println("Applying CSS from: " + cssPath);
      System.out.println("CSS Path exists: " + new File(cssPath).exists());*/
      // htmlReporterMTEST.config().setCss(cssPath); // Uncomment if custom CSS needs to be applied
      // htmlReporterMTEST.config().setCss("body { background-color: #FF0000; }");  // Inject CSS directly for testing
      htmlReporterMTEST.config().setTheme(Theme.DARK); // Use Theme.DARK for a dark theme
      htmlReporterMTEST.config().setDocumentTitle("Automation Test Report");
      htmlReporterMTEST.config().setReportName("Functional Test Report of: " + contextMTEST.getSuite().getName());

      extentReportsMTEST = new ExtentReports();
      extentReportsMTEST.attachReporter(htmlReporterMTEST);

      // Set system info
      extentReportsMTEST.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
      extentReportsMTEST.setSystemInfo("Java Version", System.getProperty("java.version"));
      extentReportsMTEST.setSystemInfo("User Name", System.getProperty("user.name"));
      extentReportsMTEST.setSystemInfo("User Home", System.getProperty("user.home"));
      extentReportsMTEST.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
      extentReportsMTEST.setSystemInfo("Architecture", System.getProperty("os.arch"));
      extentReportsMTEST.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
      extentReportsMTEST.setSystemInfo("Total Memory (MB)", String.valueOf(Runtime.getRuntime().totalMemory() / (1024 * 1024)));
      extentReportsMTEST.setSystemInfo("Free Memory (MB)", String.valueOf(Runtime.getRuntime().freeMemory() / (1024 * 1024)));
    }

  }

  @Override
  public void onTestStart(ITestResult resultMTEST) {

    // Fetch the testName from the @Test annotation
    testAnnotationMTEST = resultMTEST.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
    testNameMTEST = testAnnotationMTEST.testName();


    // Use the testName if specified, otherwise fallback to the method name
    testMTEST = extentReportsMTEST.createTest(testNameMTEST.isEmpty() ? resultMTEST.getMethod().getMethodName() : testNameMTEST);
    extenttestMTEST.set(testMTEST);
    // Dynamically assign details for each testMTEST
    authorMTEST = getAuthorFromAnnotationsMTEST(resultMTEST);  // Get author dynamically
    categoryMTEST = "Category Name: " + resultMTEST.getTestClass().getName();  // Category from class name
    deviceMTEST = getDeviceInfoMTEST(resultMTEST);
    // Assign the dynamic values
    testMTEST.assignAuthor(authorMTEST);
    testMTEST.assignCategory(categoryMTEST);
    testMTEST.assignDevice(deviceMTEST);
    //assignTags(resultMTEST);
    /*extentTest.get().assignAuthor("Rohit Mathur");
    extentTest.get().assignCategory("Category Name: "+resultMTEST.getTestClass().getName());
    extentTest.get().assignDevice("Device Info");*/
    //extentTest.get().info("Test Case Description: This testMTEST case verifies the login functionality.");
    logAndClearReporterMessagesMTEST();
  }

  // Sample method to get author from annotations
  private String getAuthorFromAnnotationsMTEST(ITestResult resultMTEST) {
    // This method would fetch author information from test annotations or other sources
    // Example: Fetching from @Author annotation if available
    /*String authorMethodName=result.getTestClass().getName();
    String authorName;
    if(authorMethodName.equals("CareInsurance.test.MobileOtp")){
      authorName="Rohit";
    } else{
      authorName="Hello";

    }*/
    return "Rohit"; // Placeholder logic; update as needed
  }

  // Sample method to get device info
  private String getDeviceInfoMTEST(ITestResult resultMTEST) {

    browserMTEST = LTBaseClass.getBrowserNameMTEST();
    System.out.println("this is the browser name for MTEST: " + browserMTEST);
// Null check for browserMTEST
    if (browserMTEST == null) {
      System.out.println("Error: browserMTEST is null, defaulting to Chrome.");
      deviceBrowserMTEST = "Chrome";
      deviceBrowserVersionMTEST = LTBaseClass.getBrowserVersionMTEST(); // Adjust as needed for a default case
    } else if (browserMTEST.equalsIgnoreCase("chrome")) {
      deviceBrowserMTEST = "Chrome";
      deviceBrowserVersionMTEST = LTBaseClass.getBrowserVersionMTEST();
    } else {
      deviceBrowserMTEST = "FireFox";
      deviceBrowserVersionMTEST = LTBaseClass.getBrowserVersionMTEST();
    }
    /*if(browserMTEST.equalsIgnoreCase("chrome")){

        deviceBrowserMTEST ="Chrome";
        deviceBrowserVersionMTEST=LTBaseClass.getBrowserVersionMTEST();

      }
     else{
        deviceBrowserMTEST ="FireFox";
        deviceBrowserVersionMTEST= LTBaseClass.getBrowserVersionMTEST();
      }
*/

    String osInfo = System.getProperty("os.name") + " " + System.getProperty("os.version");
    return "Browser Name: " + deviceBrowserMTEST + ", \n Browser Version: " + deviceBrowserVersionMTEST;
    // This could fetch device info from test parameters or a configuration
    // Placeholder logic; update as needed
  }

  private void assignTags(ITestResult resultMTEST) {
    // Assign tags based on test method or class
    // Example of assigning custom tags based on test method or class
    ExtentTest test = extentReportsMTEST.createTest(resultMTEST.getMethod().getMethodName());
    extenttestMTEST.set(test);
    String methodName = resultMTEST.getMethod().getMethodName();
    String className = resultMTEST.getTestClass().getName();

    // Assign tags based on method or class names, or any custom logic
    if (methodName.contains("Login")) {
      test.assignCategory("Login Tests");
    }
    if (className.contains("IndividualCareInsurance")) {
      test.assignCategory("Individual Care Insurance");
    } else {
      test.assignCategory("Custom Tag");
    }


  }

  @Override
  public void onTestSuccess(ITestResult resultMTEST) {
    extenttestMTEST.get().pass("Test passed");
    Reporter.log("this test is passed: " + resultMTEST.getTestClass());
    logAndClearReporterMessagesMTEST();
  }

  private String getHumanReadableTimestampMTEST() {

    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set timezone to UTC for consistency
    return sdf.format(date);
  }

  @Override
  public void onTestFailure(ITestResult resultMTEST) {

    /*System.out.println("base class from mtest: "+result.getInstance().toString());
      WebDriver driver = ((LTBaseClass) result.getInstance()).driver;*/
    //System.out.println("Base class from mtest: " + ((WebDriverProviderMTEST) resultMTEST.getInstance()).getDriverMTEST());
    //System.out.println("checking in failure: "+resultMTEST.getInstance());
    // Check if the test instance implements the WebDriverProvider interface
    if (resultMTEST.getInstance() instanceof WebDriverProviderMTEST) {
      driverMTEST = ((WebDriverProviderMTEST) resultMTEST.getInstance()).getDriverMTEST();
      // Use the WebDriver instance for further actions, like taking a screenshot
      System.out.println("WebDriver instance retrieved successfully of Mtest");
      if (driverMTEST != null) {
        screenshotMTEST = ((TakesScreenshot) driverMTEST).getScreenshotAs(OutputType.FILE);
        try {
          // Create a unique file name with timestamp
          humanReadableTimestampMTEST = getHumanReadableTimestampMTEST();
          filenameMTEST = "screenshots_MTEST/" + resultMTEST.getMethod().getMethodName() + "_" + humanReadableTimestampMTEST + ".png";
          destinationFileMTEST = new File(filenameMTEST);
          FileUtils.copyFile(screenshotMTEST, destinationFileMTEST);

          // Attach screenshotMTEST to Extent Report
          extenttestMTEST.get().fail(resultMTEST.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(destinationFileMTEST.getAbsolutePath()).build());

        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("Driver is null. Cannot take screenshot.");
      }

      // Additional logging (optional)
      Reporter.log("This is the failed result: " + resultMTEST.getName());
      Reporter.log("This method is failed: " + resultMTEST.getMethod());
      Reporter.log("This is the class where method belongs: " + resultMTEST.getTestClass());
      logAndClearReporterMessagesMTEST();
    } else {
      System.out.println("Test instance does not implement WebDriverProvider of Mtest");
    }


  }

  @Override
  public void onTestSkipped(ITestResult resultMTEST) {
    extenttestMTEST.get().skip("Test skipped");
    logAndClearReporterMessagesMTEST();
  }

  private void logAndClearReporterMessagesMTEST() {
    List<String> reporterLogsMTEST = Reporter.getOutput();
    for (String logMTEST : reporterLogsMTEST) {
      extenttestMTEST.get().info(logMTEST);
    }
    reporterLogsMTEST.clear(); // Clear the reporter logs after adding them to the Extent Report
  }

  @Override
  public void onFinish(ITestContext contextMTEST) {
    extentReportsMTEST.flush();
  }
}
