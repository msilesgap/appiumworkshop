package appiumTest.androidTests;

import appiumTest.androidTests.config.TestCase;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import pages.quikr.login.LoginPage;
import pages.quikr.login.LoginRegisterPage;
import pages.quikr.slider.SliderPage;
import utility.ActionBot;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class AndroidQuikrAppTest extends TestCase {

    @Test(description = "Use the slider",
            groups = {"android", "login", "register"})
    public void quikrSwipeGestureTest() {
        Dimension size = appiumDriver.manage().window().getSize();


        int startX = (int) (size.width * 0.90);
        int endX = (int) (size.width * 0.09);
        int startY = size.height / 2;
        int duration = 3000;

        while (ActionBot.findElement(By.id("com.quikr:id/action_button_get_started")) == null) {
            //Deprecated
            //driver.swipe(starX, starY, endX, starY,500);
            try {
                new TouchAction(appiumDriver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                ActionBot.waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }catch(Exception e){
                new TouchAction(appiumDriver
                ).press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(duration)))
                        .moveTo(point(endX, startY)).release().perform();
                ActionBot.waitForElementVisible(By.id("com.quikr:id/action_button_get_started"), 3);
            }
        }

        appiumDriver.findElement(By.id("com.quikr:id/action_button_get_started")).click();
    }

    @Test(description = "Login with Wrong user and password",
            groups = {"android", "login", "register"})
    public void quikrLoginWrongUser() {
        SliderPage sliderPage = getSliderPage();
        LoginRegisterPage loginRegisterPage = sliderPage.clickSkipButton();
        LoginPage loginPage = loginRegisterPage.clickLoginRegisterText();
        loginPage.fillRegisterForm("test@wearegap.com", "123456");
        loginPage.clickLoginButton();
    }

}