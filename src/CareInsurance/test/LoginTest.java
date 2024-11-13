package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import org.testng.annotations.*;

import java.sql.SQLException;

public class LoginTest extends BaseClassUAT {

  /*int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
  TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();

  @BeforeClass
  public void initiate() throws SQLException {

    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");

    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");
  }



  @Test(priority = 1)
  public void navigateCareInsurance() {
    cipom.careInsurance();
  }

  @Test(priority = 2)
  public void firstScreenLengthValidation() {
    ciMandatoryMsgPOM.careInsuranceFirstScreen("10", "4");
  }

  @Test(priority = 3)
  public void firstScreenMandatoryMessage() {
    String mobilemandate = "guoyo oiu Please Enter Valid Contact Number";
    String otpmandate = "Please enter valid OTP";
    String checkboxmandate = "You must agree to the terms and conditions.";
    ciMandatoryMsgPOM.firstScreenMandatory(mobilemandate, otpmandate, checkboxmandate);
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }


}
