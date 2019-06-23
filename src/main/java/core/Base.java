package core;

import core.ultils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver webDriver;
    public Log log=new Log(this.getClass().getSuperclass());


    @BeforeSuite(alwaysRun = true)
    @Parameters ({"Browser","URL"})
    public void spawnDriver(String browser, String url) {
        log.info("------------------Starting Executing Tests---------------");
        switch (browser)
        {
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                this.webDriver = new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.get(url+ "/login");
                log.info("****navigate to twitter login page****");
        }
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
    }

}
