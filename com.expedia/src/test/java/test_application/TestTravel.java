package test_application;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BasePage;

public class TestTravel extends BasePage {
    @Test
    public void testNavigationToSelectFlight(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        Assert.assertTrue(isElementVisible(selectFlight.roundTripButton));
    }
    @Test
    public void testSelectRoundTripFlightType(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.clickRoundTripButton();
        String text = selectFlight.roundTripButton.getAttribute("aria-selected");
        Assert.assertTrue(text.contains("true"));
    }
    @Test
    public void testSelectOneWayFlightType(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.clickOneWayTripButton();
        String text = selectFlight.oneWayButton.getAttribute("aria-selected");
        Assert.assertTrue(text.contains("true"));
    }
    @Test
    public void testSelectMultiCityFlightType(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.clickMultiCityTripButton();
        String text = selectFlight.multiCityButton.getAttribute("aria-selected");
        Assert.assertTrue(text.contains("true"));
    }
    @Test
    public void testSelectDepartingLocation(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.clickAndSendKeysLeavingFromButton("den",2);
        String actText = selectFlight.leavingFromButton.getAttribute("aria-label");
        Assert.assertTrue(actText.contains("Denver (DEN - Denver Intl.)"));
    }

    @Test
    public void testSelectDestination(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.clickAndSendKeysGoingToButton("par",2);
        String actText = selectFlight.goingToButton.getAttribute("aria-label");
        Assert.assertTrue(actText.contains("Paris (ORY - Orly)"));
    }

    @Test
    public void testSelectDepartingDate(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.selectDepartingDate("December","2023",24);
        String text = "Dec 29";
        Assert.assertEquals(selectFlight.theSelectedDepartDate.getText(),text);
    }
    @Test
    public void testSelectReturningDate(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight = homePage.clickFlightPage();
        selectFlight.selectReturningDate("January","2024",10);
        String text = "Jan 10";
        Assert.assertEquals(selectFlight.theSelectedReturnDate.getText(),text);
    }

    @Test
    public void testSearchForFlight(){
        HomePage homePage = new HomePage();
        SelectFlight selectFlight =homePage.clickFlightPage();
        selectFlight.clickRoundTripButton();
        selectFlight.clickAndSendKeysLeavingFromButton("denver",2);
        selectFlight.clickAndSendKeysGoingToButton("orlando",1);
        selectFlight.selectDepartingDate("November","2023",25);
        selectFlight.selectReturningDate("September","2023",10);
        selectFlight.clickSearchButton();
        Assert.assertTrue(
                selectFlight.searchResultVerification("Denver (DEN) - orlando)"));
}
