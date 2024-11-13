package LongTermLoanPackage.Main;

import Interface.WebDriverProviderMTEST;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class LTBaseClass implements WebDriverProviderMTEST {
  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/config.properties";
  private static String browserNameMTEST;
  private static String browserVersionMTEST;
  protected WebDriver driverMTEST;
  protected Properties properties;
  protected ApplyLoanPOM applyLoanPOM;
  protected LongTermLoginPOM longTermLoginPOM;

  // Getter for browser name
  public static String getBrowserNameMTEST() {
    System.out.println("browser for mtest in base class: " + browserNameMTEST);
    return browserNameMTEST;
  }

  public static String getBrowserVersionMTEST() {
    System.out.println("browser version for mtest in base class: " + browserVersionMTEST);

    return browserVersionMTEST;
  }

  /* @BeforeSuite
   public void setOutputDirectory() {
     // Generate a timestamp-based directory name
     String timestamp = getHumanReadableTimestamp();
     System.setProperty("outputDirectory", "test-output-" + timestamp);
   }*/
  private String getHumanReadableTimestamp() {
    long timestampMillis = System.currentTimeMillis();
    Date date = new Date(timestampMillis);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    return sdf.format(date);
  }

  @BeforeClass
  public void setup() throws IOException {
    // Load properties file
    properties = new Properties();
    try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
      properties.load(fileInputStream);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load properties file", e);
    }

    // Get browser type from properties
    browserNameMTEST = properties.getProperty("browser");
    System.out.println("browser name in LT base class from property: " + browserNameMTEST);

    // Initialize WebDriver based on browser type
    if ("chrome".equalsIgnoreCase(browserNameMTEST)) {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setAcceptInsecureCerts(true);
      driverMTEST = new ChromeDriver(chromeOptions);
      browserVersionMTEST = ((ChromeDriver) driverMTEST).getCapabilities().getBrowserVersion();

    } else if ("firefox".equalsIgnoreCase(browserNameMTEST)) {
      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setAcceptInsecureCerts(true);
      firefoxOptions.setBinary("C:/Users/rohit.mathur/AppData/Local/Mozilla Firefox/firefox.exe");
      WebDriverManager.firefoxdriver().setup();
      driverMTEST = new FirefoxDriver(firefoxOptions);
      browserVersionMTEST = ((FirefoxDriver) driverMTEST).getCapabilities().getBrowserVersion();

    } else {
      throw new IllegalArgumentException("Unsupported browser: " + browserNameMTEST);
    }

    driverMTEST.manage().window().maximize();
    //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    driverMTEST.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Initialize Page Object Model (POM) classes
    applyLoanPOM = new ApplyLoanPOM(driverMTEST);
    longTermLoginPOM = new LongTermLoginPOM(driverMTEST);
    // Open the URL from properties
    String url = properties.getProperty("url");
    driverMTEST.get(url);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  @Override
  public WebDriver getDriverMTEST() {
    return driverMTEST;
  }
}
