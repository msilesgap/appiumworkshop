package appiumTest.androidTests;

import appiumTest.androidTests.config.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;
import pages.quikr.login.LoginPage;
import pages.quikr.login.LoginRegisterPage;
import pages.quikr.slider.SliderPage;
import utility.ActionBot;

import static io.appium.java_client.touch.offset.PointOption.point;

public class AndroidQuikrAppTest extends TestCase {
    //AppiumDriver driver;

    /*@BeforeMethod
    public void setUp() throws MalformedURLException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app","/Users/moisessiles/GAPProjects/AppiumWorkshop/src/main/resources/quikr-8-86.apk");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Utils.getProperty("androidDevice"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Utils.getProperty("androidPlatformName"));
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset",  true);

        driver = new AndroidDriver(new URL(Utils.getProperty("AppiumURL")), capabilities);
    }*/

    /*@Test
    public void quikrSwipeGestureTest() {
        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.90);
        int endX = (int) (size.width * 0.09);
        int startY = size.height / 2;
        int duration = 3000;

        while (ActionBot.findElement(By.id("com.quikr:id/action_button_get_started")) == null) {
            //Deprecated
            //driver.swipe(starX, starY, endX, starY,500);
            try {
                new TouchAction(driver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                ActionBot.waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }catch(Exception e){
                new TouchAction(driver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                ActionBot.waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }
        }

        driver.findElement(By.id("com.quikr:id/action_button_get_started")).click();
    }*/

    @Test(description = "Login with Wrong user and password",
            groups = {"android", "login", "register"})
    public void quikrLoginWrongUser() {
        SliderPage sliderPage = getSliderPage();
        LoginRegisterPage loginRegisterPage = sliderPage.clickSkipButton();
        LoginPage loginPage = loginRegisterPage.clickLoginRegisterText();
        loginPage.fillRegisterForm("test@wearegap.com", "123456");
        loginPage.clickLoginButton();





        /*waitForElementVisible(By.id("com.quikr:id/action_button_skip"), 20);
        findElement(By.id("com.quikr:id/action_button_skip")).click();

        waitForElementVisible(By.id("com.quikr:id/login_register_view"), 20);
        //findElement(By.id("com.quikr:id/login_register_view")).sendKeys("my user");
        findElement(By.id("com.quikr:id/login_register_view")).click();

        waitForElementVisible(By.id("com.quikr:id/email_mobile"), 20);
        findElement(By.id("com.quikr:id/email_mobile")).sendKeys("test@wearegap.com");
        findElement(By.id("com.quikr:id/login_password")).sendKeys("123456");
        findElement(By.id("com.quikr:id/login_button")).click();*/
    }

   /* @AfterMethod
    public void teardown() {
        //close the app
        driver.quit();
    }*/
}