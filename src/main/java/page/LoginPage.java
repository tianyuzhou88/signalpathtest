package page;

import core.ultils.Data;
import core.ultils.ElementAction;
import core.ultils.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage implements Page {
    WebDriver driver;
    ElementAction action = new ElementAction();

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css= "input#user-name")
    WebElement username;

    @FindBy(css= "#password")
    WebElement password;

    @FindBy(css= "input.btn_action")
    WebElement submit;

    public LandingPage login(Data data){
        username.sendKeys(data.getUsername());
        password.sendKeys(data.getPassword());
        submit.click();
        return new LandingPage(this.driver);
    }

    @Override
    public Boolean isAt() {
        return submit.isDisplayed() && action.getUrl().equalsIgnoreCase("https://www.saucedemo.com/");
    }
}
