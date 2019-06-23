import core.Base;
import domain.UserInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.HomePage;
import page.TwitterLoginPage;

public class TwitterLoginTest extends Base {
    TwitterLoginPage loginPage;
    HomePage homePage;

    @BeforeTest
    public void pageInitialization(){
        loginPage = new TwitterLoginPage(webDriver);
    }


    @Test
    public void login(){
        homePage = loginPage.login(credentials().getTwittername(),credentials().getPassword());
    }

    @Test(priority = 1)
    public void contentValidation(){
        Assert.assertEquals(homePage.homepageUrl(),"https://twitter.com/");
        log.info("");
        Assert.assertEquals(homePage.twittername(),credentials().getTwittername());
        Assert.assertEquals(homePage.username(),credentials().getUsername());
    }

    @Test (priority = 2)
    public void logout(){
        homePage.signout();
    }


    private UserInfo credentials(){
        return new UserInfo("automationtes15",
                "bgqEy9hXv4gqTq7",
                "automationtesting0621");
    }
}

