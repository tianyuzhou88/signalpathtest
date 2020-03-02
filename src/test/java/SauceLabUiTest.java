import core.Base;
import core.ultils.Data;
import core.ultils.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.CartPage;
import page.LandingPage;
import page.LoginPage;

public class SauceLabUiTest extends Base{
    private LoginPage loginPage;
    private LandingPage landingPage;
    private CartPage cartPage;
    private Page page;
    private Data user1 = Data.get("src/main/java/core/data/testing.json");


    @BeforeTest
    public void pageInitialization(){
        loginPage = new LoginPage(webDriver);
    }


    @Test
    public void login(){
        page = loginPage;
        Assert.assertTrue(page.isAt());
        landingPage = loginPage.login(user1);
    }

    @Test(dependsOnMethods = "login")
    public void addProduct(){
        page = landingPage;
        Assert.assertTrue(page.isAt());
        landingPage.setFilter("T-Shirt");
        Assert.assertTrue(landingPage.bulkAddFilteredToCart());
        cartPage = landingPage.naviagateCartPage();

    }

    @Test (dependsOnMethods = "addProduct")
    public void checkout(){
        page = cartPage;
        Assert.assertTrue(page.isAt());
        cartPage.checkoutInfo();
    }

}

