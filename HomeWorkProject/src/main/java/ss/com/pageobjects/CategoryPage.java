package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends MainPageObject {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    private static String SEARCH_CATEGORY_BY_SUBSTRING_TPL = "xpath://a[@class='a_category'][contains(@title,'{SUBSTRING}')]";
    private static String SEARCH_CATEGORIES = "xpath://a[@class='a_category']";
    private static String CATEGORY_PAGE_TITLE = "xpath://a[@class='a14']";

    public void goToSubCategory(String name){
        this.waitForElementAndClick(
                getElementPath(SEARCH_CATEGORY_BY_SUBSTRING_TPL, name),
                "Category Page: cannot find SubCategory " + name);
    }

    public void checkPageTitle(String expectedName){
        String fact = this.waitForElementAndGetAttribute(
                CATEGORY_PAGE_TITLE, "text","Category Page: cannot find page title");
        Assert.assertEquals("Category page: invalid page title", expectedName, fact);
    }

    public String getFirstSubCategory(){
        return getItemByNumber(SEARCH_CATEGORIES, 0);
    }

    public String getSubCategoryByNumber(int i){
        return getItemByNumber(SEARCH_CATEGORIES, i);
    }

    public String getLastSubCategory(){
        return getItemByNumber(SEARCH_CATEGORIES, getFoundedElements(SEARCH_CATEGORIES).size()-1);
    }

}
