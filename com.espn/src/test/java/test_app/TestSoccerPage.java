package test_app;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.SoccerPage;
import page_object.SoccerSubPage;

public class TestSoccerPage extends BasePage {
    @Test
    public void testNavigatingToSoccerPage(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        Assert.assertTrue(isElementVisible(soccerPage.soccerSubMenu));
    }

    @Test
    public void testNavigatingToWCKBPPage(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        Assert.assertTrue(isElementVisible(wc.worldCupBracketHeadline));
    }

    @Test
    public void testRound16NumOfGames(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        int expNumOfGames = 8;
        int actNumOfGames = wc.getRound16NumOfGames();
        Assert.assertEquals(actNumOfGames,expNumOfGames);
    }

    @Test
    public void testQuarterFinalsNumOfGames(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        int expNumOfGames = 4;
        int actNumOfGames = wc.getQuarterFinalsNumOfGames();
        Assert.assertEquals(actNumOfGames,expNumOfGames);
    }

    @Test
    public void testSemiFinalsNumOfGames(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        int expNumOfGames = 2;
        int actNumOfGames = wc.getSemiFinalsNumOfGames();
        Assert.assertEquals(actNumOfGames,expNumOfGames);
    }

    @Test
    public void testFinalNumOfGames(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        int expNumOfGames = 1;
        int actNumOfGames = wc.getFinalNumOfGames();
        Assert.assertEquals(actNumOfGames,expNumOfGames);
    }

    @Test
    public void testWorldCupNumOfGames(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("2");
        int expNumOfGames = 15;
        int actNumOfGames = wc.getWorldCupNumOfGames();
        Assert.assertEquals(actNumOfGames,expNumOfGames);
    }

    @Test
    public void testNavigatingToScoresPage(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("3");
        Assert.assertTrue(isElementVisible(wc.scoresPageHeader));
    }

    @Test
    public void testNavigatingToSchedulePage(){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption("4");
        Assert.assertTrue(isElementVisible(wc.schedulePageHeader));
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class,dataProvider = "CountryNames")
    public void testCountryIfPresentInRound16Competitors(String subOptionNum, String countryName){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption(subOptionNum);
        boolean isPresent = wc.isCountryPresentInRound16(countryName);
        Assert.assertTrue(isPresent);
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class,dataProvider = "CountryNames")
    public void testCountryIfPresentInQuarterFinalsCompetitors(String subOptionNum, String countryName){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption(subOptionNum);
        boolean isPresent = wc.isCountryPresentInQuarterFinals(countryName);
        if(isPresent){
            Assert.assertTrue(true);
        }else{
            Assert.fail();
        }
    }
    @Test(dataProviderClass = data_providers.DataProviderClass.class,dataProvider = "CountryNames")
    public void testCountryIfPresentInSemiFinalsCompetitors(String subOptionNum, String countryName){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption(subOptionNum);
        boolean isPresent = wc.isCountryPresentInSemiFinals(countryName);
        if(isPresent){
            Assert.assertTrue(true);
        }else{
            Assert.fail();
        }
    }

    @Test(dataProviderClass = data_providers.DataProviderClass.class,dataProvider = "CountryNames")
    public void testCountryIfPresentInFinalsCompetitors(String subOptionNum, String countryName){
        HomePage homePage = new HomePage();
        SoccerPage soccerPage =homePage.navigateToSoccerPage();
        SoccerSubPage wc = soccerPage.clickSoccerSubOption(subOptionNum);
        boolean isPresent = wc.isCountryPresentInFinals(countryName);
        if(isPresent){
            Assert.assertTrue(true);
        }else{
            Assert.fail();
        }
    }



}

