package CareInsurance.main;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIPOM {

  public final By addButton = By.xpath("//button[contains(.,'Add')]");
  private final By careTitle = By.xpath("//div[@class='title_new' and contains(.,'Care Insurance')]");
  private final By username = By.id("txtUserCode");
  private final By password = By.id("txtPassword");
  private final By captcha = By.id("txtCaptcha");
  private final By loginButton = By.id("btnLogin");
  private final By otp = By.xpath("//input[@placeholder='Enter Valid OTP']");
  private final By validate = By.id("btnValidateOTP");
  private final By services = By.xpath("//li//a[contains(., 'SERVICES ')]");
  private final By insurance = By.xpath("//a[contains(., 'INSURANCE')]");
  private final By careInsurance = By.xpath("//a[contains(., 'Care Insurance')]");

  //Care Insurance Enter Mobile and Otp and check for mandatory messages
  private final By mobilenumber = By.id("mobleNumber11");
  private final By OtpCI = By.id("otpNumber");
  private final By submitButton = By.xpath("//div[@class='col-md-6 text-end']//button[contains(.,'Submit')]");
  private final By mobilemandatory = By.xpath("//div[@class='invalid-feedback' and contains(.,' Please Enter Valid Contact Number ')]");
  private final By otpmandatory = By.xpath("//div[@class='invalid-feedback' and contains(.,' Please enter valid OTP ')]");
  private final By checkboxmandatory = By.xpath("//div[@class='invalid-feedback checkRed' and contains(.,' You must agree to the terms and conditions. ')]");
  private final By selectCheckbox = By.id("flexCheckDefault12345");
  private final By sendotp = By.xpath("//div[@class='col-md-3']//span[@class='optSend']");


  private final By premiumCalculate = By.xpath("//button[contains(.,'Calculate Premium')]");
  private final By proposerDetailButton = By.xpath("//button[contains(.,'Proposer Detail')]");
  private final By InsuredDetailButton = By.xpath("//button[contains(.,'Insured Detail')]");

  //Care First Part
  private final By coverType = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[1]//input");
  private final By coverTypeArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[1]//span[@class='ng-arrow-wrapper']");
  private final By sumInsured = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[2]//input");
  private final By DropDownWait = By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']");
  private final By sumInsuredArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[2]//span[@class='ng-arrow-wrapper']");
  private final By numberOfMembers = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[3]//input");
  private final By numberofMembersArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[3]//span[@class='ng-arrow-wrapper']");
  private final By period = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[4]//input");
  private final By periodArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[4]//span[@class='ng-arrow-wrapper']");
  private final By product = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[5]//input");
  private final By productArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[5]//span[@class='ng-arrow-wrapper']");
  private final By insuranceType = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[6]//input");
  private final By insuranceTypeArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[6]//span[@class='ng-arrow-wrapper']");
  private final By nomineeName = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[7]//input");
  private final By nomineeRelation = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[8]//input");
  private final By nomineeRelationArrow = By.xpath("//div[@class='row mt-1']//div[@class='row']/div[8]//span[@class='ng-arrow-wrapper']");

  //Proposer Detail and mandatory messages
  private final By calendarButtonProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='d-flex position-relative']//button");
  private final By firstNameProposer = By.xpath("//div[@id='accordionExample']/div[1]//input[@id='firstName11']");
  private final By lastNameProposer = By.xpath("//div[@id='accordionExample']/div[1]//input[@id='lastName12']");
  private final By genderProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[4]//input");
  private final By genderArrowProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[4]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By relationProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[5]//input");
  private final By relationArrowProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[5]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By titleProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[6]//input");
  private final By titleArrowProposer = By.xpath("//div[@id='accordionExample']/div[1]//div[6]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By proposerEmail = By.xpath("//div[@id='accordionExample']/div[1]//input[@formcontrolname='emailId']");

  //Proposer Permanent  Address
  private final By proposerPermanentAddressLine1 = By.xpath("//div[@id='accordionExample']/div[1]//div[8]//input");
  private final By proposerPermanentAddressLine2 = By.xpath("//div[@id='accordionExample']/div[1]//div[9]//input");
  private final By proposerPermannentAddressArea = By.xpath("//div[@id='accordionExample']/div[1]//div[10]//input");
  private final By proposerPermanentState = By.xpath("//div[@id='accordionExample']/div[1]//div[11]//input");
  private final By proposerPermanentStateArrow = By.xpath("//div[@id='accordionExample']/div[1]//div[11]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By proposerPermanentCity = By.xpath("//div[@id='accordionExample']/div[1]//div[12]//input");
  private final By proposerPermanentCityArrow = By.xpath("//div[@id='accordionExample']/div[1]//div[12]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By proposerPermanentPinCode = By.xpath("//div[@id='accordionExample']/div[1]//div[13]//input");
  private final By proposercheckbox = By.xpath("//div[@id='accordionExample']/div[1]//div[@class='cutomCheck']//input");

  //Propose Comm Address
  private final By proposerCommunicationAddressLine1 = By.xpath("//div[@id='accordionExample']/div[1]//div[16]//input");
  private final By proposerCommunicationAddressLine2 = By.xpath("//div[@id='accordionExample']/div[1]//div[17]//input");
  private final By proposerCommunicationtAddressArea = By.xpath("//div[@id='accordionExample']/div[1]//div[18]//input");
  private final By proposerCommunicationState = By.xpath("//div[@id='accordionExample']/div[1]//div[19]//input");
  private final By proposerCommunicationtateArrow = By.xpath("//div[@id='accordionExample']/div[1]//div[19]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By proposerCommunicationCity = By.xpath("//div[@id='accordionExample']/div[1]//div[20]//input");
  private final By proposerCommunicationCityArrow = By.xpath("//div[@id='accordionExample']/div[1]//div[20]//ng-select//span[@class='ng-arrow-wrapper']");
  private final By proposerCommunicationPinCode = By.xpath("//div[@id='accordionExample']/div[1]//div[21]//input");


  //Insured Detail and mandatory messages
  private final By calendarButtonInsured = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='d-flex position-relative']//button");




  private final By firstNameInsured = By.xpath("//div[@id='accordionExample']/div[2]//input[@id='firstName15']");

  private final By lastNameInsured = By.xpath("//div[@id='accordionExample']/div[2]//input[@id='lastName15']");

  public By getFirstNameInsured() {
    return firstNameInsured;
  }

  public By getLastNameInsured() {
    return lastNameInsured;
  }
  private final By genderInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='genderId']//input");
  private final By genderArrowInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='genderId']//span[@class='ng-arrow-wrapper']");
  private final By relationInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='insuredRelationId']//input");
  private final By relationArrowInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='insuredRelationId']//span[@class='ng-arrow-wrapper']");
  private final By titleInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='titleId']//input");
  private final By titleArrowInsured = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='titleId']//span[@class='ng-arrow-wrapper']");
  private final By insuredEmail = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='emailId']");


  //Insured Detail Get
  private final By insuredDOBGet = By.xpath("//div[@id='accordionExample']/div[2]//div[1]//input");
  private final By insuredFnameGet = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='firstName']");
  private final By insuredLnameGet = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='lastName']");
  private final By insuredGender = By.xpath("//div[@id='accordionExample']/div[2]//div[4]//input");
  private final By insuredGenderGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='genderId']//span[@class='ng-value-label']");
  private final By insuredRelation = By.xpath("//div[@id='accordionExample']/div[2]//div[5]//input");
  private final By insuredRelationGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='insuredRelationId']//span[@class='ng-value-label']");
  private final By insuredTitle = By.xpath("//div[@id='accordionExample']/div[2]//div[6]//input");
  private final By insuredTitleGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='titleId']//span[@class='ng-value-label']");

  //Insured Permanent Address
  private final By insuredPermanentAddressLine1 = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='permanentAddressLine1']");
  private final By insuredPermanentAddressLine2 = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='permanentAddressLine2']");
  private final By insuredPermannentAddressArea = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='permanentArea']");
  private final By insuredPermanentState = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentState']//input");
  private final By insuredPermanentStateGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentState']//span[@class='ng-value-label']");
  private final By insuredPermanentStateArrow = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentState']//span[@class='ng-arrow-wrapper']");
  private final By insuredPermanentCity = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentCity']//input");
  private final By insuredPermanentCityGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentCity']//span[@class='ng-value-label']");
  private final By insuredPermanentCityArrow = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='permanentCity']//span[@class='ng-arrow-wrapper']");
  private final By insuredPermanentPinCode = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='permanentPinCode']");
  private final By insuredcheckbox = By.xpath("//div[@id='accordionExample']/div[2]//div[@class='cutomCheck']//input[@formcontrolname='redidenceAddress']");

  //Insured Comm Address
  private final By insuredCommunicationAddressLine1 = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='communicationAddressLine1']");
  private final By insuredCommunicationAddressLine2 = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='communicationAddressLine2']");
  private final By insuredCommunicationAddressArea = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='communicationArea']");
  private final By insuredCommunicationState = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationState']//input");
  private final By insuredCommunicationStateArrow = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationState']//span[@class='ng-arrow-wrapper']");
  private final By insuredCommunicationStateGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationState']//span[@class='ng-value-label']");
  private final By insuredCommunicationCity = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationCity']//input");
  private final By insuredCommunicationCityArrow = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationCity']//span[@class='ng-arrow-wrapper']");
  private final By insuredCommunicationCityGet = By.xpath("//div[@id='accordionExample']/div[2]//ng-select[@formcontrolname='communicationCity']//span[@class='ng-value-label']");
  private final By insuredCommunicationPincode = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='communicationPinCode']");

  //Add Insured Button
  private final By addInsured = By.xpath("//button[contains(.,'Add')]");

  //Calculate Premium Button
  private final By calculatePremiumButton = By.xpath("//button[contains(.,'Calculate Premium')]");

  //Payment Pop up
  private final By firstCheckboxPayment = By.id("flexCheckDefault12");
  private final By secondCheckboxPayment = By.id("flexCheckDefault");
  private final By paymentOtp = By.xpath("//span[@class='optSend']");
  private final By paymentSubmitButton = By.xpath("//button[contains(.,'Submit')]");
  private final By paymentPopUpClose=By.xpath("//button[text()='Close']");

  //Gender Mismatch error message
  private final By genderMismatchMessage = By.xpath(" //p[contains(.,'{*} Gender and title mismatch.')]");
  private final By arrowWait = By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']");
  private final By insuredContact = By.xpath("//div[@id='accordionExample']/div[2]//input[@formcontrolname='mobileNo']");

  private final By policyNumber = By.xpath("//p[contains(.,'Your Policy Generated Successfully with policy no')]");
  private final By premiumAmount = By.xpath("//h3[@class='modalHed']/b");
  private final By premiumClose = By.xpath("//button[contains(.,'Close')]");
  private final By resultView = By.xpath("//button[contains(.,'View')]");
  private final By searchBar = By.id("searchBar2");
  private final By editButton = By.xpath("//button[@class='btn btn-outline-primary btnvh fa fa-edit']");
  WebDriver driver;
  WebDriverWait wait;
  Actions action;
  SoftAssert softAssert;
  TestUtility testUtility;


  public CIPOM(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    action = new Actions(driver);
    softAssert = new SoftAssert();
    testUtility =new TestUtility();
  }

  public void enterCredentials(String uName, String pwd, String captch) {
    driver.findElement(username).sendKeys(uName);
    driver.findElement(password).sendKeys(pwd);
    driver.findElement(captcha).sendKeys(captch);
    driver.findElement(loginButton).click();
  }

  public void enterOTP(String MOtp) {
    //utility.waitForElement(otp);
    wait.until(ExpectedConditions.visibilityOfElementLocated(otp));
    //testUtility.getElement(otp).sendKeys(MOtp);
    driver.findElement(otp).sendKeys(MOtp);
    driver.findElement(validate).click();
  }

  public void careInsurance() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='topbar']")));
    WebElement serv = driver.findElement(services);
    action.moveToElement(serv).perform();
    //wait.until(ExpectedConditions.visibilityOfElementLocated(services)).click();
    WebElement insu = driver.findElement(insurance);
    action.moveToElement(insu).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(careInsurance)).click();
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
   // Boolean otplength = otpmaxlength.equals(otplth);
    softAssert.assertEquals(otpmaxlength, otplth, "this is max length check for otp");
    softAssert.assertAll();
  }

  public void firstScreenMandatory(String mmandate, String otpman, String cbman) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
    String mobilemandat = driver.findElement(mobilemandatory).getText().trim();
    String otpmandat = driver.findElement(otpmandatory).getText().trim();
    String checkboxmandat = driver.findElement(checkboxmandatory).getText().trim();
    softAssert.assertEquals(mobilemandat, mmandate, "this is failed because: " + mobilemandat + " is not equal to: " + mmandate);
    softAssert.assertEquals(otpmandat, otpman);
    softAssert.assertEquals(checkboxmandat, cbman);
    softAssert.assertAll();
  }

  public void entermobileotp(String mobile) {
    wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    driver.findElement(mobilenumber).sendKeys(mobile);
    driver.findElement(sendotp).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-dismissable bg-success custom-toastbar-alert text-white']//p")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='alert alert-dismissable bg-success custom-toastbar-alert text-white']//p")));

  }

  public void enterOtp(int otp) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(OtpCI));
    driver.findElement(OtpCI).sendKeys("" + otp);
    driver.findElement(selectCheckbox).click();
    driver.findElement(submitButton).click();
  }

  public void goToHomeAction() {
    System.out.println("entering the home key");

    action.sendKeys(Keys.HOME).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(coverTypeArrow));
    WebElement coverTypeArrowEle = driver.findElement(coverTypeArrow);
    action.moveToElement(coverTypeArrowEle).perform();

  }

  public void CareFormValidation() {

    //wait.until(ExpectedConditions.visibilityOfElementLocated(proposerDetailButton)).click();
    action.sendKeys(Keys.END).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(premiumCalculate));
    WebElement premium = driver.findElement(premiumCalculate);
    action.moveToElement(premium).click().perform();
  }

  public void enterFirstPart(String cType, String sInsured, String numberMember
        , String InsurancePeriod,
                             String InsuranceProduct, String Itype, String nName,
                             String nRelation) {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title_new']")));
      wait.until(ExpectedConditions.visibilityOfElementLocated(coverTypeArrow));
      driver.findElement(coverTypeArrow).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-option-label' and text()='Individual']")));
      driver.findElement(coverType).sendKeys(cType);
      action.sendKeys(Keys.ENTER).perform();
      String indiText = driver.findElement(By.xpath("//span[@class='ng-value-label' and text()='Individual']")).getText().trim();
      Reporter.log("this is the text " + indiText);

    } catch (Exception e) {
      Reporter.log("enter not hit: " + e.getMessage());
    }
    try {
      driver.findElement(sumInsuredArrow).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));
      driver.findElement(sumInsured).sendKeys(sInsured);
      action.sendKeys(Keys.ENTER).perform();
    } catch (Exception e) {
      Reporter.log("enter not hit: " + e.getMessage());
    }

    driver.findElement(numberofMembersArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));

    driver.findElement(numberOfMembers).sendKeys(numberMember);
    action.sendKeys(Keys.ENTER).perform();

    driver.findElement(periodArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));

    driver.findElement(period).sendKeys(InsurancePeriod);
    action.sendKeys(Keys.ENTER).perform();

    driver.findElement(productArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));

    driver.findElement(product).sendKeys(InsuranceProduct);
    action.sendKeys(Keys.ENTER).perform();

    driver.findElement(insuranceTypeArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));
    driver.findElement(insuranceType).sendKeys(Itype);
    action.sendKeys(Keys.ENTER).perform();

    driver.findElement(nomineeName).sendKeys(nName);


    driver.findElement(nomineeRelationArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));
    driver.findElement(By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + nRelation + "']")).click();
    //driver.findElement(nomineeRelation).sendKeys(nRelation);
    //action.sendKeys(Keys.ENTER).build().perform();

  }

  public void proposerDetailEnter(String year, String month, String date
        , String fname, String lname, String gend, String rel, String tit) {
    WebElement cButton = driver.findElement(calendarButtonProposer);
    action.moveToElement(cButton).perform();
    driver.findElement(calendarButtonProposer).click();
    Select selectYear = new Select(driver.findElement(By.xpath("//select[@aria-label='Select year']")));

    Select selectMonth = new Select(driver.findElement(By.xpath("//select[@aria-label='Select month']")));

    selectYear.selectByVisibleText(year);
    selectMonth.selectByVisibleText(month);

    driver.findElement(By.xpath("//div[@class='btn-light' and text()='" + date + "']")).click();
    try {
      String indiText = driver.findElement(By.xpath("//span[@class='ng-value-label' and text()='Individual']")).getText().trim();
      Reporter.log("this is the text " + indiText);

    } catch (Exception e) {
      Reporter.log("text not captured " + e.getMessage());
    }
    driver.findElement(firstNameProposer).sendKeys(fname);
    driver.findElement(lastNameProposer).sendKeys(lname);
    driver.findElement(genderArrowProposer).click();
    driver.findElement(genderProposer).sendKeys(gend);
    action.sendKeys(Keys.ENTER).build().perform();
    driver.findElement(relationArrowProposer).click();
    driver.findElement(relationProposer).sendKeys(rel);
    action.sendKeys(Keys.ENTER).build().perform();

    driver.findElement(titleArrowProposer).click();
    driver.findElement(titleProposer).sendKeys(tit);
    action.sendKeys(Keys.ENTER).build().perform();
  }

  public void enterProposerPermanentAddress(String add1, String add2, String area,
                                            String state, String city,
                                            String pincode, String propEmail) {
    driver.findElement(proposerPermanentAddressLine1).sendKeys(add1);
    driver.findElement(proposerPermanentAddressLine2).sendKeys(add2);
    driver.findElement(proposerPermannentAddressArea).sendKeys(area);
    try {
      WebElement stateArrow = driver.findElement(proposerPermanentStateArrow);
      action.moveToElement(stateArrow).perform();
      driver.findElement(proposerPermanentStateArrow).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
      driver.findElement(proposerPermanentState).sendKeys(state);
      action.sendKeys(Keys.ENTER).build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accordionExample']/div[1]//div[11]//span[@class='ng-value-label' and text()='DELHI & NCR']")));

    } catch (Exception e) {
      Reporter.log("enter not hit address " + e.getMessage());
    }
    try {
      WebElement cityArrow = driver.findElement(proposerPermanentCityArrow);
      action.moveToElement(cityArrow).perform();
      driver.findElement(proposerPermanentCityArrow).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
      driver.findElement(proposerPermanentCity).sendKeys(city);
      action.sendKeys(Keys.ENTER).build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accordionExample']/div[1]//div[12]//span[@class='ng-value-label' and text()='Gurgaon']")));
    } catch (Exception e) {
      Reporter.log("enter not hit address " + e.getMessage());
    }

    driver.findElement(proposerPermanentPinCode).sendKeys(pincode);

    driver.findElement(proposerEmail).sendKeys(propEmail);
  }

  public void ProposercheckboxPermCommSame() {
    WebElement checkboxEle = driver.findElement(proposercheckbox);
    action.moveToElement(checkboxEle).perform();
    driver.findElement(proposercheckbox).click();
  }

  public void InsuredcheckboxPermCommSame() {
    WebElement checkboxEle = driver.findElement(insuredcheckbox);
    action.moveToElement(checkboxEle).perform();
    driver.findElement(insuredcheckbox).click();
  }

  public void openInsuredBlock() {
    WebElement insuredButton = driver.findElement(InsuredDetailButton);
    action.moveToElement(insuredButton).build().perform();
    driver.findElement(InsuredDetailButton).click();
  }

  public void verifyEnteriesInsuredBlock(String expectedDate, String fname, String lname, String gend,
                                         String rel, String tit, String Padd1, String Padd2, String Parea, String Pstate, String Pcity
        , String Ppincode, String Cadd1, String Cadd2, String Carea, String Cstate, String Ccity, String Cpincode, String email) {
    openInsuredBlock();
    String ActualdateText = driver.findElement(By.xpath("//div[@id='accordionExample']/div[2]//div[1]//input")).getAttribute("value").trim();
    //WebElement dateInput =driver.findElement(By.xpath("//div[@id='accordionExample']/div[2]//div[1]//input"));
    //String dateText1 = dateInput.getAttribute("value");
    Reporter.log("this is date text: " + ActualdateText);
    String ActualFName = driver.findElement(getFirstNameInsured()).getAttribute("value").trim();
    Reporter.log("this is actual First name: " + ActualFName);
    String ActualLName = driver.findElement(getLastNameInsured()).getAttribute("value").trim();
    Reporter.log("this is actual last name: " + ActualLName);
    String ActualGender = driver.findElement(insuredGenderGet).getText().trim();
    Reporter.log("this is actual Gender " + ActualGender);

    String ActualRelation = driver.findElement(insuredRelationGet).getText().trim();
    Reporter.log("this is actual relation: " + ActualRelation);
    String ActualTitle = driver.findElement(insuredTitleGet).getText().trim();
    Reporter.log("this is actual title: " + ActualTitle);
    String ActualPermanentAddressLine1 = driver.findElement(insuredPermanentAddressLine1).getAttribute("value").trim();
    Reporter.log("this is actual p address 1: " + ActualPermanentAddressLine1);
    String ActualPermanentAddressLine2 = driver.findElement(insuredPermanentAddressLine2).getAttribute("value").trim();
    Reporter.log("this is actual p address 2: " + ActualPermanentAddressLine2);
    String ActualPermanentArea = driver.findElement(insuredPermannentAddressArea).getAttribute("value").trim();
    Reporter.log("this is actual p area " + ActualPermanentArea);
    String ActualPermanentState = driver.findElement(insuredPermanentStateGet).getText().trim();
    Reporter.log("this is actual permanent state: " + ActualPermanentState);
    String ActualPermanentCity = driver.findElement(insuredPermanentCityGet).getText().trim();
    String ActualPermanentPincode = driver.findElement(insuredPermanentPinCode).getAttribute("value").trim();
    String ActualCommunicationAddressLine1 = driver.findElement(insuredCommunicationAddressLine1).getAttribute("value").trim();
    String ActualCommunicationAddressLine2 = driver.findElement(insuredCommunicationAddressLine2).getAttribute("value").trim();
    String ActualCommunicationArea = driver.findElement(insuredCommunicationAddressArea).getAttribute("value").trim();
    String ActualCommunicationState = driver.findElement(insuredCommunicationStateGet).getText().trim();
    String ActualCommunicationCity = driver.findElement(insuredCommunicationCityGet).getText().trim();
    String ActualCommunicationPincode = driver.findElement(insuredCommunicationPincode).getAttribute("value").trim();
    String ActualInsuredEmail = driver.findElement(insuredEmail).getAttribute("value").trim();
    Reporter.log("This is actual permanent city: " + ActualPermanentCity);
    Reporter.log("This is actual permanent pincode: " + ActualPermanentPincode);
    Reporter.log("This is actual communication address line 1: " + ActualCommunicationAddressLine1);
    Reporter.log("This is actual communication address line 2: " + ActualCommunicationAddressLine2);
    Reporter.log("This is actual communication area: " + ActualCommunicationArea);
    Reporter.log("This is actual communication state: " + ActualCommunicationState);
    Reporter.log("This is actual communication city: " + ActualCommunicationCity);
    Reporter.log("This is actual communication pincode: " + ActualCommunicationPincode);
    Reporter.log("this is actual email: " + ActualInsuredEmail);
    softAssert.assertEquals(ActualdateText, expectedDate, "Actual date is " + ActualdateText + " but expected is " + expectedDate);
    softAssert.assertEquals(ActualFName, fname, "Actual first name is " + ActualFName + " but expected is " + fname);
    softAssert.assertEquals(ActualLName, lname, "Actual last name is " + ActualLName + " but expected is " + lname);
    softAssert.assertEquals(ActualGender, gend, "Actual gender is " + ActualGender + " but expected is " + gend);
    softAssert.assertEquals(ActualRelation, rel, "Actual relation is " + ActualRelation + " but expected is " + rel);
    softAssert.assertEquals(ActualTitle, tit, "Actual title is " + ActualTitle + " but expected is " + tit);
    softAssert.assertEquals(ActualPermanentAddressLine1, Padd1, "Actual permanent address line 1 is " + ActualPermanentAddressLine1 + " but expected is " + Padd1);
    softAssert.assertEquals(ActualPermanentAddressLine2, Padd2, "Actual permanent address line 2 is " + ActualPermanentAddressLine2 + " but expected is " + Padd2);
    softAssert.assertEquals(ActualPermanentArea, Parea, "Actual permanent area is " + ActualPermanentArea + " but expected is " + Parea);
    softAssert.assertEquals(ActualPermanentState, Pstate, "Actual permanent state is " + ActualPermanentState + " but expected is " + Pstate);
    softAssert.assertEquals(ActualPermanentCity, Pcity, "Actual permanent city is " + ActualPermanentCity + " but expected is " + Pcity);
    softAssert.assertEquals(ActualPermanentPincode, Ppincode, "Actual permanent pincode is " + ActualPermanentPincode + " but expected is " + Ppincode);
    softAssert.assertEquals(ActualCommunicationAddressLine1, Cadd1, "Actual communication address line 1 is " + ActualCommunicationAddressLine1 + " but expected is " + Cadd1);
    softAssert.assertEquals(ActualCommunicationAddressLine2, Cadd2, "Actual communication address line 2 is " + ActualCommunicationAddressLine2 + " but expected is " + Cadd2);
    softAssert.assertEquals(ActualCommunicationArea, Carea, "Actual communication area is " + ActualCommunicationArea + " but expected is " + Carea);
    softAssert.assertEquals(ActualCommunicationState, Cstate, "Actual communication state is " + ActualCommunicationState + " but expected is " + Cstate);
    softAssert.assertEquals(ActualCommunicationCity, Ccity, "Actual communication city is " + ActualCommunicationCity + " but expected is " + Ccity);
    softAssert.assertEquals(ActualCommunicationPincode, Cpincode, "Actual communication pincode is " + ActualCommunicationPincode + " but expected is " + Cpincode);
    softAssert.assertEquals(ActualInsuredEmail, email, "Actual insured email is " + ActualInsuredEmail + " but expected is " + email);
    softAssert.assertAll();
  }

  public void addInsured() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(addInsured));
    WebElement addbutton = driver.findElement(addInsured);
    action.moveToElement(addbutton).perform();
    driver.findElement(addInsured).click();
  }
  public void proposerInsuredSameAddress(){
    wait.until(ExpectedConditions.visibilityOfElementLocated(paymentPopUpClose)).click();
  }

  public void verifyGridData(String fname, String lname, String gend, String add, String mobilenumber, String email) {

    WebElement insuredData = driver.findElement(By.xpath("//table[@class='table table-bordered']"));
    action.moveToElement(insuredData).perform();

    String firstName = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[1]")).getText().trim();
    String lastName = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[2]")).getText().trim();
    String gender = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[3]")).getText().trim();
    String address = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[4]")).getText().trim();
    String mobile = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[5]")).getText().trim();
    String emailId = driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//td[6]")).getText().trim();

    softAssert.assertEquals(firstName, fname, "First name does not match. Expected: String " + fname + ", Actual: " + firstName);
    softAssert.assertEquals(lastName, lname, "Last name does not match. Expected: String " + lname + ", Actual: " + lastName);
    softAssert.assertEquals(gender, gend, "Gender does not match. Expected: String " + gend + ", Actual: " + gender);
    softAssert.assertEquals(address, add, "Address does not match. Expected: String " + add + ", Actual: " + address);
    softAssert.assertEquals(mobile, mobilenumber, "Mobile number does not match. Expected: String " + mobilenumber + ", Actual: " + mobile);
    softAssert.assertEquals(emailId, email, "Email does not match. Expected: String " + email + ", Actual: " + emailId);
    softAssert.assertAll();
  }

  public void calculatePremiumClick() {
    action.sendKeys(Keys.END).perform();
    WebElement calpre = driver.findElement(calculatePremiumButton);
    ////p[@_ngcontent-amd-c20='']
    action.moveToElement(calpre).perform();
    calpre.click();
    //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));

  }

  public void premiumpopupClose() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(premiumClose));
    driver.findElement(premiumClose).click();
  }

  public void resultView() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(resultView)).click();
  }

  public void clickEditButton() {
    wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

  }

  public void searchproposalno(String proposalno) {
    driver.findElement(searchBar).sendKeys(proposalno);
  }

  public void capturememberlesserrormessage(String error) {
    String errormessage = driver.findElement(By.xpath("//p[contains(.,'The selected cover type and the number of members must match. Please ensure they are the same.')]")).getText().trim();
    softAssert.assertEquals(errormessage, error, "this is blah blah blah");
    softAssert.assertAll();

  }

  public void paymentPopUp() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-header']")));
    driver.findElement(firstCheckboxPayment).click();
    driver.findElement(secondCheckboxPayment).click();
    driver.findElement(paymentOtp).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-dismissable bg-success custom-toastbar-alert text-white']//p")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='alert alert-dismissable bg-success custom-toastbar-alert text-white']//p")));
  }

  public void changeCoverType(String cType, String sInsured,String numberMember){
    wait.until(ExpectedConditions.visibilityOfElementLocated(coverTypeArrow)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']")));
    driver.findElement(coverType).sendKeys(cType);
    action.sendKeys(Keys.ENTER).perform();
    //String indiText = driver.findElement(By.xpath("//span[@class='ng-value-label' and text()='Individual']")).getText().trim();
    //Reporter.log("this is the text " + indiText);

    try {
    driver.findElement(sumInsuredArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));
    driver.findElement(sumInsured).sendKeys(sInsured);
    action.sendKeys(Keys.ENTER).perform();
  } catch (Exception e) {
    Reporter.log("enter not hit: " + e.getMessage());
  }
    driver.findElement(numberofMembersArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownWait));
    driver.findElement(numberOfMembers).sendKeys(numberMember);
    action.sendKeys(Keys.ENTER).perform();
  }

  public String getPremiumAmount() {
    String premAmt="";
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.bg-danger.custom-toastbar-alert p")));
      String errorText = driver.findElement(By.cssSelector("div.alert.bg-danger.custom-toastbar-alert p")).getText();
      Reporter.log("error text is: " + errorText);
      Reporter.log("Payment pop not opened up.");
    } catch (Exception e) {
      Reporter.log("checking in the catch block about the pop up.");
      wait.until(ExpectedConditions.visibilityOfElementLocated(premiumAmount));
      premAmt = driver.findElement(premiumAmount).getText();
      return premAmt;
    }
    return premAmt;
  }
  public void enterOtpPayment(int otp) {
    driver.findElement(By.xpath("//input[@formcontrolname='mobileOTP']")).sendKeys("" + otp);
    driver.findElement(paymentSubmitButton).click();

  }

  public void genderMismatch(String ExpectedGenderMessage) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(genderMismatchMessage));
    String genderMsg = driver.findElement(genderMismatchMessage).getText().trim();
    softAssert.assertEquals(genderMsg, ExpectedGenderMessage, "Actual Gender Message: " + genderMsg + " does not match with Expected Message: " + ExpectedGenderMessage);
    softAssert.assertAll();
  }

  public void ProposerDifferentCommunicationAddress(String padd1, String padd2, String parea,
                                                    String pState, String pCity, String pPincode, String pemail
        , String cadd1, String cadd2, String carea, String cState, String cCity, String cPincode) {

    enterProposerPermanentAddress(padd1, padd2, parea, pState, pCity, pPincode, pemail);
    driver.findElement(proposerCommunicationAddressLine1).sendKeys(cadd1);
    driver.findElement(proposerCommunicationAddressLine2).sendKeys(cadd2);
    driver.findElement(proposerCommunicationtAddressArea).sendKeys(carea);
    WebElement stateArrow = driver.findElement(proposerCommunicationtateArrow);
    action.moveToElement(stateArrow).perform();
    driver.findElement(proposerCommunicationtateArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(proposerCommunicationState).sendKeys(cState);
    action.sendKeys(Keys.ENTER).perform();
    WebElement cityArrow = driver.findElement(proposerCommunicationCityArrow);
    action.moveToElement(cityArrow).perform();
    driver.findElement(proposerCommunicationCityArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(proposerCommunicationCity).sendKeys(cCity);
    action.sendKeys(Keys.ENTER).perform();
    driver.findElement(proposerCommunicationPinCode).sendKeys(cPincode);
  }

  public void enterInsuredDetails(String year, String month, String date, String ifname, String ilname,
                                  String igend, String irel, String itit) {
    WebElement calbutton=driver.findElement(calendarButtonInsured);
    action.moveToElement(calbutton).perform();
    driver.findElement(calendarButtonInsured).click();
    Select selectYear = new Select(driver.findElement(By.xpath("//select[@aria-label='Select year']")));
    Select selectMonth = new Select(driver.findElement(By.xpath("//select[@aria-label='Select month']")));
    selectYear.selectByVisibleText(year);
    selectMonth.selectByVisibleText(month);
    driver.findElement(By.xpath("//div[@class='btn-light' and text()='" + date + "']")).click();
    driver.findElement(firstNameInsured).sendKeys(ifname);
    driver.findElement(lastNameInsured).sendKeys(ilname);
    WebElement genderArrow=driver.findElement(genderArrowInsured);
    action.moveToElement(genderArrow).perform();
    genderArrow.click();
    driver.findElement(genderInsured).sendKeys(igend);
    action.sendKeys(Keys.ENTER).build().perform();
    WebElement relArrow=driver.findElement(relationArrowInsured);
    action.moveToElement(relArrow).perform();
    relArrow.click();
    driver.findElement(relationInsured).sendKeys(irel);
    action.sendKeys(Keys.ENTER).build().perform();
    driver.findElement(titleArrowInsured).click();
    driver.findElement(titleInsured).sendKeys(itit);
    action.sendKeys(Keys.ENTER).build().perform();
  }

  public void enterInsuredPermanentAddress(String ipadd1, String ipadd2, String iparea, String ipstate, String ipcity, String ippincode, String icontact, String ipemail) {
    driver.findElement(insuredPermanentAddressLine1).sendKeys(ipadd1);
    driver.findElement(insuredPermanentAddressLine2).sendKeys(ipadd2);
    driver.findElement(insuredPermannentAddressArea).sendKeys(iparea);
    WebElement stateArrow = driver.findElement(insuredPermanentStateArrow);
    action.moveToElement(stateArrow).perform();
    stateArrow.click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(insuredPermanentState).sendKeys(ipstate);
    action.sendKeys(Keys.ENTER).perform();
    WebElement insuredCityArrow=driver.findElement(insuredPermanentCityArrow);
    action.moveToElement(insuredCityArrow).perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(insuredPermanentCityArrow)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(insuredPermanentCity).sendKeys(ipcity);
    action.sendKeys(Keys.ENTER).perform();
    driver.findElement(insuredPermanentPinCode).sendKeys(ippincode);
    driver.findElement(insuredContact).sendKeys(icontact);
    driver.findElement(insuredEmail).sendKeys(ipemail);
  }

  public void InsuredDifferentCommunicationAddress(String padd1, String padd2, String parea,
                                                   String pState, String pCity, String pPincode, String pemail
        , String cadd1, String cadd2, String carea, String cState, String cCity, String cPincode, String cContact) {

    enterInsuredPermanentAddress(padd1, padd2, parea, pState, pCity, pPincode, cContact, pemail);
    driver.findElement(insuredCommunicationAddressLine1).sendKeys(cadd1);
    driver.findElement(insuredCommunicationAddressLine2).sendKeys(cadd2);
    driver.findElement(insuredCommunicationAddressArea).sendKeys(carea);
    WebElement stateArrow = driver.findElement(insuredCommunicationStateArrow);
    action.moveToElement(stateArrow).perform();
    driver.findElement(insuredCommunicationStateArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(insuredCommunicationState).sendKeys(cState);
    action.sendKeys(Keys.ENTER).perform();
    WebElement cityArrow = driver.findElement(insuredCommunicationCityArrow);
    action.moveToElement(cityArrow).perform();
    driver.findElement(insuredCommunicationCityArrow).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(arrowWait));
    driver.findElement(insuredCommunicationCity).sendKeys(cCity);
    action.sendKeys(Keys.ENTER).perform();
    driver.findElement(insuredCommunicationPincode).sendKeys(cPincode);
  }

  public String getPolicyNumber() {
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(policyNumber));
      String pnumber = driver.findElement(policyNumber).getText().trim();
// Regular expression to find the policy number
      String regex = "(\\d+)";

      // Create a pattern object
      Pattern pattern = Pattern.compile(regex);

      // Create a matcher object
      Matcher matcher = pattern.matcher(pnumber);

      if (matcher.find()) {
        String policyNo = matcher.group(1);
        System.out.println("Extracted Policy Number: " + policyNo);
        if(policyNo.isEmpty()){
          Reporter.log("policy is generated with the pop up but there is no policy number in it. Check log for more info.");
          softAssert.assertEquals(true,false);
          softAssert.assertAll();
        }
        else{
          wait.until(ExpectedConditions.invisibilityOfElementLocated(policyNumber));
          return matcher.group(1);
        }

      } else {

        System.out.println("Policy number not found in the string.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(policyNumber));
        return null;
      }
    } catch (TimeoutException timeoutException) {
      Reporter.log("time out exception because the policy is not created. " + timeoutException.getMessage());
      return null;
    }

  return  null;}

}


