package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonPage {


    public WebDriver driver;
    public WebDriverWait wait;

    public AmazonPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By TodayDeal = By.xpath("//a[text() = \"Today's Deals\"]");


    public void clickTodayDeal(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TodayDeal)).click();
    }



}
