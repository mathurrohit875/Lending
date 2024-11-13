package CareInsurance.test;

import CareInsurance.main.BaseClassUAT;
import CareInsurance.main.CIMandatoryMsgPOM;
import CareInsurance.main.CIPOM;
import CareInsurance.main.TestUtility;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.sql.SQLException;

public class FormValidationTest extends BaseClassUAT {

//  int max = 99999999;
//  int min = 10000000;
//
//  int randomWithMathRandom = (int) ((Math.random() * (max - min)) + min);
//  String mobilenumber = "7" + randomWithMathRandom + "7";
TestUtility u=new TestUtility();
  String mobileNumber=u.randomMobileNumber();
  String ctype = "Cover type is required";
  String sInsured = "Sum Insured is required";
  String numberMember = "No. of Members is required";
  String period = "Period is required";
  String product = "Products is required";
  String insuranceType = "Insurance Type is required";

  String dob = "DOB is required";
  String fname = "Please enter valid First Name";
  String lname = "Please enter valid Last Name";
  String gender = "Gender is required";
  String Relation = "Relation is required";
  String Title = "Title is required";

  String address = "Address Line1 is required";
  String state = "State is required";
  String city = "City is required";
  String pincode = "Pin Code is required";
  String email = "Please enter valid Email Address";
  String year = "1993";
  String month = "Sep";
  String date = "12";
  String firstname = "cdcdc";
  String lastname = "scdsdcf";
  String title = "Mr.";
  String gend = "Male";
  String rel = "SELF";
  String add1 = "adsda";
  String add2 = "eyoe";
  String area = "oioydos";
  String pstate = "Delhi &";
  String pcity = "Gurgaon";
  String ppincode = "122012";
  String propEmail = "acdo@cdoicuc.in";
  String cType = "Family Floater";
  String sInsu = "50K";
  String InsurancePeriod = "1Y";
  String nMember = "2";
  String InsuranceProduct = "Group Care 360°(ROINET)-GMC";
  String Itype = "Health Insurance";
  String nName = "sioc";
  String nRelation = "SON";

  @BeforeClass
  public void initiate() throws SQLException {
    cipom=new CIPOM(driverUAT);
    ciMandatoryMsgPOM=new CIMandatoryMsgPOM(driverUAT);
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
    } catch (SQLException e) {
      Reporter.log(e.getMessage());

    }

  }*/

  @Test(priority = 2)
  public void CIformValidationFirstPart() throws SQLException {
    System.out.println("this is the mobile number: "+mobileNumber);
    CImain(mobileNumber);
    cipom.CareFormValidation();
    ciMandatoryMsgPOM.firstPartMandatoryVerify(ctype, sInsured, numberMember, period, product, insuranceType);

  }

  @Test(priority = 3)
  public void CIformValidationProposerFirstPart() {
    Reporter.log("proposer part");
    ciMandatoryMsgPOM.ProposerMandatoryFirstPartVerify(dob, fname, lname, gender, Relation, Title);

  }

  @Test(priority = 4)
  public void CIformValidationProposerPerPart() {
    Reporter.log("proposer part");
    ciMandatoryMsgPOM.ProposerMandatoryPermanentAddress(address, state, city, pincode);
  }

  @Test(priority = 5)
  public void CIformValidationProposerCommPart() {
    Reporter.log("proposer part");
    ciMandatoryMsgPOM.ProposerMandatoryCommunicationAddress(address, state, city, pincode, email);
   /* cipom.enterFirstPart(cType, sInsu, nMember
          ,InsurancePeriod,
          InsuranceProduct,  Itype,  nName,
           nRelation);
    cipom.proposerDetailEnter(year,month,date,firstname,lastname,gend,rel,title);
    cipom.enterProposerPermanentAddress( add1,  add2,  area,
          pstate, pcity,ppincode, propEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    cipom.addInsured();*/
  }


  @Test(priority = 6)
  public void CIformValidationInsuredFirstPart() {
    cipom.goToHomeAction();
    cipom.enterFirstPart(cType, sInsu, nMember
          , InsurancePeriod,
          InsuranceProduct, Itype, nName,
          nRelation);
    cipom.proposerDetailEnter(year, month, date, firstname, lastname, gend, rel, title);
    cipom.enterProposerPermanentAddress(add1, add2, area,
          pstate, pcity, ppincode, propEmail);
    cipom.ProposercheckboxPermCommSame();
    cipom.openInsuredBlock();
    cipom.addInsured();
    cipom.addInsured();
    Reporter.log("insured part");
    ciMandatoryMsgPOM.InsuredMandatoryFirstPartVerify(dob, fname, lname, gender, Relation, Title);
  }

  @Test(priority = 7)
  public void CIformValidationInsuredPerPart() {
    Reporter.log("insured premanent address part");
    ciMandatoryMsgPOM.InsuredMandatoryPermanentAddress(address, state, city, pincode);
  }

  @Test(priority = 8)
  public void CIformValidationInsuredCommPart() {
    Reporter.log("insured comm part");
    ciMandatoryMsgPOM.InsuredMandatoryCommunicationAddress(address, state, city, pincode, email);
  }
  @AfterTest
  public void quit() {
    driverUAT.quit();
  }

}
