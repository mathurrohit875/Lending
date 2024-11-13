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
import java.util.Random;

public class CIEditTest extends BaseClassUAT {
  /*int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "6" + randomWithMathRandom + "6";*/
  TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
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

  @BeforeClass
  public void initiate() throws SQLException {
    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");
    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");

  }

  /*@Test(priority = 1, testName = "Navigate to Care Insurance Page")
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
    cipom.openInsuredBlock();
    cipom.addInsured();
  }
String premAmt;
  SoftAssert softAssert=new SoftAssert();
  @Test(priority = 3, testName = "premium pop up open and close")
  public void calculatePremium() throws SQLException {
    cipom.calculatePremiumClick();
    premAmt = cipom.getPremiumAmount();
    System.out.println("premium amount checking: "+premAmt);
    boolean b=premAmt.isEmpty();
    if(premAmt.isEmpty()){
      Reporter.log("No action taken because  payment block is not opened. Hence policy is not generated. Check log for more information.");
      softAssert.assertEquals(true,false);
      softAssert.assertAll();
    } else {
      cipom.premiumpopupClose();
      cipom.resultView();
      String proposalno = "";
      try {
        DbClass dbClass = new DbClass();
        String query = "select top 1* from tm_careinsuranceproposerdetails where channelid=398921 order by createddate desc";
        try (ResultSet resultSet = dbClass.executeQuery(query)) {
          while (resultSet.next()) {
            proposalno = resultSet.getString("proposalno");
            System.out.println("prop number: " + proposalno);
          }
        }
        cipom.searchproposalno(proposalno);
      } catch (Exception e) {
        cipom.resultView();
        DbClass dbClass = new DbClass();
        String query = "select top 1* from tm_careinsuranceproposerdetails where channelid=398921 order by createddate desc";
        try (ResultSet resultSet = dbClass.executeQuery(query)) {
          while (resultSet.next()) {
            proposalno = resultSet.getString("proposalno");
            System.out.println("prop number: " + proposalno);
          }
        }
        cipom.searchproposalno(proposalno);
      }
    }

  }

  @Test(priority = 4)
  public void clickEdit() {
    cipom.clickEditButton();
  }

  @AfterTest
  public void quit(){
    driverUAT.quit();
  }
}
