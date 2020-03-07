package main.java.ss.com.tests;

import main.java.ss.com.lib.ParentTest;
import org.junit.Test;

public class AddToMemoTest extends ParentTest {

    @Test
    public void testAddOneMemoFromOneCategory() {

        String categoryName = menuPage.getFirstCategory();

        menuPage.goToCategory(categoryName);
        categoryPage.checkPageTitle(categoryName);

        String subCategoryName = categoryPage.getFirstSubCategory();

        categoryPage.goToSubCategory(subCategoryName);
        subCategoryPage.checkPageTitle(subCategoryName);

        String item = subCategoryPage.getFirstItem();

        subCategoryPage.goToElement(item);
        adsPage.checkPageTitle(subCategoryName, item);

        String ad = adsPage.getFirstItem();

        adsPage.goToElement(ad);
        adPage.checkPageTitle(subCategoryName, item);

        adPage.addToMemo();
        memoPage.checkMemoCounts(1);
        memoPage.goToMemoPage();
        memoPage.checkPresenceElementInMemo(ad);
        memoPage.goMainPage();
    }

    @Test
    public void testAddFewMemoFromOneCategory() {

        for (int i=1; i<3; i++) {

            String categoryName = menuPage.getFirstCategory();

            menuPage.goToCategory(categoryName);
            categoryPage.checkPageTitle(categoryName);

            String subCategoryName = categoryPage.getFirstSubCategory();

            categoryPage.goToSubCategory(subCategoryName);
            subCategoryPage.checkPageTitle(subCategoryName);

            String item = subCategoryPage.getLastItem();

            subCategoryPage.goToElement(item);
            adsPage.checkPageTitle(subCategoryName, item);

            String ad = adsPage.getItemByNumber(i);

            adsPage.goToElement(ad);
            adPage.checkPageTitle(subCategoryName, item);

            adPage.addToMemo();
            //BUG 1 to explore? sometimes Added memo went to Recently watched while going to Memo
            //BUG 1 to explore? countMemo=1
            memoPage.checkMemoCounts(i);
            memoPage.goToMemoPage();
            //BUG 1 to explore? countMemo=0
            memoPage.checkPresenceElementInMemo(ad);
            memoPage.goMainPage();
        }
    }

    @Test
    public void testAddFewMemoFromFewCategories() {

        for (int i=0; i<2; i++) {

            String categoryName = menuPage.getCategoryByNumber(i);

            menuPage.goToCategory(categoryName);
            categoryPage.checkPageTitle(categoryName);

            String subCategoryName = categoryPage.getFirstSubCategory();

            categoryPage.goToSubCategory(subCategoryName);
            subCategoryPage.checkPageTitle(subCategoryName);

            String item = subCategoryPage.getFirstItem();

            subCategoryPage.goToElement(item);
            adsPage.checkPageTitle(subCategoryName, item);

            String ad = adsPage.getFirstItem();

            adsPage.goToElement(ad);
            adPage.checkPageTitle(subCategoryName, item);

            adPage.addToMemo();
            //BUG 1 to explore? sometimes Added memo went to Recently watched while going to Memo
            //BUG 1 to explore? countMemo=1
            memoPage.checkMemoCounts(i+1);
            memoPage.goToMemoPage();
            //BUG 1 to explore? countMemo=0
            memoPage.checkPresenceElementInMemo(ad);
            memoPage.goMainPage();
        }
    }

    @Test
    public void testAddOneMemoTwice() {

        for (int i=0; i<2; i++) {

            String categoryName = menuPage.getFirstCategory();

            menuPage.goToCategory(categoryName);
            categoryPage.checkPageTitle(categoryName);

            String subCategoryName = categoryPage.getFirstSubCategory();

            categoryPage.goToSubCategory(subCategoryName);
            subCategoryPage.checkPageTitle(subCategoryName);

            String item = subCategoryPage.getFirstItem();

            subCategoryPage.goToElement(item);
            adsPage.checkPageTitle(subCategoryName, item);

            String ad = adsPage.getFirstItem();

            adsPage.goToElement(ad);
            adPage.checkPageTitle(subCategoryName, item);

            adPage.addToMemo();
            memoPage.checkMemoCounts(1);
            memoPage.goToMemoPage();
            memoPage.checkPresenceElementInMemo(ad);
            memoPage.goMainPage();
        }
    }

    @Test
    public void testAddOneMemoTwiceWithDeletion() {

        for (int i=0; i<2; i++) {

            String categoryName = menuPage.getFirstCategory();

            menuPage.goToCategory(categoryName);
            categoryPage.checkPageTitle(categoryName);

            String subCategoryName = categoryPage.getFirstSubCategory();

            categoryPage.goToSubCategory(subCategoryName);
            subCategoryPage.checkPageTitle(subCategoryName);

            String item = subCategoryPage.getFirstItem();

            subCategoryPage.goToElement(item);
            adsPage.checkPageTitle(subCategoryName, item);

            String ad = adsPage.getFirstItem();

            adsPage.goToElement(ad);
            adPage.checkPageTitle(subCategoryName, item);

            adPage.addToMemo();
            memoPage.checkMemoCounts(1);
            memoPage.goToMemoPage();
            memoPage.checkPresenceElementInMemo(ad);
            memoPage.goMainPage();
            memoPage.clearMemo();
            memoPage.checkMemoCounts(0);
        }
    }

    @Test
    public void testAddOnePageMemosFromSearch() {

        searchPage.goToSearch();
        searchPage.searchAdsByText("auto");

        int elementsFounded = searchPage.getPageSearchResult();

        searchPage.setSearchCheckboxForAllPage();
        searchPage.addSelectedAddToMemo();
        memoPage.checkMemoCounts(elementsFounded);

    }

}