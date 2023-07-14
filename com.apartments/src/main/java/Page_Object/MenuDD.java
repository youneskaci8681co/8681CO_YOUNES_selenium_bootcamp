package Page_Object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


    public class MenuDD extends BasePage {

        @FindBy(xpath = "//a[contains(text(),'Favorites')]")
        public  WebElement toolsFavSubMenu;

        @FindBy(xpath = "//*[@id='menuNavItemContainer1']/ul")
        public WebElement aptProp;

        @FindBy(xpath = "//*[@id='menuNavItemContainer2']/ul")
        public WebElement propForRent;

        @FindBy(xpath = "//*[@id='menuNavItemContainer3']/ul")
        public WebElement condosSubProp;

        @FindBy(xpath = "//*[@id='menuNavItemContainer4']/ul")
        public WebElement townHomesSubProp;

        public MenuDD(){
            PageFactory.initElements(driver,this);
        }
}
