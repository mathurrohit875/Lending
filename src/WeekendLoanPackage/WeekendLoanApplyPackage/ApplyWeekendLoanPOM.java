package WeekendLoanPackage.WeekendLoanApplyPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplyWeekendLoanPOM {

  private static final String LOG_FILE = "test-output/custom-log.txt";
  private final By wallet = By.xpath("//a[text()='WALLET']");
  private final By lending = By.xpath("//a[text()='Lending']");
  private final By longTerm = By.xpath("//a[contains(.,'Apply Weekend Loan')]");
  private final By addLoan = By.name("ctl00$ContentPlaceHolder1$btnAddLaonRequest");
  private final By loanAmount = By.name("ctl00$ContentPlaceHolder1$txtLoanAmountReq");
  private final By submitRequest = By.name("ctl00$ContentPlaceHolder1$btnSave");
  WebDriver driver;
  WebDriverWait wait;
  JavascriptExecutor js;
  Connection conn = null;
  SoftAssert softAssert;
  String totalAmountLoanDetailStr;
  String finalAmtLoanDetailStr;
  String interestAmounttLoanDetailStr;
  String finalAmtEMILoanDetailStr;
  String EmiNoLoanDetailStr;
  String emiAmtLoanDetailStr;
  Boolean principalBoolean;
  int prinAmt;
  int intrstamt;
  int prinAmtCal;
  Actions action;
  ITestNGListener iTestNGListener;
  ArrayList arrayList = new ArrayList<>();
  double baseLoan;
  int previousBalance;
  int N = 75;
  double R = 0.24;
  double lblInterestAmt;
  double interestAmountC;
  HashMap<String, String> loanDetails = new HashMap<String, String>();
  HashMap<String, Integer> loanMap = new HashMap<>();
  double totalAmtLoanDetailDbl;
  double interestAmtLoanDetail;
  double finalLoanAmtLoanDetail;
  double finalLoanAmtLoanDetailCalculate;
  int noofEMILoanDetail;
  int numberofEMICalculate;
  double finalInterestAmt = 0.00;
  ITestResult result = null;
  int installmentcalculate = 0;
  int installmentamountinteger;

  public ApplyWeekendLoanPOM(WebDriver driver) throws IOException {
    this.driver = driver;
    action = new Actions(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    js = (JavascriptExecutor) driver;
    softAssert = new SoftAssert();
  }

  public void goToLongTermLoan() {
    action.moveToElement(driver.findElement(wallet)).perform();
    action.moveToElement(driver.findElement(lending)).perform();
    driver.findElement(longTerm).click();
  }

  public void goToAddLoan() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(addLoan)).click();
  }

  public void enterLoanDetail(double loanAmt) throws IOException {
    wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmount));
    //driver.findElement(loanAmount).clear();
    driver.findElement(loanAmount).sendKeys("" + loanAmt);
    action.sendKeys(Keys.TAB).perform();
  }

  public void submitLoanRequest() {
    action.sendKeys(Keys.PAGE_DOWN).perform();
    wait.until(ExpectedConditions.elementToBeClickable(submitRequest)).click();
  }
}
