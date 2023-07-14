package page_object;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import base.BasePage;

public class SoccerPage extends BasePage {
    @FindBy(xpath = "//div[@class ='global-nav-container']/ul/li/a")
    public List<WebElement> soccerNavList;

    @FindBy(css = ".first-group")
    public WebElement soccerSubMenu;


    public SoccerPage() {
        PageFactory.initElements(driver, this);
    }

    public SoccerSubPage clickSoccerSubOption(String index){
        WebElement ele;
        int optionNum = Integer.parseInt(index);
        if(optionNum < 0 || optionNum >= soccerNavList.size()){
            System.out.println("Invalid index!!!");
        }else{
            try {
                webDriverWait.until(ExpectedConditions.visibilityOfAllElements(soccerNavList));
                ele = soccerNavList.get(optionNum);
                clickOnElement(ele);
            }catch (StaleElementReferenceException e) {
                webDriverWait.until(ExpectedConditions.visibilityOfAllElements(soccerNavList));
                ele = soccerNavList.get(optionNum);
                clickOnElement(ele);
            }
        }
        return new SoccerSubPage();
    }
}
