package page_object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = " //*[@id=gnav20-search-icon]")
    private WebElement searchBar;

}
