package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AdsPage extends MainPageObject {

    public AdsPage(WebDriver driver) {
        super(driver);
    }

    private static String SEARCH_AD_BY_SUBSTRING_TPL = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[@class='d1']//b[(contains(text(),'{SUBSTRING}'))]";
    private static String SEARCH_ADS = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[@class='d1']//b";
    private static String AD_PAGE_TITLE = "xpath://h2[@class='headtitle']";

    public void goToElement(String name){
        this.waitForElementAndClick(
                getExtractedElementPath(SEARCH_AD_BY_SUBSTRING_TPL, name),
                "Ads Page: cannot find Item " + name);
    }

    public void checkPageTitle(String expectedSubCategory, String expectedItem){
        String fact = this.waitForElementAndGetText(
                AD_PAGE_TITLE, "Ads Page: cannot find page title");
        Assert.assertEquals(
                "Ads page: invalid page title",
                expectedSubCategory + " / " + expectedItem,
                fact);
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
