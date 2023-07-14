package Page_Object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


    public class SearchResultPage extends BasePage {

        @FindBy(xpath = "//*[@id=newListingCount]")
        public WebElement resultHeader;
        public SearchResultPage(){
            PageFactory.initElements(driver,this);
        }

        public boolean isHeaderVisible(){
            return isElementVisible(resultHeader);
        }
}
