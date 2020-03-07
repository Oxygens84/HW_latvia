package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends MainPageObject {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private static String MENU_SEARCH = "xpath://a[@class='a_menu'][@href='/lv/search/']";

    private static String SEARCH_AD_BY_SUBSTRING_TPL = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[@class='d1']/a[(contains(text(),'{SUBSTRING}'))]";
    private static String SEARCH_ADS = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[@class='d1']/a";
    private static String SEARCH_CHECKBOX_BY_SUBSTRING_TPL = "xpath://table/tbody/tr[contains(@id,'tr_')][child::td/div/a/b[contains(text(),'{SUBSTRING}')]]//input[@type='checkbox']";
    private static String SEARCH_CHECKBOXS = "xpath://table/tbody/tr[contains(@id,'tr_')]//input[@type='checkbox']";

    private static String LINK_ADD_TO_MEMO = "xpath://a[@id='a_fav_sel']";
    private static String CONFIRM_ADD_TO_MEMO = "xpath://a[@id='alert_ok']";

    private static String SEARCH_FIELD = "xpath://input[@id='ptxt']";
    private static String SEARCH_SUBMIT = "xpath://input[@id='sbtn']";

    public void checkPageOpened(){
        this.waitForElementPresence(
                SEARCH_SUBMIT, "Search Page: cannot find page element Search submit");
    }

    public void goToSearch(){
        this.waitForElementAndClick(MENU_SEARCH,"Search Page: cannot find menu - Search");
        checkPageOpened();
    }

    public void setSearchCheckboxForAllPage(){
        List<WebElement> checkboxs = this.getFoundedElements(SEARCH_CHECKBOXS);
        for (WebElement each: checkboxs){
            each.click();
        }
    }

    public int getPageSearchResult(){
        return this.getFoundedElements(SEARCH_CHECKBOXS).size();
    }

    public void addSelectedAddToMemo(){
        this.waitForElementAndClick(
                LINK_ADD_TO_MEMO,
                "Search Page: cannot click Pievienot Memo");
        confirmAddToMemo();
    }

    public void confirmAddToMemo(){
        this.waitForElementAndClick(
                CONFIRM_ADD_TO_MEMO,
                "Search Page: cannot confirm Pievienot Memo");
    }

    public void goToElement(String name){
        this.waitForElementAndClick(
                getExtractedElementPath(SEARCH_AD_BY_SUBSTRING_TPL, name),
                "Search Page: cannot find Item " + name);
    }

    public void searchAdsByText(String text){
        this
                .waitForElementAndSetText(SEARCH_FIELD, text, "Search Page: cannot set text for search")
                .sendKeys(Keys.ENTER);
    }

    public void setCheckBoxForAd(String name){
        this.waitForElementAndClick(
                getExtractedElementPath(SEARCH_CHECKBOX_BY_SUBSTRING_TPL, name),
                "Search Page: cannot find checkbox for element " + name);
    }

    public String getFirstItem(){
        return getItemByNumber(SEARCH_ADS,0);
    }

    public String getItemByNumber(int i){
        return getItemByNumber(SEARCH_ADS,i);
    }

    public String getLastItem(){
        return getItemByNumber(SEARCH_ADS,getFoundedElements(SEARCH_ADS).size()-1);
    }

}
