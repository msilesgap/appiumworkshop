package appiumTest.androidTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ActionBot;
import utility.Utils;

public class AndroidCalculatorTest {
    AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Real Devices / Genymotion
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDeviceS9"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));

            capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        //capabilities.setCapability("fullReset",  true);
        //Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        //driver = new RemoteWebDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);

    }

    @Test(description = "Open Calculator app",
            groups = {"calculator"})
    public void CalculatorTest() {
        driver.rotate(ScreenOrientation.LANDSCAPE);

        WebElement plus = driver.findElement(By.id("com.android.calculator2:id/op_add"));
        plus.click();

        WebElement two = driver.findElement(By.id("com.android.calculator2:id/digit_2"));

        two.click();
        plus.click();

        WebElement four = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
        four.click();

        ActionBot.waitTime(5000);
        driver.rotate(ScreenOrientation.PORTRAIT);

        //Click the plus button
        plus.click();

        WebElement seven = driver.findElement(By.id("com.android.calculator2:id/digit_7"));
        seven.click();
        plus.click();

        WebElement three = driver.findElement(By.id("com.android.calculator2:id/digit_3"));
        three.click();

        WebElement equalTo = driver.findElement(By.id("com.android.calculator2:id/eq"));
        equalTo.click();

        Assert.assertEquals(driver.findElement(By.id("com.android.calculator2:id/result")).getText(), "16",
                "There is a problem with the results.");
    }

    @AfterMethod
    public void teardown() {
        //close the app
        driver.quit();
    }
}