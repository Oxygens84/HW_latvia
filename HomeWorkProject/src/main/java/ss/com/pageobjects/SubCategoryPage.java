package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class SubCategoryPage extends MainPageObject {

    public SubCategoryPage(WebDriver driver) {
        super(driver);
    }

    private static String SEARCH_ITEM_BY_SUBSTRING_TPL = "xpath://a[contains(@class,'a_category')][contains(@title,'{SUBSTRING}')]";
    private static String SEARCH_ITEMS = "xpath://a[contains(@class,'a_category')]";
    private static String ITEM_PAGE_TITLE = "xpath://a[@class='a14']";

    public void goToElement(String name){
        this.waitForElementAndClick(
                getElementPath(SEARCH_ITEM_BY_SUBSTRING_TPL, name),
                "Items Page: cannot find Item " + name);
    }

    public void checkPageTitle(String expectedName){
        String fact = this.waitForElementAndGetAttribute(
                ITEM_PAGE_TITLE, "text","Item Page: cannot find page title");
        Assert.assertEquals("Item page: invalid page title", expectedName, fact);
    }

    public String getFirstItem(){
        return getItemByNumber(SEARCH_ITEMS,0);
    }

    public String getItemByNumber(int i){
        return getItemByNumber(SEARCH_ITEMS,i);
    }

    public String getLastItem(){
        return getItemByNumber(SEARCH_ITEMS,getFoundedElements(SEARCH_ITEMS).size()-1);
    }

}
