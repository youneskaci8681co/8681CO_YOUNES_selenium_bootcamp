package BasePage;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


    public class HomePage extends BasePage {

        @FindBy(xpath = "//*[@id=quickSearchLookup]")
        private WebElement searchBar;

        @FindBy(xpath = "//*[@id=quickSearch]/div/div/a/span")
        private WebElement searchButton;



        public HomePage(){
            PageFactory.initElements(driver,this);
        }

        public SearchResultPage searchProduct(String str){
            sendKeysToElement(searchBar,str);
            clickOnElement(searchButton);
            return new SearchResultPage();
        }
}
