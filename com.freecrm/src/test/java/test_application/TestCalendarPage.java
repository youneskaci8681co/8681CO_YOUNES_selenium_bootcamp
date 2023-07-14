package test_application;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.CalendarPage;
import page_object.EventsPage;
import page_object.LoginIntro;
import page_object.MainNavigation;

import java.time.Month;
import base.BasePage;

public class TestCalendarPage extends BasePage {
    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewEvent")
    public void testCreatingNewEvent(String email, String password, String title, String day, String month, String year, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage cal = main.navigateToCalendarPage();
        EventsPage eventsPage = cal.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        eventsPage.chooseEventTime(time);
        eventsPage.clickSaveEventButton();
        Assert.assertTrue(isElementVisible(eventsPage.newlyCreatedEventTitle));
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewEvent")
    public void testDeletingNewEvent(String email, String password, String title, String day, String month, String year, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage cal = main.navigateToCalendarPage();
        EventsPage eventsPage = cal.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        eventsPage.chooseEventTime(time);
        eventsPage.clickSaveEventButton();
        eventsPage.clickDeleteEventButton();
        driver.navigate().refresh();
        eventsPage.navigateToRubbishBin();
        eventsPage.navigateToDeletedEvents();
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.lastDeletedEvent));
        String deletedEventName = eventsPage.lastDeletedEvent.getText();
        Assert.assertEquals(deletedEventName, title);
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewEvent")
    public void testRestoringDeletedEvent(String email, String password, String title, String day, String month, String year, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage cal = main.navigateToCalendarPage();
        EventsPage eventsPage = cal.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        eventsPage.chooseEventTime(time);
        eventsPage.clickSaveEventButton();
        eventsPage.clickDeleteEventButton();
        driver.navigate().refresh();
        eventsPage.navigateToRubbishBin();
        eventsPage.navigateToDeletedEvents();
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.lastDeletedEvent));
        eventsPage.restoreDeletedEvent();
        main.navigateToCalendarPage();
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.scheduledEvent));
        String restoredEventTitle = eventsPage.scheduledEvent.getAttribute("title");
        Assert.assertEquals(restoredEventTitle, title);

    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewEvent")
    public void testPurgingDeletedEvent(String email, String password, String title, String day, String month, String year, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage cal = main.navigateToCalendarPage();
        EventsPage eventsPage = cal.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        eventsPage.chooseEventTime(time);
        eventsPage.clickSaveEventButton();
        eventsPage.clickDeleteEventButton();
        driver.navigate().refresh();
        eventsPage.navigateToRubbishBin();
        eventsPage.navigateToDeletedEvents();
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.lastDeletedEvent));
        eventsPage.deleteForEver();
        String expText = "No records found";
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.lastDeletedEvent));
        String actText = eventsPage.lastDeletedEvent.getText();
        Assert.assertEquals(actText,expText);

    }


    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "NewEvent")
    public void testEditNewEventAfterCreation(String email, String password, String title, String day, String month, String year, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage cal = main.navigateToCalendarPage();
        EventsPage eventsPage = cal.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        eventsPage.chooseEventTime(time);
        eventsPage.clickSaveEventButton();
        eventsPage.clickEditButton();
        eventsPage.selectCategoryFromList(5);
        eventsPage.clickSaveEventButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(eventsPage.theSelectedCategoryField));
        String actSelectedCategory = eventsPage.theSelectedCategoryField.getText();
        String expAddedCategory = "Meeting";
        Assert.assertTrue(actSelectedCategory.contains(expAddedCategory));
    }


    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "DatesData")
    public void testCreatingMultipleEventsFromCalendar(String userName, String password, String month, String year, String day, String event, String title) {
        LoginIntro loginIntro = new LoginIntro();
        switch (event) {
            case "eventN1":
            case "eventN2":
            case "eventN3":
                MainNavigation main = loginIntro.loginToAccount(userName, password);
                CalendarPage calendarPage = main.navigateToCalendarPage();
                EventsPage eventsPage = calendarPage.selectDateFromMonthCalendar(month, year, day);
                eventsPage.setEventTitleText(title);
                eventsPage.clickSaveEventButton();
                if (isElementVisible(EventsPage.eventTitle)) {
                    Assert.assertTrue(true);
                }
                break;

            default:
                Assert.fail();
                break;
        }

    }


    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "EventInfo")
    public void testSelectingDateFromEventPageCal(String email, String password, String title, String day, String month, String year) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage calendarPage = main.navigateToCalendarPage();
        EventsPage eventsPage = calendarPage.clickCreateEventButton();
        eventsPage.setEventTitleText(title);
        eventsPage.clickStartDayField();
        eventsPage.selectStartDate(day, month, year);
        int selectedMonth = Month.valueOf(month.toUpperCase()).getValue();
        String actSelectedDate = eventsPage.startDateInput.getAttribute("value");
        String expDate;
        if (selectedMonth < 10) {
            expDate = day + "/" + "0" + selectedMonth + "/" + year;
        } else {
            expDate = day + "/" + selectedMonth + "/" + year;
        }
        Assert.assertTrue(actSelectedDate.contains(expDate));
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class, dataProvider = "Time")
    public void testSelectingEventTime(String email, String password, String time) {
        LoginIntro loginIntro = new LoginIntro();
        MainNavigation main = loginIntro.loginToAccount(email, password);
        CalendarPage calendarPage = main.navigateToCalendarPage();
        EventsPage eventsPage = calendarPage.clickCreateEventButton();
        eventsPage.clickStartDayField();
        eventsPage.chooseEventTime(time);
        String actSelectedTime = eventsPage.startDateInput.getAttribute("value");
        Assert.assertTrue(actSelectedTime.contains(time));
    }
}
