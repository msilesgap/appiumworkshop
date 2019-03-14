package appiumTest.iOSTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import static org.junit.Assert.assertEquals;


/**
 *
 * Simple test which demonstrates how a test can be run against Mobile Safari running on an Appium instance.
 *
 * The test is based on https://github.com/appium/appium/blob/master/sample-code/examples/node/safari.js
 *
 * @author Ross Rowe
 */
public class BrowserStackSafariTest {
    private WebDriver driver;
    public static final String USERNAME = "";
    public static final String AUTOMATE_KEY = "";
    public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    /**
     * Sets up appium.  You will need to either explictly set the sauce username/access key variables, or set
     * SAUCE_USERNAME and SAUCE_ACCESS_KEY environment variables to reference your Sauce account details.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", "iPhone");
        capabilities.setCapability("platform", "MAC");
        capabilities.setCapability("device", "iPhone 6");
        capabilities.setCapability("name", "iOS Safari Test");
        capabilities.setCapability("browserstack.debug", "true");

        driver = new RemoteWebDriver(new URL(URL), capabilities);

    }

    /**
     * Navigates to http://saucelabs.com/test/guinea-pig and interacts with the browser.
     * @throws Exception
     */
    @Test
    public void WebPageFormTest() throws Exception {
        Thread.sleep(2000);
        driver.get("http://saucelabs.com/test/guinea-pig");
        Thread.sleep(2000);
        WebElement idElement = driver.findElement(By.id("i_am_an_id"));
        Assert.assertNotNull(idElement);
        assertEquals("I am a div", idElement.getText());
        WebElement commentElement = driver.findElement(By.id("comments"));
        Assert.assertNotNull(commentElement);
        commentElement.sendKeys("This is an awesome comment");
        WebElement submitElement = driver.findElement(By.id("submit"));
        Assert.assertNotNull(submitElement);
        submitElement.click();
        Thread.sleep(10000);
        WebElement yourCommentsElement = driver.findElement(By.id("your_comments"));
        Assert.assertNotNull(yourCommentsElement);
        Assert.assertTrue(driver.findElement(By.id("your_comments")).getText().contains("This is an awesome comment"));
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