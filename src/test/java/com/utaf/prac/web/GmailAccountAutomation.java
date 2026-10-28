package com.utaf.prac.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailAccountAutomation {
    public static void main(String[] args) {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to Gmail login page
            driver.get("https://accounts.google.com/signin");

            // Maximize browser window
            driver.manage().window().maximize();

            // Enter email
            WebElement emailField = driver.findElement(By.id("identifierId"));
            emailField.sendKeys("your.email@gmail.com");

            // Click next
            WebElement nextBtn = driver.findElement(By.id("identifierNext"));
            nextBtn.click();

            // Wait for password field to load
            Thread.sleep(15000);  // Replace with WebDriverWait in production

            // Enter password
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("your_password");  // Replace with your password

            // Click Next
            WebElement passwordNextBtn = driver.findElement(By.id("passwordNext"));
            passwordNextBtn.click();

        } catch (Exception e) {
            e.printStackTrace();        // Print the error info
        } finally {
            driver.quit();
        }

    }
}
