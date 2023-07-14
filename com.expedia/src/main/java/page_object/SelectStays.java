package page_object;

import base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
public class SelectStays extends BasePage {
    @FindBy(xpath = "//*[@id='location-field-destination-menu']/div[1]/div[1]/button")
    public WebElement goingToButton;

    @FindBy(xpath = "//*[@id='location-field-destination-menu']/div[1]/div[1]/button/text()")
    public WebElement goingToButtonSearchText;

    @FindBy(xpath = "//*[@id='location-field-destination']")
    public WebElement destinationTextBox;

    @FindBy(xpath = "//*[@id='location-field-destination-menu']/div[2]/div[2]/ul/li")
    public List<WebElement> suggestionsList;

    @FindBy(xpath = "//*[@id='d1-btn']")
    public WebElement checkInButton;

    @FindBy(xpath = "//h2[@class='uitk-date-picker-month-name uitk-type-medium']")
    public WebElement datePickerMonthName;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/button[1]")
    public WebElement datePickerLeftArrow;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/button[2]")
    public WebElement datePickerRightArrow;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/button")
    public WebElement datePickerDoneButton;

    @FindBy(xpath = "//*[@id='d2-btn']")
    public WebElement checkOutButton;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button[1]")
    public WebElement checkOutPickerDateLeftArrow;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button[2]")
    public WebElement CheckOutPickerDateRightArrow;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div/button")
    public WebElement datePickerCheOutDoneButton;

    @FindBy(xpath = "//*[@id='wizard-hotel-pwa-v2-1']/div[3]/div[2]/button")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/button")
    public WebElement selectRoomAndTravlersButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/div/div/div/section/div[1]/div[2]/div/button[1]")
    public WebElement room1RemoveAdultsNumButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/div/div/div/section/div[1]/div[2]/div/button[2]")
    public WebElement room1AddAdultsNumButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/div/div/div/section/div[1]/div[3]/div/button[2]")
    public WebElement room1AddChildrenNumButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/div/div/div/section/div[1]/div[3]/div/button[2]")
    public WebElement room1RemoveChildrenNumButton;

    @FindBy(xpath = "//*[@id='child-age-input-0-0']")
    public WebElement room1ChildAgeButton;

    @FindBy(xpath = "//*[@id='adaptive-menu']/div/div/div/div[2]/button")
    public WebElement room1DoneButton;

    @FindBy(xpath = "//*[@id='app-layer-base']/div/main/div/div/div/div/div[1]/section[2]/div/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/a")
    public WebElement FraserSuitesHarmonieParis;

    @FindBy(xpath = "//*[@id='hotels-destination-menu']/div[1]/div[1]/button")
    public WebElement hotelsListButton;

    public SelectStays() {
        PageFactory.initElements(driver, this);
    }

    public void selectDestination(String destination, int suggestionNum) {
        clickOnElement(goingToButton);
        sendKeysToElement(destinationTextBox, destination);
        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(suggestionsList)));
        chooseDestination(suggestionNum);
    }

    private void chooseDestination(int num) {
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@id='location-field-destination-menu']/div[2]/div[2]/ul/li[" + num + "]/button")));
        try {
            clickOnElement(element);
        }catch (StaleElementReferenceException e){
            clickOnElement(element);
            System.out.println("Invalid data!!!Please try entering a valid location and valid suggestion number.");
        }

    }

    public void selectChildAge(String value){
        WebElement age = driver.findElement(By.xpath("//*[@id='child-age-input-0-0']//option[@value='"+value+"']"));
        clickOnElement(age);
    }

    private void clickDate(int day) {
        WebElement date = driver.findElement(By.xpath("//button[@data-day='" + day + "']"));
        clickOnElement(date);
    }

    public void selectCheckInDate(String month, String year, int day) {
        clickOnElement(checkInButton);
        String checkIngMonth = month + " " + year;
        try {
            while (true) {
                webDriverWait.until(ExpectedConditions.visibilityOf(datePickerMonthName));
                String text = datePickerMonthName.getText();
                if (text.equals(checkIngMonth)) {
                    break;
                } else {
                    if (checkIngMonth.equals("November 2022")) {
                        clickOnElement(datePickerLeftArrow);
                    } else {
                        clickOnElement(datePickerRightArrow);
                    }
                }
            }
            clickDate(day);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please enter valid month(starts with capital),year and day.");
        }
        clickOnElement(datePickerDoneButton);
    }

    public void selectCheckOutDate(String month, String year, int day) {
        clickOnElement(checkOutButton);
        String checkOutMonth = month + " " + year;
        try {
            while (true) {
                webDriverWait.until(ExpectedConditions.visibilityOf(datePickerMonthName));
                String text = datePickerMonthName.getText();
                if (text.equals(checkOutMonth)) {
                    break;
                } else {
                    if (checkOutMonth.equals("December  2023")) {
                        clickOnElement(checkOutPickerDateLeftArrow);
                    } else {
                        clickOnElement(CheckOutPickerDateRightArrow);
                    }
                }
            }
            clickDate(day);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please enter valid month(starts with capital),year and day.");
        }
        clickOnElement(datePickerCheOutDoneButton);
    }

    public void doSearch(){
        isElementVisible(searchButton);
        clickOnElement(searchButton);
    }
}
