package Page_Object;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utils.ExcelData;
import base.BasePage;

public class SignInPage extends BasePage {

    @FindBy(linkText = "//a[text() ='create an account']")
    public WebElement createAccountLink;

    @FindBy(css = "#username")
    public WebElement emailAddress;

    @FindBy(css = "#password")
    public WebElement password;

    @FindBy(xpath = "//iframe[@style='height: 598px; overflow: hidden;']")
    public WebElement frame;
    @FindBy(css = "#loginButton")
    public WebElement signInButton;

    @FindBy(css = "#username > button")
    public WebElement username;

    @FindBy(css = "#headerMyAccount")
    public WebElement myAccountButton;
    @FindBy(css = "#headerSignOut")
    public WebElement logOutButton;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean navigateToSignInPage() {
        return isElementVisible(signInButton);
    }

    public void clickLogOutButton() {
        clickOnElement(logOutButton);
    }

    public void navigateToMyAccount() {
        clickOnElement(myAccountButton);
    }
}
