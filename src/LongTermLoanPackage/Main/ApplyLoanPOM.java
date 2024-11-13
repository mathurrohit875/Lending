package LongTermLoanPackage.Main;

import LongTermLoanPackage.DBPackage.DBUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplyLoanPOM {

  private static final String LOG_FILE = "test-output/custom-log.txt";
  private final By wallet = By.xpath("//a[contains(.,'WALLET')]");
  private final By lending = By.xpath("//a[contains(.,'Lending')]");
  private final By longTerm = By.xpath("//a[contains(.,'Long Term Loan')]");
  private final By addLoan = By.name("ctl00$ContentPlaceHolder1$btnAddLaonRequest");
  private final By loanAmount = By.name("ctl00$ContentPlaceHolder1$txtLoanAmountReq");
  private final By checkBox = By.id("ContentPlaceHolder1_chkTNC");
  private final By totalAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][1]//span");
  private final By finalAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][2]//span");
  private final By interestAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][3]//span");
  private final By EMIAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][4]//span");
  private final By EMINumberLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][5]//span");
  private final By emiAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_pnlEmiCalc']//div[@class='borderbox']//div[@class='col-md-3'][6]//span");
  private final By aadharNumber = By.name("ctl00$ContentPlaceHolder1$txtAadharNo");
  private final By submit = By.name("ctl00$ContentPlaceHolder1$btnAssign");
  private final By interestRate = By.name("ctl00$ContentPlaceHolder1$ddlInterestRate");
  private final By month = By.name("ctl00$ContentPlaceHolder1$ddlDurationInMonths");
  private final By emiFreq = By.name("ctl00$ContentPlaceHolder1$ddlFrequency");
  private final By emiType = By.name("ctl00$ContentPlaceHolder1$ddlEMIType");
  private final By label = By.xpath("//label[contains(.,'File Processing Charge')]");
  private final By baseLoanAmt = By.name("ctl00$ContentPlaceHolder1$txtLoanAmount");
  public HashMap<String, String> loanNumberCollect = new HashMap<>();
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

  public ApplyLoanPOM(WebDriver driver) throws IOException {
    this.driver = driver;
    action = new Actions(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    js = (JavascriptExecutor) driver;
    softAssert = new SoftAssert();
  }

  public void goToLongTermLoan() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(wallet));
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
    // EmiDetail(loanAmt);


  }

  public void EmiDetail(double loanAmt) throws IOException {


    FileWriter fw = new FileWriter(LOG_FILE, true);
    PrintWriter pw = new PrintWriter(fw);
    Reporter.log("---------EMI DETAIL SECTION---------");
    String baseAmt = driver.findElement(baseLoanAmt).getAttribute("value");
    baseLoan = Double.parseDouble(baseAmt);
    Reporter.log("double base loan amount: " + baseLoan);
    softAssert.assertEquals(loanAmt, baseLoan);
    wait.until(ExpectedConditions.visibilityOfElementLocated(label));
    WebElement userLabel = driver.findElement(label);
    action.moveToElement(userLabel).perform();


    Select selectInterestRate = new Select(driver.findElement(interestRate));
    WebElement interestRate = selectInterestRate.getFirstSelectedOption();
    String selectedInterestRate = interestRate.getText();
    softAssert.assertEquals("24 %", selectedInterestRate);

    Select selectMonth = new Select(driver.findElement(month));
    WebElement month = selectMonth.getFirstSelectedOption();
    String selectedMonth = month.getText();
    String[] totalMonth = selectedMonth.split("");
    String loanTenure = totalMonth[0].trim();
    int loanT = Integer.parseInt(loanTenure);
    loanMap.put("loanTenure", loanT);
    softAssert.assertEquals("3 Months", selectedMonth);


    wait.until(ExpectedConditions.visibilityOfElementLocated(emiFreq));
    WebElement freq = driver.findElement(emiFreq);
    action.moveToElement(freq).perform();

    Select selectFrequency = new Select(driver.findElement(emiFreq));
    selectFrequency.selectByVisibleText("Daily");
    Reporter.log("this is Daily selected");
    pw.println("this is Daily selected");
    String selectedFrequency = "Daily";
    Reporter.log("this is selected frequency: " + selectedFrequency);
    softAssert.assertEquals("Daily", selectedFrequency);

    Select EmiType = new Select(driver.findElement(emiType));
    WebElement EmiTypeSelected = EmiType.getFirstSelectedOption();
    String SelectedEMIType = EmiTypeSelected.getText();
    softAssert.assertEquals("Reducing EMI", SelectedEMIType);

    loanDetails.put("selectedInterestRate", selectedInterestRate);
    loanDetails.put("SelectedEMIType", SelectedEMIType);
    loanDetails.put("selectedMonth", selectedMonth);
    loanDetails.put("selectedFrequency", selectedFrequency);
    wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
    WebElement ele = driver.findElement(checkBox);
    action.moveToElement(ele).click().perform();
    softAssert.assertAll();

  }

  public void collectLoanDetails(double loanAmt) throws IOException {
    FileWriter fw = new FileWriter(LOG_FILE, true);
    PrintWriter pw = new PrintWriter(fw);
    Reporter.log("---------LOAN DETAIL SECTION---------");
    wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmountLoanDetail));
    WebElement amt = driver.findElement(totalAmountLoanDetail);
    action.moveToElement(amt).perform();
    totalAmountLoanDetailStr = driver.findElement(totalAmountLoanDetail).getText();
    totalAmtLoanDetailDbl = Double.parseDouble(totalAmountLoanDetailStr);

    softAssert.assertEquals(totalAmtLoanDetailDbl, loanAmt);

    finalAmtLoanDetailStr = driver.findElement(finalAmountLoanDetail).getText();

    interestAmounttLoanDetailStr = driver.findElement(interestAmountLoanDetail).getText();
    interestAmtLoanDetail = Double.parseDouble(interestAmounttLoanDetailStr);
    Reporter.log("this is interest amount on the frontend: " + interestAmtLoanDetail);

    finalAmtEMILoanDetailStr = driver.findElement(EMIAmountLoanDetail).getText();
    finalLoanAmtLoanDetail = Double.parseDouble(finalAmtEMILoanDetailStr);
    finalLoanAmtLoanDetailCalculate = totalAmtLoanDetailDbl + interestAmtLoanDetail;
    Reporter.log("this is final loan amount on the frontend: " + finalLoanAmtLoanDetail);
    Reporter.log("this is final loan amount after calculation: " + finalLoanAmtLoanDetailCalculate);

    softAssert.assertEquals(finalLoanAmtLoanDetail, finalLoanAmtLoanDetailCalculate);

    EmiNoLoanDetailStr = driver.findElement(EMINumberLoanDetail).getText();
    noofEMILoanDetail = Integer.parseInt(EmiNoLoanDetailStr);
    int DurationInMonth = loanMap.get("loanTenure");
    numberofEMICalculate = DurationInMonth * 25;
    softAssert.assertEquals(noofEMILoanDetail, numberofEMICalculate);


    WebElement table = driver.findElement(By.id("ContentPlaceHolder1_grdEMI"));
    action.moveToElement(table).perform();
    emiAmtLoanDetailStr = driver.findElement(emiAmountLoanDetail).getText();
    double emiAmtInteger = Double.parseDouble(emiAmtLoanDetailStr);

    double dailyRate = R / 12 / 25;

    // EMI calculation
    double emi = totalAmtLoanDetailDbl * dailyRate * Math.pow(1 + dailyRate, N)
          / (Math.pow(1 + dailyRate, N) - 1);

    // Rounding off to the nearest integer
    double roundedEmi = Math.round(emi);
    softAssert.assertEquals(emiAmtInteger, roundedEmi, "Actual:  " + emiAmtInteger + " Expected:  " + roundedEmi + " is failed");

    Reporter.log("Calculated EMI: " + emi);
    Reporter.log("Rounded EMI: " + roundedEmi);
    //-------------Adding the details in Hashmap ----------------------//
    loanDetails.put("totalAmt", totalAmountLoanDetailStr);
    loanDetails.put("finalAmt", finalAmtLoanDetailStr);
    loanDetails.put("interestAmt", interestAmounttLoanDetailStr);
    loanDetails.put("finalAmtEMI", finalAmtEMILoanDetailStr);
    loanDetails.put("EmiNo", EmiNoLoanDetailStr);

    //--------------Printing the details to console and output-----------//
    Reporter.log("this is the loan amount: " + totalAmountLoanDetailStr);
    pw.println("this is the loan amount: " + finalAmtLoanDetailStr);
    //Reporter.log("this is final amount: "+finalAmt);
    //pw.println("this is final amount: "+finalAmt);
    Reporter.log("this is interest amount: " + interestAmounttLoanDetailStr);
    Reporter.log("this is Final Loan Amount For EMI Calculation(Principal + PF + Int.)  amount: " + finalAmtEMILoanDetailStr);
    Reporter.log("this is Emi numbers: " + EmiNoLoanDetailStr);
    Reporter.log("this is Emi amount:  " + emiAmtLoanDetailStr);
    softAssert.assertAll();

  }

  public void collectEmiDetail() {
    WebElement table = driver.findElement(By.id("ContentPlaceHolder1_grdEMI"));
    action.moveToElement(table).perform();


    List<WebElement> emiDetais = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center']"));
    Reporter.log("this is emi detail list size: " + emiDetais.size());
    for (int i = 0; i < emiDetais.size(); i++) {
      String prevBalance = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 1) + "]/td[5]")).getText();
      previousBalance = Integer.parseInt(prevBalance);
      if (previousBalance == 0) {
        break;
      } else {
        Reporter.log("this is previous balance in integer format: " + previousBalance);
        WebElement emiInstallment = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[2]"));
        action.moveToElement(emiInstallment).perform();
        WebElement installmentAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[2]"));
        String installmentAmt = installmentAmountele.getText();
        installmentamountinteger = Integer.parseInt(installmentAmt);
        installmentcalculate += installmentamountinteger;
        WebElement interestAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[3]"));
        String interestAmt = interestAmountele.getText();
        interestAmountC = Double.parseDouble(interestAmt);
        intrstamt = Integer.parseInt(interestAmt);
        WebElement principalAmount = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMI']//tr[@align='center'][" + (i + 2) + "]/td[4]"));
        String prinString = principalAmount.getText();
        prinAmt = Integer.parseInt(prinString);
        Reporter.log("this is principal amount in the grid: " + prinAmt);
        prinAmtCal = installmentamountinteger - intrstamt;
        Reporter.log("this is principal amount after my caculation: " + prinAmtCal);
        //softAssert.assertEquals(prinAmtCal,prinAmt,1.00,"principal amount does not match with my calculation. Expected: "+prinAmtCal+", Actual: "+prinAmt);
        principalBoolean = prinAmt == prinAmtCal;
        Reporter.log("this is the boolean value for comparison of principal values: " + principalBoolean);
        Reporter.log("this is interest amount on frontend after converting to integer: " + interestAmountC);
        lblInterestAmt = Math.round((previousBalance * (R / 12 / 25)));
        Reporter.log("this is interest amount from my calculation: " + lblInterestAmt);
        softAssert.assertEquals(interestAmountC, lblInterestAmt, 1.00, "interest amount mismatch. Expected: " + interestAmountC + ", Actual: " + lblInterestAmt);
        boolean b = interestAmountC == lblInterestAmt;
        Reporter.log("checking the interest value if they are equal on frontend and through calculation: " + b);
        String installment = emiInstallment.getText();
        Reporter.log("this is emi on frontend: " + installment);
        finalInterestAmt += lblInterestAmt;
        Reporter.log("__________________________________________________________________________________");
        arrayList.add(installment);

      }
    }

    String finalLoanAmtStr = loanDetails.get("finalAmtEMI");
    int finaloanAmtInt = Integer.parseInt(finalLoanAmtStr);
    Reporter.log("this is final total of installement= " + installmentcalculate);
    softAssert.assertEquals(finaloanAmtInt, installmentcalculate, 1.00, "the difference is more than 1.00");
    String interestAmountStr = loanDetails.get("interestAmt");
    int interestAmountInt = Integer.parseInt(interestAmountStr);
    Reporter.log("this is final total of interest= " + finalInterestAmt);
    softAssert.assertEquals(interestAmountInt, finalInterestAmt, 1.00, "the difference is more than 1.00");
    softAssert.assertAll();

  }

  public void emiprintdetail() {
    Reporter.log("size of array: " + arrayList.size());
    for (int i = 0; i < arrayList.size(); i++) {
      Reporter.log("installment details: " + arrayList.get(i).toString());
    }
  }

  public void enterOtherDetailAndSubmit(String aadharCardNumber) throws ClassNotFoundException, SQLException {


    WebElement aadhar = driver.findElement(aadharNumber);
    action.moveToElement(aadhar).perform();
    aadhar.clear();
    aadhar.sendKeys(aadharCardNumber);
    String pannumber = driver.findElement(By.name("ctl00$ContentPlaceHolder1$txtPANNo")).getAttribute("value");
    loanDetails.put("aadharnumber", aadharCardNumber);
    loanDetails.put("pannumber", pannumber);
    WebElement sub = driver.findElement(submit);
    action.moveToElement(sub).perform();
    sub.click();


  }

  public void comparewithDB(String aadharnumber) throws ClassNotFoundException, SQLException, IOException {
    FileWriter fw = new FileWriter(LOG_FILE, true);
    PrintWriter pw = new PrintWriter(fw);
    driver.switchTo().alert().accept();
    wait.until(ExpectedConditions.alertIsPresent());
    String loannoText = driver.switchTo().alert().getText();
    Reporter.log("this is alert box text result: " + loannoText);
    pw.println("cu abciob euihci iohoc ioc ");
    String[] elements = loannoText.split(" ");
    Reporter.log("This is loan number:  " + elements[5]);
    String loannumber = elements[5].trim();


    FileWriter myWriter = new FileWriter("C:/Users/rohit.mathur/IdeaProjects/Lending/abc.txt");
    myWriter.write(loannumber);
    myWriter.close();


    driver.switchTo().alert().accept();


    String DbresultLoannumber = "select " +
          "emiid,loanamt,durationinmonth,loaninterestrate,emifrequency,emitype,\n" +
          "totalnoofemi,noofdueemi,status,createdby,loantype,loanstatus,panno,\n" +
          "aadharno,loanno from tm_channelemi where loanno='" + loannumber + "'";

    DBUtil dbUtil = new DBUtil();
    ResultSet rs = dbUtil.executeQuery(DbresultLoannumber);
    while (rs.next()) {
      String emiid = rs.getString("emiid");
      double loanamt = rs.getDouble("loanamt");
      int durationinmonth = rs.getInt("durationinmonth");
      double loaninterestrate = rs.getDouble("loaninterestrate");
      String emifrequency = rs.getString("emifrequency");
      String emitype = rs.getString("emitype");
      int totalnoofemi = rs.getInt("totalnoofemi");
      int noofdueemi = rs.getInt("noofdueemi");
      String status = rs.getString("status");
      String createdby = rs.getString("createdby");
      String loantype = rs.getString("loantype");
      int loanstatus = rs.getInt("loanstatus");
      String panno = rs.getString("panno");
      String aadharno = rs.getString("aadharno");
      String loanno = rs.getString("loanno");
      String loanAmtString = loanDetails.get("totalAmt");
      double loanAmtDouble = Double.parseDouble(loanAmtString);
      String totalLoanAmount = loanDetails.get("totalAmt");
      String finalLoanAmount = loanDetails.get("finalAmt");
      String interestLoanAmount = loanDetails.get("interestAmt");
      String finalLoanAmountEMI = loanDetails.get("finalAmtEMI");
      String TotalEmiNumbers = loanDetails.get("EmiNo");
      int emitotalNumber = Integer.parseInt(TotalEmiNumbers);
      String monthDuration = loanDetails.get("selectedMonth");
      String[] monthSplit = monthDuration.split("");
      String monthD = monthSplit[0].trim();
      int monthInt = Integer.parseInt(monthD);
      String emiFreq = loanDetails.get("selectedFrequency");
      String emiType = loanDetails.get("SelectedEMIType");
      //  String adharnumber=loanDetails.get("aadharnumber");
      String pannumber = loanDetails.get("pannumber");
      String interestRate = loanDetails.get("selectedInterestRate");
      String[] interestRateArray = interestRate.split(" ");
      String interestRateMod = interestRateArray[0].trim();
      double interestRateDouble = Double.parseDouble(interestRateMod) / 100.00;

      softAssert.assertEquals(loanamt, loanAmtDouble, 1.00, "loan amount does not match. Expected: " + loanAmtDouble + ", Actual: " + loanamt);

     /* if (loanamt == loanAmtDouble) {
        Reporter.log("loan amount are equal in Db and frontend");

      } else {

        softAssert.fail("this condition is manually failed by me.");


      }*/
      softAssert.assertEquals(durationinmonth, monthInt, "Duration mismatch. Expected:  " + durationinmonth + ", Actual: " + monthInt);
      /*if (durationinmonth == monthInt) {
        Reporter.log("duration are equal in Db and frontend");
      } else {
        Reporter.log("this is faield message in this euiefoiohfioeho097o");
        softAssert.fail("the duration is failed", new RuntimeException("this is run time exception"));
      }*/


      softAssert.assertEquals(loaninterestrate, interestRateDouble, "Interest Does not match. Expected: " + loaninterestrate + ", Actual: " + interestRateDouble);
      if (loaninterestrate == interestRateDouble) {
        //Reporter.log("interest rate are same in Db and frontend");
      }
      String emiFreMod = "";
      if (emifrequency.equals("D")) {
        emiFreMod = "Daily";
        softAssert.assertEquals(emiFreq, emiFreMod, "emi freq mismatch with Daily freq.  Expected: " + emiFreMod + ", Actual: " + emiFreq);
        if (emiFreq.equals(emiFreMod)) {
          Reporter.log("EMI Frequency is daily and same in Db and frontend");
        }

      } else if (emifrequency.equals("W")) {
        emiFreMod = "Weekly";
        softAssert.assertEquals(emiFreq, emiFreMod, "emi freq mismatch with Weekly freq. Expected: " + emiFreMod + ", Actual: " + emiFreq);
        /*if (emiFreq.equals(emiFreMod)) {
          Reporter.log("EMI Frequency is weekend and same in Db and frontend");
        }*/
      }
      String emiTypeMod = "";
      if (emitype.equals("R")) {
        emiTypeMod = "Reducing EMI";
        softAssert.assertEquals(emiType, emiTypeMod, "emi type mismatch. Expected: " + emiTypeMod + ", Actual: " + emiType);
        /*if (emiType.equals(emiTypeMod)) {
          Reporter.log("EMI Type are same in Db and frontend");
        }*/
      }
      softAssert.assertEquals(totalnoofemi, emitotalNumber, "total number of emi mismatch. Expected: " + totalnoofemi + ", Actual: " + emitotalNumber);
     /* if (totalnoofemi == emitotalNumber) {
        Reporter.log("EMI numbers are same in Db and frontend");
      }*/
      softAssert.assertEquals(noofdueemi, emitotalNumber, "number of due emi does not match. Expected: " + noofdueemi + ", Actual: " + emitotalNumber);
     /* if (noofdueemi == emitotalNumber) {
        Reporter.log("EMI due are same in Db and frontend");
      }*/
      softAssert.assertEquals("A", status, "status does not match. Expected: A, Actual: " + status);
     /* if (status.equals("A")) {
        Reporter.log("Status is active in the DB");
      }*/
      softAssert.assertEquals("C", loantype, "loan type mismatch. Actual: C, Expected: " + loantype);
      /*if (loantype.equals("C")) {
        Reporter.log("Loan type is Weekend Loan");
      }*/
      softAssert.assertEquals(loanstatus, 1, "loan status does not match. Expected. 1, Actual: " + loanstatus);
     /* if (loanstatus == 1) {
        Reporter.log("loan status is active");
      }*/
      softAssert.assertEquals(panno, pannumber, "pan number does not match. Expected: " + pannumber + ", Actual: " + panno);
      /*if (panno.equals(pannumber)) {
        Reporter.log("pan number is equal in both db and frontend");
      }*/
      softAssert.assertEquals(aadharno, aadharnumber, "aadhar number does not match. Expected: " + aadharnumber + ", Actual: " + aadharno);
      /*if (aadharno.equals(aadharnumber)) {
        Reporter.log("Adhar number is equal in both db and frontend");
      }*/
      softAssert.assertAll();
    }

  }

  public void compareAfterLoanApplied() {
    String loanTerm = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[3]")).getText();
    String loanNumber = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[7]")).getText();
    String loanAmount = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[8]")).getText();
    String fileCharge = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[9]")).getText();
    String loanDuration = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[11]")).getText();
    String loanInterest = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[12]")).getText();
    String emiFreq = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[13]")).getText();
    String emiType = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[2]/td[14]")).getText();
    Reporter.log(loanTerm);
    Reporter.log(loanNumber);
    Reporter.log(loanAmount);
    Reporter.log(fileCharge);
    Reporter.log(loanDuration);
    Reporter.log(loanInterest);
    Reporter.log(emiFreq);
    Reporter.log(emiType);

  }


}
