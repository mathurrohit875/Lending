package CareInsurance.main;


import DBPackage.DbClass;
import Interface.WebDriverProviderUAT;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;


public class BaseClassUAT  {
  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/config.properties";
  private static String browserNameUAT;
  private static String browserVersionUAT;
  protected WebDriver driverUAT;
  protected CIPOM cipom;
  protected CIMandatoryMsgPOM ciMandatoryMsgPOM;
  protected Properties propertiesUAT;
  TestUtility testUtility=new TestUtility();
  @BeforeClass
  public void setupUAT() throws IOException {
    // Load properties file
    propertiesUAT = new Properties();
    try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
      propertiesUAT.load(fileInputStream);
    } catch (IOException e) {
      System.out.println("HUSCUSOCUSOOY SICYS OIC SCOYIS CYO");
      throw new RuntimeException("Failed to load properties file", e);
    }

    // Get browser type from properties
    browserNameUAT = propertiesUAT.getProperty("browserUAT");
    System.out.println("browser name in UAT base class from property: " + browserNameUAT);
    // Initialize WebDriver based on browser type
    initializeDriver();
    driverUAT.manage().window().maximize();
    //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    driverUAT.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Initialize Page Object Model (POM) classes
    cipom = new CIPOM(driverUAT);
    ciMandatoryMsgPOM = new CIMandatoryMsgPOM(driverUAT);

    // Open the URL from properties
    String url = propertiesUAT.getProperty("urlUAT");
    driverUAT.get(url);
  }

  private void initializeDriver() {
    if ("chrome".equalsIgnoreCase(browserNameUAT)) {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setAcceptInsecureCerts(true);
      driverUAT = new ChromeDriver(chromeOptions);
      // Get browser version from the Capabilities
      Capabilities capabilities = ((RemoteWebDriver) driverUAT).getCapabilities();
      browserVersionUAT = capabilities.getBrowserVersion();
      //browserVersionUAT= chromeOptions.getBrowserVersion();
      System.out.println("browser version: "+browserVersionUAT);
    } else if ("firefox".equalsIgnoreCase(browserNameUAT)) {
      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setAcceptInsecureCerts(true);
      firefoxOptions.setBinary("C:/Users/rohit.mathur/AppData/Local/Mozilla Firefox/firefox.exe");
      driverUAT = new FirefoxDriver(firefoxOptions);
      browserVersionUAT=firefoxOptions.getBrowserVersion();
    } else {
      throw new IllegalArgumentException("Unsupported browser: " + browserNameUAT);
    }
  }

  public void CImain(String mobilenumber) throws SQLException {
    cipom.careInsurance();
    Reporter.log("random number: " + mobilenumber);
    cipom.entermobileotp(mobilenumber);
    DbClass dbClass = new DbClass();
    String query = "select * from lt_logotp where mobile='" + mobilenumber + "'";
    Reporter.log("this is query: " + query);
    try (ResultSet resultSet = dbClass.executeQuery(query)) {
      while (resultSet.next()) {
        int getOtp = resultSet.getInt("otp");
        Reporter.log("this is otp: " + getOtp);
        cipom.enterOtp(getOtp);
      }
    }

  }
  public String getProperty(String key) {
    return propertiesUAT.getProperty(key);
  }


  // Getter for browser name
  public static String getBrowserNameUAT() {
    System.out.println("browser for UAT in base class: " + browserNameUAT);
    return browserNameUAT;
  }

  public static String getBrowserVersionUAT() {
    System.out.println("browser version for UAT in base class: " + browserVersionUAT);
    return browserVersionUAT;
  }


  public WebDriver getDriverUAT() {
    return driverUAT;
  }


}
/* @BeforeSuite
  public void setOutputDirectory() {
    // Generate a timestamp-based directory name
    String timestamp = getHumanReadableTimestamp();
    System.setProperty("outputDirectory", "test-output-" + timestamp);
  }*/
 /* private String getHumanReadableTimestamp() {
    long timestampMillis = System.currentTimeMillis();
    Date date = new Date(timestampMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    return sdf.format(date);
  }*/