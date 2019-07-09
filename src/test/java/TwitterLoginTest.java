import core.Base;
import core.ultils.Data;
import domain.UserInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.HomePage;
import page.TwitterLoginPage;

import java.io.IOException;

import static core.Base.webDriver;

public class TwitterLoginTest extends Base{
    TwitterLoginPage loginPage;
    HomePage homePage;
    Data data = Data.get("/Users/admin/Downloads/SignalPathAutomationArtifact/src/main/java/core/data/testing.json");

    public TwitterLoginTest() throws IOException {
    }

    @BeforeTest
    public void pageInitialization(){
        loginPage = new TwitterLoginPage(webDriver);
    }

    @Test
    public void login(){
        homePage = loginPage.login(data.getTwittername(),data.getPassword());
    }

    @Test(priority = 1)
    public void contentValidation(){
        Assert.assertEquals(homePage.homepageUrl(),"https://twitter.com/");
        log.info("");
        Assert.assertEquals(homePage.twittername(),data.getTwittername());
        Assert.assertEquals(homePage.username(),data.getUsername());
    }

    @Test (priority = 2)
    public void logout(){
        homePage.signout();
    }


//    private UserInfo credentials(){
//        return new UserInfo("automationtes15",
//                "bgqEy9hXv4gqTq7",
//                "automationtesting0621");
//    }
}

