package TestNGListenerPackage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

  public static void captureScreenshot(WebDriver driver, String fileName) {
    // Capture screenshot and store it in a file
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      // Save the screenshot to the specified location
      FileUtils.copyFile(screenshot, new File("C:/Users/rohit.mathur/IdeaProjects/Lending/Screenshots/" + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
