package Google_UI_Auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    public WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver();

        // Initialize ChromeDriver and open the browser
        driver = new ChromeDriver();
    }

    @Test
    public void TestLogin() throws InterruptedException{
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(2000);

//        driver.close();

    }


//    @AfterClass
//    public void tearDown() {
//        if (driver!=null){
//            driver.quit();
//        }
//    }
}


