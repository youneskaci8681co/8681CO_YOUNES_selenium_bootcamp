import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.MenuDD;

import java.util.List;

public class TestMenuDD extends BasePage {

    @Test
    public void testNavigationToMenu() {
        HomePage homePage = new HomePage();
        MenuDD menuDD = homePage.clickMenu();
        List<WebElement> webElementList = driver.findElements(By.xpath("//*[@id='menuNavigation']/li"));
        for (WebElement element : webElementList) {
            String elementText = element.getText().trim();
            switch (elementText) {
                case "Renter Tools":
                    clickOnElement(element);
                    Assert.assertTrue(isElementVisible(menuDD.toolsFavSubMenu));
                    break;
                case "Manage Rentals":
                case "Help Center":
                    Assert.assertTrue(isElementVisible(element));
                    break;
                case "Apartments For Rent":
                    clickOnElement(element);
                    Assert.assertTrue(isElementVisible(menuDD.aptProp));
                    break;
                case "Homes For Rent":
                    clickOnElement(element);
                    Assert.assertTrue(isElementVisible(menuDD.propForRent));
                    break;
                case "Condos For Rent":
                    clickOnElement(element);
                    Assert.assertTrue(isElementVisible(menuDD.condosSubProp));
                    break;
                case "Townhomes For Rent":
                    clickOnElement(element);
                    Assert.assertTrue(isElementVisible(menuDD.townHomesSubProp));
                    break;
            }

        }


    }


}
