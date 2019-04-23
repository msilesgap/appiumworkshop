package pages.quikr.login;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageObjectBase;
import utility.ActionBot;

public class LoginPage extends PageObjectBase {
    AppiumDriver appiumDriver;

    /*waitForElementVisible(By.id("com.quikr:id/action_button_skip"), 20);
    findElement(By.id("com.quikr:id/action_button_skip")).click();

    waitForElementVisible(By.id("com.quikr:id/login_register_view"), 20);
    //findElement(By.id("com.quikr:id/login_register_view")).sendKeys("my user");
    findElement(By.id("com.quikr:id/login_register_view")).click();

    waitForElementVisible(By.id("com.quikr:id/email_mobile"), 20);
    findElement(By.id("com.quikr:id/email_mobile")).sendKeys("test@wearegap.com");
    findElement(By.id("com.quikr:id/login_password")).sendKeys("123456");
    findElement(By.id("com.quikr:id/login_button")).click();*/

    @FindBy(id="com.quikr:id/email_mobilex")
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
