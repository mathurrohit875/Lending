package LongTermLoanPackage.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LongTermLoginPOM {
  private final By username = By.id("txtUserCode");
  private final By password = By.id("txtPassword");
  private final By captcha = By.id("txtCaptcha");
  private final By loginButton = By.id("btnLogin");
  private final By otp = By.xpath("//input[@placeholder='Enter Valid OTP']");
  private final By validate = By.id("btnValidateOTP");
  Properties properties = new Properties();
  WebDriver driver;
  WebDriverWait wait;

  public LongTermLoginPOM(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }


  public void enterCredentials(String uName, String pwd, String captch) throws IOException {

    driver.findElement(username).sendKeys(uName);
    driver.findElement(password).sendKeys(pwd);
    driver.findElement(captcha).sendKeys(captch);
    driver.findElement(loginButton).click();
  }

  public void enterOTP(String MOtp) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(otp));
    driver.findElement(otp).sendKeys(MOtp);
    driver.findElement(validate).click();
  }
}
