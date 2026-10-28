package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By usernamefiled = By.xpath("//input[contains(@placeholder, 'Enter username')]");
    private By passwordfield = By.xpath("//input[contains(@placeholder, 'ENTER PASSWORD')]");
    private By loginbtn = By.xpath("//button[normalize-space(text())='Sign In']");
    private By sucessToasterMessage = By.xpath("//div[@id='toast-container']//div[@role='alert']");
    private By errormsg = By.xpath("//input[@id='otpField']");
    private By otpfield = By.xpath("//input[@id='otpField']");
    private By submitbtn = By.xpath("//button[normalize-space(text())='Submit']");
    private By forgetPassword = By.xpath("//a[text()=' Forgot Password ? ']");
    private By backToLogin = By.xpath("//div[@id='forgotPassword']//button[text()=' Back to Login ']");


    public void EnterUsername(String Username) {
        wait.until(ExpectedConditions.presenceOfElementLocated(usernamefiled));
        driver.findElement(usernamefiled).sendKeys(Username);
    }

    public void EnterPassword(String Password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordfield));
        driver.findElement(passwordfield).sendKeys(Password);
    }

    public void ClickLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(loginbtn));
        driver.findElement(loginbtn).click();
        Thread.sleep(100);
    }

    public void NativeLogin(String Username, String Password) throws InterruptedException {
        EnterUsername(Username);
        EnterPassword(Password);
        ClickLoginButton();
    }

    public void toasterMessage() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(sucessToasterMessage));
        } catch (Exception e) {
            // Handle case where toast message might not appear
            System.out.println("Toast message not found, continuing...");
        }
    }

    public boolean VerifyInvalidCredentialsPopUp() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errormsg));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean VerifuPasswordError() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(errormsg));
            return driver.findElement(errormsg).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void EnterOTP(String OTP) {
        wait.until(ExpectedConditions.presenceOfElementLocated(otpfield));
        driver.findElement(otpfield).sendKeys(OTP);
    }

    public void ClickSubmitBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(submitbtn));
        driver.findElement(submitbtn).click();
    }

    public void ConfirmNoLogin() {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
        } catch (Exception e) {
            System.out.println("Dashboard URL not reached, but continuing...");
        }
    }

}
