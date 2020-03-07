package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AdPage extends MainPageObject {

    public AdPage(WebDriver driver) {
        super(driver);
    }

    private static String LINK_ADD_TO_MEMO = "xpath://a[@id='a_fav']";
    private static String CONFIRM_ADD_TO_MEMO = "xpath://a[@id='alert_ok']";
    private static String AD_PAGE_TITLE = "xpath://h2[@class='headtitle']";

    public void addToMemo(){
        this.waitForElementAndClick(
                LINK_ADD_TO_MEMO,
                "Ad Page: cannot click Pievienot Memo");
        confirmAddToMemo();
    }

    public void confirmAddToMemo(){
        this.waitForElementAndClick(
                CONFIRM_ADD_TO_MEMO,
                "Ad Page: cannot confirm Pievienot Memo");
    }

    public void checkPageTitle(String expectedSubCategory, String expectedItem){
        String fact = this.waitForElementAndGetText(
                AD_PAGE_TITLE, "Ad Page: cannot find page title");
        Assert.assertTrue(
                "Ad page: invalid page title",
                fact.contains(expectedSubCategory + " / " + expectedItem));
    }

}
