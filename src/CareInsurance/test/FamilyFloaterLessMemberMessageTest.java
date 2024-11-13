package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Random;

public class FamilyFloaterLessMemberMessageTest extends BaseClassUAT {
  //private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/src/LongTermLoanPackage/config.properties";
  // WebDriver driver;
  //CIPOM cipom;
 /* int max = 99999999;
  int min = 10000000;

  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
  TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
  Random random = new Random();
  String coverType = "Family Floater";
  String[] sinsure = {"50K", "1L", "3L", "5L"};
  int sinsuIndex = random.nextInt(sinsure.length);
  String[] numberOfMembers = {"3", "4", "5", "6"};
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
  //private Properties properties;

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

  @Test(priority = 1, testName = "family floater with random number of members")
  public void familyfloaterInsurance() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart(coverType, sinsure[sinsuIndex], numberOfMembers[numberMembe], InsuPeriod
          , InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]);
    cipom.proposerDetailEnter(proposeryear, proposermonth, proposerdate, proposerfname, proposerlname, proposergend, proposerrel, proposertit);
    cipom.enterProposerPermanentAddress(proposerpermanentadd1, proposerpermanentadd2, proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode, proposerEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    int members = Integer.parseInt(numberOfMembers[numberMembe]);

    if (members >= 2) {
      // Add spouse as the first insured member
      addInsuredDetails(spouseyear, spousemonth, spousedate, spousegend, spouserel, spousetit);
      members--;
      System.out.println("number members: " + members);// Decrease members count since one member is added
    }
    // Loop for adding children or additional members
    for (int i = 2; i < members; i++) {
      System.out.println("Value of i for number members: " + i);
      addInsuredDetails(childyear, childmonth, childdate, childgend, childrel, childtit);

    }

    cipom.calculatePremiumClick();
    cipom.capturememberlesserrormessage("The selected cover type and the number of members must match. Please ensure they are the same.");
  }

  private void addInsuredDetails(String year, String month, String date, String gender, String relation, String title) {
    cipom.enterInsuredDetails(year, month, date, proposerfname, proposerlname, gender, relation, title);
    cipom.enterInsuredPermanentAddress(proposerpermanentadd1, proposerpermanentadd2,
          proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode,
          mobileNumber, proposerEmail);
    cipom.InsuredcheckboxPermCommSame();
    cipom.addInsured();
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }
}
