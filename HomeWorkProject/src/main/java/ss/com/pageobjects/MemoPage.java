package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MemoPage extends MainPageObject {

    public MemoPage(WebDriver driver) {
        super(driver);
    }

    private static String MENU_MEMO = "xpath://a[@class='a_menu'][@href='/lv/favorites/']";
    private static String MENU_MEMO_COUNT = "xpath://span[@id='mnu_fav_id']";
    private static String MENU_MAIN_PAGE = "xpath://span[@class='page_header_head']";

    private static String MEMO_PAGE_SIGN = "xpath://a[@class='a9a']";

    private static String SEARCH_AD_BY_SUBSTRING_TPL = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[contains(text(),'{SUBSTRING}')]";
    private static String SEARCH_ADS = "xpath://table/tbody/tr[contains(@id,'tr_')][not(contains(@id,'tr_bnr'))]//*[@class='d1']";
    private static String SEARCH_CHECKBOX_BY_SUBSTRING_TPL = "xpath://table/tbody/tr[contains(@id,'tr_')][child::td/div/a/b[contains(text(),'{SUBSTRING}')]]//input[@type='checkbox']";

    private static String LINK_DELETE_MEMO = "xpath://*[@id='del_selected_a']";

    public void checkPageOpened(){
        this.waitForElementPresence(
                MEMO_PAGE_SIGN, "Ad Page: cannot find page element Memo");
    }

    public void goToMemoPage(){
        this.waitForElementAndClick(MENU_MEMO,"Memo Page: cannot find menu - MEMO");
        checkPageOpened();
    }

    public void goMainPage(){
        this.waitForElementAndClick(MENU_MAIN_PAGE,"Memo Page: cannot return to main page");
    }

    private Integer getMemoElements(){
        WebElement total = this.waitForElementPresence(MENU_MEMO_COUNT,"Memo Page: cannot find count of elements");
        String elements = total.getText().replaceAll("[^\\d]","");
        if (elements.isEmpty()){
           return 0;
        }
        return Integer.valueOf(elements);
    }

    public void clearMemo(){
        if (getMemoElements()>0){
            goToMemoPage();
            List<WebElement>  memos = this.getFoundedElements(SEARCH_ADS);
            for (int i=0; i < memos.size(); i++){
                setCheckBoxForMemo(memos.get(i).getText());
            }
            deleteMemo();
            goMainPage();
        }
    }

    public void checkMemoCounts(Integer expectedCount){
        Assert.assertTrue("Memo Page: count of elements failed", getMemoElements() == expectedCount);
    }

    public void checkPresenceElementInMemo(String name){
        this.waitForElementPresence(
                getExtractedElementPath(SEARCH_AD_BY_SUBSTRING_TPL, name),
                "Memo Page: cannot find element " + name);
    }

    public void setCheckBoxForMemo(String name){
        this.waitForElementAndClick(
                getExtractedElementPath(SEARCH_CHECKBOX_BY_SUBSTRING_TPL, name),
                "Memo Page: cannot find checkbox for element " + name);
    }

    public void deleteMemo(){
        this.waitForElementVisibilityBy(
                LINK_DELETE_MEMO,
                "Memo Page: delete link is still invisible");
        this.waitForElementAndClick(
                LINK_DELETE_MEMO,
                "Memo Page: cannot delete element");
    }

}
