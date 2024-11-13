package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import DBPackage.DbClass;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileOtpTest extends BaseClassUAT {

  /*int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "6" + randomWithMathRandom + "6";*/
  TestUtility testUtility=new TestUtility();
  String mobileNumber=testUtility.randomMobileNumber();
  //SoftAssert softAssert;

  @BeforeClass
  public void initiateOtp() throws SQLException {

    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");
    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");
  }


  @Test(priority = 1)
  public void CImain() throws SQLException {
    cipom.careInsurance();
    Reporter.log("random number: " + mobileNumber);
    System.out.println("this is the mobile number: "+mobileNumber);
    cipom.entermobileotp(mobileNumber);
    DbClass dbClass = new DbClass();
    String query = "select * from lt_logotp where mobile='" + mobileNumber + "'";
    Reporter.log("this is query: " + query);
    try (ResultSet resultSet = dbClass.executeQuery(query)) {
      while (resultSet.next()) {
        int getOtp = resultSet.getInt("otp");
        Reporter.log("this is otp: " + getOtp);
        cipom.enterOtp(getOtp);
      }
    }
  }

  @Test(priority = 2, testName = "Navigate to care form page")
  public void verifySuccessLogin() {
    String CareTitle = "Care Insurance";
    ciMandatoryMsgPOM.verifyCareFormPage(CareTitle);
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }
}


