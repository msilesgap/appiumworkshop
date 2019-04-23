package appiumTest.androidTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class AndroidChromeTest {
    AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));

        capabilities.setCapability("noReset", false);
        //capabilities.setCapability("fullReset",  true);
        //capabilities.setCapability("browserName", "Chrome");
        // if chrome is installed, but not default one - use MobileBrowserType.CHROME
        //capabilities.setCapability("automationName", "uiautomator2");
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        //capabilities.setCapability("adbExecTimeout", "50000");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        String os = System.getProperty("os.name");
        StringBuilder chromeDriverPath = new StringBuilder();
        if (os.contains("Win")) {
            chromeDriverPath.append(System.getProperty("user.dir")).append
                    ("\\src\\main\\resources\\selenium_standalone_binaries\\windows\\googlechrome\\64bit\\chromedriver.exe").toString();
        } else if (os.contains("Mac")) {
            chromeDriverPath.append(System.getProperty("user.dir")).append
                    ("/src/main/resources/selenium_standalone_binaries/osx/googlechrome/64bit/chromedriver").toString();
        } else {
            chromeDriverPath.append(System.getProperty("user.dir")).append
                    ("/src/main/resources/selenium_standalone_binaries/linux/googlechrome/64bit/chromedriver").toString();
        }
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
        //capabilities.setCapability("chromedriverExecutable", chromeDriverPath.toString());
        driver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void quickrChromeTest() {
        driver.get("https://www.quickr.com");
        //Click Login Menu
        driver.findElement(By.id("account")).click();
        driver.findElement(By.cssSelector("a[href='/SignIn']")).click();

        driver.findElement(By.id("fullnameFromPopUp")).sendKeys("appiumworkshop@gap.com");
        driver.findElement(By.id("password")).sendKeys("password123456");
        driver.findElement(By.id("reCaptchaLoginPassword")).click();
    }

    @AfterMethod
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