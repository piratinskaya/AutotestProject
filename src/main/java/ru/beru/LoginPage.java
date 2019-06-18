package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestSettings {

    @Step("Ввод логина")
    public void enterLogin(String login) {
        WebElement logInForm;
        logInForm = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-login-form")));
        WebElement element = logInForm.findElement(By.name("login"));
        element.click();
        element.sendKeys(login);
        element.sendKeys(Keys.ENTER);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        WebElement PassForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".passp-password-form"))));
        WebElement element = PassForm.findElement(By.name("passwd"));
        element.sendKeys(password);
        element.sendKeys(Keys.ENTER);
    }
}
