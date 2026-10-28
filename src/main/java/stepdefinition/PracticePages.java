package stepdefinition;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class PracticePages {

    public WebDriver driver;
    public WebDriverWait wait;

    public PracticePages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By radioButton1 = By.xpath("//input[@name='radioButton' and @value='radio1']");
    private By radioButton2 = By.xpath("//input[@name='radioButton' and @value='radio2']");
    private By radioButton3 = By.xpath("//input[@name='radioButton' and @value='radio3']");

    private By SuggestionClass = By.xpath("//input[@id='autocomplete']");

    private By DropDownExample = By.xpath("//select[@id='dropdown-class-example']");

    private By CheckBoxExample = By.xpath("//legend[text()='Checkbox Example']/following-sibling::label/input[@value='option1']");

    private By OpenWindowButton = By.xpath("//button[text()='Open Window']");

    private By SwitchTab = By.xpath("//a[text()='Open Tab']");

    private By SwitchToAlertButton = By.xpath("//input[@id='name']");
    private By AlertButton = By.xpath("//input[@id='alertbtn']");

    private By WebTableExample = By.xpath("//table[@id='product' and @name='courses']");
    private By WebTableRows = By.xpath("//table[@id='product' and @name='courses']/tbody/tr");
    private By WebTableHeader = By.xpath("//table[@id='product' and @name='courses']/tbody/tr/th");
    private By WebTableData = By.xpath("//table[@id='product' and @name='courses']/tbody/tr/td");

    private By hideButton = By.id("hide-textbox");
    private By showButton = By.id("show-textbox");
    private By textBox = By.id("displayed-text");
    private By hideShowExampleInputText = By.xpath("//input[@id='displayed-text']");

    private By mouseHoverButton = By.id("mousehover");
    private By topOption = By.xpath("//a[text()='Top']");
    private By reloadOption = By.xpath("//a[text()='Reload']");

    private By iFrameExample = By.xpath("//iframe[@id='courses-iframe']");



    public void clickRadioButton() {
        driver.findElement(radioButton1).click();
        System.out.println("Radio button 1 clicked");
        driver.findElement(radioButton2).click();
        System.out.println("Radio button 2 clicked");
        driver.findElement(radioButton3).click();
        System.out.println("Radio button 3 clicked");
    }


    public void enterSuggestion(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SuggestionClass));
        driver.findElement(SuggestionClass).sendKeys(text);
        driver.findElement(SuggestionClass).sendKeys(Keys.ENTER);
        System.out.println("Suggestion entered: " + text);
    }

    public void selectDropDown(String value) {

///        If the dropdown is not a standard <select> element (e.g., custom dropdowns built with <ul> and <li>), iterating through the options using XPath is necessary. This shows your ability to handle non-standard elements.

        wait.until(ExpectedConditions.visibilityOfElementLocated(DropDownExample));
        driver.findElement(DropDownExample).click();

        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//option[text()='Option2']"));
        for (WebElement element : dropdownOptions){
            if(element.getText().equalsIgnoreCase(value)){
                element.click();
                System.out.println("Selected dropdown value: " + value);
                break;
            }

        }


        /// If the dropdown is a standard <select> element, using the Select class is the preferred approach. It is concise, readable, and demonstrates knowledge of Selenium's built-in utilities.
//        WebElement dropdown = driver.findElement(DropDownExample);
//        Select select = new Select(dropdown);
//        select.selectByVisibleText(value);
//        System.out.println("Selected dropdown value: " + value);

    }

    public void clickCheckBox() {

        /// for single checkbox click
//        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckBoxExample)).click();

        /// for multiple checkbox click
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement element : checkboxes){
            if (!element.isSelected()){
                element.click();
            }
        }
        System.out.println(" All Checkbox clicked");
    }


    /// Window Handling Example
    public void clickOpenWindowButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(OpenWindowButton)).click();
    }

    public String switchToChildWindow(){
        String parentWindow = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows){
            if (!window.equals(parentWindow)){
                driver.switchTo().window(window);
                System.out.println("Switched to child window");
                break;
            }
        }
        return parentWindow;
    }

    public void closeChildWindow(){
        driver.close();
    }

    public void switchToParentWindow(String parent){
        driver.switchTo().window(parent);
        System.out.println("Switched back to parent window");
    }


    /// Tab Handling Example
    public String clickSwitchTabButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SwitchTab)).click();

        String parentTab = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs){
            if (!tab.equals(parentTab)){
                driver.switchTo().window(tab);
                System.out.println("Switched to new tab");
                break;
            }
        }

        return parentTab;
    }

    public void closeCurrentTab(){
        driver.close();
        System.out.println("Closed current tab");
    }

    public void switchBacktoParentTab(String parent){
        driver.switchTo().window(parent);
        System.out.println("Switched back to parent tab");
    }


    /// Alert Handling Example
    public void enterTextInAlert(String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SwitchToAlertButton)).sendKeys(text);
        driver.findElement(AlertButton).click();
        System.out.println("Alert button clicked with text: " + text);

    }

    public void handleAlert(){
        new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("Alert accepted");
    }

    public int getRowCount(){
        List<WebElement> rows = driver.findElements(WebTableRows);
        return rows.size();
    }

    public int getColumnCount(){
        List<WebElement> col = driver.findElements(WebTableHeader);
        return col.size();
    }

    public int getDataCount(){
        List<WebElement> data = driver.findElements(WebTableData);
        return data.size();
    }


    /// Print Entire Table Data
    public void printTableData() {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product' and @name='courses']/tbody/tr"));
        for (WebElement row : rows){
            List<WebElement> cols = row.findElements(By.tagName("th"));

            if (cols.isEmpty()){
                cols = row.findElements(By.tagName("td"));
            }

            for (WebElement col : cols){
                System.out.println(col.getText() + "\t");
            }
            System.out.println();
        }
    }


    /// Get Price for a specific course
    public String getCoursePrice(String courName){
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product' and @name='courses']/tbody/tr"));

        for (int i=2; i<=rows.size(); i++){
            String courseName = driver.findElement(By.xpath("//table[@id='product' and @name='courses']/tbody/tr[" + i + "]/td[2]")).getText();

            if (courseName.equalsIgnoreCase(courName)){
                return driver.findElement(By.xpath("//table[@id='product' and @name='courses']/tbody/tr[" + i + "]/td[3]")).getText();

            }
        }

        return "Course not found";
    }

    /// Verify if a specific course exists in the table
    public boolean isCoursePresent(String courseName){
        List<WebElement> couses = driver.findElements(By.xpath("//table[@id='product' and @name='courses']/tbody/tr/td[2]"));
        for (WebElement course: couses ){
            if(course.getText().equalsIgnoreCase(courseName)){
                return true;
            }
        }
        return false;
    }

    /// Hide and Show TextBox Example
    public void clickHideButton() {
        wait.until(ExpectedConditions.elementToBeClickable(hideButton)).click();
        System.out.println("Hide button clicked");
    }

    public void clickShowButton(){
        wait.until(ExpectedConditions.elementToBeClickable(showButton)).click();
        System.out.println("Show button clicked");
    }

    public boolean isTextBoxDisplayed() {
        return driver.findElement(textBox).isDisplayed();
    }

    public void enterhideShowExampleInputText(String text){

        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(hideShowExampleInputText));
        inputField.clear();
        inputField.sendKeys(text);
        System.out.println("Enter text in Hide/Show Example Input Text: " + text);
    }


    /// Scroll down by pixels
    public void scrollDown(int pixels){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
        System.out.println("Scrolled down by " + pixels + " pixels");
    }

    /// Scroll up by pixels
    public void scrollUp(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -" + pixels + ");");
        System.out.println("Scrolled up by " + pixels + " pixels");
    }

    /// Scroll to a specific element
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("Scrolled to the element: " + element);
    }


//    public void scrollToMouseHover(){
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(mouseHoverButton));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
//    }

    /// Mouse Hover Example
    public void clickMouseHoverButton(){
        WebElement hover = wait.until(ExpectedConditions.visibilityOfElementLocated(mouseHoverButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(hover).perform();
        System.out.println("Mouse hovered over the button");
    }

    public void clickTopOption() {
        WebElement hover = wait.until(ExpectedConditions.visibilityOfElementLocated(mouseHoverButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(hover).perform();

        wait.until(ExpectedConditions.elementToBeClickable(topOption)).click();
        System.out.println("Top option clicked");
    }

    public void clickReloadOption() {
        WebElement hover = wait.until(ExpectedConditions.visibilityOfElementLocated(mouseHoverButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(hover).perform();

        wait.until(ExpectedConditions.elementToBeClickable(reloadOption)).click();;
        System.out.println("Reload option clicked");
    }


    /// iFrame Handling - Complete Suite

    // Switch to iframe by WebElement (recommended - most reliable)
    public void switchToFrameByElement() {
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(iFrameExample));
        scrollToElement(iframe);
        driver.switchTo().frame(iframe);
        System.out.println("Switched to iframe using WebElement");
    }

    // Switch to iframe by id or name
    public void switchToFrameByIdOrName(String idOrName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(iFrameExample));
        driver.switchTo().frame(idOrName);
        System.out.println("Switched to iframe using id/name: " + idOrName);
    }

    // Switch to iframe by index (0-based)
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
        System.out.println("Switched to iframe using index: " + index);
    }

    // Switch back to parent frame
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
        System.out.println("Switched to parent frame");
    }

    // Switch back to top-level document
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        System.out.println("Switched to default content (top-level)");
    }

    // Click an element inside the currently focused iframe
    public void clickInsideFrame(By insideFrameLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(insideFrameLocator)).click();
        System.out.println("Clicked element inside iframe");
    }

    // Get count of iframes on the page
    public int getIFrameCountOnPage() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        return iframes.size();
    }

    // Verify element is visible inside iframe (after switching to iframe)
    public boolean isElementVisibleInFrame(By elementLocator) {
        try {
            return driver.findElement(elementLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



}
