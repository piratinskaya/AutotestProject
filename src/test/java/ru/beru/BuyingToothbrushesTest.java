package ru.beru;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class BuyingToothbrushesTest extends TestSettings {
    @Test
    public void buyingToothbrushesTest() {
        StartPage page = new StartPage();
        page.findItem("Электрические зубные щетки");
        ProductListPage prodPage = new ProductListPage();
        prodPage.inputMinimumPrice(999);
        prodPage.inputMaximumPrice(1999);
        prodPage.showAllProduct();
        prodPage.getListAllProducts();
        prodPage.checkListNotEmpty();
        prodPage.checkPriceInRange(999, 1999);
        prodPage.addToBasket();
        BasketPage basketPage = prodPage.toMyBasket();
        basketPage.checkDeliveryText("бесплатной доставки осталось");
        basketPage.checkCorrectionPrice();
        basketPage.addCountProduct(2999);
        basketPage.checkDeliveryIsFreeTitle("Поздравляем!");
        basketPage.checkDeliveryIsFree();
        basketPage.checkCorrectionPrice();
    }
}
