//package Web.Tests;
//
//import Web.Base.BaseClass;
////import com.utaf.prac.web.ExcelReader;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import stepdefinition.LoginPage;
//
//import java.util.List;
//import java.util.Map;
//
//public class login extends BaseClass {
//
//    private LoginPage loginPage;
//
//    @BeforeMethod
//    public void launchRBIWithValidCredentials() {
//        super.launchCredTPAPApplication();
//        loginPage = new LoginPage(driver);
//    }
//
//    @DataProvider(name = "validLoginData")
//    public Object[][] getValidLoginData() {
//        List<Map<String, String>> testData = ExcelReader.getTestDataFromSheet(
//            "src/test/resources/loginTestData.xlsx", "Sheet1");
//
//        Object[][] data = new Object[testData.size()][2];
//        for (int i = 0; i < testData.size(); i++) {
//            Map<String, String> row = testData.get(i);
//            data[i][0] = row.get("Username");
//            data[i][1] = row.get("Password");
//        }
//        return data;
//    }
//
//    @Test(dataProvider = "validLoginData", description = "Test: Login with Valid Credentials from Excel")
//    public void SuccessfullyLogin(String username, String password) throws InterruptedException {
//        loginPage.NativeLogin(username, password);
//        loginPage.toasterMessage();
//        // Assuming OTP is 999999 for all test cases
//        loginPage.EnterOTP("999999");
//        loginPage.ClickSubmitBtn();
//        loginPage.ConfirmNoLogin();
//    }
//
//    @Test(description = "Test: Login with Invalid Credentials")
//    public void LoginNegativeScenario() throws InterruptedException {
//        loginPage.NativeLogin("INVALID_USER", "INVALID_PASSWORD");
//        // Add verification for error message if needed
//        if (!driver.getCurrentUrl().contains("login"))
//            Assert.fail();
//        else
//            driver.navigate().refresh();
//    }
//
//    @AfterMethod
//    public void TearDown() {
//        driver.quit();
//    }
//
//}
