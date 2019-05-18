package ru.beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestSettings {

    public static WebDriver driver;


    @BeforeMethod
    public void preparation() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://beru.ru");
    }

    @AfterMethod
    public void clear() {
        WebElement elem = driver.findElement(By.cssSelector(".header2-nav__user"));
        if (elem.getAttribute("textContent").contains("Мой профиль")) {
            elem.click();
            driver.findElement(By.cssSelector("[class*='type_logout']")).click();
        }
        driver.quit();
    }
}