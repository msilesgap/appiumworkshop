package utility;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utility.Log;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

public class ActionBot {

    /**
     * Attributes
     */
    private static WebDriver driver;

    /**
     * constructor
     */
    public ActionBot() {
        super();
    }

    /**
     * constructor
     *
     * @param driver instance of the web driver
     */
    public ActionBot(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get the current url
     *
     * @return String with the url
     */
    public static String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get the web driver instance.
     *
     * @return web driver
     */
    public static WebDriver getDriverInstance() {
        return driver;
    }

    //<editor-fold desc="Click Elements Methods">

    /**
     * Click on a element through JS function
     *
     * @param element element to click on
     */
    public static void clickElementThroughJS(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s"
                    , element, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, ex));
        }
    }

    /**
     * Click on a element through JS function
     *
     * @param locator locator to click on
     */
    public static void clickElementThroughJS(By locator) {
        try {
            WebElement element = findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s"
                    , locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", locator, ex));
        }
    }

    /**
     * Click on the element
     *
     * @param element element to click
     */
    public static void clickElement(WebElement element) {
        try {
            waitUntilElementIsDisplayed(element);
            //waitForElementToBeClickable(element);
            //scrollToElementAndClick(element);
            element.click();
            Log.info("Click Element: " + element);
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s"
                    , element, ex));
        }
    }

    /**
     * Click on the element
     *
     * @param by element to click
     */
    public static void clickElement(By by) {
        try {
            waitUntilElementIsDisplayed(by);
            //waitForElementToBeClickable(by);
            scrollToElementAndClick(by);
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", by, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", by, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", by, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s"
                    , by, ex));
        }
    }

    /**
     * Click on the element
     *
     * @param by element to click
     */
    public static void submitElement(By by) {
        try {
            waitUntilElementIsDisplayed(by);
            waitForElementToBeClickable(by);
            scrollToElement(by);
            findElement(by).submit();
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", by, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", by, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", by, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s"
                    , by, ex));
        }
    }

    /**
     * Wait for an element and click on it
     *
     * @param by locator to wait an click
     */
    public static void waitAndClickElement(By by) {
        waitUntilElementIsDisplayed(by);
        clickElement(findElement(by));
    }

    /**
     * Click on an element through actions
     *
     * @param element element to click
     */
    public static void clickAction(WebElement element) {
        try {
            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(element);
            waitForElementToBeClickable(element);
            scrollToElement(element);
            action.moveToElement(element).click(element);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s", element, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, ex));
        }
    }

    /**
     * Click on an element through actions
     *
     * @param locator element to click
     */
    public static void clickAction(By locator) {
        try {
            WebElement element = findElement(locator);

            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(element);
            scrollToElement(element);
            action.moveToElement(element).click(element);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", locator, ex));
        }
    }

    /**
     * Double click using the Actions class by element
     *
     * @param element element to click
     */
    public static void doubleClickAction(WebElement element) {
        try {
            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(element);
            waitForElementToBeClickable(element);
            scrollToElement(element);
            action.moveToElement(element).doubleClick(element);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s", element, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", element, ex));
        }
    }

    /**
     * Double click using the Actions class by locator
     *
     * @param locator
     */
    public static void doubleClickAction(By locator) {
        try {
            WebElement element = findElement(locator);
            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(element);
            waitForElementToBeClickable(element);
            scrollToElement(element);
            action.moveToElement(element).doubleClick(element);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", locator, exc));
        } catch (InvalidElementStateException ex) {
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            throw new RuntimeException(String.format("There was a problem clicking on the element: %s, %s ", locator, ex));
        }
    }

    //</editor-fold>

    //<editor-fold desc="Select Elements Methods">

    /**
     * Get the selected option from a select
     *
     * @param locator locator to find
     * @return web element
     */
    public static WebElement getSelectedOption(By locator) {
        try {
            WebElement activeElement = findElement(locator);
            Select select = new Select(activeElement);
            return select.getFirstSelectedOption();
        } catch (UnexpectedTagNameException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option from the dropdown " +
                    "with the locator: %s,%s", locator, ex));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option from the dropdown " +
                    "with the locator: %s,%s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option from the dropdown " +
                    "with the locator: %s,%s", locator, ex));
        }
    }

    /**
     * Select a value from a dropdown according to the text it gets
     *
     * @param locator     locator to find out
     * @param visibleText text that should be visible in the dropdown
     */
    public static void selectDropDownValueByVisibleText(final By locator, String visibleText) {
        try {
            WebElement activeElement = driver.findElement(locator);
            Select select = new Select(activeElement);
            select.selectByVisibleText(visibleText);
        } catch (UnexpectedTagNameException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option by text from the dropdown " +
                    "with the locator: %s,%s", locator, e));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option from the dropdown " +
                    "with the locator: %s,%s", locator, ex));
        }
    }

    /**
     * @param selectElement
     * @param selectOptions
     * @param optionToSelect
     */
    public static void  selectOptionFromInputDropdownByText(By selectElement, By selectOptions, String optionToSelect) {
        try {
            if (!optionToSelect.isEmpty()) {
                ActionBot.waitUntilElementIsDisplayed(selectElement, "Element is not found: " + selectElement.toString());
                List<WebElement> optionList;
                WebElement dropdown = ActionBot.findElement(selectElement);
                Assert.assertTrue(dropdown.isDisplayed(), "Dropdown element does not exist on the page.");
                try {
                    ActionBot.clickElement(dropdown);
                } catch (Exception e) {
                    ActionBot.clickElement(selectElement);
                }
                //Some dropdowns need time to display the different options
                waitTime(2000);

                ActionBot.waitUntilInstancesAreMoreThan(selectOptions, 0);
                optionList = ActionBot.findElements(selectOptions);
                Assert.assertNotNull(optionList, "Dropdown is empty, make sure there are options to be selected." + dropdown);
                Assert.assertTrue(optionList.size() > 0, "Dropdown is empty, make sure there are options to be selected." + dropdown);

                if (optionList.size() > 0 && optionList != null) {
                    for (int i = 0; i < optionList.size(); i++) {
                        try {
                            if (optionList.size() > 10) {
                                if (i != optionList.size()) {
                                    Actions action = new Actions(driver);
                                    action.sendKeys(optionList.get(i), Keys.ARROW_DOWN);
                                }
                            }
                        } catch (Exception e) {
                            //There is a problem with Google Chrome with the sendkeys
                        }
                        optionList = ActionBot.findElements(selectOptions);
                        Assert.assertTrue(optionList.size() > 0, "Dropdown is empty, make sure there are options to be selected." + dropdown);

                        optionList = ActionBot.findElements(selectOptions);
                        if (optionList.get(i).getText().equalsIgnoreCase(optionToSelect) || optionList.get(i).getText().contains(optionToSelect)) {
                            ActionBot.waitForElementToBeClickable(optionList.get(i));
                            //Fixme review this click action
                            ActionBot.clickElement(optionList.get(i));
                            Assert.assertTrue(ActionBot.waitForElementInvisible(selectOptions),
                                    "Looks like the element was not correctly selected");
                            break;
                        }
                    }
                } else {
                    Assert.assertTrue(optionList.size() > 0, "There are no elements on the dropdown...");
                }
            }
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option by text from the dropdown " +
                    "with the locator: %s,%s", selectElement, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option by text from the dropdown " +
                    "with the locator: %s,%s", selectElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem selecting an option by text from the dropdown " +
                    "with the locator: %s,%s", selectElement, ex));
        }
    }

    //</editor-fold>

    //<editor-fold desc="Elements Methods">

    /**
     * Verify whether the element exists
     *
     * @param locator locator to find
     * @return boolean
     */
    public static boolean doesElementExist(By locator) {
        if (findElements(locator).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify whether the element exists
     *
     * @param locator locator to find
     * @param parent
     * @return boolean
     */
    public static boolean doesElementExist(By locator, WebElement parent) {
        if (parent.findElements(locator).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify if the element is displayed
     *
     * @param locator element to look for
     * @return boolean
     */
    public static boolean isElementDisplayed(By locator) {
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
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is displayed. %s, %s", locator, ex));
        }
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            if (element.isDisplayed())
                return true;
            else
                return false;
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is displayed. %s, %s", element, ex));
        }
    }

    public static boolean isElementEnabled(WebElement element) {
        try {
            if (element.isEnabled())
                return true;
            else
                return false;
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is displayed. %s, %s", element, ex));
        }
    }

    public static boolean isElementEnabled(By locator) {
        try {
            WebElement element = findElement(locator);
            if (element.isEnabled())
                return true;
            else
                return false;
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is displayed. %s, %s", locator, ex));
        }
    }

    public static boolean isElementClickable(By locator) {
        boolean clickable = false;
        WebElement element = findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
        if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
            clickable = true;
        }
        return clickable;
    }

    public static boolean isElementClickable(WebElement element) {
        boolean clickable = false;
        WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
        if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
            clickable = true;
        }
        return clickable;
    }

    /**
     * get text from the element using locator
     *
     * @param locator element to get the text
     * @return String
     */
    public static String getTextFromElement(By locator) {
        try {
            if (findElements(locator).size() > 0) {
                return findElement(locator).getText();
            } else {
                return null;
            }
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem getting the text for the element. %s :,%s", locator, exc));
        }
    }

    /**
     * get text from the element using WebElement
     *
     * @param element
     * @return
     */
    public static String getTextFromElement(WebElement element) {
        try {
            return element.getText();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem getting the text for the element. %s :,%s", element, exc));
        }
    }

    /**
     * Scroll down through JS
     *
     * @param wElement element to scroll down
     */
    public static void scrollToElement(WebElement wElement) {
        try {
            //Commented by msiles just to check # of failures
            /*Actions actions = new Actions(driver);
            actions.moveToElement(wElement, 10, 15).build().perform();*/

            //Workaround 1
            /*Locatable scrollToItem = (Locatable) wElement;
            int y = scrollToItem.getCoordinates().inViewPort().getY();
            y = y - 100;
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ");");*/

            //Workaround 2
            /*Point p = wElement.getLocation();
            //((JavascriptExecutor) getDriverInstance()).executeScript("window.scrollBy(" + p.getX() + "," + (p.getY() + 200) + ");");
            ((JavascriptExecutor) getDriverInstance()).executeScript("window.scrollBy(" + p.getX() + "," + p.getY() + ");");*/

            //Workaround 3
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", wElement);

            int yScrollPosition = wElement.getLocation().getY() - 100;
            ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + yScrollPosition + ");");
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", wElement, ex));
        }
    }

    /**
     * Scroll to Element, we are using this method to scroll down on the Grids for EAB project
     *
     * @param wElement
     */
    public static void scrollToElementForGrid(WebElement wElement) {
        try {
            //Workaround 3 Selenium 3
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", wElement);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wElement);
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", wElement, ex));
        }
    }

    /**
     * Scroll to Element, we are using this method to scroll down on the Grids for EAB project
     *
     * @param wElement
     */
    public static void scrollToElement(WebElement wElement, String scrollIntoView) {
        try {
            //Workaround 3 Selenium 3
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView('" + scrollIntoView + "');", wElement);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wElement);
        } catch (WebDriverException ex) {
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", wElement, ex));
        }
    }

    /**
     * Scroll to Element, we are using this method to scroll down on the Grids for EAB project
     *
     * @param wLocator
     */
    public static void scrollToElement(By wLocator, String scrollIntoView) {
        try {
            //Workaround 3 Selenium 3
            WebElement wElement = driver.findElement(wLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView('" + scrollIntoView + "');", wElement);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wElement);
        } catch (WebDriverException ex) {
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", wLocator, ex));
        }
    }

    /**
     * Scroll to Element, we are using this method to scroll down on the Grids for EAB project
     *
     * @param locator
     */
    public static void scrollToElementForGrid(By locator) {
        try {
            //Workaround 3 Selenium 3
            WebElement wElement = findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", wElement);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wElement);
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", locator, ex));
        }
    }

    /**
     * Scroll down through JS
     *
     * @param wLocator locator to scroll down
     */
    public static void scrollToElement(By wLocator) {
        try {
            //Commented by msiles just to check # of failures
            /*Actions actions = new Actions(driver);
            WebElement wElement = ActionBot.findElement(wLocator);
            actions.moveToElement(wElement, 15, 20).build().perform();*/

            /*            Locatable scrollToItem = (Locatable) findElement(wLocator);
            int y = scrollToItem.getCoordinates().inViewPort().getY();
            y = y - 100;
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ");");*/

            /*//Workaround 2
            WebElement wElement = getDriverInstance().findElement(wLocator);
            Point p = wElement.getLocation();
            //((JavascriptExecutor) getDriverInstance()).executeScript("window.scroll(" + p.getX() + "," + (p.getY() + 200) + ");");
            ((JavascriptExecutor) getDriverInstance()).executeScript("window.scroll(" + p.getX() + "," + p.getY() + ");");*/

            //Workaround 3
            WebElement element = driver.findElement(wLocator);
            scrollToElement(element);
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("The element could not be scrolled down through JS: %s :,%s", wLocator, ex));
        }
    }

    /**
     * Scroll down, then click on the element
     *
     * @param element element to click on
     */
    public static void scrollToElementAndClick(WebElement element) {
        try {
            //Commented by msiles
            /*scrollToElement(element);
            if (ActionBot.isElementClickable(element)) {
                try {
                    element.click();
                } catch (WebDriverException ex2){
                    ((JavascriptExecutor) driver).executeScript("window.scroll(0,100);");
                    clickElementThroughJS(element);
                }
            } else {
                scrollToElement(element);
                clickElementThroughJS(element);
            }*/

            //Go to the Top of the page
            /*int yScrollPosition = element.getLocation().getY() - 200;
            ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + yScrollPosition + ");");
            element.click();*/

            //Workaround3
            scrollToElementForGrid(element);
            waitTime(1000);
            element.click();

            //Workaround3
            //scrollToElement(element);
            //element.click();
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, exc));
        }
    }

    /**
     * scroll down, then click on the element
     *
     * @param element      element to click on
     * @param headerOffset amount to subtract
     */
    public static void scrollToElementAndClick(WebElement element, Integer headerOffset) {
        try {
            /*int yScrollPosition = element.getLocation().getY() - headerOffset;
            ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + yScrollPosition + ");");*/
            scrollToElement(element);
            element.click();
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", element, exc));
        }
    }

    /**
     * scroll down, then click on the element
     *
     * @param locator locator to click on
     */
    public static void scrollToElementAndClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            /*int yScrollPosition = element.getLocation().getY() - 100;
            ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + yScrollPosition + ");");
            waitForElementToBeClickable(locator);*/
            scrollToElement(element);
            waitTime(1000);
            element.click();
        } catch (NoSuchElementException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", locator, ex));
        } catch (StaleElementReferenceException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", locator, e));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem clicking the element. %s :,%s", locator, exc));
        }
    }

    /**
     * Scroll up in the browser
     */
    public static void scrollUp() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scroll(0,0);");
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem scrolling down.,%s", e));
        }
    }

    /**
     * Get the parent element for a web element
     *
     * @param wElement web element to get the parent
     * @return Web element
     */
    public static WebElement getParentElement(WebElement wElement) {
        try {
            return wElement.findElement(By.xpath(".."));
        } catch (InvalidSelectorException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", wElement, ex));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", wElement, exc));
        } catch (StaleElementReferenceException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", wElement, exc));
        }
    }

    /**
     * Get the parent element for a web element
     *
     * @param locator web element to get the parent
     * @return Web element
     */
    public static WebElement getParentElement(By locator) {
        try {
            WebElement wElement = findElement(locator);
            return wElement.findElement(By.xpath(".."));
        } catch (InvalidSelectorException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, ex));
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        } catch (StaleElementReferenceException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        }
    }

    /**
     * Get the attribute from the element
     *
     * @param element   element to get the attibute
     * @param attribute attribute to get
     * @return string
     */
    public static String getElementAttribute(WebElement element, String attribute) {
        try {
            return element.getAttribute(attribute);
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem getting the attribute from the element: %s, %s"
                    , element, ex));
        }
    }

    /**
     * Get the attribute from the element
     *
     * @param locator   element to get the attibute
     * @param attribute attribute to get
     * @return string
     */
    public static String getElementAttribute(By locator, String attribute) {
        try {
            WebElement element = findElement(locator);
            return element.getAttribute(attribute);
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem getting the attribute from the element: %s, %s"
                    , locator, ex));
        }
    }

    /**
     * Find all the element with the locator passes as parameter
     *
     * @param locator element to find
     * @return List of elements
     */
    public static List<WebElement> findElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (InvalidSelectorException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, ex));
        } catch (StaleElementReferenceException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        }
    }

    /**
     * Find element to use it
     *
     * @param locator locator to find
     * @return Web element
     */
    public static WebElement findElement(By locator) {
        try {
            if (findElements(locator).size() > 0) {
                return driver.findElement(locator);
            } else {
                return null;
            }
        } catch (InvalidSelectorException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, ex));
        } catch (StaleElementReferenceException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        }
    }

    /**
     * Finds an element within a element
     *
     * @param within
     * @param locator
     * @return
     */
    public static WebElement findElement(WebElement within, By locator) {
        try {
            return within.findElement(locator);
        } catch (InvalidSelectorException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, ex));
        } catch (StaleElementReferenceException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem looking for the element. %s, %s", locator, exc));
        }
    }

    /**
     * Move to the element received in the locator
     *
     * @param locator locator to move
     */
    public static void moveToElement(By locator) {
        try {
            Actions action = new Actions(driver);
            WebElement wElement = findElement(locator);
            action.moveToElement(wElement, 0, 0).perform();

            //This is a workaround for the MoveTo
            //((JavascriptExecutor)getDriverInstance()).executeScript("$('div#evy_aboutme_content_id08  div.evy_edit_overflow > div.evy_rltn_icon2 i').hover();");
            /*String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            WebElement wElement = findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);*/
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem moving to the element. %s, %s", locator, exc));
        }
    }

    /**
     * Move to the element received in the locator
     *
     * @param wElement element to move
     */
    public static void moveToElement(WebElement wElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(wElement, 0, 0).perform();

            //This is a workaround for the MoveTo
            //((JavascriptExecutor)getDriverInstance()).executeScript("$('div#evy_aboutme_content_id08  div.evy_edit_overflow > div.evy_rltn_icon2 i').hover();");
            /*String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);*/
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem moving to the element. %s, %s", wElement, exc));
        }
    }

    /**
     * Move to the element received in the locator
     *
     * @param locator locator to move
     */
    public static void moveToElement(By locator, int x, int y) {
        try {
            Actions action = new Actions(driver);
            WebElement wElement = findElement(locator);
            action.moveToElement(wElement, x, y).perform();

            //This is a workaround for the MoveTo
            //((JavascriptExecutor)getDriverInstance()).executeScript("$('div#evy_aboutme_content_id08  div.evy_edit_overflow > div.evy_rltn_icon2 i').hover();");
            /*String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            WebElement wElement = findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);*/
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem moving to the element. %s, %s", locator, exc));
        }
    }

    /**
     * Move to the element received in the locator
     *
     * @param wElement locator to move
     */
    public static void moveToElement(WebElement wElement, int x, int y) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(wElement, x, y).perform();

            //This is a workaround for the MoveTo
            //((JavascriptExecutor)getDriverInstance()).executeScript("$('div#evy_aboutme_content_id08  div.evy_edit_overflow > div.evy_rltn_icon2 i').hover();");
            /*String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            WebElement wElement = findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);*/
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem moving to the element. %s, %s", wElement, exc));
        }
    }


    /**
     * Move to the element received in the locator
     *
     * @param wElement locator to move
     */
    public static void mouseOverByJS(WebElement wElement) {
        try {
            String mouseOverScript = "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);";

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);

            //This is a workaround for the MoveTo
            //((JavascriptExecutor)getDriverInstance()).executeScript("$('div#evy_aboutme_content_id08  div.evy_edit_overflow > div.evy_rltn_icon2 i').hover();");
            /*String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            WebElement wElement = findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(mouseOverScript, wElement);*/
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem moving to the element. %s, %s", wElement, exc));
        }
    }


    //</editor-fold>

    //<editor-fold desc="Wait Elements Methods">


    /**
     * Wait until the element is displayed in the UI
     *
     * @param elementLocator locator to find out
     */
    public static void waitUntilElementIsDisplayed(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, e));
        }
    }

    /**
     * Wait until an element is displayed.
     *
     * @param elementLocator element to wait for
     * @param errorMessage   error msg
     */
    public static void waitUntilElementIsDisplayed(By elementLocator, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s : %s , %s", errorMessage, elementLocator, e));
        }
    }

    /**
     * wait until an element is displayed it depends on the waitTimeSec variable
     *
     * @param elementLocator element to wait until it is visible
     * @param waitTimeSec    second to wait until it fails
     */
    public static boolean waitUntilElementIsDisplayed(By elementLocator, int waitTimeSec) {
        boolean isElementDisplayed = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, e));
        }
        return isElementDisplayed;
    }

    /**
     * Wait until the element is displayed.
     *
     * @param elementLocator locator to wait
     * @param parentElement  parent element
     */
    public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, e));
        }
    }

    /**
     * Wait until the element is displayed.
     *
     * @param elementLocator locator to wait
     * @param parentElement  parent element
     * @param waitTimeSec    seconds to wait
     */
    public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement, int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, e));
        }
    }

    /**
     * Wait until the element is displayed, if it nos displayed an exception is thrown
     *
     * @param elementLocator element to wait
     * @param waitTimeSec    seconds to wait
     * @param errorMessage   error msg to displayed
     */
    public static void waitUntilElementIsDisplayed(By elementLocator, int waitTimeSec, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s : %s,%s", errorMessage, elementLocator, e));
        }
    }

    /**
     * Wait until the element is displayed, if it nos displayed an exception is thrown
     *
     * @param elementLocator element to wait
     * @param parentElement  parent element
     * @param waitTimeSec    seconds to wait
     * @param errorMessage   error msg to displayed
     */
    public static void waitUntilElementIsDisplayed(By elementLocator, WebElement parentElement, int waitTimeSec, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOf(parentElement.findElement(elementLocator)));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s : %s,%s", errorMessage, elementLocator, e));
        }
    }

    /**
     * Wait until the element is displayed
     *
     * @param wElement element to wait
     */
    public static void waitUntilElementIsDisplayed(WebElement wElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOf(wElement));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", wElement, e));
        }
    }

    /**
     * Wait until the element is displayed
     *
     * @param wElement    element to wait
     * @param waitTimeSec seconds to wait
     */
    public static void waitUntilElementIsDisplayed(WebElement wElement, int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.visibilityOf(wElement));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", wElement, e));
        }
    }

    /**
     * Wait until an element is invisible
     *
     * @param by locator to wait
     */
    public static void waitForInvisibilityOfElementLocated(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element to be invisible: %s :,%s", by, e));
        }
    }

    /**
     * Wait until an element is invisible
     *
     * @param by          locator to wait
     * @param waitTimeSec seconds to wait
     */
    public static void waitForInvisibilityOfElementLocated(By by, int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", by, e));
        }
    }

    /**
     * Wait until an element is invisible
     *
     * @param by           locator to wait
     * @param errorMessage error message to display
     */
    public static void waitForInvisibilityOfElementLocated(By by, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s: %s ,%s", errorMessage, by, e));
        }
    }

    /**
     * Wait until an element is invisible
     *
     * @param by           locator to wait
     * @param waitTimeSec  seconds to wait
     * @param errorMessage error message to display
     */
    public static void waitForInvisibilityOfElementLocated(By by, int waitTimeSec, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s: %s ,%s", errorMessage, by, e));
        }
    }

    /**
     * Wait until the element is visible
     *
     * @param by locator to wait
     */
    public static void waitForVisibilityOfElementLocated(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", by, e));
        }
    }

    /**
     * Wait until the instaces be more than
     *
     * @param elementLocator locator to find
     * @param instances      amount of instances
     */
    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances) {
        try {
            new WebDriverWait(driver, Const.WAIT_TIME) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() > instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, ex));
        }
    }

    /**
     * Wait until the instaces be more than
     *
     * @param elementLocator locator to find
     * @param instances      amount of instances
     * @param errorMessage   error message to display
     */
    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances, String errorMessage) {
        try {
            new WebDriverWait(driver, Const.WAIT_TIME) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() > instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("%s: %s :,%s", elementLocator, elementLocator, ex));
        }
    }

    /**
     * Wait until the instaces be more than
     *
     * @param elementLocator locator to find
     * @param instances      amount of instances
     * @param waitTimeSec    seconds to wait
     */
    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances, final int waitTimeSec) {
        try {
            new WebDriverWait(driver, waitTimeSec) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() > instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, ex));
        }
    }

    /**
     * Wait until the instaces be more than
     *
     * @param elementLocator locator to find
     * @param instances      amount of instances
     * @param waitTimeSec    seconds to wait
     * @param errorMessage   error message to display
     */
    public static void waitUntilInstancesAreMoreThan(final By elementLocator, final int instances, final int waitTimeSec, String errorMessage) {
        try {
            new WebDriverWait(driver, waitTimeSec) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() > instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("%s: %s :,%s", errorMessage, elementLocator, ex));
        }
    }

    /**
     * Wait until the instance is equals to.
     *
     * @param elementLocator element compare
     * @param instances      amount of instances
     */
    public static void waitUntilInstancesEqual(final By elementLocator, final int instances) {
        try {
            new WebDriverWait(driver, Const.WAIT_TIME) {
            }.until(new ExpectedCondition<Boolean>() {
                //@Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() == instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, ex));
        }
    }

    /**
     * Wait until the instance is equals to.
     *
     * @param elementLocator element to compare
     * @param instances      amount of locators
     * @param waitTimeSec    seconds to wait
     */
    public static void waitUntilInstancesEqual(final By elementLocator, final int instances, final int waitTimeSec) {
        try {
            new WebDriverWait(driver, waitTimeSec) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() == instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element: %s :,%s", elementLocator, ex));
        }
    }

    /**
     * Wait until the instance is equals to.
     *
     * @param elementLocator element to compare
     * @param instances      amount of locators
     * @param waitTimeSec    seconds to wait
     */
    public static void waitUntilInstancesEqual(final By elementLocator, final int instances, final int waitTimeSec, String errorMessage) {
        try {
            new WebDriverWait(driver, waitTimeSec) {
            }.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (findElements(elementLocator).size() == instances);
                }
            });
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("%s: %s :,%s", errorMessage, elementLocator, ex));
        }
    }

    /**
     * Waint until the element is clickeable
     *
     * @param element element to click
     */
    public static void waitForElementToBeClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element to be clickable: %s :,%s", element, ex));
        }
    }

    /**
     * Wait until the element is clickable
     *
     * @param locator locator to wait
     */
    public static void waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.elementToBeClickable(findElement(locator)));
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting for the element to be clickable: %s :,%s", locator, ex));
        }
    }


    /**
     * Wait until the alert is not present
     */
    public static void waitUntilAlertIsPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the alert is present :,%s", ex));
        }
    }

    /**
     * Wait until the alert is present
     *
     * @param waitTimeSec seconds to wait
     */
    public static void waitUntilAlertIsPresent(final int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the alert is present :,%s", ex));
        }
    }

    /**
     * Get the alert text
     *
     * @return String
     */
    public static String getAlertText() {
        try {
            String text = "";
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                text = alert.getText();
            }
            return text;
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("Had a problem getting the text from the popup %s:", ex));
        }
    }

    /**
     * Get if the alert is present
     *
     * @return boolean
     */
    public static boolean isAlertPresent() {
        try {
            ActionBot.driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            Log.error(ex.getMessage());
            return false;
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("Had a problem switching to the popup %s:", exc));
        }
    }

    /**
     * Click on accept button
     */
    public static void acceptAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                alert.accept();
            }
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("Had a problem accepting the popup %s:", exc));
        }
    }

    /**
     * Dismiss the popup
     */
    public static void dismissAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                alert.dismiss();
            }
        } catch (WebDriverException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("Had a problem dismissing the popup %s:", exc));
        }
    }

    /**
     * Wait until a text is present in the element
     *
     * @param locator     locator to find
     * @param text        text to wait
     * @param waitTimeSec seconds to wait
     */
    public static void waitUntilTextToBePresentInElementLocated(By locator, String text, final int waitTimeSec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSec);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the text is present : %s ,%s", locator, e));
        }
    }

    /**
     * Wait until a text is present in the element
     *
     * @param locator locator to find
     * @param text    text to wait
     */
    public static void waitUntilTextToBePresentInElementLocated(By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the text is present : %s ,%s", locator, e));
        }
    }

    /**
     * Wait until a text is present in the element
     *
     * @param locator      locator to find
     * @param text         text to wait
     * @param errorMessage
     */
    public static void waitUntilTextToBePresentInElementLocated(By locator, String text, String errorMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("%s : %s ,%s", errorMessage, locator, e));
        }
    }

    /**
     * Wait until the text is the expected one
     *
     * @param locator locator to use
     * @param text    text to wait
     */
    public static void waitUntilTextToBe(By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.textToBe(locator, text));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the text is the expected : %s ,%s", locator, e));
        }
    }

    /**
     * Wait until the text is the expected one
     *
     * @param locator locator to use
     * @param text    text to wait
     */
    public static void waitUntilTextToBe(By locator, String text, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.textToBe(locator, text));
        } catch (TimeoutException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Timeout waiting until the text is the expected : %s ,%s", locator, e));
        }
    }

    /**
     * This method wait that the element is present in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public static boolean waitForElementVisible(final WebElement element) {
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
    public static boolean waitForElementVisible(final By element, int amountOfSecondsToWait) {
        boolean isElementVisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, amountOfSecondsToWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
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
    public static boolean waitForElementVisible(final By element) {
        boolean isElementVisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            isElementVisible = false;
        }
        return isElementVisible;
    }

    /**
     * This method wait that the element is not visible in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public static boolean waitForElementInvisible(final By element) {
        boolean isElementInvisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Const.WAIT_TIME);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            isElementInvisible = false;
        }
        return isElementInvisible;
    }

    /**
     * This method wait that the element is not visible in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public static boolean waitForElementInvisible(final By element, int waitTime) {
        boolean isElementInvisible = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            isElementInvisible = false;
        }
        return isElementInvisible;
    }

    /**
     * This method wait for the element in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public static boolean waitForElementPresent(final By element) {
        boolean isElementPresent = true;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(Const.WAIT_TIME))
                    .ignoring(NoSuchElementException.class);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver d) {
                    setImplicitWait(0);
                    WebElement we = findElement(element);
                    return we;
                }
            });
        } catch (TimeoutException te) {
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait for the element in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    public static boolean waitForElementPresent(final By element, int waitTimeSec) {
        boolean isElementPresent = true;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(waitTimeSec))
                    .ignoring(NoSuchElementException.class);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver d) {
                    setImplicitWait(0);
                    WebElement we = findElement(element);
                    return we;
                }
            });
        } catch (TimeoutException te) {
            isElementPresent = false;
        }
        return isElementPresent;
    }

    public static void waitUntilElementTextChanges(By elementLocator, int waitTimeSec, String text) {
        WebElement current = ActionBot.findElement(elementLocator);
        while ((current.getText() == text) && (waitTimeSec-- > 0)) {
            waitTime(1000);
            current = ActionBot.findElement(elementLocator);
        }
    }

    /**
     * Set an implicity wait
     *
     * @param iw seconds to wait
     */
    public static void setImplicitWait(long iw) {
        driver.manage().timeouts().implicitlyWait(iw, TimeUnit.SECONDS);
    }

    /**
     * This method waits for X amount of milliseconds for continuing the next instruction
     *
     * @param timeToWaitInMilliseconds overall of milliseconds to wait.
     */
    public static void waitTime(int timeToWaitInMilliseconds) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeToWaitInMilliseconds) {
        }
    }

    /**
     * This method wait until the page is loaded
     */
    public static void waitForPageToLoad() {
        driver.manage().timeouts().pageLoadTimeout(Const.WAIT_TIME, TimeUnit.SECONDS);
    }

    /**
     * Wait until ajax call is done
     */
    public static void waitForAjax() {
        /*try {
            Log.info("Wait until ajax call is done");
*//*            JavascriptExecutor executor = (JavascriptExecutor) driver;
            boolean done = false;
            int count = 0;
            while (done || count < 30) {
                done = executor.executeScript("jQuery.active").equals(0);
                waitTime(2000);
                count++;
            }*//*
            new WebDriverWait(driver, 15).until(new ExpectedCondition<Boolean>(){
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return (Boolean) js.executeScript("return jQuery.active == 0");
                }
            });
        } catch (Exception exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s", exc));
        }*/
    }

    //</editor-fold>

    //<editor-fold desc="SendKeys Methods">

    /**
     * Type a text into a field
     *
     * @param locator locator to use
     * @param text    text to type
     */
    public static void sendKeys(By locator, String text) {
        try {
            WebElement wElement = findElement(locator);
            if (wElement != null)
                wElement.sendKeys(text);
            else
                throw new RuntimeException("Can't find the Element, Locator: " + locator.toString());
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        }
    }

    /**
     * Type a text into a field
     *
     * @param wElement element to use
     * @param text     text to type
     */
    public static void sendKeys(WebElement wElement, String text) {
        try {
            scrollToElement(wElement);
            wElement.sendKeys(text);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        }
    }

    /**
     * Type a text into a field
     *
     * @param wElement element to use
     * @param text     text to type
     */
    public static void sendKeysWithClear(WebElement wElement, String text) {
        try {
            wElement.clear();
            wElement.sendKeys(text);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        }
    }

    /**
     * Type a text into a field
     *
     * @param locator locator to use
     * @param text    text to type
     */
    public static void sendKeysWithClear(By locator, String text) {
        try {
            WebElement wElement = driver.findElement(locator);
            wElement.clear();
            ActionBot.waitTime(1000);
            wElement.sendKeys(text);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        }
    }

    /**
     * @param locator
     * @param text
     */
    public static void sendKeysOneByOneWithClear(By locator, String text) {
        try {
            String val = text;
            WebElement wElement = driver.findElement(locator);
            wElement.clear();
            for (int i = 0; i < text.length(); i++) {
                //wElement.sendKeys(text.charAt(i) + "");
                char c = val.charAt(i);
                String s = new StringBuilder().append(c).toString();
                wElement.sendKeys(s);
                ActionBot.waitTime(1000);
            }
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        }
    }

    /**
     * @param wElement
     * @param text
     */
    public static void sendKeysOneByOneWithClear(WebElement wElement, String text) {
        try {
            String val = text;
            for (int i = 0; i < text.length(); i++) {
                //wElement.sendKeys(text.charAt(i) + "");
                char c = val.charAt(i);
                String s = new StringBuilder().append(c).toString();
                wElement.sendKeys(s);
                ActionBot.waitTime(1000);
            }
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, ex));
        }
    }

    /**
     * Type a text in to an element
     *
     * @param wElement web element to use
     * @param key      key to type
     */
    public static void sendKeys(WebElement wElement, Keys key) {
        try {
            //Commented by msiles Selenium 3
            //scrollToElement(wElement);
            //moveToElement(wElement);
            wElement.sendKeys(key);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the element: %s, %s", wElement, ex));
        }
    }

    /**
     * Type a text in to an element
     *
     * @param locator web element to use
     * @param key     key to type
     */
    public static void sendKeys(By locator, Keys key) {
        try {
            driver.findElement(locator).sendKeys(key);
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        }
    }

    /**
     *
     * @param locator
     * @param value
     */
    public static void sendKeysAction(By locator, String value) {
        try {
            WebElement element = findElement(locator);
            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(element);
            scrollToElement(element);
            action.sendKeys(element, value);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", locator, ex));
        }
    }

    /**
     *
     * @param wElement
     * @param value
     */
    public static void sendKeysAction(WebElement wElement, String value) {
        try {
            Actions action = new Actions(driver);
            waitUntilElementIsDisplayed(wElement);
            scrollToElement(wElement);
            action.sendKeys(wElement, value);
            action.perform();
        } catch (ElementNotVisibleException exc) {
            Log.error(exc.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, exc));
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem typing a text in the locator: %s, %s", wElement, ex));
        }
    }

    /**
     * @param dataValue
     * @param element
     */
    public static void editHTMLText(String dataValue, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1]", element, dataValue);
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem etiting the text in the element: %s, %s", element, ex));
        }
    }

    /**
     * This method adds the display none in the html to a specific element
     *
     * @param element element to display none
     */
    public static void addDisplayNoneInHTMLElementThroughJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'display:none')", element);
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There is a problem hidden the element through JS %s", e));
        }
    }

    /**
     * This method adds the display none in the html to a specific element
     *
     * @param locator element to display none
     */
    public static void addDisplayNoneInHTMLElementThroughJS(By locator) {
        try {
            WebElement element = ActionBot.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'display:none')", element);
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There is a problem hidden the element through JS %s", e));
        }
    }

    /**
     * This method adds the display none in the html to a specific element
     *
     * @param element element to display none
     */
    public static void addDisplayBlockInHTMLElementThroughJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'display:block')", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('visibility', 'visible')", element);
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There is a problem hidden the element through JS %s", e));
        }
    }

    /**
     * This method adds the display none in the html to a specific element
     *
     * @param locator element to display none
     */
    public static void addDisplayBlockInHTMLElementThroughJS(By locator) {
        try {
            WebElement element = ActionBot.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'display:block')", element);
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There is a problem hidden the element through JS %s", e));
        }
    }

    /**
     * Set the text to a field
     *
     * @param textValue text to set
     * @param locator   locator to use
     */
    public static void setTextFieldValue(String textValue, By locator) {
        if (textValue != null && !textValue.isEmpty()) {
            Assert.assertTrue(waitForElementVisible(locator), "There is a problem with the element.");
            ActionBot.scrollToElement(locator);
            ActionBot.clickAction(locator);
            ActionBot.sendKeysWithClear(locator, textValue);
        }
    }

    /**
     * Select a dropdown option
     *
     * @param option         option to select
     * @param locator        locator to use
     * @param locatorOptions locator options
     */
    public static void selectDropdownOption(String option, By locator, By locatorOptions) {
        if (option != null && !option.isEmpty()) {
            ActionBot.scrollToElement(locator);
            ActionBot.selectOptionFromInputDropdownByText(locator,
                    locatorOptions, option);
        }
    }

    //</editor-fold>

    //<editor-fold desc="General Methods">

    /**
     * Delete the cookies from the browser.
     */
    public static void deleteAllCookies() {
        try {
            Log.info("Delete Cookies");
            driver.manage().deleteAllCookies();
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem deleting the cookies for the app, %s", ex));
        }
    }

    /**
     * Verify if the element is valid
     *
     * @param locator         locator to use
     * @param shouldBeVisible shouldBeVisible
     * @return Web element
     */
    public static WebElement verifyElement(By locator, Boolean shouldBeVisible) {
        try {
            if (shouldBeVisible) {
                ActionBot.waitForElementVisible(locator);
                Assert.assertNotNull(locator, "Element is not displayed on the Page.");
                scrollToElementForGrid(ActionBot.findElement(locator));
            }
            boolean exists = ActionBot.doesElementExist(locator);
            boolean visible = ActionBot.isElementDisplayed(locator);
            if ((visible && exists) && shouldBeVisible) {
                Assert.assertTrue(exists, "Element does not exist: " + locator.toString());
                Assert.assertTrue(visible, "Element was not visible: " + locator.toString());
                return ActionBot.findElement(locator);
            } else {
                if (shouldBeVisible) {
                    Assert.assertFalse(exists, "Element does exist: " + locator.toString());
                    Assert.assertFalse(visible, "Element was visible: " + locator.toString());
                }
                return null;
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying the element for the locator: %s, %s",
                    locator, e));
        }
    }

    /**
     * Verify elements without wait them.
     *
     * @param locator locator to verify
     * @param mode    mode to use
     * @return Web element
     */
    public static WebElement verifyElementWithoutWait(By locator, Boolean mode) {
        try {
            boolean exists = ActionBot.doesElementExist(locator);
            boolean visible = ActionBot.isElementDisplayed(locator);
            if ((visible && exists) && mode) {
                Assert.assertTrue(exists, "Element does not exist: " + locator.toString());
                Assert.assertTrue(visible, "Element was not visible: " + locator.toString());
                return ActionBot.findElement(locator);
            } else {
                if (mode) {
                    Assert.assertFalse(exists, "Element does exist: " + locator.toString());
                    Assert.assertFalse(visible, "Element was visible: " + locator.toString());
                }
                return null;
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying the element for the locator: %s, %s",
                    locator, e));
        }
    }

    /**
     * Verify if the element is valid
     *
     * @param locator locator to use
     * @param mode    mode
     */
    public static List<WebElement> verifyElements(By locator, Boolean mode) {
        try {
            boolean exists = ActionBot.doesElementExist(locator);
            boolean visible = ActionBot.isElementDisplayed(locator);
            if ((visible && exists) && mode) {
                Assert.assertTrue(exists, "Elements do not exist: " + locator.toString());
                Assert.assertTrue(visible, "Elements were not visible: " + locator.toString());
                return ActionBot.findElements(locator);
            } else {
                if (mode) {
                    Assert.assertFalse(exists, "Elements exist: " + locator.toString());
                    Assert.assertFalse(visible, "Elements were visible: " + locator.toString());
                }
                return null;
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying the element for the locator: %s, %s",
                    locator, e));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Check/Uncheck Methods">

    /**
     * Check for an element
     *
     * @param locator locator to check
     */
    public static void checkElement(By locator) {
        try {
            WebElement checkBox = findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }
            if (!checkBox.isSelected()) {
                clickElement(checkBox);
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem checking the element: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem checking the element: %s, %s", locator, ex));
        }
    }

    /**
     * Check for an element
     *
     * @param checkBox element to check
     */
    public static void checkElement(WebElement checkBox) {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }
            if (!checkBox.isSelected()) {
                clickElement(checkBox);
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem checking the element: %s, %s", checkBox, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem checking the element: %s, %s", checkBox, ex));
        }
    }

    /**
     * Uncheck a check
     *
     * @param locator locator to use
     */
    public static void unCheckElement(By locator) {
        try {
            WebElement checkBox = findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }
            if (checkBox.isSelected()) {
                clickElement(checkBox);
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem un checking the element: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem un checking the element: %s, %s", locator, ex));
        }
    }

    /**
     * Uncheck a check
     *
     * @param checkBox locator to use
     */
    public static void unCheckElement(WebElement checkBox) {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }
            if (checkBox.isSelected()) {
                clickElement(checkBox);
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem un checking the element: %s, %s", checkBox, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem un checking the element: %s, %s", checkBox, ex));
        }
    }

    /**
     * Verify is the element is checked
     *
     * @param locator locator to search
     * @return boolean
     */
    public static boolean isChecked(By locator) {
        try {
            WebElement checkBox = findElement(locator);
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }

            if (checkBox.getAttribute("checked") != null) {
                if (checkBox.getAttribute("checked").equals("checked") || checkBox.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", locator, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", locator, ex));
        } catch (NullPointerException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", locator, ex));
        }

    }

    /**
     * Verify is the element is checked
     *
     * @param checkBox locator to search
     * @return boolean
     */
    public static boolean isChecked(WebElement checkBox) {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                Assert.fail("This elementLocator is not a checkbox!");
            }

            if (checkBox.getAttribute("checked") != null) {
                if (checkBox.getAttribute("checked").equals("checked") || checkBox.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (InvalidElementStateException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", checkBox, ex));
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", checkBox, ex));
        } catch (NullPointerException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There was a problem verifying if the element is checked: %s, %s", checkBox, ex));
        }

    }


    //</editor-fold>

    /**
     * Switch to another frame
     *
     * @param iframe iframe to change
     */
    public static void switchToIFrame(By iframe) {
        driver.switchTo().frame(findElement(iframe));
    }

    /**
     * Switch to the default content
     */
    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Refresh the page
     */
    public static void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (WebDriverException ex) {
            Log.error(ex.getMessage());
            throw new RuntimeException(String.format("There is a problem refreshing the page %s: ", ex));
        }
    }

    /**
     * open the url in the browser
     *
     * @param url page to load
     */
    public static void openWebApp(String url) {
        try {
            driver.get(url);
        } catch (WebDriverException e) {
            throw new RuntimeException(String.format("Had a problem open the URL %s ", e));
        }
    }

    /**
     * Navigate back to a page
     */
    public static void webDriverNavigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Had a problem with the back action %s ", e));
        }
    }

    /**
     * switch to a tab or window
     *
     * @return new tab
     */
    public static WebDriver.TargetLocator switchTo() {
        try {
            return driver.switchTo();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Had a problem with the switch To %s ", e));
        }
    }

    /**
     * switch to a tab or window
     *
     * @return new tab
     */
    public static void switchToNewTab() {
        try {
            String currentWindowHandle = driver.getWindowHandle();

            //Get the list of all window handles
            ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

            for (String window : windowHandles) {

                //if it contains the current window we want to eliminate that from switchTo();
                if (window != currentWindowHandle) {
                    //Now switchTo new Tab.
                    driver.switchTo().window(window);
                }
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("Had a problem with the switch To New Tab %s ", e));
        }
    }

    /**
     * Run JS code in the browser
     *
     * @param script script to execute
     */
    public static void executeJSInBrowser(String script) {
        try {
            ((JavascriptExecutor) driver).executeScript(script);
        } catch (IllegalStateException ex) {
            Log.error(ex.getMessage());
            throw new IllegalStateException(String.format("This driver does not support JavaScript!. %s", ex));
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
            throw new RuntimeException(String.format("There was a problem running the JS: %s, %s", script, e));
        }
    }
}
