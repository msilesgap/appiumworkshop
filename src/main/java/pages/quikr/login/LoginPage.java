package pages.quikr.login;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObjectBase;
import utility.ActionBot;

public class LoginPage extends PageObjectBase {
    AppiumDriver appiumDriver;

    @FindBy(id="com.quikr:id/email_mobile")
    private WebElement email;

    @FindBy(id="com.quikr:id/login_password")
    private WebElement password;

    @FindBy(id="com.quikr:id/login_button")
    private WebElement loginButton;

    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    /**
     *
     * @param username
     * @param _password
     */
    public void fillRegisterForm(String username, String _password) {
        ActionBot.sendKeysWithClear(email, username);
        ActionBot.sendKeysWithClear(password, _password);
        clickLoginButton();
    }

    /**
     *
     */
    public void clickLoginButton(){
        ActionBot.clickElement(loginButton);
    }



}
