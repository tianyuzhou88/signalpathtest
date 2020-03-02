package page;

import core.ultils.ElementAction;
import core.ultils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LandingPage implements Page {
    protected WebDriver driver;
    private String filter;
    private double totalPrice;
    protected ElementAction action = new ElementAction();
    private int itemCount;

    public int getItemCount() {
        return itemCount;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css= "#shopping_cart_container")
    WebElement cart;

    @FindBy(css= "#shopping_cart_container > a > span")
    WebElement cartItemsCount;


    private By inventoryList = By.cssSelector("div.inventory_item_name");

    private List<WebElement> inventoryList(){
        action.waitForElement(inventoryList,60);
        return this.driver.findElements(this.inventoryList);
    }

    private WebElement addToCart(int index){
        return this.driver.findElement(By.cssSelector(String.format("div:nth-child(%s) > div.pricebar > button",index)));

    }

    private double itemPrice(int index){
        String price = this.driver.findElement(By.cssSelector(String.format("#inventory_container " +
                "> div > div:nth-child(%s) > div.pricebar > div",index))).getText();
        price= price.substring(1);
        return Double.parseDouble(price);
    }

    protected List<Integer> filteredResultIndex (){
        List<WebElement> filteredList = this.inventoryList().stream().
                filter(webElement -> webElement.getText().contains(this.filter))
                .collect(Collectors.toList());

        //error handling for non found filer
        if(filteredList.size() ==0) throw new Error("filtered item is not found");

        List<Integer> filteredIndexList = filteredList.stream()
                .mapToInt(element -> this.inventoryList().stream().map(WebElement::getText)
                        .collect(Collectors.toList()).indexOf(element.getText()))
                .mapToObj(index -> index + 1).collect(Collectors.toList());
        return filteredIndexList;
    }

    public boolean bulkAddFilteredToCart(ITestContext context){
        this.filteredResultIndex().forEach(i -> {
            addToCart(i).click();
            Assert.assertTrue(addToCart(i).getText().equalsIgnoreCase("REMOVE"));
        });
        for (int i : this.filteredResultIndex()) {
            totalPrice+=this.itemPrice(i);
        }
        itemCount = filteredResultIndex().size();
        context.setAttribute("totalPrice", getTotalPrice());
        context.setAttribute("itemCount", getItemCount());
        return Integer.parseInt(this.cartItemsCount.getText()) == filteredResultIndex().size();
    }

    public CartPage naviagateCartPage(){
        cart.click();
        return new CartPage(driver);
    }


    @Override
    public Boolean isAt() {
        return cart.isDisplayed();
    }
}
