package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingTest extends BasePage {

    @FindBy(xpath="/html/body/div[2]/div/div/div[5]/div[2]/div/div/div/div[2]/div[1]/form/button[1]")
    public WebElement result;
}
