package Login;

import java.sql.*;

public class DatabaseConnect {
  private Connection conn;

  public DatabaseConnect(Connection conn) {
    this.conn = conn;

  }

  public void DbConnet() throws ClassNotFoundException, SQLException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://103.231.41.61;databaseName=XPRESSO_MTEST";
    String username = "sa";
    String password = "UAT_R0INET_SQL";
    conn = DriverManager.getConnection(url, username, password);
  }

  public void viewLoanList(String loannumber) throws SQLException {
    Statement stmt = conn.createStatement();
    String QUERY = "select m.createdby as User_Code, t.username as User_Name,m.loanno as Loan_Number,m.loanamt as Loan_Amt,\n" +
          "m.loanstatus as Loan_Status,m.addendumstatus as Addendum_Status,m.loanamountfornextcycle as Loan_Amt_Next_Cycle,\n" +
          "m.approvedby as Approved_By,m.approveddate as Approved_Date,m.panno as PAN_Number,m.aadharno as Aadhar_Number,\n" +
          "m.cibilpath as CIBIL_Path,m.emailpath as Email_Path,m.chequescancopies as ChequeScan_Path,\n" +
          "m.chequescancopiesaddendum as AddendumCheque_Path from tm_channelemi m\n" +
          "inner join tm_user t on m.channelid=t.userid\n" +
          "where channelid='" + loannumber + "'";
    ResultSet rs = stmt.executeQuery(QUERY);

  }

}
