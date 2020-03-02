package page;

import core.ultils.ElementAction;
import core.ultils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LandingPage implements Page {
    WebDriver driver;
    private String filter;

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css= "#shopping_cart_container")
    WebElement cart;

    @FindBy(css= "#shopping_cart_container > a > span")
    WebElement cartItemsCount;


    private By inventoryList = By.cssSelector("div.inventory_item_name");

    private List<WebElement> inventoryList(){
        return this.driver.findElements(this.inventoryList);
    }

    private WebElement addToCart(int index){
        return this.driver.findElement(By.cssSelector(String.format("div:nth-child(%s) > div.pricebar > button",index)));

    }

    private String itemPrice(int index){
        return this.driver.findElement(By.cssSelector(String.format("#inventory_container " +
                "> div > div:nth-child(%s) > div.pricebar > div",index))).getText();
    }

    private List<Integer> filteredResultIndex (){
        List<WebElement> filteredList = this.inventoryList().stream().
                filter(webElement -> webElement.getText().contains(this.filter))
                .collect(Collectors.toList());
        List<Integer> filteredIndexList = filteredList.stream()
                .mapToInt(element -> this.inventoryList().stream().map(WebElement::getText)
                .collect(Collectors.toList()).indexOf(element.getText()))
                .mapToObj(index -> index + 1).collect(Collectors.toList());
        return filteredIndexList;
    }

    public boolean bulkAddFilteredToCart(){
        this.filteredResultIndex().forEach(i -> {
            addToCart(i).click();
            Assert.assertTrue(addToCart(i).getText().equalsIgnoreCase("REMOVE"));
        });
        return Integer.parseInt(this.cartItemsCount.getText()) == filteredResultIndex().size();
    }




    @Override
    public Boolean isAt() {
        return cart.isDisplayed();
    }
}
