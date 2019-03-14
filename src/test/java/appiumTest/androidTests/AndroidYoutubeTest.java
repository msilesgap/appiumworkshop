package appiumTest.androidTests;

import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utility.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidYoutubeTest {
    WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException{
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Emulator
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));

        capabilities.setCapability("appPackage", "com.google.android.youtube");
        capabilities.setCapability("appActivity", "com.google.android.youtube.HomeActivity");

        //Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        driver = new RemoteWebDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);
    }


    @Test
    public void YoutubeTest()  throws Exception{
        //TODO Add Test Info
        System.out.print("Youtube Test");
        Thread.sleep (3000);
    }

    @After
    public void teardown(){
        //close the app
        driver.quit();
    }
}