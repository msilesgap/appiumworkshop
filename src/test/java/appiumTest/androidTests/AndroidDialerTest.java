package appiumTest.androidTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDialerTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException{
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Real Devices / Genymotion
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));

        capabilities.setCapability("appPackage", "com.android.dialer");
        capabilities.setCapability("appActivity", "com.android.dialer.DialtactsActivity");

        //4.2
        //capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.phone");
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.phone.EmergencyDialer");

        //Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        //driver = new RemoteWebDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);
    }

    @Test
    public void dialerFunctionalityTest() {
        //WebElement dialPadButton = driver.findElement(By.id("floating_action_button"));
        WebElement dialPadButton = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"key pad\"]"));

        dialPadButton.click();

        driver.findElement(By.id("com.android.dialer:id/six")).click();
        driver.findElement(By.id("com.android.dialer:id/five")).click();
        driver.findElement(By.id("com.android.dialer:id/nine")).click();
        driver.findElement(By.id("com.android.dialer:id/seven")).click();
        driver.findElement(By.id("com.android.dialer:id/eight")).click();
        driver.findElement(By.id("com.android.dialer:id/seven")).click();
        driver.findElement(By.id("com.android.dialer:id/six")).click();
        driver.findElement(By.id("com.android.dialer:id/five")).click();

        WebElement dial = driver.findElement(By.id("dial"));
        dial.click();
    }

    @AfterMethod
    public void teardown(){
        //close the app
        driver.quit();
    }
}