package test_searchbar_fonctionality;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.SearchResultPage;

public class TestSearchBar extends BasePage {
    @Test
    public void TestSearchItem() {
        HomePage hp = new HomePage();
        SearchResultPage searchResultPage = hp.searchProduct("gift card");
        Assert.assertTrue(searchResultPage.isHeaderVisible());
    }
}