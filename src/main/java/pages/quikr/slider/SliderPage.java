package pages.quikr.slider;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObjectBase;
import pages.quikr.login.LoginRegisterPage;
import utility.ActionBot;

public class SliderPage extends PageObjectBase {
    AppiumDriver appiumDriver;

    @FindBy(id="com.quikr:id/action_button_skip")
    private WebElement skipButton;

    public SliderPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public LoginRegisterPage clickSkipButton(){
        ActionBot.clickElement(skipButton);
        return new LoginRegisterPage(appiumDriver);
    }
}
