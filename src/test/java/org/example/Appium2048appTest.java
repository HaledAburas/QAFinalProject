package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Appium2048appTest {
    private AndroidDriver driver;

    @BeforeEach
            public void SetUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.uberspot.a2048");
        caps.setCapability("appActivity", "com.uberspot.a2048.MainActivity");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl,caps);
    }

    public void changeLogMessege(){
        WebElement ClickOk = driver.findElement(By.id("android:id/button1"));
        ClickOk.click();
    }

    public void Wait(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        changeLogMessege();
    }

    @Test
    //this test case is testing the swipe action in the app
        public void TestSwipe() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        changeLogMessege();
        Thread.sleep(2000);
        TouchAction touchActionX = new TouchAction(driver);
        TouchAction touchActionY = new TouchAction(driver);
        Dimension screenSize = driver.manage().window().getSize();
        //Swiping Left And Up
        int startX = (int) (screenSize.width * 0.8);
        int endX = (int) (screenSize.width * 0.2);
        int startY = screenSize.height / 2;
        int endY = (int) (screenSize.width * 0.2);
        touchActionX.longPress(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, startY)).release().perform();
        touchActionY.longPress(PointOption.point(startX,startY))
                .moveTo(PointOption.point(startX,endY)).release().perform();
        WebElement mergedTile = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[7]/android.view.View[2]/android.view.View[3]/android.view.View"));
        String MergedTile = mergedTile.getText();
        Assertions.assertNotNull(MergedTile);

    }

    @Test
    //this test case testing if the score shows the correct number
    public void Score(){
        Wait();
        WebElement ScoreNewGAme_Element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[1]/android.widget.TextView"));
        String scoreBeforeMerge = ScoreNewGAme_Element.getText();
        Assertions.assertEquals("0",scoreBeforeMerge);
    }
    @Test
    //this test case  shows of the new game button works as intended
    public void NewGameButton() throws InterruptedException{
        Wait();
        Thread.sleep(2000);
        WebElement NewGame_Button = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[5]"));
        NewGame_Button.click();
        Thread.sleep(2000);
        WebElement NewGameConfirmation = driver.findElement(By.xpath("//android.view.View[@content-desc=\"OK\"]"));
        Assertions.assertTrue(NewGameConfirmation.isDisplayed());
        NewGameConfirmation.click();
    }
    @Test
    //this test shows if the Undo button works as intended
    public void UndoButton() throws InterruptedException{
        Wait();
        Thread.sleep(2000);
        WebElement Undo_Button = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Undo\"]"));
        Undo_Button.click();
        Thread.sleep(2000);
        WebElement UndoConfirmation = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Undo\"])[2]"));
        Assertions.assertTrue(UndoConfirmation.isDisplayed());
        UndoConfirmation.click();
    }

    @AfterEach
    public void Quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }




}
