package ru.novikova.av.test.selenium.example.page;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.novikova.av.test.selenium.example.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private final WebDriver driver;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(xpath = "//div[@class='inventory_list']/div[@class='inventory_item']")
    private List<WebElement> items;

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @SneakyThrows
    public List<Item> getItems() {
        Thread.sleep(5000);
        return items.stream()
                .map(itemWebElement -> Item.builder()
                        .name(itemWebElement.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText())
                        .image(itemWebElement.findElement(By.xpath(".//img[@class='inventory_item_img']")).getAttribute("src"))
                        .price(itemWebElement.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText())
                        .build()
                )
                .collect(Collectors.toList());
    }
     public void exit(){
        logoutButton.click();
     }

     public void clickMenu(){
        menuButton.click();
     }
}
