package TestNGListenerPackage;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ExtentReportListenerUAT implements ITestListener {

  private static ExtentReports extentReportsUAT;
  private static final ThreadLocal<ExtentTest> extentTestUAT = new ThreadLocal<>();
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
  ExtentTest testUAT;
  WebDriver driverUAT;
  private String deviceBrowserUAT = "";
  private String deviceBrowserVersionUAT = "";

  @Override
  public void onStart(ITestContext contextUAT) {
    // Code to execute when the test suite starts
    // Generate the report file name with the test suite name and timestamp
    if (extentReportsUAT == null) {
      String suiteNameUAT = contextUAT.getSuite().getName(); // Get the test suite name
      String timestampUAT = new SimpleDateFormat("MMM-yyyy/dd-MM-yyyy HH.mm a").format(new Date()); // Format timestamp
      String reportFileNameUAT = "test-output/extent-reports/" + suiteNameUAT + "_" + timestampUAT + ".html";

      ExtentSparkReporter htmlReporterUAT = new ExtentSparkReporter(reportFileNameUAT);
     /* String cssPath = "C:/Users/rohit.mathur/IdeaProjects/Lending/customfile-custome.css";

      // Configure the reporter
      System.out.println("Applying CSS from: " + cssPath);
      System.out.println("CSS Path exists: " + new File(cssPath).exists());*/
      // htmlReporter.config().setCss(cssPath); // Uncomment if custom CSS needs to be applied
      // htmlReporter.config().setCss("body { background-color: #FF0000; }");  // Inject CSS directly for testing
      htmlReporterUAT.config().setTheme(Theme.STANDARD); // Use Theme.DARK for a dark theme
      htmlReporterUAT.config().setDocumentTitle("Automation Test Report");
      htmlReporterUAT.config().setReportName("Functional Test Report of: " + contextUAT.getSuite().getName());

      extentReportsUAT = new ExtentReports();
      extentReportsUAT.attachReporter(htmlReporterUAT);

      // Set system info
      extentReportsUAT.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
      extentReportsUAT.setSystemInfo("Java Version", System.getProperty("java.version"));
      extentReportsUAT.setSystemInfo("User Name", System.getProperty("user.name"));
      extentReportsUAT.setSystemInfo("User Home", System.getProperty("user.home"));
      extentReportsUAT.setSystemInfo("Java Vendor", System.getProperty("java.vendor"));
      extentReportsUAT.setSystemInfo("Architecture", System.getProperty("os.arch"));
      extentReportsUAT.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
      extentReportsUAT.setSystemInfo("Total Memory (MB)", String.valueOf(Runtime.getRuntime().totalMemory() / (1024 * 1024)));
      extentReportsUAT.setSystemInfo("Free Memory (MB)", String.valueOf(Runtime.getRuntime().freeMemory() / (1024 * 1024)));
    }

  }

  @Override
  public void onTestStart(ITestResult resultUAT) {
    testUAT = extentReportsUAT.createTest(resultUAT.getMethod().getMethodName());
    extentTestUAT.set(testUAT);
    // Dynamically assign details for each test
    String author = getAuthorFromAnnotations(resultUAT);  // Get author dynamically
    String category = "Tag_Name- " + resultUAT.getTestClass().getName();  // Category from class name
    String device = getDeviceInfoUAT(resultUAT);
    // Assign the dynamic values
    //testUAT.assignAuthor(author);
   // testUAT.assignCategory(category);
    testUAT.assignDevice(device);
    assignTags(resultUAT);
    /*extentTest.get().assignAuthor("Rohit Mathur");
    extentTest.get().assignCategory("Category Name: "+resultUAT.getTestClass().getName());
    extentTest.get().assignDevice("Device Info");*/
    //extentTest.get().info("Test Case Description: This test case verifies the login functionality.");
    logAndClearReporterMessages();
  }

  // Sample method to get author from annotations
  private String getAuthorFromAnnotations(ITestResult resultUAT) {
    // This method would fetch author information from test annotations or other sources
    // Example: Fetching from @Author annotation if available
    String authorMethodName = resultUAT.getTestClass().getName();
    return switch (authorMethodName) {
      case "CareInsurance.test.MobileOtpTest" -> "Rohit";
      case "CareInsurance.test.LoginTest" -> "ABCDE";
      case "CareInsurance.test.EditIndividualToFamilyFloaterTest" -> "XYZ";
      default -> "Hello";
    }; // Placeholder logic; update as needed
  }

  // Sample method to get device info
  private String getDeviceInfoUAT(ITestResult resultUAT) {
    //String dv= resultUAT.getInstanceName().toString();
    String browserUAT = BaseClassUAT.getBrowserNameUAT();
    System.out.println("browser name in device info of Extent UAT: " + browserUAT);

    // Null check for browserMTEST
    if (browserUAT == null) {
      System.out.println("Error: browserMTEST is null, defaulting to Chrome.");

      // Adjust as needed for a default case
    } else if (browserUAT.equalsIgnoreCase("chrome")) {
      deviceBrowserUAT = "Chrome";
      deviceBrowserVersionUAT = BaseClassUAT.getBrowserVersionUAT();
      System.out.println("browser version in listener: "+deviceBrowserVersionUAT);

    } else {
      deviceBrowserUAT = "FireFox";
      deviceBrowserVersionUAT = BaseClassUAT.getBrowserVersionUAT();
    }

    /*if(browserUAT.equalsIgnoreCase("chrome")){

        deviceBrowserUAT ="Chrome";
        deviceBrowserVersionUAT = BaseClassUAT.getBrowserVersionUAT();

      }
     else{
        deviceBrowserUAT ="FireFox";
        deviceBrowserVersionUAT = BaseClassUAT.getBrowserVersionUAT();
      }
*/
    String osInfo = System.getProperty("os.name") + " " + System.getProperty("os.version");
    return "Browser_Name- " + deviceBrowserUAT + ", and, Browser_Version- " + deviceBrowserVersionUAT;
    // This could fetch device info from test parameters or a configuration
    // Placeholder logic; update as needed
  }

  private void assignTags(ITestResult resultUAT) {
    // Assign tags based on test method or class
    // Example of assigning custom tags based on test method or class
    // Use the existing ExtentTest set in onTestStart, no need to create a new one
    ExtentTest test = extentTestUAT.get();
    //ExtentTest test = extentReportsUAT.createTest(resultUAT.getMethod().getMethodName());
    //extentTestUAT.set(test);
    String methodName = resultUAT.getMethod().getMethodName();
    String className = resultUAT.getTestClass().getName();

    // Assign tags based on method or class names, or any custom logic
    if (className.contains("LoginTest")) {
      test.assignCategory("LoginTests");
    } else if(className.contains("MobileOtpTest")){
      test.assignCategory("MobileOtpTest");
    } else if(className.contains("FormValidationTest")){
      test.assignCategory("FormValidationTest");
    } else if(className.contains("CalculatePremiumErrorMessageTest")){
      test.assignCategory("CalculatePremiumErrorMessageTest");
    } else if (className.contains("IndividualCareInsuranceTest")) {
      test.assignCategory("IndividualCareInsuranceTest");
    } else {
      test.assignCategory("Test with no defined tags");
    }


  }

  @Override
  public void onTestSuccess(ITestResult resultUAT) {
    extentTestUAT.get().pass("Test passed");
    Reporter.log("this test is passed: " + resultUAT.getTestClass());
    logAndClearReporterMessages();
  }

  private String getHumanReadableTimestamp() {
    long timestampMillis = System.currentTimeMillis();
    Date date = new Date(timestampMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH.mm a");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set timezone to UTC for consistency
    return sdf.format(date);
  }

  @Override
  public void onTestFailure(ITestResult resultUAT) {
// Log the failure message first

    BaseClassUAT base = (BaseClassUAT) resultUAT.getInstance();  // Cast the test instance to BaseClass
    driverUAT = base.getDriverUAT();
    extentTestUAT.get().fail(resultUAT.getThrowable());
    System.out.println("base clas from UAT: "+resultUAT.getInstance().toString());
      //WebDriver driver = ((WebDriver) resultUAT.getInstance());
    System.out.println("Base class from uat: " + resultUAT.getInstance().toString());

   // driverUAT = ((WebDriver) resultUAT.getInstance());
      // Use the WebDriver instance for further actions, like taking a screenshot
      System.out.println("WebDriver instance retrieved successfully UAT");


    // WebDriver instance retrieved successfully, take a screenshot
    if (driverUAT != null) {
      String screenshotPath = TestUtility.captureScreenshot(driverUAT, resultUAT.getMethod().getMethodName());
      if (screenshotPath != null) {
        // Attach screenshot to Extent Report
        extentTestUAT.get().fail(resultUAT.getThrowable(),
              MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
      } else {
        System.out.println("Failed to take screenshot.");
      }
    } else {
      System.out.println("Driver is null. Cannot take screenshot.");
    }
//      if (driverUAT != null) {
//        File screenshot = ((TakesScreenshot) driverUAT).getScreenshotAs(OutputType.FILE);
//        try {
//          // Create a unique file name with timestamp
//          String humanReadableTimestamp = getHumanReadableTimestamp();
//          String fileName = "screenshots/" + resultUAT.getMethod().getMethodName() + "_" + humanReadableTimestamp + ".png";
//          File destinationFile = new File(fileName);
//          FileUtils.copyFile(screenshot, destinationFile);
//
//          // Attach screenshot to Extent Report
//          extentTestUAT.get().fail(resultUAT.getThrowable(),
//                MediaEntityBuilder.createScreenCaptureFromPath(destinationFile.getAbsolutePath()).build());
//
//        } catch (IOException e) {
//          System.out.println("checking the error log: " + e.getMessage());
//          e.printStackTrace();
//        }
//      } else {
//        System.out.println("Driver is null. Cannot take screenshot.");
//      }
      // Additional logging (optional)
      Reporter.getCurrentTestResult();
      Reporter.log("This is the failed resultUAT: " + resultUAT.getName());
      Reporter.log("This method is failed: " + resultUAT.getMethod());
      Reporter.log("This is the class where method belongs: " + resultUAT.getTestClass());
      logAndClearReporterMessages();
    }

  @Override
  public void onTestSkipped(ITestResult resultUAT) {
    extentTestUAT.get().skip("Test skipped");
    logAndClearReporterMessages();
  }

  private void logAndClearReporterMessages() {
    List<String> reporterLogs = Reporter.getOutput();
    // Create a copy of the reporterLogs to avoid modifying the original list during iteration
    List<String> logsCopy = new ArrayList<>(reporterLogs);

    for (String log : logsCopy) {
      extentTestUAT.get().info(log);
    }
    reporterLogs.clear(); // Clear the reporter logs after adding them to the Extent Report
  }

  @Override
  public void onFinish(ITestContext context) {
    extentReportsUAT.flush();
  }


}
