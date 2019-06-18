package ru.beru;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ChooseCityTest extends TestSettings {

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "Хвалынск" },
                        { "Москва" },
                        { "Самара" }
                };
    }

    @Test(dataProvider="SearchProvider")
    public void chooseCityTest(String cityName) {
        StartPage page = new StartPage();
        page.clickCityInner();
        page.changeCityName(cityName);
        page.checkCityName();
        LoginPage loginForm = page.clickButtonAccount();
        String email = "yu.piratinskaya@yandex.ru";
        loginForm.enterLogin(email);
        String password = "Test!2019";
        loginForm.enterPassword(password);
        ProfilePage address = page.goToMyProfile();
        address.findCityInner();
        address.findDeliveryAddress();
        address.checkAddresses();
    }
}
