package main.java.ss.com.pageobjects;

import main.java.ss.com.lib.MainPageObject;
import org.openqa.selenium.WebDriver;

public class CategoriesMenuPage extends MainPageObject {

    public CategoriesMenuPage(WebDriver driver) {
        super(driver);
    }

    private static String SEARCH_CATEGORY_HEAD_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'main_head2')]//a[contains(@title,'{SUBSTRING}')]";
    private static String SEARCH_CATEGORIES = "xpath://div[@class = 'main_head2']";

    public void goToCategory(String name){
        this.waitForElementAndClick(
                getElementPath(SEARCH_CATEGORY_HEAD_BY_SUBSTRING_TPL, name),
                "Categories Page: cannot find Category " + name);
    }

    public String getFirstCategory(){
        return getItemByNumber(SEARCH_CATEGORIES,0);
    }

    public String getCategoryByNumber(int i){
        return getItemByNumber(SEARCH_CATEGORIES,i);
    }

    public String getLastCategory(){
        return getItemByNumber(SEARCH_CATEGORIES, getFoundedElements(SEARCH_CATEGORIES).size()-1);
    }

}
