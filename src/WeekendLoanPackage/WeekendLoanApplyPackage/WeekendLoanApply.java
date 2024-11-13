package WeekendLoanPackage.WeekendLoanApplyPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class WeekendLoanApply {
  double lA = 0.00;
  WebDriver driver;
  ApplyWeekendLoanPOM applyWeekendLoanPOM;
  String aadharcardnumber = "123456789012";

  @BeforeTest
  public void setupUser() throws IOException {

    Properties properties = new Properties();
    ChromeOptions options = new ChromeOptions();
    FileInputStream fileInputStream = new FileInputStream("C:/Users/rohit.mathur/IdeaProjects/Lending/src/LongTermLoanPackage/config.properties");
    properties.load(fileInputStream);
    String url = properties.getProperty("url");
    String usernameUser = properties.getProperty("weekendLoanUser");
    String passwordUser = properties.getProperty("passwordUser");

    //options.addArguments("headless");
    options.setAcceptInsecureCerts(true);
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(options);

    JavascriptExecutor js = (JavascriptExecutor) driver;
    applyWeekendLoanPOM = new ApplyWeekendLoanPOM(driver);
    driver.manage().window().maximize();
    driver.get(url);
    WeekendLoginPOM weekendLoginPOM = new WeekendLoginPOM(driver);
    weekendLoginPOM.enterCredentials(usernameUser, passwordUser, "sdcd");
    weekendLoginPOM.enterOTP("222111");
  }

  @Test(priority = 1, testName = "weekend loan apply")
  public void loginUser() throws SQLException, ClassNotFoundException, IOException {

    applyWeekendLoanPOM.goToLongTermLoan();
    int from = 10000, to = 200000, multi = 5000;
    Random rand = new Random();
    int n = multi * (Math.round(rand.nextInt((to + multi - from)) + from) / multi);
    lA = Double.valueOf(n);
    Reporter.log("" + n);
    applyWeekendLoanPOM.goToAddLoan();
    double loanAmount = 100000;
    applyWeekendLoanPOM.enterLoanDetail(lA);
    applyWeekendLoanPOM.submitLoanRequest();
    //applyLoanPOM.compareAfterLoanApplied();

  }
}
