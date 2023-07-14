package Page_Object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


    public class HomePage extends BasePage {

        @FindBy(xpath = "//a[@title='Sign Up']")
        public WebElement signUpButton;

        @FindBy(xpath = "//a[@title='Sign In']")
        public WebElement signInButton;

        @FindBy(id = "headerMenuLink")
        public WebElement menu;

        @FindBy(xpath = "//*[@id='menuNavigation']")
        public WebElement menuNavigation;

        @FindBy(css = "#quickSearchLookup")
        public WebElement searchBar;

        @FindBy(xpath = "//*[@id='quickSearch']/div/div/a")
        public WebElement searchButton;

        public HomePage(){
            PageFactory.initElements(driver,this);
        }

        public SignInPage clickSignInButton(){
            isElementVisible(signInButton);
            clickOnElement(signInButton);
            return new SignInPage();
        }

        public SignUpPage clickSignUpButton(){
            isElementVisible(signUpButton);
            clickOnElement(signUpButton);
            return new SignUpPage();

        }




        public MenuDD clickMenu(){
            isElementVisible(menu);
            clickOnElement(menu);
            return new MenuDD();
        }
}
