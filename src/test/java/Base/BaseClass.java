package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
    public static WebDriver driver;

//    protected string Username = "0";
//    protected string Password = "124@#";
    protected String BaseUrl = "https://www.amazon.in/";

    public void SetUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(BaseUrl);
    }
}
