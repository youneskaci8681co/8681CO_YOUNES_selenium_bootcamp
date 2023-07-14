package page_object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import base.BasePage;

public class SelectFlight extends BasePage {
    @FindBy(xpath = "//*[@id='uitk-tabs-button-container']/div[1]/li[1]/a")
    public WebElement roundTripButton;

    @FindBy(xpath = "//*[@id='uitk-tabs-button-container']/div[1]/li[2]/a")
    public WebElement oneWayButton;

    @FindBy(xpath = "//*[@id='uitk-tabs-button-container']/div[1]/li[3]/a")
    public WebElement multiCityButton;
    @FindBy(xpath = "//*[@id='location-field-leg1-origin-menu']/div[1]/div[1]/button")
    public WebElement leavingFromButton;

    @FindBy(xpath = "//*[@id='location-field-leg1-origin']")
    public WebElement leavingFromTextBox;

    @FindBy(xpath = "//*[@id='location-field-leg1-origin-menu']/div[2]/div[2]/ul/li")
    public WebElement suggestionsList1;

    @FindBy(xpath = "//*[@id='location-field-leg1-destination-menu']/div[1]/div[1]/button")
    public WebElement goingToButton;

    @FindBy(xpath = "//*[@id='location-field-leg1-destination']")
    public WebElement destinationTextBox;

    @FindBy(xpath = "//*[@id='location-field-leg1-destination-menu']/div[2]/div[2]/ul/li")
    public WebElement suggestionsList2;

    @FindBy(xpath = "//*[@id='location-field-leg1-destination-menu']/div[2]/div[2]/ul/li[1]/button")
    public WebElement firstOption;

    @FindBy(xpath = "//*[@id='d1-btn']")
    public WebElement departButton;

    @FindBy(xpath = "//button[@id='d2-btn']")
    public WebElement returnButton;

    @FindBy(xpath = "//h2[@class='uitk-date-picker-month-name uitk-type-medium']")
    public WebElement datePickerMonthName;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/button[2]")
    public WebElement departDatePickerArrowRight;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/button[1]")
    public WebElement departDatePickerArrowLeft;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div[1]/table/tbody/tr/td/button")
    public List<WebElement> datePickerAllDays;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button[2]")
    public WebElement returnDatePickerArrowRight;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button[1]")
    public WebElement returnDatePickerArrowLeft;

    @FindBy(xpath = "//*[@id='wizard-flight-pwa-1']/div[3]/div[2]/button")
    public WebElement searchFlightButton;

    @FindBy(xpath = "//*[@id='typeahead-destinationInput-0-menu']/div[1]/div[1]/button")
    public WebElement flyingToButton;

    @FindBy(xpath = "//*[@id='d2-btn']")
    public WebElement theSelectedReturnDate;

    @FindBy(xpath = "//*[@id='location-field-leg1-destination-menu']/div[1]/div[1]/button")
    public WebElement selectedDestinationButton;

    @FindBy(xpath = "//*[@id='d1-btn']")
    public WebElement theSelectedDepartDate;

    @FindBy(xpath = "//*[@id='wizard-flight-tab-roundtrip']//button[contains(text(),'Done')]")
    public WebElement datePickerDoneButton;

    public SelectFlight() {
        PageFactory.initElements(driver, this);
    }

    public void clickRoundTripButton() {
        clickOnElement(roundTripButton);
    }

    public void clickOneWayTripButton() {
        clickOnElement(oneWayButton);
    }

    public void clickMultiCityTripButton() {
        clickOnElement(multiCityButton);
    }

    public Boolean searchResultVerification(String expText){
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='app-layer-base']/div[2]/div[3]/div[1]/section/main/ul/li//div[@data-test-id='arrival-departure']"));
        for(WebElement element: list){
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(list));
            if(!element.getText().equals(expText)){
                return false;
            }
        }return true;
    }

    public void clickAndSendKeysLeavingFromButton(String leavingFrom, int suggestionNum) {
        clickOnElement(leavingFromButton);
        sendKeysToElement(leavingFromTextBox, leavingFrom);
        webDriverWait.until(ExpectedConditions.visibilityOf(suggestionsList1));
        chooseDepartingFromSuggestions(suggestionNum);
    }

    private void chooseDepartingFromSuggestions(int suggestion) {
        try {
            WebElement element = driver.findElement(By.xpath("//*[@id='location-field-leg1-origin-menu']" +
                    "/div[2]/div[2]/ul/li[" + suggestion + "]/button"));
            clickOnElement(element);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please try entering a valid location and valid suggestion number.");
        }
    }

    public void clickAndSendKeysGoingToButton(String destination, int suggestionNum) {
        clickOnElement(goingToButton);
        sendKeysToElement(destinationTextBox, destination);
        webDriverWait.until(ExpectedConditions.visibilityOf(suggestionsList2));
        chooseDestinationFromSuggestions(suggestionNum);
    }

    private void chooseDestinationFromSuggestions(int num) {
        try {
            WebElement suggestion = driver.findElement(By.xpath("//*[@id='location-field-leg1-destination-menu']" +
                    "/div[2]/div[2]/ul/li[" + num + "]/button"));
            clickOnElement(suggestion);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please try entering a valid destination and a valid suggestion number.");
        }
    }

    private void clickDate(int day) {
        WebElement date = driver.findElement(By.xpath("//button[@data-day='" + day + "']"));
        clickOnElement(date);
    }

    /*month has to start with capital letter*/
    public void selectDepartingDate(String month, String year, int day) {
        clickOnElement(departButton);
        String departMonth = month + " " + year;
        try {
            while (true) {
                webDriverWait.until(ExpectedConditions.visibilityOf(datePickerMonthName));
                String text = datePickerMonthName.getText();
                if (text.equals(departMonth)) {
                    break;
                } else {
                    if (departMonth.equals("December 24")) {
                        clickOnElement(departDatePickerArrowLeft);
                    } else {
                        clickOnElement(departDatePickerArrowRight);
                    }
                }
            }
            clickDate(day);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please enter a valid month(starts with capital letter), year and day.");
        }
        clickOnElement(datePickerDoneButton);
    }

    //Month has to start with capital letter
    public void selectReturningDate(String month, String year, int day) {
        clickOnElement(returnButton);
        String returnMonth = month + " " + year;
        try {
            while (true) {
                webDriverWait.until(ExpectedConditions.visibilityOf(datePickerMonthName));
                String text = datePickerMonthName.getText();
                if (text.equals(returnMonth)) {
                    break;
                } else {
                    if ("December  2023".equals(returnMonth)) {
                        clickOnElement(returnDatePickerArrowLeft);
                    } else {
                        clickOnElement(returnDatePickerArrowRight);
                    }
                }
            }
            clickDate(day);
        } catch (Exception e) {
            System.out.println("Invalid data!!!Please enter a valid month(starts with capital letter), year and day.");
        }
        clickOnElement(datePickerDoneButton);
    }

    public void clickSearchButton() {
        clickOnElement(searchFlightButton);
    }
}
