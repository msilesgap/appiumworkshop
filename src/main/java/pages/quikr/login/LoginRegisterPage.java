package pages.quikr.login;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObjectBase;
import utility.ActionBot;

public class LoginRegisterPage extends PageObjectBase {
    AppiumDriver appiumDriver;

    @FindBy(id="com.quikr:id/login_register_view")
    private WebElement loginRegisterTextField;

    /**
     *
     * @param appiumDriver
     */
    public LoginRegisterPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    /**
     *
     */
    public LoginPage clickLoginRegisterText(){
        ActionBot.clickElement(loginRegisterTextField);
        return new LoginPage(appiumDriver);
    }
}
