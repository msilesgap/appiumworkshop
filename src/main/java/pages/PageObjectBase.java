package pages;

import utility.ActionBot;
import org.openqa.selenium.WebDriver;

/**
 * This page object is the incharge of initialize the bot in order to be used for the rest of page objects. All the page
 * objects inherit from this class
 */
public class PageObjectBase {

    /**
     * Attributes
     */
    protected ActionBot actionBot;
    protected WebDriver driver;

    /**
     * Constructor
     * @param webDriver webDriver instance
     */
    public PageObjectBase(WebDriver webDriver) {
        actionBot = new ActionBot(webDriver);
        this.driver = webDriver;
    }

    public PageObjectBase() {

    }

    /**
     * This method gets the actual instance of webDriver
     * @return webdriver instance the getDriver method inherited from TestCaseBase class
     */
    public WebDriver getDriverInstance() {
        return this.driver;
    }
}
