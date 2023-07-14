package Page_Object;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenerateData;
public class SignUpPage extends BasePage {
    @FindBy(css = "#signupSignin")
    public WebElement linkSignIn;

    @FindBy(css = "#signupFirstName")
    public WebElement firstName;

    @FindBy(css = "#signupLastName")
    public WebElement lastName;

    @FindBy(css = "#signupEmail")
    public WebElement emailAddress;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    public WebElement checkBoxFrame;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    public WebElement checkBox;

    @FindBy (css = "#signupBtn")
    public WebElement newAccountSignUpButton;

    @FindBy(xpath = "//iframe[contains(@id ,'iFrameResizer')]")
    public WebElement createPassIframe;

    @FindBy(xpath = "//*[@id='newPw']")
    public WebElement newPassTextBox;

    @FindBy(xpath = "//*[@id='NewPasswordConfirm']")
    public WebElement confirmNewPassTxtBox;

    @FindBy(css = "#setNewPassword")
    public WebElement setPassButton;

    @FindBy(css = "#username > button")
    public WebElement username;

    public SignUpPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean navigateToSignInFromSignUpPage() {
        return isElementVisible(linkSignIn);
    }
    public void setFirstName() {
        String fName = GenerateData.firstName();
        clickOnElement(firstName);
        sendKeysToElement(firstName, fName);
    }

    public void setLastName() {
        String lName = GenerateData.lastName();
        clickOnElement(lastName);
        sendKeysToElement(lastName, lName);
    }

    public void setEmailAddress() {
        String emailAdd = GenerateData.email();
        clickOnElement(emailAddress);
        sendKeysToElement(emailAddress, emailAdd);
    }
    public void setAndConfirmNewPass(){
        String password = GenerateData.password();
        sendKeysToElement(newPassTextBox,password);
        sendKeysToElement(confirmNewPassTxtBox,password);
    }
}
