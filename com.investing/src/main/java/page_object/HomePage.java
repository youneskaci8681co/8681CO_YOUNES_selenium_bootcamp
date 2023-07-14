package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(xpath = "/html/body/div[6]/header/div[1]/div/div[3]/div[1]/input")
    private WebElement searchBar;

    @FindBy ( xpath = "/html/body/div[6]/header/div[1]/div/div[3]/div[1]/label")
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





