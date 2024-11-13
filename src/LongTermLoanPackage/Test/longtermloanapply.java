package LongTermLoanPackage.Test;

import LongTermLoanPackage.Main.LTBaseClass;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class longtermloanapply extends LTBaseClass {
  //double lA = 0.00;
  int from = 10000, to = 200000, multi = 5000;
  Random rand = new Random();
  int n = multi * (Math.round(rand.nextInt((to + multi - from)) + from) / multi);
  double lA = n;
  String aadharcardnumber = "123456789012";


  @BeforeClass
  public void initiate() throws SQLException, IOException {
    String usernameAdmin = getProperty("usernameCSP");
    String passwordAdmin = getProperty("passwordAdmin");
    longTermLoginPOM.enterCredentials("CSP027451", passwordAdmin, "sddds");
    longTermLoginPOM.enterOTP("222111");
  }

  @Test(priority = 1, testName = "User will login to their account")
  public void loginUser() throws SQLException, ClassNotFoundException, IOException {
    applyLoanPOM.goToLongTermLoan();

    applyLoanPOM.goToAddLoan();


  }


  @Test(priority = 2, testName = "Go to Long Term Loan Page")
  public void goToAddLoan() throws IOException, SQLException, ClassNotFoundException {

    Reporter.log(String.valueOf(n));
    //double loanAmount = 100000;
    applyLoanPOM.enterLoanDetail(lA);


    // applyLoanPOM.emiprintdetail();

    //applyLoanPOM.compareAfterLoanApplied();
  }

  @Test(priority = 3, testName = "verify the loan emi details in the result grid of the loan")
  public void verifyLoanEMIDetail() throws IOException {
    applyLoanPOM.EmiDetail(lA);
  }

  @Test(priority = 4, testName = "verify the loan detail in the loan detail section")
  public void verifyCollectLoanDetail() throws IOException {
    applyLoanPOM.collectLoanDetails(lA);
  }

  @Test(priority = 5, testName = "verify the emi detail section ")
  public void verifyEmiDetai() {
    applyLoanPOM.collectEmiDetail();
  }

  @Test(priority = 6, testName = "submit the loan request")
  public void submitLoan() throws SQLException, ClassNotFoundException {
    applyLoanPOM.enterOtherDetailAndSubmit(aadharcardnumber);
  }

  @Test(priority = 7, testName = "compare loan detail with the DB")
  public void comparison() throws SQLException, IOException, ClassNotFoundException {
    applyLoanPOM.comparewithDB(aadharcardnumber);
    applyLoanPOM.compareAfterLoanApplied();
  }

  /*@AfterTest
  public void quit(){
    driver.quit();
  }*/

}
