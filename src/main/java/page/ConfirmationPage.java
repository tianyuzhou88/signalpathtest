package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestContext;

import java.util.List;

public class ConfirmationPage extends LandingPage {

    @FindBy(css= "a.btn_action.cart_button")
    WebElement finishButton;

    @FindBy(css= "div.summary_subtotal_label")
    WebElement total;

    public List<WebElement> item(){
        return driver.findElements(By.cssSelector("div.cart_item"));
    }

    ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean validation(ITestContext iTestContext){
        return item().size()==(int)iTestContext.getAttribute("itemCount") && total.getText().contains(iTestContext.getAttribute("totalPrice")+"");
    }

    public FinishPage pay(){
        finishButton.click();
        return new FinishPage(driver);
    }
}
