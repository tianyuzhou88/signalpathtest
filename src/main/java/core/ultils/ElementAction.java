package core.ultils;

import core.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementAction extends Base {
    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void open(String url) {
        webDriver.navigate().to(url);
        this.sleep(1);
        log.info(" Open Browser，Open "+url+" Address!");
    }

    public void sleep(double inSecs) {
        try {
            Thread.sleep((long) inSecs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 60);
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}
