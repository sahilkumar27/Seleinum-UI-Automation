package Web.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AmazonProductFilterTest {

    @Test
    public void filtersLaptopProductsByPrice() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            driver.get("https://www.amazon.in");

            // Search for laptops
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("twotabsearchtextbox")
                    )
            );

            searchBox.sendKeys("laptop");

            driver.findElement(
                    By.id("nav-search-submit-button")
            ).click();

            // Wait for search results
            By productLocator = By.cssSelector(
                    "div[data-component-type='s-search-result']"
            );

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            productLocator
                    )
            );

            // Apply price filter
            WebElement minPrice = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("low-price")
                    )
            );

            WebElement maxPrice = driver.findElement(
                    By.id("high-price")
            );

            minPrice.clear();
            minPrice.sendKeys("30000");

            maxPrice.clear();
            maxPrice.sendKeys("60000");
            maxPrice.submit();

            // Wait for results to load
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            productLocator
                    )
            );

            // Fetch a maximum of 5 products
            List<WebElement> products =
                    driver.findElements(productLocator);

            assertTrue(products.size() > 0, "Expected at least one filtered laptop product");

            int count = Math.min(products.size(), 5);

            System.out.println("===== AMAZON PRODUCT RESULTS =====");

            for (int i = 0; i < count; i++) {

                WebElement product = products.get(i);

                List<WebElement> names =
                        product.findElements(
                                By.cssSelector("h2 a span")
                        );

                List<WebElement> prices =
                        product.findElements(
                                By.cssSelector(".a-price .a-offscreen")
                        );

                List<WebElement> links =
                        product.findElements(
                                By.cssSelector("h2 a")
                        );

                String name = names.isEmpty()
                        ? "Name unavailable"
                        : names.get(0).getText().trim();

                String price = prices.isEmpty()
                        ? "Price unavailable"
                        : prices.get(0).getText().trim();

                String url = links.isEmpty()
                        ? "URL unavailable"
                        : links.get(0).getAttribute("href");

                System.out.println("\nProduct " + (i + 1));
                System.out.println("Name  : " + name);
                System.out.println("Price : " + price);
                System.out.println("URL   : " + url);
            }
        } finally {
            driver.quit();
        }
    }
}
