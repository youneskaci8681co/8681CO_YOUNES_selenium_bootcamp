package test_searchbar_functionality;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.SearchResultPage;

public class TestSearchBar extends BasePage {

    @Test
    public void TestSearchItem() {
        HomePage hp = new HomePage();
        SearchResultPage searchResultPage = hp.searchProduct("tesla");
        Assert.assertTrue(searchResultPage.);
    }
}