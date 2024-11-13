package CareInsurance.main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CIMandatoryMsgPOM {

  public final By addButton = By.xpath("//button[contains(.,'Add')]");
  private final By submitButton = By.xpath("//div[@class='col-md-6 text-end']//button[contains(.,'Submit')]");
  private final By mobilemandatory = By.xpath("//div[@class='invalid-feedback' and contains(.,' Please Enter Valid Contact Number ')]");
  private final By otpmandatory = By.xpath("//div[@class='invalid-feedback' and contains(.,' Please enter valid OTP ')]");
  private final By checkboxmandatory = By.xpath("//div[@class='invalid-feedback checkRed' and contains(.,' You must agree to the terms and conditions. ')]");
  private final By careTitle = By.xpath("//div[@class='title_new' and contains(.,'Care Insurance')]");
  private final By mobilenumber = By.id("mobleNumber11");
  private final By OtpCI = By.id("otpNumber");
  //FIRST PART MANDATORY MESSAGE//
  private final By coverTypeMan = By.xpath("//div[@class='row mt-1']/form/div/div[1]//div[@class='invalid-feedback']");
  private final By sumInsuredMan = By.xpath("//div[@class='row mt-1']/form/div/div[2]//div[@class='invalid-feedback']");
  private final By numberMemberMan = By.xpath("//div[@class='row mt-1']/form/div/div[3]//div[@class='invalid-feedback']");
  private final By periodMan = By.xpath("//div[@class='row mt-1']/form/div/div[4]//div[@class='invalid-feedback']");
  private final By productMan = By.xpath("//div[@class='row mt-1']/form/div/div[5]//div[@class='invalid-feedback']");
  private final By insuranceTypeMan = By.xpath("//div[@class='row mt-1']/form/div/div[6]//div[@class='invalid-feedback']");
  //PROPOSER MANDATORY MESSAGE//
  private final By proposerDetailButton = By.xpath("//button[contains(.,'Proposer Detail')]");
  private final By proposerDobMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[1]//div[@class='text-danger']");
  private final By proposerFirstNameMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[2]//div[@class='text-danger']");
  private final By proposerLastNameMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[3]//div[@class='text-danger']");
  private final By proposerGenderMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[4]//div[@class='invalid-feedback']");
  private final By proposerRelationMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[5]//div[@class='invalid-feedback']");
  private final By proposerTitleMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[6]//div[@class='invalid-feedback']");
  private final By proposerPerAddLine1Man = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[8]//div[@class='invalid-feedback']");
  private final By proposerPerStateMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[11]//div[@class='invalid-feedback']");
  private final By proposerPerCityMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[12]//div[@class='invalid-feedback']");
  private final By proposerPerPincodeMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[13]//div[@class='invalid-feedback']");
  private final By proposerCommAddLine1Man = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[16]//div[@class='invalid-feedback']");
  private final By proposerCommStateMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[19]//div[@class='invalid-feedback']");
  private final By proposerCommCityMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[20]//div[@class='invalid-feedback']");
  private final By proposerCommPincodeMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[21]//div[@class='invalid-feedback']");
  private final By proposerEmailMan = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='row pt-2']/div[23]//div[@class='invalid-feedback']");
  //INSURED MANDATORY MESSAGE//
  private final By InsuredDetailButton = By.xpath("//button[contains(.,'Insured Detail')]");
  private final By insuredDobMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[1]//div[@class='text-danger']");
  private final By insuredFirstNameMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[2]//div[@class='text-danger']");
  private final By insuredLastNameMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[3]//div[@class='text-danger']");
  private final By insuredGenderMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[4]//div[@class='invalid-feedback']");
  private final By insuredTitleMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[6]//div[@class='invalid-feedback']");
  private final By insuredPerAddLine1Man = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[9]//div[@class='invalid-feedback']");
  private final By insuredRelationMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[5]//div[@class='invalid-feedback']");
  private final By insuredPerStateMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[12]//div[@class='invalid-feedback']");
  private final By insuredPerCityMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[13]//div[@class='invalid-feedback']");
  private final By insuredPerPincodeMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[14]//div[@class='invalid-feedback']");
  private final By insuredCommAddLine1Man = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[17]//div[@class='invalid-feedback']");
  private final By insuredCommStateMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[20]//div[@class='invalid-feedback']");
  private final By insuredCommCityMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[21]//div[@class='invalid-feedback']");
  private final By insuredCommPincodeMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[22]//div[@class='invalid-feedback']");
  private final By insuredEmailMan = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='row pt-2']/div[25]//div[@class='invalid-feedback']");
  WebDriver driver;
  WebDriverWait wait;
  Actions action;
  SoftAssert softAssert;


  public CIMandatoryMsgPOM(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    action = new Actions(driver);
    softAssert = new SoftAssert();
  }

  public void verifyCareFormPage(String Ctitle) {
    String careTitleTxt = driver.findElement(careTitle).getText().trim();
    softAssert = new SoftAssert();
    softAssert.assertEquals(careTitleTxt, Ctitle, "care title not match");
    softAssert.assertAll();
  }

  public void careInsuranceFirstScreen(String mmaxlength, String otplth) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();
    String mobilemaxlength = driver.findElement(mobilenumber).getAttribute("maxlength");
    // Boolean mobile = mobilemaxlength.contains(mmaxlength);
    softAssert.assertEquals(mobilemaxlength, mmaxlength, "this is max length check for mobile number");
    String otpmaxlength = driver.findElement(OtpCI).getAttribute("maxlength");
    //Boolean otplength = otpmaxlength.equals(otplth);
    softAssert.assertEquals(otpmaxlength, otplth, "this is max length check for otp");
    softAssert.assertAll();
  }

  public void firstScreenMandatory(String mmandate, String otpman, String cbman) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
    String mobilemandat = driver.findElement(mobilemandatory).getText().trim();
    String otpmandat = driver.findElement(otpmandatory).getText().trim();
    String checkboxmandat = driver.findElement(checkboxmandatory).getText().trim();
    Reporter.log("mobile message: " + mobilemandat);
    Reporter.log("otp mandate: " + otpmandat);
    Reporter.log("check box mandate: " + checkboxmandat);
    softAssert.assertEquals(mobilemandat, mmandate, "this is failed because: " + mobilemandat + " is not equal to: " + mmandate);
    softAssert.assertEquals(otpmandat, otpman);
    softAssert.assertEquals(checkboxmandat, cbman);
    softAssert.assertAll();
  }

  public void firstPartMandatoryVerify(String cType, String SInsured,
                                       String numberMember, String period,
                                       String product, String InsuranceType) {
    action.sendKeys(Keys.HOME).perform();
    String actualCtype = driver.findElement(coverTypeMan).getText().trim();
    String actualSinsured = driver.findElement(sumInsuredMan).getText().trim();
    String actualNumMem = driver.findElement(numberMemberMan).getText().trim();
    String actualPeriod = driver.findElement(periodMan).getText().trim();
    String actualProduct = driver.findElement(productMan).getText().trim();
    String actualInsuranceType = driver.findElement(insuranceTypeMan).getText().trim();
    softAssert.assertEquals(actualCtype, cType);
    softAssert.assertEquals(actualSinsured, SInsured);
    softAssert.assertEquals(actualNumMem, numberMember);
    softAssert.assertEquals(actualPeriod, period);
    softAssert.assertEquals(actualProduct, product);
    softAssert.assertEquals(actualInsuranceType, InsuranceType);
    softAssert.assertAll();
  }

  public void ProposerMandatoryFirstPartVerify(String dob, String fname, String lname,
                                               String gender, String relation, String title) {

    // wait.until(ExpectedConditions.visibilityOfElementLocated(proposerDetailButton)).click();
    action.sendKeys(Keys.HOME).perform();
    WebElement propDob=driver.findElement(proposerDobMan);
    action.moveToElement(propDob).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(proposerDobMan));
    String actualDob = driver.findElement(proposerDobMan).getText().trim();
    Reporter.log("actual dob: " + actualDob);
    String actualfname = driver.findElement(proposerFirstNameMan).getText().trim();
    Reporter.log("actual first name: " + actualfname);
    String actuallname = driver.findElement(proposerLastNameMan).getText().trim();
    Reporter.log("actual last name: " + actuallname);
    String actualgender = driver.findElement(proposerGenderMan).getText().trim();
    Reporter.log("actual gender: " + actualgender);
    String actualRelation = driver.findElement(proposerRelationMan).getText().trim();
    Reporter.log("actual relation: " + actualRelation);
    String actualTitle = driver.findElement(proposerTitleMan).getText().trim();
    Reporter.log("actual title: " + actualTitle);
    softAssert.assertEquals(actualDob, dob, "this is " + actualDob + " not equal to: " + dob);
    softAssert.assertEquals(actualfname, fname, "this is " + actualfname + " not equal to: " + fname);
    softAssert.assertEquals(actuallname, lname, "this is " + actuallname + " not equal to: " + lname);
    softAssert.assertEquals(actualgender, gender, "this is " + actualgender + " not equal to: " + gender);
    softAssert.assertEquals(actualRelation, relation, "this is " + actualRelation + " not equal to: " + relation);
    softAssert.assertEquals(actualTitle, title, "this is " + actualTitle + " not equal to: " + title);

    softAssert.assertAll();

  }

  public void ProposerMandatoryPermanentAddress(String add1, String state, String city, String pincode) {
    action.scrollByAmount(0,150).perform();
    WebElement percity = driver.findElement(proposerPerCityMan);
    action.moveToElement(percity).perform();
    String actualPerAdd1 = driver.findElement(proposerPerAddLine1Man).getText().trim();
    Reporter.log("proposer actual per add1: " + actualPerAdd1);
    String actualPerState = driver.findElement(proposerPerStateMan).getText().trim();
    Reporter.log("proposer actual per state: " + actualPerState);
    String actualPerCity = driver.findElement(proposerPerCityMan).getText().trim();
    Reporter.log("proposer actual per city: " + actualPerCity);
    String actualPerPincode = driver.findElement(proposerPerPincodeMan).getText().trim();
    Reporter.log("proposer actual per pincode: " + actualPerPincode);
    softAssert.assertEquals(actualPerAdd1, add1, "this is " + actualPerAdd1 + " not equal to: " + add1);
    softAssert.assertEquals(actualPerState, state, "this is " + actualPerState + " not equal to: " + state);
    softAssert.assertEquals(actualPerCity, city, "this is " + actualPerCity + " not equal to: " + city);
    softAssert.assertEquals(actualPerPincode, pincode, "this is " + actualPerPincode + " not equal to: " + pincode);
    softAssert.assertAll();
  }

  public void ProposerMandatoryCommunicationAddress(String add1, String state, String city, String pincode, String email) {
    WebElement commcity = driver.findElement(proposerCommCityMan);
    action.moveToElement(commcity).perform();
    String actualCommAdd1 = driver.findElement(proposerCommAddLine1Man).getText().trim();

    String actualCommState = driver.findElement(proposerCommStateMan).getText().trim();
    String actualCommCity = driver.findElement(proposerCommCityMan).getText().trim();
    String actualCommPincode = driver.findElement(proposerCommPincodeMan).getText().trim();
    String actualCommEmail = driver.findElement(proposerEmailMan).getText().trim();
    softAssert.assertEquals(actualCommAdd1, add1, "this is " + actualCommAdd1 + " not equal to: " + add1);
    softAssert.assertEquals(actualCommState, state, "this is " + actualCommState + " not equal to: " + state);
    softAssert.assertEquals(actualCommCity, city, "this is " + actualCommCity + " not equal to: " + city);
    softAssert.assertEquals(actualCommPincode, pincode, "this is " + actualCommPincode + " not equal to: " + pincode);
    softAssert.assertEquals(actualCommEmail, email, "this is " + actualCommEmail + " not equal to: " + email);
    softAssert.assertAll();
  }

  public void InsuredMandatoryFirstPartVerify(String dob, String fname, String lname,
                                              String gender, String relation, String title) {

    WebElement proButton = driver.findElement(proposerDetailButton);
    action.moveToElement(proButton).perform();
    driver.findElement(proposerDetailButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(insuredDobMan));
    String actualDob = driver.findElement(insuredDobMan).getText().trim();
    Reporter.log("actual dob: " + actualDob);
    String actualfname = driver.findElement(insuredFirstNameMan).getText().trim();
    Reporter.log("actual first name: " + actualfname);
    String actuallname = driver.findElement(insuredLastNameMan).getText().trim();
    Reporter.log("actual last name: " + actuallname);
    String actualgender = driver.findElement(insuredGenderMan).getText().trim();
    Reporter.log("actual gender: " + actualgender);
    String actualRelation = driver.findElement(insuredRelationMan).getText().trim();
    Reporter.log("actual relation: " + actualRelation);
    String actualTitle = driver.findElement(insuredTitleMan).getText().trim();
    Reporter.log("actual title: " + actualTitle);
    softAssert.assertEquals(actualDob, dob, "this is " + actualDob + " not equal to: " + dob);
    softAssert.assertEquals(actualfname, fname, "this is " + actualfname + " not equal to: " + fname);
    softAssert.assertEquals(actuallname, lname, "this is " + actuallname + " not equal to: " + lname);
    softAssert.assertEquals(actualgender, gender, "this is " + actualgender + " not equal to: " + gender);
    softAssert.assertEquals(actualRelation, relation, "this is " + actualRelation + " not equal to: " + relation);
    softAssert.assertEquals(actualTitle, title, "this is " + actualTitle + " not equal to: " + title);
    softAssert.assertAll();
  }

  public void InsuredMandatoryPermanentAddress(String add1, String state, String city, String pincode) {
    WebElement percity = driver.findElement(insuredCommCityMan);
    action.moveToElement(percity).perform();
    String actualPerAdd1 = driver.findElement(insuredPerAddLine1Man).getText().trim();
    String actualPerState = driver.findElement(insuredPerStateMan).getText().trim();
    String actualPerCity = driver.findElement(insuredPerCityMan).getText().trim();
    String actualPerPincode = driver.findElement(insuredPerPincodeMan).getText().trim();
    softAssert.assertEquals(actualPerAdd1, add1, "this is " + actualPerAdd1 + " not equal to: " + add1);
    softAssert.assertEquals(actualPerState, state, "this is " + actualPerState + " not equal to: " + state);
    softAssert.assertEquals(actualPerCity, city, "this is " + actualPerCity + " not equal to: " + city);
    softAssert.assertEquals(actualPerPincode, pincode, "this is " + actualPerPincode + " not equal to: " + pincode);
    softAssert.assertAll();
  }

  public void InsuredMandatoryCommunicationAddress(String add1, String state, String city, String pincode, String email) {
    WebElement commcity = driver.findElement(insuredCommCityMan);
    action.moveToElement(commcity).perform();
    String actualCommAdd1 = driver.findElement(insuredCommAddLine1Man).getText().trim();
    String actualCommState = driver.findElement(insuredCommStateMan).getText().trim();
    String actualCommCity = driver.findElement(insuredCommCityMan).getText().trim();
    String actualCommPincode = driver.findElement(insuredCommPincodeMan).getText().trim();
    String actualEmail = driver.findElement(insuredEmailMan).getText().trim();
    softAssert.assertEquals(actualCommAdd1, add1, "this is " + actualCommAdd1 + " not equal to: " + add1);
    softAssert.assertEquals(actualCommState, state, "this is " + actualCommState + " not equal to: " + state);
    softAssert.assertEquals(actualCommCity, city, "this is " + actualCommCity + " not equal to: " + city);
    softAssert.assertEquals(actualCommPincode, pincode, "this is " + actualCommPincode + " not equal to: " + pincode);
    softAssert.assertEquals(actualEmail, email, "this is " + actualEmail + " not equal to: " + email);
    softAssert.assertAll();
  }
}
