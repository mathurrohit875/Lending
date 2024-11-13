package LongTermLoanPackage.LongTermEMIPayPackage;

import LongTermLoanPackage.DBPackage.DBUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGListener;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ViewLongTermLoanPOM {

  // By variables
  private static final String LOG_FILE = "test-output/custom-log.txt";
  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/src/LongTermLoanPackage/config.properties";
  private final WebDriver driver;
  private final WebDriverWait wait;
  private final JavascriptExecutor js;
  private final SoftAssert softAssert;
  // By locators
  private final By wallet = By.xpath("//a[text()='WALLET']");
  private final By lending = By.xpath("//a[text()='Lending']");
  private final By longTerm = By.xpath("//a[contains(.,'Long Term Loan')]");
  private final By addLoan = By.name("ctl00$ContentPlaceHolder1$btnAddLaonRequest");
  private final By loanAmount = By.name("ctl00$ContentPlaceHolder1$txtLoanAmountReq");
  private final By checkBox = By.id("ContentPlaceHolder1_chkTNC");
  private final By loanNumberTextBox = By.name("ctl00$ContentPlaceHolder1$txtLoanNo");
  private final By viewButton = By.name("ctl00$ContentPlaceHolder1$btnView");
  private final By viewLoanDetail = By.xpath("//input[@name='ctl00$ContentPlaceHolder1$grdList$ctl02$btnViewDetails']");
  private final By totalAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][1]//span");
  private final By finalAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][2]//span");
  private final By interestAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][3]//span");
  private final By EMIAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][4]//span");
  private final By EMINumberLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][5]//span");
  private final By emiAmountLoanDetail = By.xpath("//div[@id='ContentPlaceHolder1_divEmiKycVW']//div[@class='borderbox']//div[@class='col-md-3'][6]//span");
  private final By aadharNumber = By.name("ctl00$ContentPlaceHolder1$txtAadharNo");
  private final By submit = By.name("ctl00$ContentPlaceHolder1$btnAssign");
  private final By interestRate = By.name("ctl00$ContentPlaceHolder1$ddlInterestRateVW");
  private final By month = By.name("ctl00$ContentPlaceHolder1$ddlDurationInMonthsVW");
  private final By emiFreq = By.name("ctl00$ContentPlaceHolder1$ddlFrequencyVW");
  private final By emiType = By.name("ctl00$ContentPlaceHolder1$ddlEMITypeVW");
  private final By label = By.xpath("//label[contains(.,'File Processing Charge')]");
  private final By loanAmtVerify = By.name("ctl00$ContentPlaceHolder1$txtLoanAmountReqVW");
  private final By statusName = By.name("ctl00$ContentPlaceHolder1$ddlLoanStatus");
  private final By submitChanges = By.name("ctl00$ContentPlaceHolder1$btnGoAhead");
  String totalAmountLoanDetailStr;
  String finalAmtLoanDetailStr;
  String interestAmounttLoanDetailStr;
  String finalAmtEMILoanDetailStr;
  String EmiNoLoanDetailStr;
  String emiAmtLoanDetailStr;
  int prinAmt;
  double intrstamt;
  double prinAmtCal;
  Boolean principalBoolean;
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
  double installmentcalculate = 0;
  double installmentamountinteger;
  private Connection conn;
  private Properties properties;

  public ViewLongTermLoanPOM(WebDriver driver) throws IOException {
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

  public void enterLoanNumber(String loanNumber) {

    wait.until(ExpectedConditions.visibilityOfElementLocated(loanNumberTextBox)).sendKeys(loanNumber);
    Select select = new Select(driver.findElement(By.name("ctl00$ContentPlaceHolder1$ddlSearchLoanStatus")));
    select.selectByVisibleText("--Select--");
    wait.until(ExpectedConditions.visibilityOfElementLocated(viewButton)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(viewLoanDetail)).click();
  }

  public void EmiDetail(double loanAmt) throws IOException {


    FileWriter fw = new FileWriter(LOG_FILE, true);
    PrintWriter pw = new PrintWriter(fw);
    Reporter.log("---------EMI DETAIL SECTION---------");
    String baseAmt = driver.findElement(loanAmtVerify).getAttribute("value");
    baseLoan = Double.parseDouble(baseAmt);
    Reporter.log("double base loan amount: " + baseLoan);
    softAssert.assertEquals(loanAmt, baseLoan, 1.00, "this is comparison of loan amount. It is failing because loan amount is not matching in DB and Admin Frontend.");
    wait.until(ExpectedConditions.visibilityOfElementLocated(label));
    WebElement userLabel = driver.findElement(label);
    action.moveToElement(userLabel).perform();


    Select selectInterestRate = new Select(driver.findElement(interestRate));
    WebElement interestRate = selectInterestRate.getFirstSelectedOption();
    String selectedInterestRate = interestRate.getText();
    softAssert.assertEquals("24 %", selectedInterestRate, "it failed because interest rate does not match");

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
    WebElement frequency = selectFrequency.getFirstSelectedOption();
    String selectedFrequency = frequency.getText();
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
    //wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
    //WebElement ele=driver.findElement(checkBox);
    //action.moveToElement(ele).click().perform();

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


    WebElement table = driver.findElement(By.id("ContentPlaceHolder1_grdEMIVW"));
    action.moveToElement(table).perform();
    emiAmtLoanDetailStr = driver.findElement(emiAmountLoanDetail).getText();
    double emiAmtInteger = Double.parseDouble(emiAmtLoanDetailStr);

    double dailyRate = R / 12 / 25;

    // EMI calculation
    double emi = totalAmtLoanDetailDbl * dailyRate * Math.pow(1 + dailyRate, N) / (Math.pow(1 + dailyRate, N) - 1);

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

    Reporter.log("---------------Collect EMI Detail--------------------");
    WebElement table = driver.findElement(By.id("ContentPlaceHolder1_grdEMIVW"));
    action.moveToElement(table).perform();


    List<WebElement> emiDetais = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr"));
    Reporter.log("this is emi detail list size: " + emiDetais.size());
    String prevBalance;
    String calculateprevbal = loanDetails.get("finalAmt");
    Reporter.log("PREVIOUS calculate prev bal " + calculateprevbal);
    double prevBalCal = Double.parseDouble(calculateprevbal);
    Reporter.log("previous double balance " + prevBalCal);
    int prevBalInt = (int) prevBalCal;
    Reporter.log("previous balance from inteer " + prevBalInt);


    for (int i = 0; i < emiDetais.size(); i++) {

      if (i + 2 > emiDetais.size()) {
        break;
      } else {
        Reporter.log("DETAIL AT INDEX " + i);


        WebElement emiInstallment = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr[" + (i + 2) + "]/td[2]"));
        action.moveToElement(emiInstallment).perform();

        WebElement installmentAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr[" + (i + 2) + "]/td[2]"));
        String installmentAmt = installmentAmountele.getText();
        installmentamountinteger = Double.parseDouble(installmentAmt);
        installmentcalculate += installmentamountinteger;

        WebElement interestAmountele = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr[" + (i + 2) + "]/td[4]"));
        String interestAmt = interestAmountele.getText();
        interestAmountC = Double.parseDouble(interestAmt);
        intrstamt = Double.parseDouble(interestAmt);
        Reporter.log("this is interest amount on frontend after converting to integer: " + interestAmountC);

        WebElement principalAmount = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr[" + (i + 2) + "]/td[5]"));
        String prinString = principalAmount.getText();
        prinAmt = Integer.parseInt(prinString);
        Reporter.log("this is principal amount in the grid: " + prinAmt);

        prevBalance = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdEMIVW']//tr[" + (i + 2) + "]/td[6]")).getText();
        previousBalance = Integer.parseInt(prevBalance);
        Reporter.log("this is previous balance in integer format: " + previousBalance);


        prinAmtCal = installmentamountinteger - intrstamt;
        Reporter.log("this is principal amount after my caculation: " + prinAmtCal);

        principalBoolean = prinAmt == prinAmtCal;
        Reporter.log("this is the boolean value for comparison of principal values: " + principalBoolean);
        Reporter.log("this is previous balance in the loop at index :" + i + " = " + prevBalInt);
        lblInterestAmt = Math.round((prevBalInt * (R / 12 / 25)));
        Reporter.log("this is interest amount from my calculation: " + lblInterestAmt);
        boolean b = interestAmountC == lblInterestAmt;
        Reporter.log("checking the interest value if they are equal on frontend and through calculation: " + b);

        String installment = emiInstallment.getText();
        Reporter.log("this is emi on frontend: " + installment);
        finalInterestAmt += lblInterestAmt;
        prevBalInt = previousBalance;
        Reporter.log("__________________________________________________________________________________");
        arrayList.add(installment);


      }

    }

    String finalLoanAmtStr = loanDetails.get("finalAmtEMI");
    double finaloanAmtInt = Double.parseDouble(finalLoanAmtStr);
    Reporter.log("this is final total of installement= " + installmentcalculate);
    softAssert.assertEquals(finaloanAmtInt, installmentcalculate, 1.00, "the difference is more than 1.00");
    String interestAmountStr = loanDetails.get("interestAmt");
    double interestAmountInt = Double.parseDouble(interestAmountStr);
    Reporter.log("this is final total of interest= " + finalInterestAmt);
    softAssert.assertEquals(interestAmountInt, finalInterestAmt, 1.00, "the difference is more than 1.00");
    softAssert.assertAll();

  }

  public void changeStatusOfLoan(String loanNumber) throws ClassNotFoundException, SQLException {

    action.sendKeys(Keys.HOME).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(statusName));
    Select selectStatus = new Select(driver.findElement(statusName));
    selectStatus.selectByVisibleText("EAgreementSignedDue");
    action.sendKeys(Keys.END).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(submitChanges)).click();
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    String query = "select * from tm_channelemi where loanno='" + loanNumber + "'";
    DBUtil dbUtil = new DBUtil();
    ResultSet resultSet = dbUtil.executeQuery(query);
    int loanStatus;
    int emiid;
    while (resultSet.next()) {
      loanStatus = resultSet.getInt("loanstatus");
      emiid = resultSet.getInt("emiid");
      if (loanStatus == 2) {
        Reporter.log("Loan status changed to E Agreement Signed Due");

                   /* String updateLoanStatus="update tm_channelemi set loanstatus=?,isemandate=? where emiid=?";
                    PreparedStatement preparedStatement=conn.prepareStatement(updateLoanStatus);
                    preparedStatement.setInt(1,3);
                    preparedStatement.setInt(2,1);
                    preparedStatement.setInt(3,emiid);
                    int i=preparedStatement.executeUpdate();*/
        String updateLoanStatus = "update tm_channelemi set loanstatus=?, isemandate=? where emiid=?";
        int rowsUpdated = dbUtil.executeUpdate(updateLoanStatus, 3, 1, emiid);
        Reporter.log("Status Updated " + rowsUpdated);
      }
    }
    changeStatusApprove();

  }

  public void changeStatusApprove() throws ClassNotFoundException, SQLException {


    DBAvailableLimit();
    driver.findElement(viewLoanDetail).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(statusName));
    Select selectStatus = new Select(driver.findElement(statusName));
    selectStatus.selectByVisibleText("LoanApproved");
    action.sendKeys(Keys.END).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(submitChanges)).click();
    wait.until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    DBAvailableLimit();

  }

  public void DBAvailableLimit() throws ClassNotFoundException, SQLException {

    try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
      properties = new Properties();
      properties.load(fileInputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    DBUtil dbUtil = new DBUtil();
    double availableLimit = 0.00;
    String usercode = properties.getProperty("usernameUser");
    String query = "select availablelimit from tm_channel inner join tm_user on tm_user.userid=tm_channel.channelid where tm_user.usercode='" + usercode + "'";

    ResultSet resultSet = dbUtil.executeQuery(query);
    while (resultSet.next()) {
      availableLimit = resultSet.getDouble("availablelimit");
    }

    Reporter.log("Available limit before/after loan is approved in the users account: " + availableLimit);
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
    driver.switchTo().alert().accept();


    String DbresultLoannumber = "select " + "emiid,loanamt,durationinmonth,loaninterestrate,emifrequency,emitype,\n" + "totalnoofemi,noofdueemi,status,createdby,loantype,loanstatus,panno,\n" + "aadharno,loanno from tm_channelemi where loanno='" + loannumber + "'";

    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";

    conn = DriverManager.getConnection(url, username, password);

    if (conn != null) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(DbresultLoannumber);
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

        //softAssert.assertEquals(loanamt,loanAmtDouble);

        if (loanamt == loanAmtDouble) {
          Reporter.log("loan amount are equal in Db and frontend");

        } else {

          softAssert.fail("this condition is manually failed by me.");


        }
        //softAssert.assertEquals(durationinmonth,monthInt);
        if (durationinmonth == monthInt) {
          Reporter.log("duration are equal in Db and frontend");
        } else {
          Reporter.log("this is faield message in this euiefoiohfioeho097o");
          softAssert.fail("the duration is failed", new RuntimeException("this is run time exception"));
        }


        // softAssert.assertEquals(loaninterestrate, interestRateDouble);
        if (loaninterestrate == interestRateDouble) {
          Reporter.log("interest rate are same in Db and frontend");
        }
        String emiFreMod = "";
        if (emifrequency.equals("D")) {
          emiFreMod = "Daily";
          //softAssert.assertEquals(emiFreq,emiFreMod);
          if (emiFreq.equals(emiFreMod)) {
            Reporter.log("EMI Frequency is daily and same in Db and frontend");
          }

        } else if (emifrequency.equals("W")) {
          emiFreMod = "Weekly";
          // softAssert.assertEquals(emiFreq,emiFreMod);
          if (emiFreq.equals(emiFreMod)) {
            Reporter.log("EMI Frequency is weekend and same in Db and frontend");
          }
        }
        String emiTypeMod = "";
        if (emitype.equals("R")) {
          emiTypeMod = "Reducing EMI";
          //softAssert.assertEquals(emiType,emiTypeMod);
          if (emiType.equals(emiTypeMod)) {
            Reporter.log("EMI Type are same in Db and frontend");
          }
        }


        //softAssert.assertEquals(totalnoofemi,emitotalNumber);
        if (totalnoofemi == emitotalNumber) {
          Reporter.log("EMI numbers are same in Db and frontend");
        }
        // softAssert.assertEquals(noofdueemi,emitotalNumber);
        if (noofdueemi == emitotalNumber) {
          Reporter.log("EMI due are same in Db and frontend");
        }
        // softAssert.assertEquals("A",status);
        if (status.equals("A")) {
          Reporter.log("Status is active in the DB");
        }
        //softAssert.assertEquals("C",loantype);
        if (loantype.equals("C")) {
          Reporter.log("Loan type is Weekend Loan");
        }
        //softAssert.assertEquals(1,loanstatus);
        if (loanstatus == 1) {
          Reporter.log("loan status is active");
        }
        // softAssert.assertEquals(panno,pannumber);
        if (panno.equals(pannumber)) {
          Reporter.log("pan number is equal in both db and frontend");
        }
        //softAssert.assertEquals(aadharno,aadharnumber);
        if (aadharno.equals(aadharnumber)) {
          Reporter.log("Adhar number is equal in both db and frontend");
        }
        softAssert.assertAll();
      }
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
