package ru.novikova.av.test.selenium.example;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.novikova.av.test.selenium.example.model.Item;
import ru.novikova.av.test.selenium.example.page.InventoryPage;
import ru.novikova.av.test.selenium.example.page.LoginPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SauceDemoTest {
    private static LoginPage loginPage;
    private static InventoryPage inventoryPage;
    private static WebDriver driver;

    private static List<Item> expectedItemNames = new ArrayList<>(Arrays.asList(
            new Item("Sauce Labs Backpack", "$29.99", "https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.34e7aa42.jpg"),
            new Item("Sauce Labs Bike Light", "$9.99", "https://www.saucedemo.com/static/media/bike-light-1200x1500.a0c9caae.jpg"),
            new Item("Sauce Labs Bolt T-Shirt", "$15.99", "https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c0dae290.jpg"),
            new Item("Sauce Labs Fleece Jacket", "$49.99", "https://www.saucedemo.com/static/media/sauce-pullover-1200x1500.439fc934.jpg"),
            new Item("Sauce Labs Onesie", "$7.99", "https://www.saucedemo.com/static/media/red-onesie-1200x1500.1b15e1fa.jpg"),
            new Item("Test.allTheThings() T-Shirt (Red)", "$15.99", "https://www.saucedemo.com/static/media/red-tatt-1200x1500.e32b4ef9.jpg")
    ));

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.inputUserName("standard_user");
//        loginPage.inputUserName("problem_user");
        Thread.sleep(1000);
        loginPage.inputPassword("secret_sauce");
        Thread.sleep(1000);
        loginPage.clickLogin();
        Thread.sleep(1000);

        List<Item> actualItems = inventoryPage.getItems();
        for (int i = 0; i < actualItems.size(); i++) {
            Assert.assertEquals("Товар не соответствует: ", expectedItemNames.get(i), actualItems.get(i));
        }
        inventoryPage.clickMenu();
        Thread.sleep(1000);

        inventoryPage.exit();

        Thread.sleep(1000);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
