package test_app;

import base.BasePage;
import com.google.common.base.Verify;
import config.BaseConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page_object.SignInPage;
import page_object.SignUpPage;
import utils.ExcelData;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestAuthentication extends BasePage {


    @Test
    public void testNavigationSignUpPage() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.clickSignUpButton();
        Assert.assertTrue(signUpPage.navigateToSignInFromSignUpPage());

    }

    @Test
    public void testVerifyTextEditableSignUpPage() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.clickSignUpButton();
        signUpPage.setFirstName();
        signUpPage.setLastName();
        signUpPage.setEmailAddress();
        Verify.verifyNotNull(signUpPage.emailAddress);
        Verify.verifyNotNull(signUpPage.firstName);
        Verify.verifyNotNull(signUpPage.lastName);
        signUpPage.switchToFrameByElement(signUpPage.checkBoxFrame);
        signUpPage.clickOnElement(signUpPage.checkBox);
        signUpPage.switchToParentFrame();
        signUpPage.clickOnElement(signUpPage.newAccountSignUpButton);
        //switch to frame
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(signUpPage.createPassIframe));
        signUpPage.setAndConfirmNewPass();
        signUpPage.clickOnElement(signUpPage.setPassButton);
        Assert.assertTrue(isElementVisible(signUpPage.username));
    }




    @Test
    public void testNavigationToSignInPage() {
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickSignInButton();
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(signInPage.frame));
        Assert.assertTrue(signInPage.navigateToSignInPage());
    }
    @DataProvider(name = "data")
    public String[][] getData() {
        ExcelData myData = new ExcelData(DATA_PATH);
        String[][] data = myData.readStringArrays("Sheet1");
        return data;
    }
    // positive and negative testing
    @Test(dataProvider = "data")
    public void testSignIn(String emailAdd, String pwd, String exp) {
        HomePage homePage = new HomePage();
        SignInPage signInPage = homePage.clickSignInButton();
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(signInPage.frame));
        clearSendKeysToElement(signInPage.emailAddress,emailAdd);
        clearSendKeysToElement(signInPage.password,pwd);
        clickOnElement(signInPage.signInButton);
        switchToParentFrame();
        if (exp.equals("Valid")) {
            if (isElementVisible(signInPage.username)) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        } else if (exp.equals("Invalid")) {
            if (isElementVisible(signInPage.username)) {
                Assert.fail();
            } else {
                Assert.assertTrue(true);
            }
        }
    }


}