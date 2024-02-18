package Tests.Amazon;

import Base.BaseClass;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage.SearchProductPage;

public class Amazon_search extends BaseClass {

    public SearchProductPage searchProductPage;
    @BeforeMethod
    public void SetUpTests() {
        super.SetUp();
        searchProductPage = new SearchProductPage(driver);
    }

    @Test(description = "Test: Verify that Amazon Seaarch")
    @Severity(SeverityLevel.BLOCKER)
    public void VerifyClickOnDAmazonSearch() {
        searchProductPage.ClickAmazonSearch("Iphone");
        searchProductPage.ClickIphonePath();
        if (!driver.getCurrentUrl().contains("amazon")) {
            Assert.fail();
        }
    }

    @Test(description = "Test: Verify that Amazon Seaarch")
    @Severity(SeverityLevel.BLOCKER)
    public void VerifyClickOnDAmazonApplePage() {
        searchProductPage.ClickAmazonSearch("Iphone");
        searchProductPage.ClickIphonePath();
        searchProductPage.ClickViewAll();
        searchProductPage.ClickClickIphonePath();
        searchProductPage.ClickProductQuantity("3");
        searchProductPage.ClickButtonAddToCart();
        searchProductPage.ClickButtonCart();
        if (!driver.getCurrentUrl().contains("amazon")) {
            Assert.fail();
        }
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }


}
