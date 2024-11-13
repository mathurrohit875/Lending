package LongTermLoanPackage.LongTermEMIPayPackage;

import LongTermLoanPackage.DBPackage.DBUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class LongTermLoanAdmin {
  private static final String CONFIG_FILE_PATH = "C:/Users/rohit.mathur/IdeaProjects/Lending/src/LongTermLoanPackage/config.properties";
  private static final String DB_URL = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
  private static final String DB_USERNAME = "sa";
  private static final String DB_PASSWORD = "UAT_R0INET_SQL";
  Connection conn;
  double loanAmt;
  ViewLongTermLoanPOM viewLongTermLoanPOM;
  private WebDriver driver;
  private JavascriptExecutor js;
  private Properties properties;
  private String loanNumberDbClass;

  @BeforeTest
  public void setupAdmin() throws IOException {
    ChromeOptions options = new ChromeOptions();
    options.setAcceptInsecureCerts(true);
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    viewLongTermLoanPOM = new ViewLongTermLoanPOM(driver);

    try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
      properties = new Properties();
      properties.load(fileInputStream);
    }

    String url = properties.getProperty("url");
    driver.get(url);
    js = (JavascriptExecutor) driver;

    LongTermLoanAdminLoginPOM longTermLoanAdminLoginPOM = new LongTermLoanAdminLoginPOM(driver);
    String usernameAdmin = properties.getProperty("usernameAdmin");
    String passwordAdmin = properties.getProperty("passwordAdmin");
    longTermLoanAdminLoginPOM.enterCredentials(usernameAdmin, passwordAdmin, "sdcd");
    longTermLoanAdminLoginPOM.enterOTP("222111");

  }

  @Test(priority = 1)
  public void loginUser() throws SQLException, IOException, ClassNotFoundException {
    viewLongTermLoanPOM.goToLongTermLoan();
    DBUtil dbUtil = new DBUtil();
    String usernameUser = properties.getProperty("usernameUser");
    Reporter.log("this is username of user from the properties file: " + usernameUser);
    String query = "SELECT * " + "FROM tm_user " + "JOIN tm_channelemi ON tm_user.userid = tm_channelemi.channelid " + "WHERE tm_user.usercode =? AND tm_channelemi.loanstatus = 1";

    try (ResultSet rs = dbUtil.executePreparedQuery(query, usernameUser)) {
      if (rs.next()) {
        loanNumberDbClass = rs.getString("loanno");
        loanAmt = rs.getDouble("loanamt");
        Reporter.log("this is loan number from the DB " + loanNumberDbClass);
        Reporter.log("this is loan amount from db: " + loanAmt);
      }
      Reporter.log("this is loan number outside the loop from the DB " + loanNumberDbClass);
      Reporter.log("this is loan amount outside the loop from the DB: " + loanAmt);
      viewLongTermLoanPOM.enterLoanNumber(loanNumberDbClass);
    }
  }

  @Test(priority = 2)
  public void collectLoan() throws IOException {
    viewLongTermLoanPOM.EmiDetail(loanAmt);
    viewLongTermLoanPOM.collectLoanDetails(loanAmt);
    viewLongTermLoanPOM.collectEmiDetail();
  }

  @Test(priority = 3)
  public void changeLoanStatus() throws SQLException, ClassNotFoundException {
    viewLongTermLoanPOM.changeStatusOfLoan(loanNumberDbClass);
  }


}






   /* @DataProvider(name="testdatalogin")
    public Object[][] DataProviderLogin(){
// Create object array with 2 rows and 2 column- first parameter is row and second is //column
        Object [][] userData=new Object[1][2];

// Enter data to row 0 column 0
        userData[0][0]="Mona.Sharma";
// Enter data to row 0 column 1
        userData[0][1]="roinet@1234";
// Enter data to row 1 column 0
        //userData[1][0]="CSP174387";
// Enter data to row 1 column 1
        //userData[1][1]="roinet@1234";

// Enter data to row 2 column 0
        // userData[2][0]="CSP251413";
// Enter data to row 2 column 1
        // userData[2][1]="roinet@1234";
// Enter data to row 3 column 0
        // userData[3][0]="CSP250284";
// Enter data to row 3 column 1
        // userData[3][1]="roinet@1234";
// return arrayobject to testscript
        return userData;
    }*/
