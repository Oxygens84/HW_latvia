package main.java.ss.com.lib;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected WebDriver driver;
    private int defaultTimeout = 50;

    public MainPageObject(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresence(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresenceBy(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public Boolean isTextPresentedInElement(String locator, String text, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(by, text)
        );
    }


    public Boolean waitForElementInvisibilityBy(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementVisibilityBy(String locator, String errorMessage){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresence(String locator, String errorMessage) {
        return waitForElementPresence(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public List<WebElement> waitForElementsPresenceBy(String locator, String errorMessage) {
        return waitForElementsPresenceBy(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public Boolean waitForElementInvisibilityBy(String locator, String errorMessage){
        return waitForElementInvisibilityBy(locator, errorMessage + "\nFounded elements " + locator, defaultTimeout);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage) {
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSetText(String locator, String text, String errorMessage){
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.sendKeys(text);
        return element;
    }

    public String waitForElementAndGetText(String locator, String errorMessage){
        WebElement element = waitForElementPresence(locator, errorMessage);
        return element.getText();
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage){
        WebElement element = waitForElementPresence(locator, errorMessage);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage) {
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.clear();
        return element;
    }

    public String getTextFrom(WebElement element){
        return element.getAttribute("text");
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int getAmountOfFoundedElements(String locator){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.withMessage("Cannot find elements for " + locator + " \n");
        try {
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
            return driver.findElements(by).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<WebElement> getFoundedElements(String locator){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.withMessage("Cannot find elements for " + locator + " \n");
        try {
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
            return driver.findElements(by);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void assertElementPresent(By by, String attribute, String errorMessage){
        try {
            Assert.assertTrue(errorMessage + "\n Cannot find an attribute value for " + attribute, !driver.findElement(by).getAttribute(attribute).isEmpty());
        } catch (Throwable t) {
            System.out.println(errorMessage + "\n" + t);
            Assert.fail();
        }
    }

    private By getLocatorByString(String locator_with_type){
        String[] given_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String type = given_locator[0];
        String locator = given_locator[1];
        if (type.equals("xpath")) {
            return By.xpath(locator);
        } else if (type.equals("id")) {
            return By.id(locator);
        } else if (type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public Boolean isElementPresence(String locator){
        return this.getAmountOfFoundedElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int attepmts){
        int current_attempt = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try{
                this.waitForElementAndClick(locator, errorMessage);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempt > attepmts) {
                    this.waitForElementAndClick(locator, errorMessage);
                }
            }
            ++current_attempt;
        }
    }

    public static String getElementPath(String template, String element){
        return template.replace("{SUBSTRING}", element);
    }

    public static String getExtractedElementPath(String template, String element){
        String extract = element;
        if (element.contains(" ")){
            extract = element.substring(0, element.indexOf(' '));
            return template.replace("{SUBSTRING}", extract);
        }
        return template.replace("{SUBSTRING}", extract);
    }


    public String getItemByNumber(String locator, int i){
        List<WebElement> items = getFoundedElements(locator);
        if (items.size() > i) {
            return items.get(i).getText();
        }
        Assert.fail("Error: no items found for locator: " + locator);
        return "test failed";
    }

}
