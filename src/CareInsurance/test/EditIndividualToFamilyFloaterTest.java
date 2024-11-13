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

public class EditIndividualToFamilyFloaterTest extends BaseClassUAT {
/*  int max = 99999999;
  int min = 10000000;

  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
  String mobilenumber = "6" + randomWithMathRandom + "6";*/
SoftAssert softAssert = new SoftAssert();
TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
  Random random = new Random();
  String coverType = "Individual";
  String[] sinsure = {"50K","1L"};
  int sinsuIndex = random.nextInt(sinsure.length);
  String numberOfMembersIndividual = "1";
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

String premAmt;
  @Test(priority = 1, testName = "Perform Edit from Individual to Family Floater")
  public void editCareInsurance() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.enterFirstPart(coverType, sinsure[sinsuIndex], numberOfMembersIndividual, InsuPeriod
          , InsuProduct, InsuType, nomineeName, nomineeRelation[nomineeRelIndex]);
    cipom.proposerDetailEnter(proposeryear, proposermonth, proposerdate, proposerfname, proposerlname, proposergend, proposerrel, proposertit);
    cipom.enterProposerPermanentAddress(proposerpermanentadd1, proposerpermanentadd2, proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode, proposerEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();


    cipom.calculatePremiumClick();
    premAmt = cipom.getPremiumAmount();
    System.out.println("premium amount checking: "+premAmt);
    boolean b=premAmt.isEmpty();
    if(premAmt.isEmpty()){
      Reporter.log("No action taken because  payment block is not opened. Hence policy is not generated. Check log for more information.");
      softAssert.assertEquals(true,false);
      softAssert.assertAll();
    } else{
      System.out.println("else loop when premium pop is closed");
      cipom.premiumpopupClose();
      cipom.resultView();
      cipom.clickEditButton();
      cipom.changeCoverType("Family Floater","50K",numberOfMembers[numberMembe]);
      enterInsuredDetail();}




  }

  /*@Test(priority = 2, testName = "Change cover type first part",invocationCount = 2)
  public void changeCoverTypeFirstPart(){

  }*/
  //@Test(priority = 3, testName = "Enter insured detail",invocationCount = 2)
  public void enterInsuredDetail(){
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
    cipom.calculatePremiumClick();
   String premAmt = cipom.getPremiumAmount();
    Reporter.log("this is premium amount in pop up: " + premAmt);
    ///////////////////////////////////////////////////////////////////////////
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
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    ///////////////////////////////////////////////////////////////////////////
  }

  private void addInsuredDetails(String year, String month, String date, String gender, String relation, String title, String firstName, String LastName) {
    cipom.enterInsuredDetails(year, month, date, firstName, LastName, gender, relation, title);
    cipom.enterInsuredPermanentAddress(proposerpermanentadd1, proposerpermanentadd2,
          proposerpermanentarea, proposerpermanentstate,
          proposerpermanentcity, proposerpermanentpincode,
          mobileNumber, proposerEmail);
    cipom.InsuredcheckboxPermCommSame();
    cipom.addInsured();
  }

/*@AfterTest
  public void quit(){
    driverUAT.quit();
  }*/
}
