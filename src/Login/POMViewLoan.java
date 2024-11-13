package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class POMViewLoan {
  WebDriver driver;

  JavascriptExecutor js;
  List<WebElement> listOfLoanNumbers;
  Connection conn = null;
  //  DatabaseConnect Db=new DatabaseConnect(conn);

  ArrayList loanNumberFrontend = new ArrayList<>();
  ArrayList loanNumberDataBase = new ArrayList<>();
  private final By viewButton = By.id("ContentPlaceHolder1_btnView");

  public POMViewLoan(WebDriver driver) {
    this.driver = driver;
    js = (JavascriptExecutor) driver;
  }

  public void viewButton() throws InterruptedException {
    Select select = new Select(driver.findElement(By.id("ContentPlaceHolder1_ddlSearchLoanStatus")));
    select.selectByVisibleText("--All--");

    driver.findElement(viewButton).click();
    Thread.sleep(2000);
  }

  public void iterateList() throws InterruptedException, SQLException, ClassNotFoundException {

    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    Thread.sleep(4000);
    List<WebElement> pageNumberList = driver.findElements(By.xpath("//tr[@class='GridPager']//table//tr/td"));

    listOfLoanNumbers = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[@style='height:12px;']/td[3]"));

    String loanno = "";
    for (int k = 0; k < pageNumberList.size(); k++) {


      for (int i = 0; i < listOfLoanNumbers.size(); i++) {
        Reporter.log("COUNT VALUE " + i);

        Reporter.log("____________________________________________________________________________________");
        listOfLoanNumbers = driver.findElements(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[@style='height:12px;']/td[3]"));
        String loanNumber = listOfLoanNumbers.get(i).getText();
        Reporter.log("This is the loannumber: " + loanNumber);
        iteratelists(loanNumber);
        Reporter.log("____________________________________________________________________________________");

      }
      try {
        WebElement pageClick = driver.findElement(By.xpath("//tr[@class='GridPager']//table//tr/td[" + (k + 2) + "]/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", pageClick);
        pageClick.click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(4000);

      } catch (Exception e) {
        Reporter.log("this is the end hence breaking the loop");
      }
    }
  }


  public void iteratelists(String loannumber) throws InterruptedException, SQLException, ClassNotFoundException {

    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";


    String QUERY = "select m.createdby as User_Code, t.username as User_Name,m.loanno as Loan_Number," +
          "m.loanamt as Loan_Amt,\n" + "m.loanstatus as Loan_Status," +
          "m.addendumstatus as Addendum_Status,m.loanamountfornextcycle as Loan_Amt_Next_Cycle,\n" +
          "m.approvedby as Approved_By,m.approveddate as Approved_Date,m.panno as PAN_Number," +
          "m.aadharno as Aadhar_Number,\n" + "m.cibilpath as CIBIL_Path," +
          "m.emailpath as Email_Path,m.chequescancopies as ChequeScan_Path,\n" +
          "m.chequescancopiesaddendum as AddendumCheque_Path from tm_channelemi m\n" +
          "inner join tm_user t on m.channelid=t.userid\n" + "where loanno='" + loannumber + "'";
    conn = DriverManager.getConnection(url, username, password);

    WebElement loannumberData = driver.findElement(By.xpath("//table[@id='ContentPlaceHolder1_grdList']//tbody//tr[contains(.,'" + loannumber + "')]"));
    js.executeScript("arguments[0].scrollIntoView(true);", loannumberData);
    Thread.sleep(2000);
    List<WebElement> columnData = loannumberData.findElements(By.xpath("td"));

    if (conn != null) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(QUERY);
      DecimalFormat df = new DecimalFormat("#.00");
      while (rs.next()) {
        Reporter.log("____________________________________________________________________________________");
        String user_code = columnData.get((0)).getText();
        String user_name = columnData.get((1)).getText();
        String loan_number = columnData.get((2)).getText();
        loanNumberFrontend.add(loan_number);
        String loan_amount = columnData.get(3).getText();


        String userCodeDB = rs.getString("User_Code");
        String userNameDB = rs.getString("User_Name");
        String loanNumberDB = rs.getString("Loan_Number");
        loanNumberDataBase.add(loanNumberDB);
        double loan_Amount_Int = rs.getDouble("Loan_Amt");
        Reporter.log("checking doubnle: " + df.format(loan_Amount_Int));
        String formatLoanAmount = df.format(loan_Amount_Int);

        Reporter.log("checking formatred loan amount: " + formatLoanAmount);



                        /*Reporter.log("User code from db: "+userCodeDB);
                        Reporter.log("User name from db: " + userNameDB);
                        Reporter.log("loan number from db: "+loanNumberDB);
                        Reporter.log("loan amount Integer from db: "+loan_Amount_Int);
                        Reporter.log("loan amount Double after formatting from db: "+formatLoanAmount);*/
        //Reporter.log("loan amount String from db: "+formatLoanAmount);

        boolean userCodeCompare = user_code.equals(userCodeDB);
        Reporter.log("User code compared with db. " + userCodeCompare);

        boolean userNameCompare = user_name.equals(userNameDB);
        Reporter.log("User name compared with db: " + userNameCompare);


        boolean loanNumberCompare = loan_number.equals(loanNumberDB);
        Reporter.log("loan number compared with db: " + loanNumberCompare);

        boolean loanAmountCompare = loan_amount.equals(formatLoanAmount);
        Reporter.log("loan amount compared with db: " + loanAmountCompare);
        Reporter.log("____________________________________________________________________________________");

      }

    }


  }

  public void loanNumbercompare() throws ClassNotFoundException, SQLException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";

    conn = DriverManager.getConnection(url, username, password);
    String getUserId = "select * from tm_user where usercode='CMF000041'";

    if (conn != null) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(getUserId);
      int userId = rs.getInt("userid");
      String loanNumberGet = "select * from tm_channelemi where channelid=" + userId;
      Reporter.log("this is the user id: " + userId);
      ResultSet rs1 = stmt.executeQuery(loanNumberGet);
      String loanNumberDatabase = rs1.getString("loanno");
      while (rs1.next()) {

        Reporter.log("this is the loan number from the database: " + loanNumberDatabase);

      }


      Reporter.log("size of loan number from frontend: " + loanNumberFrontend.size());
      Reporter.log("Size of loan number from Database: " + loanNumberDataBase.size());
      for (int i = 0; i < loanNumberFrontend.size(); i++) {
        Reporter.log("this is loan number: " + loanNumberFrontend.get(i).toString());
        Reporter.log("this is loan number from db: " + loanNumberDataBase.get(i).toString());

      }
    }
  }
}
