package ru.beru;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends TestSettings {

    @Test
    public void loginTest() {
        StartPage topPanel = new StartPage();
        LoginPage loginForm = topPanel.clickButtonAccount();
        String email = "yu.piratinskaya@yandex.ru";
        loginForm.enterLogin(email);
        String password = "Test!2019";
        loginForm.enterPassword(password);
        topPanel.checkTextOnAccountInfoButton();
        topPanel.checkUserEmail();
    }
}