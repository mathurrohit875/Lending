package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewLoan {


  ArrayList al = new ArrayList();
  ArrayList list = new ArrayList();
  Actions action;
  WebDriver driver;
  LoginPOM loginPOM;
  POMViewLoan pomViewLoan;

  // Logger log;
  @BeforeMethod
  public void setUp() {
    //  log = Logger.getLogger(ViewLoan.class);
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    driver.manage().window().maximize();
    driver.get("https://uatxpresso.roinet.in/Login.aspx");
    loginPOM = new LoginPOM(driver);
    pomViewLoan = new POMViewLoan(driver);
    loginPOM.enterCredentials("CMF000041", "roinet@1234", "sdcd");
    loginPOM.enterOTP("222111");
    action = new Actions(driver);
  }

  @Test
  public void setup() throws InterruptedException, ClassNotFoundException, SQLException {

    ChromeOptions options = new ChromeOptions();
    //options.addArguments("headless");
    options.setAcceptInsecureCerts(true);


    action.moveToElement(driver.findElement(By.xpath("//a[text()='WALLET']"))).perform();
    action.moveToElement(driver.findElement(By.xpath("//a[text()='Lending']"))).perform();
    driver.findElement(By.xpath("//a[contains(.,'Apply Weekend Loan')]")).click();
    pomViewLoan.viewButton();

    pomViewLoan.iterateList();
    pomViewLoan.loanNumbercompare();


  }

}


