package appiumTest.iOSTests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IOSNativeTest {
    private IOSDriver driver;


    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/main/resources/");
        File app = new File(appDir, "UICatalog.app");

        //desiredCapabilities.setCapability("name", name.getMethodName());
        //URL sauceUrl = new URL("http://" + authentication.getUsername() + ":"+ authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");

        //Emulator
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        desiredCapabilities.setCapability("udid", "4D8E137D-5753-46D1-B040-A4E50CEC6D63");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new IOSDriver(new URL(Utils.getProperty("AppiumURL")), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);
    }

    @Test
    public void testUIComponents(){
        WebElement actionSheets = driver.findElement(By.name("Action Sheets"));
        actionSheets.click();

        //Back Button
        WebElement uiBackButton = driver.findElement(By.name("UICatalog"));
        uiBackButton.click();

        WebElement pageControl = driver.findElement(By.name("Page Control"));
        pageControl.click();
        uiBackButton.click();


        WebElement datePicker = driver.findElement(By.name("Date Picker"));
        datePicker.click();

        //Back Button
        uiBackButton.click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}