package Tests.Google_UI_Auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver();

        // Initialize ChromeDriver and open the browser
        driver = new ChromeDriver();
    }

    @Test
    public void testOpenGoogle() throws InterruptedException {
        // Navigate to Google
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        Thread.sleep(5000);

        // Perform assertions or additional actions as needed
        WebElement searchBox = driver.findElement(By.name("q"));
        Thread.sleep(5000);
        searchBox.sendKeys("Selenium Automation");
        searchBox.submit();
        Thread.sleep(6000);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
