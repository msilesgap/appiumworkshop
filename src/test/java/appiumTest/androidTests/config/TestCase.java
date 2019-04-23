package appiumTest.androidTests.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.quikr.slider.SliderPage;
import listeners.ScreenshotListener;
import utility.Log;
import org.testng.annotations.*;
import utility.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * This class sets up the browser as well as it closes the browser after each proof. This class has the initialization
 * for the ActionBot class that interact with the browser
 *
 */
@Listeners(ScreenshotListener.class)
public class TestCase {

    protected AppiumDriver appiumDriver;
    private long testClassEndTime;
    private String currentTestClass;
    private long testClassStartTime;
    private static SliderPage sliderPage = null;

    /**
     * This method overrides the setUp method from TestCaseBase classL
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        this.testClassStartTime = System.currentTimeMillis();

        // configure log4j properties file
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");


        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","/Users/moisessiles/GAPProjects/AppiumWorkshop/src/main/resources/quikr-8-86.apk");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset",  true);

        appiumDriver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);

        this.currentTestClass = this.getClass().getName();
        Log.info("Test Class: " + this.currentTestClass);
    }

    /**
     * This method overrides the quitBrowser method inherited from TestCaseBase class
     */
    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        if(appiumDriver != null)
            appiumDriver.quit();
        this.testClassEndTime = System.currentTimeMillis();
        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(this.testClassEndTime - this.testClassStartTime);
        Log.info("Complete; total time " + timeSeconds + " seconds.");
        Log.info("");
        Log.info("---------------------------------------------------------------------");
    }

    /**
     * Get the instance of login page
     *
     * @return instance of login page to be used
     */
    public SliderPage getSliderPage() {
        Log.info("Getting the Slider Page Object.");
        if (sliderPage == null) {
            sliderPage = new SliderPage(this.appiumDriver);
        }
        return sliderPage;
    }
}
