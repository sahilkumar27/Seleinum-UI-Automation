package Web.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class PracBestClass {

    public static WebDriver driver;
    public static WebDriverWait wait;

    protected String Practice = "https://rahulshettyacademy.com/AutomationPractice/";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Practice);
    }


@AfterMethod
    public void teardown() {
    if (driver != null){
        driver.quit();
        driver = null;
    }
}

}
