package page;

import core.ultils.ElementAction;
import core.ultils.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage implements Page{
    WebDriver driver;
    ElementAction action = new ElementAction();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css= "b.u-linkComplex-target")
    WebElement twittername;

    @FindBy(css= "li#global-nav-home a")
    WebElement homeButton;

    @FindBy(id= "user-dropdown-toggle")
    WebElement userDropdown;

    @FindBy(css= "b.fullname")
    WebElement username;

    @FindBy(xpath = "//li[@id='signout-button']/button")
    WebElement signout;

    public String twittername(){

        action.waitForElement(twittername);
        return action.getText(twittername);
    }

    public String username(){
        action.waitForElement(userDropdown);
        userDropdown.click();
        action.waitForElement(username);
        return action.getText(username);
    }

    public String homepageUrl(){
        return action.getUrl();
    }

    public void signout(){
        action.waitForElement(signout);
        signout.click();
    }

    @Override
    public Boolean isAt() {
        return signout.isDisplayed();
    }
}
