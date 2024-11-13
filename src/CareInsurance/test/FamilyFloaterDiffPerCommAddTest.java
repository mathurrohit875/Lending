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

public class FamilyFloaterDiffPerCommAddTest extends BaseClassUAT {


 /* int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
  Random random = new Random();
SoftAssert softAssert=new SoftAssert();
  TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
  String coverType = "Family Floater";
  String[] sinsure = {"50K", "1L", "3L", "5L"};
  int sinsuIndex = random.nextInt(sinsure.length);
  String[] numberOfMembers = {"2", "3", "4", "5", "6"};
  int numberMembe = random.nextInt(numberOfMembers.length);
  String InsuPeriod = "1Y";
  String InsuProduct = "Group Care 360°(ROINET)-GMC";
  String InsuType = "Health Insurance";
  String nomineeName = "Rohit";
  String[] nomineeRelation = {"BROTHER", "FATHER", "GRAND FATHER", "GRAND MOTHER", "BROTHER-IN-LAW", "MOTHER-IN-LAW", "SISTER-IN-LAW", "MOTHER", "SISTER", "SON", "DAUGHTER", "WIFE", "HUSBAND"};
  int nomineeRelIndex = random.nextInt(nomineeRelation.length);

  String padd1 = "poipoo ouwep pipi pi";
  String padd2 = "trteyr erwerwiiu uiuiuiui";
  String parea = "trte rtertert rtrwwwt";
  String pstate = "DELHI &";
  String pcity = "Gurgaon";
  String ppincode = "122012";
  String propEmail = "pouipi@cwewrc.in";
  String proposeryear = "1990";
  String proposermonth = "Jul";
  String proposerdate = "3";
  String proposerfname = "iyu";
  String proposerlname = "trtyt";
  String proposergend = "Male";
  String proposerrel = "SELF";
  String proposertit = "Mr.";
  String proposerpermanentadd1 = "poipoo ouwep pipi pi";
  String proposerpermanentadd2 = "trteyr erwerwiiu uiuiuiui";
  String proposerpermanentarea = "trte rtertert rtrwwwt";
  String proposerpermanentstate = "DELHI &";
  String proposerpermanentcity = "Gurgaon";
  String proposerpermanentpincode = "122012";
  String proposerEmail = "pouipi@cwewrc.in";
  String cadd1 = "uijik";
  String cadd2 = "cpi ou";
  String carea = "iwye oow";
  String cstate = "Maharashtra";
  String ccity = "Pune";
  String cpincode = "411057";

  String dateCompare = "03/07/1990";
  String pstateCompare = "DELHI & NCR";
  String cstateCompare = "Maharashtra";

  String spouseyear = "1990";
  String spousemonth = "Jul";
  String spousedate = "3";
  String childyear = "2016";
  String childmonth = "Jul";
  String childdate = "3";
  String spousegend = "Female";
  String spouserel = "Spouse";
  String spousetit = "Mrs.";
  String childgend = "Male";
  String childrel = "Son";
  String childtit = "Mr.";


  @BeforeClass
  public void initiate() throws SQLException {
    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");
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
  String premAmt;

  @Test(priority = 1, testName = "family floater")
  public void familyfloaterInsurance() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart(coverType, sinsure[sinsuIndex], numberOfMembers[numberMembe], InsuPeriod, InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]);
    cipom.proposerDetailEnter(proposeryear, proposermonth, proposerdate, proposerfname, proposerlname, proposergend, proposerrel, proposertit);
    cipom.enterProposerPermanentAddress(proposerpermanentadd1, proposerpermanentadd2, proposerpermanentarea, proposerpermanentstate, proposerpermanentcity, proposerpermanentpincode, proposerEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    int members = Integer.parseInt(numberOfMembers[numberMembe]);
    if (members >= 2) {
      addInsuredDetails(spouseyear, spousemonth, spousedate, spousegend, spouserel, spousetit);
    }
    for (int i = 3; i <= members; i++) {
      addInsuredDetails(childyear, childmonth, childdate, childgend, childrel, childtit);
    }
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

    }




  }

  private void addInsuredDetails(String year, String month, String date, String gender, String relation, String title) {
    cipom.enterInsuredDetails(year, month, date, proposerfname, proposerlname, gender, relation, title);
    cipom.InsuredDifferentCommunicationAddress(padd1, padd2, parea, pstate, pcity, ppincode, propEmail, cadd1, cadd2, carea, cstate, ccity, cpincode, mobileNumber);
    cipom.addInsured();
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }

}

