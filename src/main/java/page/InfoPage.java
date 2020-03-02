package page;

import core.ultils.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoPage extends LandingPage{
    InfoPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameBy = By.cssSelector("#first-name") ;

    @FindBy(css= "#first-name")
    WebElement firstName;


    @FindBy(css= "#last-name")
    WebElement lastName;


    @FindBy(css= "#postal-code")
    WebElement zip;


    @FindBy(css= "div.checkout_buttons > input")
    WebElement continueButton;


    public ConfirmationPage shipInfo(Data data){
        action.waitForElement(firstNameBy, 60);
        firstName.sendKeys(data.getFirstName());
        lastName.sendKeys(data.getLastName());
        zip.sendKeys(data.getZipCode());
        continueButton.click();
        return new ConfirmationPage(driver);

    }

}
