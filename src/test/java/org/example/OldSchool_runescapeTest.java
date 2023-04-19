package org.example;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.TableHeaderUI;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class OldSchool_runescapeTest {

    public WebDriver driver = null;

    @BeforeEach
    public void SeleniumInitialize() {
        System.setProperty("webdriver.edge.driver", "E:\\QA_ECOM\\Edge driver\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    //****************************************************************************************************************************************************


    @Test
    @DisplayName("Verify Url")
    public void testUrl() {
        driver.get("https://oldschool.runescape.com/");
        Assertions.assertEquals("https://oldschool.runescape.com/", driver.getCurrentUrl());
        driver.getPageSource();
    }


    @Test
    // This test case logs in to the website
    @DisplayName("Login test - im not robot")
    public void LoginTest() throws InterruptedException {
        driver.get("https://oldschool.runescape.com");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        WebElement LogIn_Button = driver.findElement(By.className("login-box__login-link"));
        LogIn_Button.click();
        Thread.sleep(3000);
        WebElement EmailAddress = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/form/div[1]/label"));
        Thread.sleep((1000));
        EmailAddress.sendKeys("protossrulz@gmail.com");
        EmailAddress.submit();
        Thread.sleep(5000);
        WebElement Password = driver.findElement(By.name("password"));
        Password.sendKeys("ShturJtks1");
        Password.submit();
        String pageTitle = driver.getTitle();
        Thread.sleep(3000);
        Assertions.assertTrue(pageTitle.contains("RuneScape | Old School RuneScape"), "Page title does not contain RuneScape | Old School RuneScape");
    }

    // This test case searches for a user in the highscores using the search bar
    @Test
    @DisplayName("HighScore Search Bar test")
    public void HighScore_SearchTest() {
        // Open the Old School RuneScape highscores page
        driver.get("https://secure.runescape.com/m=hiscore_oldschool/overall#_ga=2.55925227.1776783215.1678470563-1885399325.1676315357");
        // Allow cookies
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        // Enter the user in the search box
        WebElement UserSeearchBox = driver.findElement(By.name("user1"));
        UserSeearchBox.sendKeys("Zydraxis");
        UserSeearchBox.submit();
        // Verify that the current URL is correct
        Assertions.assertEquals("https://secure.runescape.com/m=hiscore_oldschool/hiscorepersonal", driver.getCurrentUrl());
    }


    @Test
// This test case checks the navigation bar dropdown in the highscores
    @DisplayName("IronMan navigation bar Dropdown using Action  test")
    public void HighScore_NavigatioBar() throws InterruptedException {
        driver.get("https://secure.runescape.com/m=hiscore_oldschool/overall");
        //Clicking on allow Cookies
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        // Find the "Ironman" option on the navigation bar
        WebElement ironmanNavBar = driver.findElement(By.cssSelector("#body > div.centerDiv > div:nth-child(5) > div.ironman-nav > div:nth-child(2)"));
        // Create an Actions object and move the mouse to hover over the "Ironman" option
        Actions actions = new Actions(driver);
        actions.moveToElement(ironmanNavBar).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Thread.sleep(3000);
        // Find the "Hiscores (Ironman)" sub-menu option and click on it
        WebElement hiscoresIronmanOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#body > div.centerDiv > div:nth-child(5) > div.ironman-nav > div:nth-child(2) > div > a:nth-child(1)")));
        hiscoresIronmanOption.click();
        String expectedTitle = "Old School Ironman - Hiscores";
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    //this test case checks the sidebar scroller in the highscores page
    @DisplayName("HighScores sidebar scroll UP & DOWN test")
    public void ScrollUP_Down_Highscores() throws InterruptedException {
        driver.get("https://secure.runescape.com/m=hiscore_oldschool/overall");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        Thread.sleep(5000);
        // Find the sidebar element by its ID
        WebElement sidebar = driver.findElement(By.id("contentCategory"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Get the current scroll position of the sidebar before scrolling down
        long BeforeScrollDown = (long) js.executeScript("return arguments[0].scrollTop", sidebar);
        // Scroll down the sidebar
        js.executeScript("arguments[0].scrollTop += 100", sidebar);
        // Wait for the page to load after scrolling
        Thread.sleep(5000);
        // Get the current scroll position of the sidebar after scrolling down
        long AfterScrollDown = (long) js.executeScript("return arguments[0].scrollTop", sidebar);
        // Assert that the scroll position has changed by 100 pixels
        Assertions.assertEquals(AfterScrollDown - BeforeScrollDown, 100);
        // Get the current scroll position of the sidebar before scrolling up
        long BeforeScrollUp = (long) js.executeScript("return arguments[0].scrollTop", sidebar);
        // Scroll up the sidebar
        js.executeScript("arguments[0].scrollTop -= 100", sidebar);
        // Get the current scroll position of the sidebar before scrolling down
        long AfterScrollUp = (long) js.executeScript("return arguments[0].scrollTop", sidebar);
        // Assert that the scroll position has changed by 100 pixels
        Assertions.assertEquals(BeforeScrollUp - AfterScrollUp, 100);
        // Wait for the page to load after scrolling
    }

    @Test
    //this test case checks if the pages are the same after using forward and backwards
    @DisplayName("Backward & Forward test")
    public void Back_forwardTest() {
        driver.get("https://oldschool.runescape.com/");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        // click on a link to go to another page on the website
        WebElement WorldSelectButton = driver.findElement(By.cssSelector("#os-home > div > div > main > div > nav > div:nth-child(1) > ul > li:nth-child(2) > a"));
        WorldSelectButton.click();
        // go back to the previous page using the browser's back button
        driver.navigate().back();

        // verify that we are back on the previous page
        String expectedTitle = "Old School RuneScape - Play Old School RS";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
        // go forward to the next page using the browser's forward button
        driver.navigate().forward();

        // verify that we are on the expected page
        String expectedTitle2 = "Play Old School RuneScape - World Server List";
        String actualTitle2 = driver.getTitle();
        Assertions.assertEquals(expectedTitle2, actualTitle2);
    }

    @Test
    //this test case taking a screenshot from the website and saves it in the project
    @DisplayName("taking screenshot")
    public void ScreenShot() throws IOException {
        driver.get("https://oldschool.runescape.com/");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        File ScreenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filepath = ".//screenshot/screen.png";
        FileUtils.copyFile(ScreenshotFile, new File(filepath));
        File actualFile = new File(filepath);
        Assertions.assertTrue(actualFile.exists(), "Screenshot file does not exist");
        Assertions.assertTrue(actualFile.length() > 0, "Screenshot file is empty");
    }

    @Test
    //this test case checks if the chck box work properly
    @DisplayName("Checkbox test")
    public void CheckBoxTest() throws InterruptedException {
        driver.get("https://secure.runescape.com/m=itemdb_oldschool/Zulrah%27s+scales/viewitem?obj=12934");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        WebElement CheckBox2 = driver.findElement(By.xpath("//*[@id=\"toggleTrend\"]"));
        Assertions.assertFalse(CheckBox2.isSelected(), "the checkbox should not be selected");
        Thread.sleep(3000);
        CheckBox2.click();
        Assertions.assertTrue(CheckBox2.isSelected(), "the check box should be selected");
    }

    @Test
    //this test case checks the dropdown list in the forums
    @DisplayName("DropDown List Test")
    public void DropDownList() {
        driver.get("https://secure.runescape.com/m=forum/forums#group63&_ga=2.215767767.1776783215.1678470563-1885399325.1676315357");
        WebElement AllowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        AllowCookies.click();
        WebElement ForumCategory = driver.findElement(By.xpath("//*[@id=\"group43\"]/article[1]/a/div/p"));
        ForumCategory.click();
        String currentForumUrl = driver.getCurrentUrl();
        String ExpectedForumUrl = "https://secure.runescape.com/m=forum/forums?254,255";
        Assertions.assertEquals(ExpectedForumUrl, currentForumUrl, "not the same Url");
        WebElement Dropdown = driver.findElement(By.xpath("//*[@id=\"forum-picker\"]"));
        Dropdown.click();
        Select select = new Select(Dropdown);
        select.selectByIndex(2);
        String CurrentForumTitle = driver.getTitle();
        String ExpectedForumTitle = "Events and Competitions - RuneScape Forum";
        Assertions.assertTrue(CurrentForumTitle.contains(ExpectedForumTitle));
    }

    //**************************************************************************************************************


    @Test
    //this test case checks if the checkout popup shows after clicking the checkout
    @DisplayName("PopUp and Price check")
    public void PopUpTest() throws InterruptedException {
        driver.get("https://runescape.backstreetmerch.com/");
        WebElement SelectingProduct = driver.findElement(By.xpath("//*[@id=\"shopify-section-template--15719520338134__featured-collection\"]/section/div[2]/div/div[2]"));
        SelectingProduct.click();
        Thread.sleep(5000);
        WebElement LocationPopUp = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/form/button"));
        LocationPopUp.click();
        Thread.sleep(1000);
        WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"product_form_template--15719520633046__main8000926843094\"]/div[3]/button"));
        AddToCart.click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mini-cart\"]/div")));
        Assertions.assertTrue(popup.isDisplayed());
        WebElement checkoutButton = popup.findElement(By.xpath("//*[@id=\"mini-cart\"]/div/div[2]/div[2]/div/button"));
        checkoutButton.click();
        WebElement TotalpriceElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[1]/div/aside/div[2]/div/div/div/section/div[2]/div[3]/div[2]/div/div/strong"));
        String ActualPrice = TotalpriceElement.getText();
        String ExpectedPrice = "₪40.00";
        Assertions.assertEquals(ExpectedPrice, ActualPrice, "not the expected price");
    }
    @AfterEach
    public void quit() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    @DisplayName("Copy paragraph from Wikipedia")
    public void copyParagraphFromWikipedia() throws IOException {
        // Navigate to the Wikipedia page
        driver.get("https://oldschool.runescape.wiki/");


        // Find the first paragraph element
        WebElement paragraph = driver.findElement(By.cssSelector("#mw-content-text > div.mw-parser-output > div.mainpage-header.nomobile > div.header-intro > p"));

        // Get the text of the paragraph
        String paragraphText = paragraph.getText();

        // Save the paragraph to a file
        File paragraphFile = new File("paragraph.txt");
        FileUtils.writeStringToFile(paragraphFile, paragraphText, "UTF-8");

        // Verify that the file was created and has content
        assertTrue(paragraphFile.exists());
        assertTrue(paragraphFile.length() > 0);
    }
}