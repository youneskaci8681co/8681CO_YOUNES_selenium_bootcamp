package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath ="/html/body/div[2]/div/div/div[2]/div[4]/div/nav/ul/li[3]/button/span")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=zip_code]")
    private WebElement getSearchButton;

    public HomePage() {PageFactory.initElements(driver,this);

    }
}
