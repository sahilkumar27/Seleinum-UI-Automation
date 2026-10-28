package Web.Tests;

import Web.Base.PracBestClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stepdefinition.PracticePages;

public class PracticeSeleinum extends PracBestClass {

    private PracticePages practicePages;

    @BeforeMethod
    public void initializePracticePages(){
        practicePages = new PracticePages(driver);
    }

    @Test (description = "Test: Radio Button Example", priority = 1)
    public void testRadioButtonClick() {
        practicePages.clickRadioButton();

        if(!driver.getCurrentUrl().contains("AutomationPractice"))
            Assert.fail("The current URL does not contain the expected substring.");
        else
            driver.navigate().refresh();
    }


    @Test(description = "Test: Suggestion Class Example", priority = 2)
    public void textSuggestionInput() {
        practicePages.enterSuggestion("India");
    }


    @Test (description = "Test: Drop Down Example", priority = 3)
    public void testDropDownSelection() {
        practicePages.selectDropDown("Option2");
    }

    @Test(description = "Test: Check Box Example", priority = 4)
    public void testCheckBoxSelection() {
        practicePages.clickCheckBox();
    }

    @Test(description = "Test: Open Window Example", priority = 5)
    public void testOpenWindow() {
        practicePages.clickOpenWindowButton();
        String parentWindowHandle = practicePages.switchToChildWindow();

        System.out.println(driver.getTitle());

        // Close the Child Window
        practicePages.closeChildWindow();


        // Switch back to the Parent Window
        practicePages.switchToParentWindow(parentWindowHandle);


        System.out.println("Parent Title : " + driver.getTitle());
        System.out.println("Current Handle : " + driver.getWindowHandle());


        // Assertion
        Assert.assertEquals(driver.getWindowHandle(), parentWindowHandle);
    }


    @Test(description = "Test: Switch Tab Example", priority = 6)
    public void testSwitchTab() {

        String parentTabHandle = practicePages.clickSwitchTabButton();

        System.out.println(driver.getTitle());

        // Close the Current Tab
        practicePages.closeCurrentTab();

        // Switch back to the Parent Tab
        practicePages.switchBacktoParentTab(parentTabHandle);

        System.out.println("Parent Title: " + driver.getTitle());
        System.out.println("Current Handle: " + driver.getWindowHandle());

        // Assertion
        Assert.assertEquals(driver.getWindowHandle(), parentTabHandle);
    }


    @Test(description = "Test: Alert Example", priority = 7)
    public void testAlertHandling() {
        practicePages.enterTextInAlert("Test Alert");
        practicePages.handleAlert();
    }


    @Test(description = "Test: WebTable Example", priority = 8)
    public void testWebTable() {
        System.out.println("Rows : " + practicePages.getRowCount());
        System.out.println("Columns : " + practicePages.getColumnCount());
        System.out.println("Data Count : " + practicePages.getDataCount());

        practicePages.printTableData();

        String price = practicePages.getCoursePrice("WebServices / REST API Testing with SoapUI");
        System.out.println(price);

        Assert.assertTrue(practicePages.isCoursePresent("Learn SQL in Practical + Database Testing from Scratch"));
    }

    @Test(description = "Test: Hide and Show Example", priority = 9)
    public void testHideAndShow() {
        // Initially visible
        Assert.assertTrue(practicePages.isTextBoxDisplayed());

        // Hide
        practicePages.clickHideButton();
        Assert.assertFalse(practicePages.isTextBoxDisplayed());


        // Show
        practicePages.clickShowButton();
        Assert.assertTrue(practicePages.isTextBoxDisplayed());

        // Input
        practicePages.enterhideShowExampleInputText("Hello Tester");
    }

    @Test(description = "Test: Mouse Hover Example", priority = 10)
    public void testMouseHover() {
        practicePages.scrollDown(800);

//        practicePages.scrollToMouseHover();
        practicePages.clickMouseHoverButton();
        practicePages.clickTopOption();
        practicePages.clickReloadOption();
    }

    @Test(description = "Test: iFrame Example", priority = 11)
    public void testIFrame() throws InterruptedException {
        // Get iframe count
        int iframeCount = practicePages.getIFrameCountOnPage();
        System.out.println("Total iframes on page: " + iframeCount);
        Assert.assertTrue(iframeCount > 0, "Expected at least one iframe on the page");

        // Scroll to iframe into view
        practicePages.scrollDown(900);
        Thread.sleep(1000);

        // Method 1: Switch to iframe by WebElement
        System.out.println("--- Method 1: Switch by WebElement ---");
        practicePages.switchToFrameByElement();
        Assert.assertTrue(practicePages.isElementVisibleInFrame(By.tagName("body")), "Iframe body should be visible");
        practicePages.switchToDefaultContent();
        System.out.println("Successfully switched and returned from iframe using WebElement");

        // Method 2: Switch to iframe by id/name
        System.out.println("--- Method 2: Switch by id/name ---");
        practicePages.switchToFrameByIdOrName("courses-iframe");
        Assert.assertTrue(practicePages.isElementVisibleInFrame(By.tagName("body")), "Iframe body should be visible");
        practicePages.switchToDefaultContent();
        System.out.println("Successfully switched and returned from iframe using id/name");

        // Method 3: Switch to iframe by index
        System.out.println("--- Method 3: Switch by index ---");
        practicePages.switchToFrameByIndex(0);
        Assert.assertTrue(practicePages.isElementVisibleInFrame(By.tagName("body")), "Iframe body should be visible");
        practicePages.switchToDefaultContent();
        System.out.println("Successfully switched and returned from iframe using index");

        // Try to interact with content inside iframe
        System.out.println("--- Attempt to interact with iframe content ---");
        practicePages.switchToFrameByElement();

        try {
            By courseLink = By.cssSelector("a[href*='courses']");
            if (driver.findElements(courseLink).size() > 0) {
                practicePages.clickInsideFrame(courseLink);
                System.out.println("Successfully clicked element inside iframe");
            } else {
                System.out.println("No course links found inside iframe - content may vary");
            }
        } catch (Exception e) {
            System.out.println("Could not click inside iframe: " + e.getMessage());
        }

        practicePages.switchToDefaultContent();
        System.out.println("iFrame test completed successfully");
    }
}
