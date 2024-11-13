package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.TestUtility;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class IndividualDiffPresentCommunicationAddressTest extends BaseClassUAT {


 /* int max = 99999999;
  int min = 10000000;
  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "7" + randomWithMathRandom + "7";*/
 TestUtility u=new TestUtility();
 String mobileNumber=u.randomMobileNumber();
  String coverType = "Individua";
  String sumInsured = "50K";
  String numberOfMembers = "1";
  String InsuPeriod = "1Y";
  String InsuProduct = "Group Care 360°(ROINET)-GMC";
  String InsuType = "Health Insurance";
  String nomineeName = "Rohit";
  String nomineeRelation = "BROTHER";
  String year = "1990";
  String month = "Jul";
  String date = "3";
  String fname = "iyu";
  String lname = "trtyt";
  String gend = "Male";
  String rel = "SELF";
  String tit = "Mr.";

  String padd1 = "poipoo ouwep pipi pi";
  String padd2 = "trteyr erwerwiiu uiuiuiui";
  String parea = "trte rtertert rtrwwwt";
  String pstate = "DELHI &";
  String pcity = "Gurgaon";
  String ppincode = "122012";
  String propEmail = "pouipi@cwewrc.in";

  String cadd1 = "uijik";
  String cadd2 = "cpi ou";
  String carea = "iwye oow";
  String cstate = "Maharashtra";
  String ccity = "Pune";
  String cpincode = "411057";

  String dateCompare = "03/07/1990";
  String pstateCompare = "DELHI & NCR";
  String cstateCompare = "Maharashtra";
  String genderMismatchMsg = "{*} Gender and title mismatch.";

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

  @Test(priority = 1, testName = "Different Permanent Communication Address")
  public void DifferentPermCommAdd() throws SQLException {
   System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart(coverType, sumInsured, numberOfMembers,
          InsuPeriod, InsuProduct, InsuType, nomineeName, nomineeRelation);
    cipom.proposerDetailEnter(year, month, date, fname, lname, gend, rel, tit);

    cipom.ProposerDifferentCommunicationAddress(padd1, padd2, parea, pstate, pcity, ppincode, propEmail, cadd1, cadd2, carea, cstate, ccity, cpincode);

  }

  @Test(priority = 2, testName = "verifying that same detail in the insured block")
  public void verifyDetailInInsuredBlock() {
    cipom.verifyEnteriesInsuredBlock(dateCompare, fname, lname, gend, rel, tit,
          padd1, padd2, parea, pstateCompare, pcity, ppincode, cadd1, cadd2, carea, cstateCompare, ccity, cpincode, propEmail);
    cipom.addInsured();
  }

  @AfterTest
  public void quit() {
    driverUAT.quit();
  }
}
