package test_application;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BasePage;

public class TestHotelStays extends BasePage {
    @Test
    public void testSelectingDestination() {
        SelectStays selectStays = new SelectStays();
        selectStays.selectDestination("Paris", 3);
        String expText = "Paris City Center";
        String desActText = selectStays.goingToButton.getAttribute("aria-label");
        Assert.assertTrue(desActText.contains(expText));
    }

    @Test
    public void testSelectingCheckInDate() {
        SelectStays selectStays = new SelectStays();
        selectStays.selectCheckInDate("December", "2022", 22);
        String actText = selectStays.checkInButton.getAttribute("aria-label");
        Assert.assertTrue(actText.contains("Check-in December 22, 2022"));
    }

    @Test
    public void testSelectingCheckOutDate() {
        SelectStays selectStays = new SelectStays();
        selectStays.selectCheckOutDate("January", "2023", 2);
        String actText = selectStays.checkOutButton.getAttribute("aria-label");
        Assert.assertTrue(actText.contains("Check-out January 2, 2023"));

    }

    @Test
    public void testAddingAndRemovingTravelers() {
        SelectStays selectStays = new SelectStays();
        selectStays.safeClickOnElement(selectStays.selectRoomAndTravlersButton);
        selectStays.safeClickOnElement(selectStays.room1AddAdultsNumButton);
        selectStays.safeClickOnElement(selectStays.room1AddAdultsNumButton);
        selectStays.safeClickOnElement(selectStays.room1RemoveAdultsNumButton);
        selectStays.safeClickOnElement(selectStays.room1RemoveAdultsNumButton);
        selectStays.safeClickOnElement(selectStays.room1DoneButton);
        String expText="1 room, 2 travelers";
        String actText = selectStays.selectRoomAndTravlersButton.getText();
        Assert.assertEquals(actText, expText);
    }

    @Test
    public void testAddingAndRemovingChildren() {
        SelectStays selectStays = new SelectStays();
        selectStays.safeClickOnElement(selectStays.selectRoomAndTravlersButton);
        selectStays.safeClickOnElement(selectStays.room1AddChildrenNumButton);
        selectStays.safeClickOnElement(selectStays.room1AddChildrenNumButton);
        selectStays.safeClickOnElement(selectStays.room1RemoveChildrenNumButton);
        selectStays.safeClickOnElement(selectStays.room1DoneButton);
        Assert.assertTrue(isElementVisible(selectStays.room1ChildAgeButton));
    }

    @Test
    public void testSelectChildAge() {
        SelectStays selectStays = new SelectStays();
        selectStays.safeClickOnElement(selectStays.selectRoomAndTravlersButton);
        selectStays.safeClickOnElement(selectStays.room1AddChildrenNumButton);
        selectStays.selectChildAge("3");
        selectStays.selectChildAge("1");
        selectStays.selectChildAge("14");
        selectStays.selectChildAge("12");
        selectStays.selectChildAge("8");
        selectStays.safeClickOnElement(selectStays.room1DoneButton);
        String expText="1 room, 3 travelers";
        String actText = selectStays.selectRoomAndTravlersButton.getText();
        Assert.assertEquals(actText, expText);
    }

    @Test
    public void testSelectStayHotel(){
        SelectStays selectStays = new SelectStays();
        selectStays.selectDestination("orlando", 3);
        selectStays.selectCheckInDate("December", "2023", 24);
        selectStays.selectCheckOutDate("January", "2024", 3);
        selectStays.clickOnElement(selectStays.searchButton);
        webDriverWait.until(ExpectedConditions.visibilityOf(selectStays.FraserSuitesHarmonieParis));
        Assert.assertTrue(isElementVisible(selectStays.hotelsListButton));
    }
}
