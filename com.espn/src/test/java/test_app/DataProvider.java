package data_providers;

import base.BasePage;

public class DataProvider extends BasePage {
    @DataProvider(name = "CountryNames")
    public static Object[][] getCountryNames() {
        return excel.readStringArrays("CountryNames");
    }
}
