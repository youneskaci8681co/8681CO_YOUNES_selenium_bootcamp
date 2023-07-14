package page_object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import base.BasePage;

public class EventsPage extends BasePage {
    @FindBy(xpath = "//div[contains(text(),'Create new Event')]")
    public static WebElement newEventHeader;

    @FindBy(xpath = "//div[@class ='react-datepicker__input-container']/input[@class = 'calendarField react-datepicker-ignore-onclickoutside']")
    public static WebElement startDate;

    @FindBy(xpath = "//div[@class = 'react-datepicker__current-month']")
    public WebElement startDateCurrentMonth;

    @FindBy(xpath = "//*[@aria-label ='Next Month']")
    public WebElement calendarNextMonthButton;

    @FindBy(xpath = "//*[@aria-label ='Previous Month']")
    public WebElement calendarPreviousMonthButton;

    @FindBy(xpath = "//div[@class = 'ui form segment']/div[5]")
    public WebElement theSelectedCategoryField;

    @FindBy(xpath = "//*[@id='main-content']/div/div[2]/div/div[1]/div/div[1]/div[1]")
    public static WebElement eventTitle;

    @FindBy(xpath = "//*[@class = 'ui header item mb5 light-black']")
    public WebElement newlyCreatedEventTitle;

    @FindBy(xpath = "//a[contains(@href,'/calendar/edit/')]")
    public WebElement editEventButton;

    @FindBy(xpath = "//div[@name = 'category'and @role ='option']")
    public List<WebElement> categoryList;

    @FindBy(xpath = "//div[@name = 'category'and @role ='listbox']")
    public WebElement categoryBox;

    @FindBy(xpath = "//*[@name = 'title']")
    public WebElement eventTitleTextBox;

    @FindBy(xpath = "//button[@class ='ui linkedin button']")
    public WebElement saveNewEventButton;

    @FindBy(xpath = "//div[@class ='react-datepicker__input-container']")
    public WebElement startDateField;

    @FindBy(xpath = "//div[@class ='react-datepicker__input-container']/input")
    public WebElement startDateInput;

    @FindBy(xpath = "//*[@class ='react-datepicker__time-list']/li")
    public List<WebElement> startDayTimeList;

    @FindBy(xpath = "//*[@class ='item view-page-toolbar']/button[3]")
    public WebElement deleteEventButton;

    @FindBy(xpath = "//*[@class ='ui red button']")
    public WebElement redDeleteButton;

    @FindBy(xpath = "//button[@class = 'ui basic button item']")
    public WebElement rubbishBinButton;

    @FindBy(xpath = "//*[@class = 'ui menu']/a[8]")
    public WebElement deletedEventsButton;

    @FindBy(xpath = "//*[@id='main-content']/div/div[2]/div/div[3]/table/tbody/tr[1]/td[2]")
    public WebElement lastDeletedEvent;

    @FindBy(xpath = "//*[@id='main-content']/div/div[2]/div/div[3]/table/tbody/tr[1]/td[1]/div")
    public WebElement lastDeletedEventCheckBox;

    @FindBy(xpath = "//*[@class = 'ui button']")
    public WebElement restoreSelectedButton;

    @FindBy(xpath = "//button[@class = 'ui negative button']")
    public WebElement purgeSelectedButton;

    @FindBy(xpath = "//*[@class = 'ui primary button']")
    public WebElement confirmRestoreButton;

    @FindBy(xpath = "//button[@class ='ui primary button']")
    public WebElement confirmPurgeButton;

    @FindBy(xpath = "//div[@class = 'rbc-event']/div")
    public WebElement scheduledEvent;


    public EventsPage() {
        PageFactory.initElements(driver, this);
    }


    public void setEventTitleText(String title) {
        sendKeysToElement(eventTitleTextBox, title);
    }

    public void clickStartDayField() {
        clickOnElement(startDateField);
    }

    public void clickSaveEventButton() {
        clickOnElement(saveNewEventButton);
    }

    public void selectStartDate(String day, String month, String year) {
        WebElement ele;
        String chosenMonth = month + " " + year;
        while (true) {
            if (chosenMonth.equalsIgnoreCase(startDateCurrentMonth.getText())) {
                break;
            } else {
                LocalDate date = LocalDate.of(Integer.parseInt(year), Month.valueOf(month.toUpperCase()), Integer.parseInt(day));
                LocalDate todayDate = LocalDate.now();
                if (date.isBefore(todayDate)) {
                    clickOnElement(calendarPreviousMonthButton);
                } else {
                    clickOnElement(calendarNextMonthButton);
                }
            }
        }
        selectDayFromCalendar(Integer.parseInt(day));
    }

    private void selectDayFromCalendar(int day) {
        int i = 1;
        while (i > 0 && i <= 5) {
            int j = 1;
            while (j > 0 && j <= 7) {
                WebElement ele = driver.findElement(By.xpath("//div[@class ='react-datepicker__week'][" + i + "]//div[" + j + "]"));
                webDriverWait.until(ExpectedConditions.visibilityOf(ele));
                int calendarValue = Integer.parseInt(ele.getText());
                if (day == calendarValue) {
                    clickOnElement(ele);
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
    }

    public void chooseEventTime(String time) {
        String[] timeArray = time.split(":");
        int hour = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        switch (minutes) {
            case 15:
                hour = (hour * 4) + 1;
                break;
            case 30:
                hour = (hour * 4) + 2;
                break;
            case 45:
                hour = (hour * 4) + 3;
                break;
            default:
                hour = hour * 4;
        }
        WebElement ele = startDayTimeList.get(hour);
        webDriverWait.until(ExpectedConditions.visibilityOf(ele));
        clickOnElement(ele);
    }

    public void clickEditButton() {
        clickOnElement(editEventButton);
    }

    public void selectCategoryFromList(int number) {
        clickOnElement(categoryBox);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(categoryList));
        chooseCategory(number);
    }

    private void chooseCategory(int num) {
        WebElement ele;
        num = num - 1;
        if (num == 0 || num > categoryList.size()) {
            System.out.println("Invalid category!!!");
        } else {
            ele = categoryList.get(num);
            clickOnElement(ele);
        }
    }

    public void clickDeleteEventButton() {
        clickOnElement(deleteEventButton);
        webDriverWait.until(ExpectedConditions.visibilityOf(redDeleteButton));
        clickOnElement(redDeleteButton);
    }

    public void navigateToRubbishBin() {
        clickOnElement(rubbishBinButton);
    }

    public void navigateToDeletedEvents() {
        clickOnElement(deletedEventsButton);
    }

    private void checkElementsToBeRestoredOrPurged() {
        clickOnElement(lastDeletedEventCheckBox);
    }

    private void clickRestoreSelectedButton() {
        fluentWait.until(ExpectedConditions.visibilityOf(restoreSelectedButton));
        clickOnElement(restoreSelectedButton);
        clickOnElement(confirmRestoreButton);
    }

    public void restoreDeletedEvent(){
        checkElementsToBeRestoredOrPurged();
        clickRestoreSelectedButton();
    }
    private void clickPurgeSelectedButton(){
        fluentWait.until(ExpectedConditions.visibilityOf(purgeSelectedButton));
        clickOnElement(purgeSelectedButton);
        clickOnElement(confirmPurgeButton);
    }

    public void deleteForEver(){
        checkElementsToBeRestoredOrPurged();
        clickPurgeSelectedButton();
    }


}
