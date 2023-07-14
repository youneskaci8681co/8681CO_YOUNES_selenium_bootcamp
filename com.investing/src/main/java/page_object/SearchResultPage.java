package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = " //*[@id=fullColumn]/div/div[2]/div[1]/span")
    public WebElement resultHeader;

    public SearchResultPage(){PageFactory.initElements(driver,this);}





}