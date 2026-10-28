package Web.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice.expandtesting.com/");

        driver.manage().window().maximize();

        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//button[@id='btn']"));




        driver.quit();



    }
}
