package appiumTest.androidTests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Utils;

public class AndroidHybridTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException{
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/apps/Android/");
        File app = new File(appDir, "Hybridtestapp.zip");

        //Emulator
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"AndroidTestDevice");
        //capabilities.setCapability("avd","AndroidTestDevice");

        //Real Devices / Genymotion
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));

        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //capabilities.setCapability(MobileCapabilityType, "com.example.hybridtestapp");
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.example.hybridtestapp.MainActivity");

        driver = new RemoteWebDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);
    }

    @Test
    public void HybridFormTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        //WebElement firstName = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.widget.EditText[1]"));
        WebElement firstName = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText[1]"));

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        firstName.sendKeys("Test");

        //WebElement lastName = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.widget.EditText[1]"));
        WebElement lastName = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText[2]"));
        lastName.sendKeys("LastName");

        //WebElement age = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[3]/android.widget.EditText[1]"));
        WebElement age = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText[3]"));
        age.sendKeys("26");

        //WebElement username = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[4]/android.widget.EditText[1]"));
        WebElement username = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText[4]"));
        username.sendKeys("username");

        //WebElement password = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[5]/android.widget.EditText[1]"));
        WebElement password = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.EditText[5]"));
        password.sendKeys("appium@123");

        WebElement btnRegister = driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.Button[1]"));
        btnRegister.click();
    }

    @AfterMethod
    public void teardown(){
        //close the app
        driver.quit();
    }
}