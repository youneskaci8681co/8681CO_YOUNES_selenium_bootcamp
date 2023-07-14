package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//*[@id='mainContent']/div[1]/div/div[2]/div[1]/div[1]/h1")
    public WebElement resultHeader;
    public SearchResultPage(){
        PageFactory.initElements(driver,this);
    }

    public boolean isHeaderVisible(){
        return isElementVisible(resultHeader);
    }
}
