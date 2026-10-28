package Web.Base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class BaseClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static final String RBI_URL = "rbi_url";
    public static final String SBI_URL = "http://whitelabel-utxotesting.npcinet.tech:2238/retail/tokensbi/dashboard/ui/login";
    public static final String CRED_URL = "http://whitelabel-utxotesting.npcinet.tech:2238/retail/tokencred/dashboard/ui/login";

    public void launchRBIBankApplication () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(RBI_URL);
    }

    public void launchSBIBankApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SBI_URL);
    }

    public void launchCredTPAPApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(CRED_URL);
    }

}
