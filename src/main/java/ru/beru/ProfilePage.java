package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProfilePage extends TestSettings {

    @Step("Находим текущий город")
    public WebElement findCityInner() {
        return driver.findElement(By.cssSelector("[class*='settings-list_type_region'] [class*='__inner']"));
    }

    @Step("Находим адрес доставки")
    public WebElement findDeliveryAddress() {
        return driver.findElement(By.cssSelector("[class*='__region'] [class*='__inner']"));
    }

    @Step("Проверка того, что адрес доставки тот же, что и текущий город")
    public void checkAddresses() {
        Assert.assertEquals(findCityInner().getAttribute("textContent"),
                findDeliveryAddress().getAttribute("textContent"));
    }
}
