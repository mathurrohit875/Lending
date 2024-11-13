package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Random;

public class FamilyFloaterTest extends BaseClassUAT {

 /* int max = 99999999;
  int min = 10000000;

  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
 TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
 Random random = new Random();
  String coverType = "Family Floater";
  String[] sinsure = {"50K"};
  int sinsuIndex = random.nextInt(sinsure.length);
  String[] numberOfMembers = {"2", "3", "4", "5", "6"};
  int numberMembe = random.nextInt(numberOfMembers.length);
  String InsuPeriod = "1Y";
  String InsuProduct = "Group Care 360°(ROINET)-GMC";
  String InsuType = "Health Insurance";
  String nomineeName = "Rohit";
  String[] nomineeRelation = {"BROTHER", "FATHER", "GRAND FATHER", "GRAND MOTHER", "BROTHER-IN-LAW",
        "MOTHER-IN-LAW", "SISTER-IN-LAW", "MOTHER", "SISTER", "SON", "DAUGHTER", "WIFE", "HUSBAND"};
  int nomineeRelIndex = random.nextInt(nomineeRelation.length);
  String proposeryear = "1990";
  String proposermonth = "Jul";
  String proposerdate = "3";
  String proposerfname = "JKJS";
  String proposerlname = "OUPO";
  String proposergend = "Male";
  String proposerrel = "SELF";
  String proposertit = "Mr.";
  String proposerpermanentadd1 = "IOIU IOY YR IO UP";
  String proposerpermanentadd2 = "ATER IOAYUEUW POQ TU";
  String proposerpermanentarea = "IOY IOUPIO UPOAR";
  String proposerpermanentstate = "DELHI &";
  String proposerpermanentcity = "Gurgaon";
  String proposerpermanentpincode = "122012";
  String proposerEmail = "pouipi@cwewrc.in";
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
  String[] fnanme = {"UTYIU", "AEWR", "ETRUI", "YE", "YUAR"};
  int fnname = random.nextInt(fnanme.length);
  String[] lnanme = {"TERU", "UII", "cdsUII", "iocUIATI", "dcioyUIYI"};
  int lnname = random.nextInt(lnanme.length);


  @BeforeClass
  public void initiate() throws SQLException {
    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");
    cipom.enterCredentials(usernameAdmin, passwordAdmin, "sddds");
    cipom.enterOTP("222111");
  }

 /* @Test(priority = 1, testName = "login to Care Insurance")
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

  @Test(priority = 1, testName = "family floater with random number of members")
  public void familyfloaterInsurance() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    waitForElementAndPerform(() -> cipom.enterFirstPart(coverType, sinsure[sinsuIndex], numberOfMembers[numberMembe], InsuPeriod
          , InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]));
    waitForElementAndPerform(() -> cipom.proposerDetailEnter(proposeryear, proposermonth, proposerdate, proposerfname, proposerlname, proposergend, proposerrel, proposertit));
    waitForElementAndPerform(() -> cipom.enterProposerPermanentAddress(proposerpermanentadd1, proposerpermanentadd2, proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode, proposerEmail));
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    int members = Integer.parseInt(numberOfMembers[numberMembe]);
    if (members >= 2) {
      int randomFNameIndex = random.nextInt(fnanme.length);
      int randomLNameIndex = random.nextInt(lnanme.length);
      addInsuredDetails(spouseyear, spousemonth, spousedate, spousegend, spouserel, spousetit, fnanme[randomFNameIndex], lnanme[randomLNameIndex]);
    }
    for (int i = 3; i <= members; i++) {
      // Generate new random indices for each iteration
      int randomFNameIndex = random.nextInt(fnanme.length);
      int randomLNameIndex = random.nextInt(lnanme.length);
      addInsuredDetails(childyear, childmonth, childdate, childgend, childrel, childtit, fnanme[randomFNameIndex], lnanme[randomLNameIndex]);
    }
    /*cipom.calculatePremiumClick();
    cipom.paymentPopUp();
    DbClass dbClass = new DbClass();
    String query = "select top 1* from lt_logotp where mobile='" + mobilenumber + "'";
    Reporter.log("this is query: " + query);
    ResultSet resultSet = dbClass.executeQuery(query);
    while (resultSet.next()) {
      int getOtp = resultSet.getInt("otp");
      Reporter.log("this is otp: " + getOtp);
      cipom.enterOtpPayment(getOtp);
    }*/


  }

  private void addInsuredDetails(String year, String month, String date, String gender, String relation, String title, String firstName, String LastName) {
    waitForElementAndPerform(() -> cipom.enterInsuredDetails(year, month, date, firstName, LastName, gender, relation, title));
    waitForElementAndPerform(() -> cipom.enterInsuredPermanentAddress(proposerpermanentadd1, proposerpermanentadd2,
          proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode,
          mobileNumber, proposerEmail));
    cipom.InsuredcheckboxPermCommSame();
    cipom.addInsured();

  }

  // Helper method to wait for an action and perform it
  private void waitForElementAndPerform(Runnable action) {
    try {
      action.run();
    } catch (Exception e) {
      // Log the exception or handle as needed
      Reporter.log("Error while performing action: " + e.getMessage());
    }
  }
  @AfterTest
  public void quit(){
    driverUAT.quit();
  }
}
