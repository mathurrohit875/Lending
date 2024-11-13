package Login;

import org.testng.Reporter;

import java.sql.*;

public class DBTestClass {

  static Connection conn = null;

  public static void main(String[] args) throws SQLException {
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";
    final String QUERY = "select emiid, channelid, loanamt,fileprocesschrg, loaninterestrate," +
          "emifrequency,status,\n" +
          "createdby,loantype,loanstatus, panno, aadharno, loanno,tncyn,isemandate,loantype\n" +
          "from tm_channelemi where loanno='ROIE04034'";


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
      while (rs.next()) {
        //Display values
        Reporter.log("EMIId: " + rs.getInt("emiid"));
        Reporter.log("Channel ID: " + rs.getInt("channelid"));
        Reporter.log("Loan Amount: " + rs.getInt("loanamt"));
        Reporter.log("File Processing Charge: " + rs.getInt("fileprocesschrg"));
        Reporter.log("Loan Interest Rate: " + rs.getInt("loaninterestrate"));
        Reporter.log("EMI Frequency: " + rs.getString("emifrequency"));
        Reporter.log("Status: " + rs.getString("status"));
        Reporter.log("Created By: " + rs.getString("createdby"));
        Reporter.log("Loan Type: " + rs.getString("loantype"));
        Reporter.log("Loan Status: " + rs.getString("loanstatus"));
        Reporter.log("PAN Number: " + rs.getString("panno"));
        Reporter.log("Aadhar Number: " + rs.getString("aadharno"));
        Reporter.log("Loan Number: " + rs.getString("loanno"));
        Reporter.log("Terms and Condition: " + rs.getString("tncyn"));
        Reporter.log("E-Mandate: " + rs.getString("isemandate"));
        Reporter.log("Loan Mandate: " + rs.getString("loantype"));

      }
    } else {
      Reporter.log("error");
    }

  }
}
