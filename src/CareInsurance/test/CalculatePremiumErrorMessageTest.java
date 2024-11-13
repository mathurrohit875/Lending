package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.POMData;
import CareInsurance.main.TestUtility;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Random;

public class CalculatePremiumErrorMessageTest extends BaseClassUAT {
  TestUtility u=new TestUtility();
  POMData pomData=new POMData();
  String usernameAdmin;
  String passwordAdmin;
//  int max = 99999999;
//  int min = 10000000;
//  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
//  String mobilenumber = "6" + randomWithMathRandom + "6";
  String mobileNumber=u.randomMobileNumber();
  String coverType = "Individual";
  String[] sinsure = {"50K", "1L", "3L", "5L"};
  Random random = new Random();
  int sinsuIndex = random.nextInt(sinsure.length);
  String numberOfMembers = "1";
  String InsuPeriod = "1Y";
  String InsuProduct = "Group Care 360°(ROINET)-GMC";
  String InsuType = "Health Insurance";
  String nomineeName = "Rohit";
  String[] nomineeRelation = {"BROTHER", "FATHER", "GRAND FATHER", "GRAND MOTHER", "BROTHER-IN-LAW",
        "MOTHER-IN-LAW", "SISTER-IN-LAW", "MOTHER", "SISTER", "SON", "DAUGHTER", "WIFE", "HUSBAND"};
  int nomineeRelIndex = random.nextInt(nomineeRelation.length);
  String year = "1990";
  String month = "Jul";
  String date = "3";
  String fname = "iyu";
  String lname = "trtyt";
  String gend = "Male";
  String rel = "SELF";
  String tit = "Mrs.";
  String add1 = "poipoo ouwep pipi pi";
  String add2 = "trteyr erwerwiiu uiuiuiui";
  String area = "trte rtertert rtrwwwt";
  String state = "DELHI &";
  String city = "Gurgaon";
  String pincode = "122012";
  String propEmail = "pouipi@cwewrc.in";

  @BeforeClass
  public void initiate() throws SQLException {
    //pomData.enterCredentials();
    usernameAdmin = getProperty("usernameCSP");
    passwordAdmin = getProperty("passwordAdmin");
    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");
  }

 

  @Test(priority = 2, testName = "gender mismatch error message verify")
  public void calculatePremiumError() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart("Individual", sinsure[sinsuIndex], numberOfMembers, InsuPeriod
          , InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]);
    cipom.proposerDetailEnter(year, month, date, fname, lname, gend, rel, tit);
    cipom.enterProposerPermanentAddress(add1, add2, area, state,
          city, pincode, propEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    cipom.calculatePremiumClick();
    cipom.genderMismatch("{*} Gender and title mismatch.");
  }

  @AfterTest
  public void quit(){
    driverUAT.quit();
  }
}
