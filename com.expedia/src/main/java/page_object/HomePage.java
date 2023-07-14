package page_object;

import base.BasePage;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@id='wizardMainRegionV2']/div/div/div/div/ul/li[2]/a")
    public WebElement selectFlight;

    @FindBy(xpath = "//*[@id='wizardMainRegionV2']/div/div/div/div/ul/li[1]/a")
    public WebElement selectStays;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public SelectFlight clickFlightPage() {
        clickOnElement(selectFlight);
        return new SelectFlight();
    }

    public SelectStays getStaysPage(){
        return new SelectStays();
    }
}
