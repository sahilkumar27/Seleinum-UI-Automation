package Tests.Google_UI_Auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowHandler {
    private static WebDriver driver;

    @BeforeClass
    public void setDriver(){
        WebDriverManager.chromedriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description= "Test the scrolloffset and compare the value")
    @Severity(SeverityLevel.BLOCKER)
    public static void test(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");  //parent window
        String parentWindowID = driver.getWindowHandle();
        driver.findElement(By.name("linkedin"));
        driver.findElement(By.name("facebook"));
        driver.findElement(By.name("twitter"));
        driver.findElement(By.name("youtube"));
//        driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']/descendant::a[@href= 'https://www.facebook.com/OrangeHRM/']")).click();
//        driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']/descendant::a[@href=\"https://twitter.com/orangehrm?lang=en\"]")).click();
//        driver.findElement(By.xpath("//div[@class='orangehrm-login-footer-sm']/descendant::a[@href=\"https://www.youtube.com/c/OrangeHRMInc\"]")).click();

        Set<String> handles = driver.getWindowHandles();
        List<String> hList = new ArrayList<String>(handles);
        if(switchToRightWindow ("Facebook", hList)){
            System.out.println(driver.getCurrentUrl()+ ":" + driver.getTitle());
        }

        closeAllWindows(hList, parentWindowID);
        switchToParentWindow(parentWindowID);
        System.out.println(driver.getCurrentUrl() + ":" + driver.getTitle()) ;
    }

    public static void closeAllWindows(List<String> hlist, String parentWindowID){
        for (String element : hlist){
            if(!element.equals(parentWindowID)){
                driver.switchTo().window(element).close();
            }
        }
    }


    public static void switchToParentWindow(String parentWindowID){
        driver.switchTo().window(parentWindowID);
    }


    public static boolean switchToRightWindow(String windowTitle, List<String> hlist){
        for(String element : hlist){
            String title = driver.switchTo().window(element).getTitle();
            if (title.contains(windowTitle)){
                System.out.println("Found the right window.....");
                return true;
            }
        }
        return false;
    }

    @AfterMethod
    public void TearDown(){
        driver.close();
    }

}
