package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    public WebDriver driver = null;

    @BeforeEach
    public void SeleniumInitialize() {

        System.setProperty("webdriver.edge.driver", "E:\\QA_ECOM\\Edge driver\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @DisplayName("Leage Url")
    public void testUrl() {
        driver.get("https://www.leagueoflegends.com/en-gb/");//Open the specified url in the browser
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/", driver.getCurrentUrl());
//Check that the url is as expected
        driver.getPageSource();//There is an option to get the whole page html source file
    }

    @Test
    @DisplayName("Action on page")
    public void testActionsOnPage() throws InterruptedException {
        driver.get("https://www.leagueoflegends.com/en-gb/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("CHAMPIONS")).click();
//Get link by it's text and click it
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/champions/", driver.getCurrentUrl());
//Check that the url is as expected
        driver.navigate().back();//Go back
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/", driver.getCurrentUrl());
//Check that the url is as expected
        driver.navigate().forward();//Go forward
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/champions/", driver.getCurrentUrl());
//Check that the url is as expected
        driver.navigate().refresh();//Refresh the page

//Check that the url is as expect
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/champions/", driver.getCurrentUrl());

    }

    @Test
    public void TestPageView() throws InterruptedException {
        //navigate to the website and check the champion page and pick any champ.
        driver.get("https://www.leagueoflegends.com/en-gb/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("CHAMPIONS")).click();
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/champions/", driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.findElement(By.linkText("YASUO")).click();
        Thread.sleep(2000);
    }
    @Ignore
    @Test
    public void loginTest() throws InterruptedException {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new EdgeDriver();

        // Navigate to the League of Legends login page
        driver.get("https://www.leagueoflegends.com/en-gb/");

        // Find the username and password fields and enter your credentials
        WebElement Sign_in = driver.findElement(By.xpath("//*[@id=\"riotbar-right-content\"]/div[4]/div[1]/a"));
        Sign_in.click();
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Shadowgurdian");
        Thread.sleep(2000);
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("besnbesn3116");

        // Click the login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Assert that the page title has changed to the home page
        assert driver.getTitle().equals("Sign In");
    }

    @Ignore
    @Test
    public void testSelectSkin() throws InterruptedException {

        // navigate to the Yasuo champion page on the League of Legends website
        driver.get("https://na.leagueoflegends.com/en-us/champions/yasuo/");

        // find the skin thumbnail image by expath and click on it
        WebElement skinThumbnail = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/section[3]/div/div[2]/div/div[1]/div[5]/div/img"));
        skinThumbnail.getClass();
        Thread.sleep(2000);
        WebElement DragonSkin = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/section[3]/div/div[2]/div/div[2]/div/ul/div/div/li[5]/button"));
        DragonSkin.getClass();
        Thread.sleep(2000);

        // verify that the selected skin is displayed in the large preview image
        WebElement previewImage = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/section[3]/div/div[2]/div/div[1]/div[5]/div/img"));
        String expectedImageSrc = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_9.jpg";
        String actualImageSrc = previewImage.getAttribute("src");
        Assertions.assertEquals(expectedImageSrc, actualImageSrc);
    }

    @Test
    public void SearchBarTest() {
        // in the test we are gonna test the search bar of the website and send any key word we want to see if it works
        driver.get("https://www.leagueoflegends.com/en-gb/");
        WebElement SearchBar = driver.findElement(By.name("q"));
        SearchBar.sendKeys("game");
        SearchBar.submit();
        WebElement SearchTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div/div/div[1]/div/div/h1"));
        Assertions.assertTrue(SearchTitle.isDisplayed());
    }

    @Ignore
    @Test
    public void CreatAccountTest() throws InterruptedException {
        driver.get("https://signup.br.leagueoflegends.com/en-us/");//u enter the singup link and type the detials
        WebElement Email = driver.findElement(By.name("email"));
        Thread.sleep(2000);
        Email.sendKeys("EcomProject1@gmail.com");
        Thread.sleep(2000);
        WebElement start = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/form/div[2]/button"));
        start.click();
        driver.get("https://signup.br.leagueoflegends.com/en-us/signup/index#/date-of-birth");//here we need to enter the link of the signup after we did our email and enter the rest of list
        Select Monthlist = new Select(driver.findElement(By.name("dob-month")));
        List<WebElement> options = Monthlist.getOptions();
        Monthlist.selectByIndex(1);
        Assertions.assertEquals(1, Monthlist.getAllSelectedOptions().size());
        Assertions.assertEquals(options.get(1), Monthlist.getFirstSelectedOption());
        Monthlist.selectByValue("2");
        Assertions.assertEquals(options.get(2), Monthlist.getFirstSelectedOption());

        Select Daylist = new Select(driver.findElement(By.name("dob-day")));
        List<WebElement> option = Daylist.getOptions();
        Daylist.selectByIndex(3);
        Daylist.selectByValue("3");
        Assertions.assertEquals(option.get(3), Daylist.getFirstSelectedOption());

        Select Yearlist = new Select(driver.findElement(By.name("dob-year")));
        List<WebElement> OPTION = Yearlist.getOptions();
        Yearlist.selectByIndex(25);
        Assertions.assertEquals(OPTION.get(25), Yearlist.getFirstSelectedOption());
        Yearlist.selectByValue("1999");
        Assertions.assertEquals(OPTION.get(25), Yearlist.getFirstSelectedOption());

        WebElement Next = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/form/div[2]"));
        Next.click();

        driver.get("https://signup.br.leagueoflegends.com/en-us/signup/index#/registration");//here we type user name and password
        WebElement UserName = driver.findElement(By.name("username"));
        UserName.sendKeys("EcomPlayer1");

        WebElement Password = driver.findElement(By.name("password"));
        Password.sendKeys("EcomPass123");

        WebElement Confirmpass = driver.findElement(By.name("confirm_password"));
        Confirmpass.sendKeys("EcomPass123");
    }
    @Ignore
    @Test
    public void testingtobuy() throws InterruptedException {//here we gonna test if the link lead us to the store and we can go to visa info to fill it
        driver.get("https://merch.riotgames.com/en-gb/product/sugar-rush-poro-figure");
        Thread.sleep(3000);
        WebElement checkout = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/main/div[2]/div/div[2]/div[1]/div[3]/div/div[3]/button"));
        checkout.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//here i set wait duration for the driver so it can load and then click on the right button
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"riot-games-merch\"]/div/div/div[2]/div[3]/div[2]")));
        WebElement checkoutButton = popup.findElement(By.xpath("//*[@id=\"riot-games-merch\"]/div/div/div[2]/div[3]/div[2]/div/div[3]/button[1]"));
        checkoutButton.click();

    }

    @Test
    public void MediaTest() throws InterruptedException {//in this test im going to go to league website and then im going to test if the media link works and i can run the video
        driver.get("https://www.leagueoflegends.com/en-gb/");
        WebElement news = driver.findElement(By.linkText("NEWS"));
        news.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//we have to seit wait duration so it the element I want can be clickable
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Media")));
        popup.click();
        Assertions.assertEquals("https://www.leagueoflegends.com/en-gb/news/media/", driver.getCurrentUrl());
        WebElement Show = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/div[2]/div/div[1]/div/ol/li[3]"));
        Show.click();
        Thread.sleep(2000);
    }

    @Test
    public void opentickt() throws InterruptedException {
        driver.get("https://www.leagueoflegends.com/en-gb/");
        WebElement support = driver.findElement(By.linkText("SUPPORT"));
        support.click();
    }

    @AfterEach
    public void CloseDriver()throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();


    }
}
