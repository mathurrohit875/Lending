package TestNGListenerPackage;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class CustomListenerManager implements ISuiteListener {


  @Override
  public void onStart(ISuite suite) {
    System.out.println("Suite name in custom listener: " + suite.getName());

    switch (suite.getName()) {
      case "Care Insurance Suite":
        System.out.println("Checking if Care Insurance Suite is executed");
        suite.addListener(new TestNGListenerPackage.ExtentReportListenerUAT());
        break;
      case "Long Term Lending Suite":
        System.out.println("Checking if Long Term Lending Suite is executed");
        suite.addListener(new TestNGListenerPackage.ExtentReportListenerMTEST());
        break;
      default:
        System.out.println("No specific listener attached for suite: " + suite.getName());
        break;
    }
    ISuiteListener.super.onStart(suite);
  }

  @Override
  public void onFinish(ISuite suite) {
    ISuiteListener.super.onFinish(suite);
  }
}

