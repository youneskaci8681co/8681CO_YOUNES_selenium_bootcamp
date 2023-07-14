package test_application;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.ContactsPage;
import page_object.LoginIntro;
import page_object.MainNavigation;
import base.BasePage;

public class TestContactPage extends BasePage {
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testNewContactAddedToContactsPage(String email, String password, String firstName, String lastName, String day, String month, String year) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        ContactsPage contactsPage = main.navigateToContactsPage();
        contactsPage.createNewContact(firstName, lastName, day, month, year);
        clickOnElement(MainNavigation.contactsNavButton);
        String name = firstName + " " + lastName;
        driver.navigate().refresh();
        Assert.assertEquals(ContactsPage.contactsName.getText(),name);
    }


    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "contactsInfo")
    public void testAddingMultipleContact(String email, String password, String firstName, String lastName, String day, String month, String year) {
        LoginIntro loginIntro = new LoginIntro();
        loginIntro.loginToAccount(email, password).navigateToContactsPage()
                .createNewContact(firstName, lastName, day, month, year);
        Assert.assertTrue(isElementVisible(ContactsPage.newlyCreatedContactName));
    }
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testSelectingBirthday(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        loginIntro.loginToAccount(email, password).navigateToContactsPage()
                .createNewContact(firstName, lastName, day, month, year);
        String expBirthdayText = day +" "+ month +" "+ year;
        scrollByVisibleElement(webDriverWait.until(ExpectedConditions.visibilityOf(ContactsPage.actBirthday)));
        String actBirthdayText = ContactsPage.actBirthday.getText();
        Assert.assertTrue(actBirthdayText.contains(expBirthdayText));
    }
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testEditContact(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        loginIntro.loginToAccount(email, password).navigateToContactsPage()
                .createNewContact(firstName, lastName, day, month, year);
        ContactsPage contactsPage = new ContactsPage();
        contactsPage.clickEditContactButton().selectContactCategory();
        contactsPage.clickSaveContactButton();
        String expCategory = "Contact";
        webDriverWait.until((ExpectedConditions.visibilityOf(contactsPage.actCategorySelected)));
        String actCategory = contactsPage.actCategorySelected.getText();
        Assert.assertTrue(actCategory.contains(expCategory));
    }
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testDeleteContact(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation mainNavigation = loginIntro.loginToAccount(email, password);
        ContactsPage contactsPage = mainNavigation.navigateToContactsPage();
        contactsPage.createNewContact(firstName,lastName,day,month,year);
        contactsPage.clickContactPageDeleteButton();
        contactsPage.clickConfirmDeletion();
        String expText = "No records found";
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsPage.noRecordsFoundElement));
        String actText = contactsPage.noRecordsFoundElement.getText();
        Assert.assertTrue(actText.contains(expText));
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testDeletedContactSentToRubbishBin(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation mainNavigation = loginIntro.loginToAccount(email, password);
        ContactsPage contactsPage = mainNavigation.navigateToContactsPage();
        contactsPage.createNewContact(firstName,lastName,day,month,year);
        contactsPage.clickContactPageDeleteButton();
        contactsPage.clickConfirmDeletion();
        clickOnElement(contactsPage.uiDeleteIcon);
        clickOnElement(contactsPage.contactsDeletedButton);
        String expText = firstName+" "+lastName;
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsPage.lastDeletedContact));
        String actText = contactsPage.lastDeletedContact.getText();
        Assert.assertEquals(actText,expText);
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testRestoreContactAfterDeletion(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation mainNavigation = loginIntro.loginToAccount(email, password);
        ContactsPage contactsPage = mainNavigation.navigateToContactsPage();
        contactsPage.createNewContact(firstName,lastName,day,month,year);
        contactsPage.clickContactPageDeleteButton();
        contactsPage.clickConfirmDeletion();
        clickOnElement(contactsPage.uiDeleteIcon);
        clickOnElement(contactsPage.contactsDeletedButton);
        clickOnElement(contactsPage.lastDeletedContactCheckBox);
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsPage.restoreSelectedButton));
        clickOnElement(contactsPage.restoreSelectedButton);
        clickOnElement(contactsPage.confirmRestoreButton);
        clickOnElement(MainNavigation.contactsNavButton);
        String name = firstName+" "+lastName;
        driver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.visibilityOf(ContactsPage.contactsName));
        Assert.assertEquals(ContactsPage.contactsName.getText(),name);
    }
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewContact")
    public void testPurgeTheDeletedContact(String email, String password, String firstName, String lastName, String day, String month, String year){
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation mainNavigation = loginIntro.loginToAccount(email, password);
        ContactsPage contactsPage = mainNavigation.navigateToContactsPage();
        contactsPage.createNewContact(firstName,lastName,day,month,year);
        contactsPage.clickContactPageDeleteButton();
        contactsPage.clickConfirmDeletion();
        clickOnElement(contactsPage.uiDeleteIcon);
        clickOnElement(contactsPage.contactsDeletedButton);
        clickOnElement(contactsPage.lastDeletedContactCheckBox);
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsPage.purgeSelectedButton));
        clickOnElement(contactsPage.purgeSelectedButton);
        clickOnElement(contactsPage.confirmPurgeButton);
        String expText = "No records found";
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsPage.lastDeletedContact));
        String actText = contactsPage.lastDeletedContact.getText();
        Assert.assertEquals(actText,expText);

    }

}
