package CareInsurance.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class FireFoxBasClass {
  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/config.properties";
  private static String browserName;
  private static String browserVersion;
  public WebDriver driver;
  protected CIPOM cipom;
  protected CIMandatoryMsgPOM ciMandatoryMsgPOM;
  protected Properties properties;

  // Getter for browser name
  public static String getBrowserName() {
    return browserName;
  }

  public static String getBrowserVersion() {
    return browserVersion;
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
  public void setup() throws FileNotFoundException {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.setAcceptInsecureCerts(true);
    firefoxOptions.setBinary("C:/Users/rohit.mathur/AppData/Local/Mozilla Firefox/firefox.exe");
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver(firefoxOptions);

    browserVersion = ((FirefoxDriver) driver).getCapabilities().getBrowserVersion();

    driver.manage().window().maximize();
    //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Initialize Page Object Model (POM) classes
    cipom = new CIPOM(driver);
    ciMandatoryMsgPOM = new CIMandatoryMsgPOM(driver);
    // Load properties file
    properties = new Properties();
    try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
      properties.load(fileInputStream);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load properties file", e);
    }


    // Open the URL from properties
    String url = properties.getProperty("urlUAT");
    driver.get(url);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
