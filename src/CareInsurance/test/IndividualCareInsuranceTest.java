package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import DBPackage.DbClass;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class IndividualCareInsuranceTest extends BaseClassUAT {
  SoftAssert softAssert = new SoftAssert();
  String usernameAdmin;
  String passwordAdmin;
  String coverType = "Individual";
  String[] sinsure = {"50K", "1L", "3L", "5L"};
  Random random = new Random();
  int sinsuIndex = random.nextInt(sinsure.length);
  String numberOfMembers = "1";
  int numberOfMembersCompare = 1;
  String InsuPeriod = "1Y";
  int insuPeriodCompare = 1;
  String InsuProduct = "Group Care 360°(ROINET)-GMC";
  String InsuType = "Health Insurance";
  String nomineeName = "Rohit";
  String[] nomineeRelation = {"MOTHER", "SON", "DAUGHTER", "WIFE", "HUSBAND", "BROTHER", "FATHER", "GRAND FATHER", "GRAND MOTHER", "MOTHER-IN-LAW", "SISTER-IN-LAW", "BROTHER-IN-LAW"};// "MOTHER","SON", "DAUGHTER", "WIFE", "HUSBAND", "BROTHER", "FATHER", "GRAND FATHER", "GRAND MOTHER","MOTHER-IN-LAW", "SISTER-IN-LAW","BROTHER-IN-LAW"
  int nomineeRelIndex = random.nextInt(nomineeRelation.length);

  String year = "1990";
  String month = "Jul";
  String date = "3";
  String fname = "iyu";
  String lname = "trtyt";
  String gend = "Male";
  String genderDB = "M";
  String rel = "SELF";
  String tit = "Mr.";
  String add1 = "poipoo ouwep pipi pi";
  String add2 = "trteyr erwerwiiu uiuiuiui";
  String area = "trte rtertert rtrwwwt";
  String state = "DELHI &";
  String city = "Gurgaon";
  String pincode = "122012";
  String propEmail = "pouipi@cwewrc.in";
  String dateCompare = "03/07/1990";
  String dateCompareDB = "1990-07-03";
  String stateCompare = "DELHI & NCR";
/*  int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();

  // Declare int variables
  int careinsuranceproposerdetailsid, channelid, permanentcity, permanentstate, communicationcity, communicationstate;

  // Declare String variables
  String customermobile, cspcode, title, firstname, lastname, dob, gender,
        permanentaddressline1, permanentaddressline2, permanentarea, permanentpincode,
        communicationaddressline1, communicationaddressline2, communicationarea;


  String communicationpinCode, pan, emailid, mobile, stdcode, nominee, nomineerelationwithproposer, proposalno, policyno, covertypeDB;
  int age, numberofmembersDB, policyperiod;
  String suminsured, premium, policystatus, policymaturitydate, policycommencementdate, remarks, reason, insurancetype, referenceid;
  int transactionid, agencyid;
  String createddate, createdby, lastupdateddate, lastupdatedby, iscustomerdeclaration, ishealthdeclaration,
        isagentdeclaration, productid, relation, permanentcityName, permanentstateName, commcityName, commStateName, premAmt;
  double premAmtDb, doublePremAmtPopUp;
  String iscustomerdeclarationDb = "1";
  String ishealthdeclarationDb = "1";
  String isagentdeclarationDb = "1";
  String productidDb = "80002955";
  Date now = new Date();
  // Define the date format
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

  // Get tomorrow's date with time set to 00:00:00.0
  LocalDate tomorrowDate = LocalDate.now().plusDays(1);
  LocalDateTime startDate = tomorrowDate.atTime(LocalTime.MIDNIGHT);
  String formattedStartDate = startDate.format(formatter);
  //Reporter.log("Start Date and Time: " + formattedStartDate);

  // Get the date and time one year later with time set to 00:00:00.0
  LocalDateTime oneYearLater = startDate.plusYears(1).minusDays(1);
  String formattedOneYearLater = oneYearLater.format(formatter);

  // Define the date format
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  // Format the current date and time
  String formattedDate = sdf.format(now);
  //Reporter.log("Date and Time One Year Later: " + formattedOneYearLater);

  @BeforeClass
  public void initiate() throws SQLException {
    usernameAdmin = getProperty("usernameCSP");
    passwordAdmin = getProperty("passwordAdmin");
    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");
  }

  /*@Test(priority = 1, testName = "login to Care Insurance")
  public void CImain() throws SQLException {
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
  }*/

  @Test(priority = 1, testName = "filling first part")
  public void enterFirstPart() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart(coverType, sinsure[sinsuIndex], numberOfMembers, InsuPeriod
          , InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]);
  }

  @Test(priority = 2, testName = "filling proposer detail")
  public void enterProposerDetail() {
    cipom.proposerDetailEnter(year, month, date, fname, lname, gend, rel, tit);
    cipom.enterProposerPermanentAddress(add1, add2, area, state,
          city, pincode, propEmail);
    cipom.ProposercheckboxPermCommSame();
  }

  @Test(priority = 3, testName = "verifying insured detail are same as proposer detail")
  public void verifyProposerDetailInInsured() {
    cipom.verifyEnteriesInsuredBlock(dateCompare, fname, lname, gend, rel, tit,
          add1, add2, area, stateCompare, city, pincode, add1, add2, area, stateCompare, city, pincode, propEmail);
    cipom.addInsured();
  }

  @Test(priority = 4, testName = "verifying data in the grid.")
  public void verifyGridData() {
    cipom.verifyGridData(fname, lname, gend, add1, mobileNumber, propEmail);
  }

  @Test(priority = 5, testName = "calculating the premium")
  public void calculatePremium() throws SQLException {

    cipom.calculatePremiumClick();
    premAmt = cipom.getPremiumAmount();
    System.out.println("premium amount checking: "+premAmt);
    boolean b=premAmt.isEmpty();
    if(premAmt.isEmpty()){
      Reporter.log("No action taken because  payment block is not opened. Hence policy is not generated. Check log for more information.");
      softAssert.assertEquals(true,false);
      softAssert.assertAll();
    } else{
      Reporter.log("this is premium amount in pop up: " + premAmt);
      cipom.paymentPopUp();
      DbClass dbClass = new DbClass();
      Reporter.log("random number: " + mobileNumber);
      String query = "select top 1* from lt_logotp where mobile='" + mobileNumber + "'";
      Reporter.log("this is query: " + query);
      try (ResultSet resultSet = dbClass.executeQuery(query)) {
        while (resultSet.next()) {
          int getOtp = resultSet.getInt("otp");
          Reporter.log("this is otp: " + getOtp);
          cipom.enterOtpPayment(getOtp);
        }
      }
      policyDetail();
    }

  }


 // @Test(priority = 6, testName = "check policy in DB")
  public void policyDetail() throws SQLException {
    System.out.println("this is the mobile number in policy Detail method of Individual Care Insurance Test: "+mobileNumber);
    String policyNumber = cipom.getPolicyNumber();
    Reporter.log("this is prem amount in policy detail: " + premAmt);
    doublePremAmtPopUp = Double.parseDouble(premAmt);

    if (policyNumber != null) {
      Reporter.log("Policy Number: " + policyNumber);
      DbClass dbClass = new DbClass();
      String query = "select * from tm_careinsuranceproposerdetails where policyno='" + policyNumber + "'";
      Reporter.log("this is query: " + query);
      try (ResultSet resultSet = dbClass.executeQuery(query)) {
        while (resultSet.next()) {
          careinsuranceproposerdetailsid = resultSet.getInt("careinsuranceproposerdetailsid");
          channelid = resultSet.getInt("channelid");
          customermobile = resultSet.getString("customermobile");
          cspcode = resultSet.getString("cspcode");
          title = resultSet.getString("title");
          firstname = resultSet.getString("firstname");
          lastname = resultSet.getString("lastname");
          dob = resultSet.getString("dob");
          gender = resultSet.getString("gender");
          permanentaddressline1 = resultSet.getString("permanentaddressline1");
          permanentaddressline2 = resultSet.getString("permanentaddressline2");
          permanentarea = resultSet.getString("permanentarea");
          permanentcity = resultSet.getInt("permanentcity");
          permanentstate = resultSet.getInt("permanentstate");
          permanentpincode = resultSet.getString("permanentpincode");
          communicationaddressline1 = resultSet.getString("communicationaddressline1");
          communicationaddressline2 = resultSet.getString("communicationaddressline2");
          communicationarea = resultSet.getString("communicationarea");
          communicationcity = resultSet.getInt("communicationcity");
          communicationstate = resultSet.getInt("communicationstate");
          communicationpinCode = resultSet.getString("communicationpinCode");
          pan = resultSet.getString("pan");
          emailid = resultSet.getString("emailid");
          mobile = resultSet.getString("mobile");
          stdcode = resultSet.getString("stdcode");
          nominee = resultSet.getString("nominee");
          nomineerelationwithproposer = resultSet.getString("nomineerelationwithproposer");
          proposalno = resultSet.getString("proposalno");
          policyno = resultSet.getString("policyno");
          covertypeDB = resultSet.getString("covertype");
          age = resultSet.getInt("age");
          numberofmembersDB = resultSet.getInt("numberofmembers");
          policyperiod = resultSet.getInt("policyperiod");
          suminsured = resultSet.getString("suminsured");
          premium = resultSet.getString("premium");
          premAmtDb = Double.parseDouble(premium);
          policystatus = resultSet.getString("policystatus");
          policymaturitydate = resultSet.getString("policymaturitydate");
          policycommencementdate = resultSet.getString("policycommencementdate");
          remarks = resultSet.getString("remarks");
          reason = resultSet.getString("reason");
          insurancetype = resultSet.getString("insurancetype");
          referenceid = resultSet.getString("referenceid");
          transactionid = resultSet.getInt("transactionid");
          agencyid = resultSet.getInt("agencyid");
          createddate = resultSet.getString("createddate");
          createdby = resultSet.getString("createdby");
          lastupdateddate = resultSet.getString("lastupdateddate");
          lastupdatedby = resultSet.getString("lastupdatedby");
          iscustomerdeclaration = resultSet.getString("iscustomerdeclaration");
          ishealthdeclaration = resultSet.getString("ishealthdeclaration");
          isagentdeclaration = resultSet.getString("isagentdeclaration");
          productid = resultSet.getString("productid");
          relation = resultSet.getString("relation");

          // For the purpose of this example, we'll use Reporter.log
          Reporter.log("careinsuranceproposerdetailsid: " + careinsuranceproposerdetailsid);
          Reporter.log("channelid: " + channelid);
          Reporter.log("customermobile: " + customermobile);

          Reporter.log("cspcode: " + cspcode);
          Reporter.log("title: " + title);
          Reporter.log("firstname: " + firstname);
          Reporter.log("lastname: " + lastname);
          Reporter.log("dob: " + dob);
          Reporter.log("gender: " + gender);
          Reporter.log("permanentaddressline1: " + permanentaddressline1);
          Reporter.log("permanentaddressline2: " + permanentaddressline2);
          Reporter.log("permanentarea: " + permanentarea);
          Reporter.log("permanentcity: " + permanentcity);
          Reporter.log("permanentstate: " + permanentstate);
          Reporter.log("permanentpincode: " + permanentpincode);
          Reporter.log("communicationaddressline1: " + communicationaddressline1);
          Reporter.log("communicationaddressline2: " + communicationaddressline2);
          Reporter.log("communicationarea: " + communicationarea);
          Reporter.log("communicationcity: " + communicationcity);
          Reporter.log("communicationstate: " + communicationstate);
          Reporter.log("communicationpinCode: " + communicationpinCode);
          Reporter.log("pan: " + pan);
          Reporter.log("emailid: " + emailid);
          Reporter.log("mobile: " + mobile);
          Reporter.log("stdcode: " + stdcode);
          Reporter.log("nominee: " + nominee);
          Reporter.log("nomineerelationwithproposer: " + nomineerelationwithproposer);
          Reporter.log("proposalno: " + proposalno);
          Reporter.log("policyno: " + policyno);
          Reporter.log("covertype: " + covertypeDB);
          Reporter.log("age: " + age);
          Reporter.log("numberofmembers: " + numberofmembersDB);
          Reporter.log("policyperiod: " + policyperiod);
          Reporter.log("suminsured: " + suminsured);
          Reporter.log("premium: " + premium);
          Reporter.log("policystatus: " + policystatus);
          Reporter.log("policymaturitydate: " + policymaturitydate);
          Reporter.log("policycommencementdate: " + policycommencementdate);
          Reporter.log("remarks: " + remarks);
          Reporter.log("reason: " + reason);
          Reporter.log("insurancetype: " + insurancetype);
          Reporter.log("referenceid: " + referenceid);
          Reporter.log("transactionid: " + transactionid);
          Reporter.log("agencyid: " + agencyid);
          Reporter.log("createddate: " + createddate);
          Reporter.log("createdby: " + createdby);
          Reporter.log("lastupdateddate: " + lastupdateddate);
          Reporter.log("lastupdatedby: " + lastupdatedby);
          Reporter.log("iscustomerdeclaration: " + iscustomerdeclaration);
          Reporter.log("ishealthdeclaration: " + ishealthdeclaration);
          Reporter.log("isagentdeclaration: " + isagentdeclaration);
          Reporter.log("productid: " + productid);
          Reporter.log("relation: " + relation);
        }
      }
      String permanentcityState = "select c.cityname,d.districtname,s.statename from tp_city c inner join tp_district d on c.districtid=d.districtid\n" +
            "inner join tp_state s on s.stateid=d.stateid where c.cityid=" + permanentcity;
      Reporter.log("this is query: " + query);
      try (ResultSet resultSetCityState = dbClass.executeQuery(permanentcityState)) {
        while (resultSetCityState.next()) {
          permanentcityName = resultSetCityState.getString("cityname");
          permanentstateName = resultSetCityState.getString("statename");
        }
      }
      Reporter.log("City Name: " + permanentcityName);
      Reporter.log("State Name: " + permanentstateName);
      String communicationcityState = "select c.cityname,d.districtname,s.statename from tp_city c inner join tp_district d on c.districtid=d.districtid\n" +
            "inner join tp_state s on s.stateid=d.stateid where c.cityid=" + communicationcity;
      Reporter.log("this is query: " + query);
      try (ResultSet resultSetCommCityState = dbClass.executeQuery(communicationcityState)) {
        while (resultSetCommCityState.next()) {
          commcityName = resultSetCommCityState.getString("cityname");
          commStateName = resultSetCommCityState.getString("statename");
        }
      }
      switch (sinsure[sinsuIndex]) {
        case "50K" -> {
          Reporter.log("printing sum insured of 50K by the user: " + sinsure[sinsuIndex]);
          Reporter.log(" printing sum insured of 50K from DB: " + suminsured);
        }
        case "1L" -> {
          Reporter.log("printing sum insured of 1L by the user: " + sinsure[sinsuIndex]);
          Reporter.log(" printing sum insured of 1L from DB: " + suminsured);
        }
        case "3L" -> {
          Reporter.log("printing sum insured of 3L by the user: " + sinsure[sinsuIndex]);
          Reporter.log(" printing sum insured of 3L from DB: " + suminsured);
        }
        default -> {
          Reporter.log("printing sum insured of 5L by the user: " + sinsure[sinsuIndex]);
          Reporter.log(" printing sum insured of 5L from DB: " + suminsured);
        }
      }
      // Use the policy number for database validation
      // For example:
      // DatabaseUtils.checkPolicyNumberInDatabase(policyNumber);
    } else {
      Reporter.log("Policy number could not be retrieved.");
    }
    Reporter.log("customer declaration: " + iscustomerdeclarationDb);
    Reporter.log("health declaration: " + ishealthdeclarationDb);
    Reporter.log("agent declaration: " + isagentdeclarationDb);
    // Assertions with custom messages including actual values
    softAssert.assertEquals(customermobile, mobileNumber,
          "Custom Mobile number does not match expected value. Expected: " + mobileNumber + ", Actual: " + customermobile);
    softAssert.assertEquals(firstname, fname,
          "First name does not match expected value. Expected: " + fname + ", Actual: " + firstname);
    softAssert.assertEquals(lastname, lname,
          "Last name does not match expected value. Expected: " + lname + ", Actual: " + lastname);
    softAssert.assertEquals(dob, dateCompareDB,
          "Date of birth does not match expected value. Expected: " + dateCompareDB + ", Actual: " + dob);
    softAssert.assertEquals(gender, genderDB,
          "Gender does not match expected value. Expected: " + genderDB + ", Actual: " + gender);
    softAssert.assertEquals(permanentaddressline1, add1,
          "Permanent address line 1 does not match expected value. Expected: " + add1 + ", Actual: " + permanentaddressline1);
    softAssert.assertEquals(permanentaddressline2, add2,
          "Permanent address line 2 does not match expected value. Expected: " + add2 + ", Actual: " + permanentaddressline2);
    softAssert.assertEquals(permanentarea, area,
          "Permanent area does not match expected value. Expected: " + area + ", Actual: " + permanentarea);
    softAssert.assertEquals(permanentcityName, city,
          "Permanent city does not match expected value. Expected: " + city + ", Actual: " + permanentcityName);
    softAssert.assertEquals(permanentstateName, stateCompare,
          "Permanent state does not match expected value. Expected: " + stateCompare + ", Actual: " + permanentstateName);
    softAssert.assertEquals(permanentpincode, pincode,
          "Permanent pincode does not match expected value. Expected: " + pincode + ", Actual: " + permanentpincode);
    softAssert.assertEquals(communicationaddressline1, add1,
          "Communication address line 1 does not match expected value. Expected: " + add1 + ", Actual: " + communicationaddressline1);
    softAssert.assertEquals(communicationaddressline2, add2,
          "Communication address line 2 does not match expected value. Expected: " + add2 + ", Actual: " + communicationaddressline2);
    softAssert.assertEquals(communicationarea, area,
          "Communication area does not match expected value. Expected: " + area + ", Actual: " + communicationarea);
    softAssert.assertEquals(commcityName, city,
          "Communication city does not match expected value. Expected: " + city + ", Actual: " + commcityName);
    softAssert.assertEquals(commStateName, stateCompare,
          "Communication state does not match expected value. Expected: " + stateCompare + ", Actual: " + commStateName);
    softAssert.assertEquals(communicationpinCode, pincode,
          "Communication pincode does not match expected value. Expected: " + pincode + ", Actual: " + communicationpinCode);
    softAssert.assertEquals(pan, null,
          "PAN should be null. Actual: " + pan);
    softAssert.assertEquals(emailid, propEmail,
          "Email ID does not match expected value. Expected: " + propEmail + ", Actual: " + emailid);
    softAssert.assertEquals(mobile, null,
          "Mobile number should be null. Actual: " + mobile);
    softAssert.assertEquals(stdcode, "+91",
          "STD code does not match expected value. Expected: +91, Actual: " + stdcode);
    softAssert.assertEquals(nominee, nomineeName,
          "Nominee name does not match expected value. Expected: " + nomineeName + ", Actual: " + nominee);
    Reporter.log("this is nominee relation: " + nomineeRelation[nomineeRelIndex]);
    //"MOTHER-IN-LAW", "SISTER-IN-LAW","BROTHER-IN-LAW"
    switch (nomineeRelation[nomineeRelIndex]) {
      case "MOTHER-IN-LAW" -> softAssert.assertEquals(nomineerelationwithproposer, "MOTHERINLAW",
            "Nominee relation with proposer does not match expected value. Expected: MOTHERINLAW, Actual: " + nomineerelationwithproposer);
      case "SISTER-IN-LAW" -> softAssert.assertEquals(nomineerelationwithproposer, "SISTERINLAW",
            "Nominee relation with proposer does not match expected value. Expected: SISTERINLAW, Actual: " + nomineerelationwithproposer);
      case "BROTHER-IN-LAW" -> softAssert.assertEquals(nomineerelationwithproposer, "BROTHERINLAW",
            "Nominee relation with proposer does not match expected value. Expected: BROTHERINLAW, Actual: " + nomineerelationwithproposer);
      case "GRAND MOTHER" -> softAssert.assertEquals(nomineerelationwithproposer, "GRANDMOTHER",
            "Nominee relation with proposer does not match expected value. Expected: GRANDMOTHER, Actual: " + nomineerelationwithproposer);
      case "GRAND FATHER" -> softAssert.assertEquals(nomineerelationwithproposer, "GRANDFATHER",
            "Nominee relation with proposer does not match expected value. Expected: GRANDFATHER, Actual: " + nomineerelationwithproposer);
      default -> softAssert.assertEquals(nomineerelationwithproposer, nomineeRelation[nomineeRelIndex],
            "Nominee relation with proposer does not match expected value. Expected: " + nomineeRelation[nomineeRelIndex] + ", Actual: " + nomineerelationwithproposer);
    }
    softAssert.assertEquals(createdby, usernameAdmin, "user name does not match. Expected:" + usernameAdmin + " Actual: " + createdby);
    //softAssert.assertEquals(createddate,formattedDate,"created date does not match. Expected: "+formattedDate+" Actual: "+createddate);
    softAssert.assertEquals(covertypeDB.toLowerCase(), coverType.toLowerCase(),
          "Cover type does not match expected value. Expected: " + coverType.toLowerCase() + ", Actual: " + covertypeDB.toLowerCase());
    softAssert.assertEquals(age, 0,
          "Age does not match expected value. Expected: 0, Actual: " + age);
    softAssert.assertEquals(numberofmembersDB, numberOfMembersCompare,
          "Number of members does not match expected value. Expected: " + numberOfMembersCompare + ", Actual: " + numberofmembersDB);
    softAssert.assertEquals(policyperiod, insuPeriodCompare,
          "Policy period does not match expected value. Expected: 1, Actual: " + policyperiod);
    softAssert.assertEquals(premAmtDb, doublePremAmtPopUp, 1.00,
          "premium amount does not match with db. Expected: " + doublePremAmtPopUp + " Actual: " + premAmtDb);
    softAssert.assertEquals(policystatus, "A",
          "Policy status does not match expected value. Expected: A, Actual: " + policystatus);
    softAssert.assertEquals(policycommencementdate, formattedStartDate, "Policy start date does not match with the start date. Expected: " + formattedStartDate + ", Actual: " + policycommencementdate);
    softAssert.assertEquals(policymaturitydate, formattedOneYearLater, "Policy end date does not match with the end date. Expected: " + formattedOneYearLater + ", Actual: " + policymaturitydate);
    softAssert.assertEquals(iscustomerdeclaration, iscustomerdeclarationDb, "Is customer declaration is not equal. Expected: " + iscustomerdeclarationDb + " Actual: " + iscustomerdeclaration);
    softAssert.assertEquals(ishealthdeclaration, ishealthdeclarationDb, "Is health declaration is not equal. Expected: " + ishealthdeclarationDb + " Actual: " + ishealthdeclaration);
    softAssert.assertEquals(isagentdeclaration, isagentdeclarationDb, "Is Agent declaration is not equal. Expected: " + isagentdeclarationDb + " Actual: " + isagentdeclaration);
    softAssert.assertEquals(productid, productidDb, "product id is not equal. Expected: " + productidDb + " Actual: " + productid);
    softAssert.assertAll();
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }
}
