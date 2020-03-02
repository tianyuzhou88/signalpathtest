package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinishPage extends LandingPage{

    @FindBy(css= "#checkout_complete_container > h2")
    WebElement thankYouText;

    @FindBy(css= "#contents_wrapper > div.subheader")
    WebElement title;

    FinishPage(WebDriver driver) {
        super(driver);
    }

    public boolean validation(){
        action.sleep(3);
        return thankYouText.isDisplayed() && title.getText().trim().equalsIgnoreCase("Finish");
    }
}
