package page_object;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import base.BasePage;

public class SoccerSubPage extends BasePage {
    @FindBy(xpath = "//h1[@class = 'headline headline__h1 dib']")
    public WebElement worldCupBracketHeadline;

    @FindBy(xpath = "//div[@id ='scoreboard-page']/header")
    public WebElement scoresPageHeader;

    @FindBy(xpath = "//div[@class ='schedule__card']/header")
    public WebElement schedulePageHeader;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy ']/div/div")
    public List<WebElement> worldCupGames;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][1]/div/div/a/div[2]/div/div[1]/div[3]")
    public List<WebElement> round16Competitors;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][1]/div/div")
    public List<WebElement> round16Games;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][2]/div/div/a/div[2]/div/div[1]/div[3]")
    public List<WebElement> quarterFinalsCompetitors;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][2]/div/div")
    public List<WebElement> quarterFinalsGames;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][3]/div/div/a/div[2]/div/div[1]/div[3]")
    public List<WebElement> semiFinalsCompetitors;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][3]/div/div")
    public List<WebElement> semiFinalsGames;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][4]/div/div/a/div[2]/div/div[1]/div[3]")
    public List<WebElement> finalCompetitors;

    @FindBy(xpath = "//div[@class ='VZTD rEPu zkpV gKEB VWuy '][4]/div/div")
    public List<WebElement> finalGame;


    public SoccerSubPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isCountryPresentInRound16(String countryName) {
        int i;
        String competitorsName;
        for (i = 0; i < round16Competitors.size(); i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(round16Competitors));
            competitorsName = round16Competitors.get(i).getText();
            if (countryName.equalsIgnoreCase(competitorsName)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCountryPresentInQuarterFinals(String countryName) {
        int i;
        String competitorsName;
        for (i = 0; i < quarterFinalsCompetitors.size(); i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(quarterFinalsCompetitors));
            competitorsName = quarterFinalsCompetitors.get(i).getText();
            if (countryName.equalsIgnoreCase(competitorsName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCountryPresentInSemiFinals(String countryName){
        int i;
        String competitorsName;
        for (i = 0; i < semiFinalsCompetitors.size(); i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(semiFinalsCompetitors));
            competitorsName = semiFinalsCompetitors.get(i).getText();
            if (countryName.equalsIgnoreCase(competitorsName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCountryPresentInFinals(String countryName){
        int i;
        String competitorsName;
        for (i = 0; i < finalCompetitors.size(); i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(finalCompetitors));
            competitorsName = finalCompetitors.get(i).getText();
            if (countryName.equalsIgnoreCase(competitorsName)) {
                return true;
            }
        }
        return false;
    }

    public int getRound16NumOfGames() {
        return round16Games.size();
    }

    public int getQuarterFinalsNumOfGames() {
        return quarterFinalsGames.size();
    }

    public int getSemiFinalsNumOfGames() {
        return semiFinalsGames.size();
    }

    public int getFinalNumOfGames() {
        return finalGame.size();
    }

    public int getWorldCupNumOfGames() {
        return worldCupGames.size();
    }

}
