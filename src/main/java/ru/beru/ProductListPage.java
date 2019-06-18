package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductListPage extends TestSettings {
    private List<WebElement> productList;

    @Step("Ввод минимальной цены")
    public void inputMinimumPrice(int price) {
        WebElement fieldPriceFrom = driver.findElement(By.id("glpricefrom"));
        fieldPriceFrom.click();
        String priceStr = Integer.toString(price);
        fieldPriceFrom.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        WebElement until = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Ввод максимальной цены")
    public void inputMaximumPrice(int price) {
        WebElement fieldPriceTo = driver.findElement(By.id("glpriceto"));
        fieldPriceTo.click();
        String priceStr = Integer.toString(price);
        fieldPriceTo.sendKeys(priceStr);
        WebElement wind = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 40)).until(ExpectedConditions.visibilityOf(wind));
    }

    @Step("Показать список всех товаров")
    public void showAllProduct() {
        while(true) {
            try {
                WebElement showNewElement = driver.findElement(By.cssSelector(".n-pager-more__button"));
                showNewElement.click();
            } catch (Exception e) {
                break;
            }
        }
    }

    @Step("Получить список всех товаров")
    public void getListAllProducts() {
        final int countElement = Integer.parseInt(
                    driver.findElement(By.cssSelector(".n-search-preciser__results-count"))
                        .getAttribute("textContent").split(" ")[1]);
        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By
                        .cssSelector("[class*='grid-snippet_react']")).size() == countElement;
            }
        });
        productList = driver.findElements(By.cssSelector("[class*='grid-snippet_react']"));
    }

    @Step("Проверка, что список товаров не пуст")
    public void checkListNotEmpty() {
        Assert.assertNotNull(productList);
        Assert.assertTrue(productList.size() != 0);
    }

    @Step("Проверка того, что цены находятся в нужном диапазоне")
    public void checkPriceInRange(int minPrice, int maxPrice) {
        for(WebElement element : productList) {
            String pr = element.findElement(By.cssSelector("[data-tid = 'c3eaad93']")).getAttribute("textContent");
            int price = Integer.parseInt(pr.replace(" ", ""));
            Assert.assertTrue(price >= minPrice && price <= maxPrice);
        }
    }

    @Step("Добавить товар в корзину")
    public void addToBasket() {
        productList.get(productList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));
    }

    @Step("Перейти в корзину")
    public BasketPage toMyBasket() {
        productList.get(productList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='_3AlSA6AOKL']")));
        return new BasketPage();
    }
}


