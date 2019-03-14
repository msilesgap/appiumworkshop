package appiumTest.iOSTests;

import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utility.Utils;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

/**
 *
 * Simple test which demonstrates how a test can be run against Mobile Safari running on an Appium instance.
 *
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Ross Rowe
 */
public class IOSSafariTest {

    private WebDriver driver;

    /**
     * Instantiates the {@link #driver} instance by using DesiredCapabilities which specify the
     * 'iPhone Simulator' device and 'safari' app.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Emulator
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability("udid", "4D8E137D-5753-46D1-B040-A4E50CEC6D63");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");

        driver = new RemoteWebDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(Utils.getProperty("TimeoutImplicit")), TimeUnit.SECONDS);
    }

    /**
     * Navigates to http://saucelabs.com/test/guinea-pig and interacts with the browser.
     * @throws Exception
     */
    @Test
    public void WebPageFormTest() throws Exception {
        Thread.sleep(2000);
        driver.get("http://saucelabs.com/test/guinea-pig");
        Thread.sleep(10000);
        WebElement idElement = driver.findElement(By.id("i_am_an_id"));
        assertNotNull(idElement);
        assertEquals("I am a div", idElement.getText());
        WebElement commentElement = driver.findElement(By.id("comments"));
        assertNotNull(commentElement);
        commentElement.sendKeys("This is an awesome comment");
        WebElement submitElement = driver.findElement(By.id("submit"));
        assertNotNull(submitElement);
        submitElement.click();
        Thread.sleep(10000);
        WebElement yourCommentsElement = driver.findElement(By.id("your_comments"));
        assertNotNull(yourCommentsElement);
        assertTrue(driver.findElement(By.id("your_comments")).getText().contains("This is an awesome comment"));
        System.out.println(driver.getCurrentUrl());
    }

    /**
     * Navigates to http://wwww.google.com and search for youtube
     * @throws Exception
     */
    @Test
    public void SearchPageTest() throws Exception {
        Thread.sleep(2000);
        driver.get("https://www.google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium youtube");
        searchField.sendKeys(Keys.ENTER);
        System.out.println(driver.getCurrentUrl());
    }

    /**
     * Closes the {@link #driver} instance.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}