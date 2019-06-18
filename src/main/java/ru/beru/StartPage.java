package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StartPage extends TestSettings {
    private String cityName;

    private WebElement giveAccountInfoButton() {
        return driver.findElement(By.cssSelector(".header2-nav__user"));
    }

    @Step("Нажатие на кнопку \"Войти в аккаунт\"")
    public LoginPage clickButtonAccount() {
        giveAccountInfoButton().click();
        return new LoginPage();
    }

    @Step("Проверка e-mail пользователя")
    public void checkUserEmail() {
        // навести курсор мыши на объект
        (new Actions(driver)).moveToElement(giveAccountInfoButton()).build().perform();
        WebElement userMenuEmail = driver.findElement(By.cssSelector("[class*='user-menu__email']"));
        Assert.assertEquals(userMenuEmail.getAttribute("textContent"), "yu.piratinskaya@yandex.ru");
    }

    @Step("Проверка того, что текст сменился на \"Мой профиль\"")
    public void checkTextOnAccountInfoButton() {
        WebElement textButtonUser = giveAccountInfoButton().findElement(By.cssSelector("[class*='__text']"));
        Assert.assertEquals(textButtonUser.getAttribute("textContent"), "Мой профиль");
    }

    private WebElement findCurrentCity() {
        return driver.findElement(By.cssSelector("[class*='__region'] [class*='__inner']"));
    }

    @Step("Клик на текущий город")
    public void clickCityInner() {
        findCurrentCity().click();
    }

    @Step("Ввод нового города")
    public void changeCityName(String cityName) {
        this.cityName = cityName;
        WebElement CityForm = (new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='region-select-form']"))));
        WebElement city = CityForm.findElement(By.cssSelector("[class*='region-suggest'] [class*='input__control']"));
        city.click();
        city.sendKeys(cityName);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(city.
                        findElement(By.xpath("//strong[text()[contains(.,\'" + cityName + "\')]]"))));
        city.sendKeys(Keys.ENTER);
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.invisibilityOf(
                        driver.findElement(By.cssSelector("[class*='suggestick-list']"))));
        city.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
    }

    @Step("Проверка того, что назание города сменилоь на новое")
    public void checkCityName() {
        Assert.assertEquals(findCurrentCity().getAttribute("textContent"), cityName);
    }

    @Step("Переход в личный кабинет")
    public ProfilePage goToMyProfile() {
        (new Actions(driver)).moveToElement(giveAccountInfoButton()).build().perform();
        WebElement addresses = driver.findElement(By.cssSelector("[class*='item_type_addresses']"));
        addresses.click();
        return new ProfilePage();
    }

    @Step("Ввод запроса в строку поиска")
    public void findItem(String item) {
        WebElement fieldForSearch = driver.findElement(By.id("header-search"));
        fieldForSearch.click();
        fieldForSearch.sendKeys(item);
        fieldForSearch.sendKeys(Keys.ENTER);
    }
}
