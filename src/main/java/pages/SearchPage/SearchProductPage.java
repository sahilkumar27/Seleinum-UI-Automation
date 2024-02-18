package pages.SearchPage;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;

public class SearchProductPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public SearchProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By AmazonSearch = By.xpath("//input[@id='twotabsearchtextbox']");
    private By IphonePath = By.xpath("//div[@class='a-section a-spacing-none _YXV0b_container_1DI1V _YXV0b_block_1vI8- _YXV0b_hFull_2lnNw _YXV0b_wFull_3f8b2 _YXV0b_row_3CEm0 _YXV0b_itemsCenter_3flfN']/img");
    private By ViewAll = By.xpath("//div[@id='ufmz9o9csc']//a[@class='Overlay__overlay__LloCU EditorialTile__overlay__RMD1L']");
    private By NewMacBookPro = By.xpath("//div[@id='vnrgwajbj4']//a[@class='Overlay__overlay__LloCU EditorialTile__overlay__RMD1L']");
    private By ProductQuantity = By.xpath("//select[@id='quantity']");
    private By ButtonAddToCart = By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']");
    private By ButtonCart = By.xpath("//div[@class='a-row a-spacing-top-small']/descendant::span[@class='a-button-inner']/span[@id='attach-sidesheet-view-cart-button-announce']");

    public void ClickAmazonSearch(String Gadget){
        driver.findElement(AmazonSearch).click();
        driver.findElement(AmazonSearch).sendKeys(Gadget);
        driver.findElement(AmazonSearch).sendKeys(Keys.ENTER);
    }
    public void ClickIphonePath(){
        wait.until(ExpectedConditions.elementToBeClickable(IphonePath));
        driver.findElement(IphonePath).click();
    }
    public void ClickViewAll(){
        wait.until(ExpectedConditions.elementToBeClickable(ViewAll));
        driver.findElement(ViewAll).click();
    }

    public void ClickClickIphonePath(){
        wait.until(ExpectedConditions.elementToBeClickable(NewMacBookPro));
        driver.findElement(NewMacBookPro).click();
    }

    public void ClickProductQuantity(String Quantity){
//        WebDriver driver = new EdgeDriver();
        driver.findElement(ProductQuantity).click();
        driver.findElement(ProductQuantity).sendKeys(Quantity);
        driver.findElement(ProductQuantity).sendKeys(Keys.ENTER);

        try {
            WebElement element = driver.findElement(ProductQuantity);
            element.click();
        }catch (NoSuchElementException  e){
            System.out.println("Element not found: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public void ClickButtonAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(ButtonAddToCart));
        driver.findElement(ButtonAddToCart).click();
    }

    public void ClickButtonCart(){
        wait.until(ExpectedConditions.elementToBeClickable(ButtonCart));
        driver.findElement(ButtonCart).click();
    }


}