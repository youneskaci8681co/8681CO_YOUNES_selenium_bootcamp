package data_providers;

import base.BasePage;
import org.testng.annotations.DataProvider;

public class DataProviderClass extends BasePage {

    @DataProvider(name = "CountryNames")
    public static Object[][] getCountryNames() {
        return excel.readStringArrays("CountryNames");
    }
}