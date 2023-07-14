package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {


    @FindBy(xpath = "//*[@id='gh-ac']")
    private WebElement searchBar;

    @FindBy(xpath = "//*[@id='gh-btn']")
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
