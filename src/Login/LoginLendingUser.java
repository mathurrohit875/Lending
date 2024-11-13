package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginLendingUser {

  //private static final Logger log = LoggerFactory.getLogger(LoginLendingUser.class);
  Connection conn = null;
  ArrayList al = new ArrayList();
  ArrayList list = new ArrayList();

  @Test
  public void setup() throws InterruptedException, ClassNotFoundException, SQLException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

// Specify the connection URL, username, and password
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";
    ChromeOptions options = new ChromeOptions();
    //options.addArguments("headless");
    options.setAcceptInsecureCerts(true);
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver(options);

    JavascriptExecutor js = (JavascriptExecutor) driver;
    driver.manage().window().maximize();
    driver.get("https://uatxpresso.roinet.in/Login.aspx");
    LoginPOM loginPOM = new LoginPOM(driver);
    loginPOM.enterCredentials("CMF000041", "roinet@1234", "sdcd");
    loginPOM.enterOTP("222111");
    Actions action = new Actions(driver);

    action.moveToElement(driver.findElement(By.xpath("//a[text()='WALLET']"))).perform();
    action.moveToElement(driver.findElement(By.xpath("//a[text()='Lending']"))).perform();
    driver.findElement(By.xpath("//a[contains(.,'Apply Weekend Loan')]")).click();
    driver.findElement(By.xpath("//input[@value='Add']")).click();
    Thread.sleep(5000);
    driver.findElement(By.name("ctl00$ContentPlaceHolder1$txtLoanAmountReq")).sendKeys("100000");
    action.sendKeys(Keys.TAB).perform();
    Thread.sleep(2000);
    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    Thread.sleep(4000);

    driver.findElement(By.id("ContentPlaceHolder1_btnSave")).click();
    Thread.sleep(4000);

    //driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$btnSave']")).click();
    String loannosplit = driver.switchTo().alert().getText();
    String[] elements = loannosplit.split(" ");
    Reporter.log("This is loan number:  " + elements[5]);
    String loannumber = elements[5].trim();
    Reporter.log("This is loan number:  " + loannumber);
    Reporter.log("this is alert text: " + driver.switchTo().alert().getText());
    driver.switchTo().alert().accept();
    Thread.sleep(3000);

    final String QUERY = "select emiid, channelid, loanamt,fileprocesschrg, loaninterestrate," +
          "emifrequency,status,\n" +
          "createdby,loantype,loanstatus, panno, aadharno, loanno,tncyn,isemandate,loantype\n" +
          "from tm_channelemi where loanno='" + loannumber + "'";

// Create the connection
    conn = DriverManager.getConnection(url, username, password);
    if (conn != null) {

      Reporter.log("The connection has been successfully established.");

      DatabaseMetaData dm = conn.getMetaData();
      Reporter.log("Driver name: " + dm.getDriverName());
      Reporter.log("Driver version: " + dm.getDriverVersion());
      Reporter.log("Product name: " + dm.getDatabaseProductName());
      Reporter.log("Product version: " + dm.getDatabaseProductVersion());
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(QUERY);
      int emmiid = 0;
      int channelid = 0;
      int loanamt = 0;
      int fileprocesschrg = 0;
      int loaninterestrate = 0;

      String emi_id = "";
      String channel_id = "";
      String loan_amt = "";
      String fileprocess_chrg = "";
      String loaninterest_rate = "";
      String emifrequency = "";
      String status = "";
      String createdby = "";
      String loantype = "";
      String loanstatus = "";
      String panno = "";
      String aadharno = "";
      String loanno = "";
      String tncyn = "";
      String isemandate = "";
      String loanmandate = "";
      HashMap<Integer, String> map = new HashMap<Integer, String>();

      while (rs.next()) {
        //Display values
        emmiid = rs.getInt("emiid");
        emi_id = Integer.toString(emmiid);
        map.put(1, emi_id);
        al.add(emi_id);
        Reporter.log("EMIId: " + emi_id);


        channelid = rs.getInt("channelid");
        channel_id = Integer.toString(channelid);
        map.put(2, channel_id);
        al.add(channel_id);
        Reporter.log("Channel ID: " + channel_id);

        loanamt = rs.getInt("loanamt");
        loan_amt = Integer.toString(loanamt);
        map.put(3, loan_amt);
        al.add(loan_amt);
        Reporter.log("Loan Amount: " + loan_amt);

        fileprocesschrg = rs.getInt("fileprocesschrg");
        fileprocess_chrg = Integer.toString(fileprocesschrg);
        map.put(4, fileprocess_chrg);
        al.add(fileprocess_chrg);
        Reporter.log("File Processing Charge: " + fileprocess_chrg);


        loaninterestrate = rs.getInt("loaninterestrate");
        loaninterest_rate = Integer.toString(loaninterestrate);
        map.put(5, loaninterest_rate);
        al.add(loaninterest_rate);
        Reporter.log("Loan Interest Rate: " + loaninterest_rate);

        emifrequency = rs.getString("emifrequency");
        map.put(6, emifrequency);
        al.add(emifrequency);
        Reporter.log("EMI Frequency: " + emifrequency);

        status = rs.getString("status");
        map.put(7, status);
        al.add(status);
        Reporter.log("Status: " + status);

        createdby = rs.getString("createdby");
        map.put(8, createdby);
        al.add(createdby);
        Reporter.log("Created By: " + createdby);

        loantype = rs.getString("loantype");
        map.put(9, loantype);
        al.add(loantype);
        Reporter.log("Loan Type: " + loantype);

        loanstatus = rs.getString("loanstatus");
        map.put(10, loanstatus);
        al.add(loanstatus);
        Reporter.log("Loan Status: " + loanstatus);

        panno = rs.getString("panno");
        map.put(11, panno);
        al.add(panno);
        Reporter.log("PAN Number: " + panno);

        aadharno = rs.getString("aadharno");
        map.put(12, aadharno);
        al.add(aadharno);
        Reporter.log("Aadhar Number: " + aadharno);

        loanno = rs.getString("loanno");
        map.put(13, loanno);
        al.add(loanno);
        Reporter.log("Loan Number: " + loanno);


        tncyn = rs.getString("tncyn");
        map.put(14, tncyn);
        al.add(tncyn);
        Reporter.log("Terms and Condition: " + tncyn);

        isemandate = rs.getString("isemandate");
        map.put(15, isemandate);
        al.add(isemandate);
        Reporter.log("E-Mandate: " + isemandate);

        loanmandate = rs.getString("loantype");
        map.put(16, loanmandate);
        al.add(loanmandate);
        Reporter.log("Loan Mandate: " + loanmandate);

        list.add(al);

      }

      for (int i = 0; i < al.size(); i++) {
        if (al.get(i).toString() == null) {
          Reporter.log("this is the " + i + " value from the list-> " + " null");
        } else {
          Reporter.log("this is the " + i + " value from the list-> " + al.get(i).toString());
        }

      }
      for (Map.Entry m : map.entrySet()) {
        Reporter.log(m.getKey() + " " + m.getValue());

      }
    }


  }
}
