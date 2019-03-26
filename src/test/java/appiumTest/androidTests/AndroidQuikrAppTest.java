package appiumTest.androidTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class AndroidQuikrAppTest {
    AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","/Users/moisessiles/GAPProjects/AppiumWorkshop/src/main/resources/quikr-8-86.apk");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset",  true);

        driver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
    }

    @Test()
    public void quikrSwipeGestureTest() {
        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.90);
        int endX = (int) (size.width * 0.09);
        int startY = size.height / 2;
        int duration = 3000;

        while (findElement(By.id("com.quikr:id/action_button_get_started")) == null) {
            //Deprecated
            //driver.swipe(starX, starY, endX, starY,500);
            try {
                new TouchAction(driver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }catch(Exception e){
                new TouchAction(driver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }
        }

        driver.findElement(By.id("com.quikr:id/action_button_get_started")).click();
    }

    @Test()
    public void quikrLoginWrongUser() throws Exception{
        waitForElementVisible(By.id("com.quikr:id/action_button_skip"), 20);
        findElement(By.id("com.quikr:id/action_button_skip")).click();

        waitForElementVisible(By.id("com.quikr:id/login_register_view"), 20);
        //findElement(By.id("com.quikr:id/login_register_view")).sendKeys("my user");
        findElement(By.id("com.quikr:id/login_register_view")).click();

        waitForElementVisible(By.id("com.quikr:id/email_mobile"), 20);
        findElement(By.id("com.quikr:id/email_mobile")).sendKeys("test@wearegap.com");
        findElement(By.id("com.quikr:id/login_password")).sendKeys("123456");
        findElement(By.id("com.quikr:id/login_button")).click();

        Thread.sleep(8000);
        //Wait for the Error
    }

    @After
    public void teardown() {
        //close the app
        driver.quit();
    }

    /**
     * Find element to use it
     *
     * @param locator locator to find
     * @return Web element
     */
    public WebElement findElement(By locator) {
        try {
            if (driver.findElements(locator).size() > 0) {
                return driver.findElement(locator);
            } else {
                return null;
            }
        } catch (InvalidSelectorException ex) {
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, ex));
        } catch (StaleElementReferenceException exc) {
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        }
    }

    /**
     * Verify if the element is displayed
     *
     * @param locator element to look for
     * @return boolean
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement wElement = findElement(locator);
            if (wElement != null) {
                if (wElement.isDisplayed())
                    return true;
                else
                    return false;
            } else {
                return false;
            }
        } catch (WebDriverException ex) {
            throw new RuntimeException(String.format("There was a problem verifying if the element is displayed. %s, %s", locator, ex));
        }
    }

    /**
     * This method wait that the element is present in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public boolean waitForElementVisible(final WebElement element) {
        boolean isElementVisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException te) {
            isElementVisible = false;
        }
        return isElementVisible;
    }

    /**
     * This method wait that the element is present in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public boolean waitForElementVisible(final By element, int amountOfSecondsToWait) {
        boolean isElementVisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, amountOfSecondsToWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            isElementVisible = false;
        }
        return isElementVisible;
    }
}