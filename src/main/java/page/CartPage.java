package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartPage extends LandingPage {

    private By items = By.cssSelector("div.cart_item");


    @FindBy(css= "a.btn_action.checkout_button")
    WebElement checkout;

    CartPage(WebDriver driver) {
        super(driver);
    }

    public InfoPage checkoutInfo(){
        Assert.assertEquals(driver.findElements(items).size(), Integer.parseInt(super.cartItemsCount.getText()));
        return new InfoPage(driver);
    }


    @Override
    public Boolean isAt() {
        action.waitForElement(items, 60);
        return checkout.isDisplayed();
    }
}
