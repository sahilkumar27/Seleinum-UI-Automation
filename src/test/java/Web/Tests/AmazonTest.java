package Web.Tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonTest {

    public static WebDriver driver;
    public static void main(String[] args) {

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximize");
//
//        driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.amazon.in/");



        By TodayDeal = By.xpath("//a[text() = \"Today's Deals\"]");

//        WebElement element = new

    }
}
