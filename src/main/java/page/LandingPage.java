package page;

import core.ultils.ElementAction;
import core.ultils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LandingPage implements Page {
    protected WebDriver driver;
    private String filter;
    protected ElementAction action = new ElementAction();

    public void setFilter(String filter) {
        this.filter = filter;
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

    private int itemPrice(int index){
        String price = this.driver.findElement(By.cssSelector(String.format("#inventory_container " +
                "> div > div:nth-child(%s) > div.pricebar > div",index))).getText();
        Pattern p = Pattern.compile("(\\d+\\.\\d{1,2})");
        Matcher m = p.matcher(price);
        System.out.println(Integer.parseInt(m.group(0)));
        return Integer.parseInt(m.group(0));
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

    public boolean bulkAddFilteredToCart(){
        this.filteredResultIndex().forEach(i -> {
            addToCart(i).click();
            Assert.assertTrue(addToCart(i).getText().equalsIgnoreCase("REMOVE"));
        });
        return Integer.parseInt(this.cartItemsCount.getText()) == filteredResultIndex().size();
    }

    int totalPrice(){
        int totalPrice = 0;
        for (Integer i : this.filteredResultIndex()) {
            totalPrice+=this.itemPrice(i);
        }
        return totalPrice;
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
