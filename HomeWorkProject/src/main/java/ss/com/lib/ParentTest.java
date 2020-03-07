package main.java.ss.com.lib;


import main.java.ss.com.pageobjects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ParentTest{

    protected WebDriver driver = new ChromeDriver();

    public CategoriesMenuPage menuPage = new CategoriesMenuPage(driver);
    public CategoryPage categoryPage = new CategoryPage(driver);
    public SubCategoryPage subCategoryPage = new SubCategoryPage(driver);
    public AdsPage adsPage = new AdsPage(driver);
    public AdPage adPage = new AdPage(driver);
    public MemoPage memoPage = new MemoPage(driver);
    public SearchPage searchPage = new SearchPage(driver);

    @Before
    public void before() {
        driver.get("https://www.ss.com");
        memoPage.clearMemo();
    }


    @After
    public void finish() {
        //memoPage.clearMemo();
        driver.quit();
    }

}