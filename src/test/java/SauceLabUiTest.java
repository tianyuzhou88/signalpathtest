import com.google.inject.internal.cglib.core.internal.$CustomizerRegistry;
import core.Base;
import core.ultils.Data;
import core.ultils.Page;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.*;

public class SauceLabUiTest extends Base{
    private LoginPage loginPage;
    private LandingPage landingPage;
    private CartPage cartPage;
    private InfoPage infoPage;
    private ConfirmationPage confirmationPage;
    private FinishPage finishPage;
    private Page page;
    private Data user1;


    @BeforeTest
    public void pageInitialization(){
        loginPage = new LoginPage(webDriver);
        String relativePath = "src/main/java/core/data/testing.json";
        String path;
        if(SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) path = relativePath;
        else if (SystemUtils.IS_OS_WINDOWS) path = FilenameUtils.separatorsToWindows(relativePath);
        else throw new Error ("no supporting os yet");
        user1 = Data.get(path);
    }


    @Test
    public void login(){
        page = loginPage;
        Assert.assertTrue(page.isAt());
        landingPage = loginPage.login(user1);
    }

    @Test(dependsOnMethods = "login")
    public void addProducts(ITestContext iTestContext){
        page = landingPage;
        Assert.assertTrue(page.isAt());
        landingPage.setFilter("T-Shirt");
        Assert.assertTrue(landingPage.bulkAddFilteredToCart(iTestContext));
        cartPage = landingPage.naviagateCartPage();
    }

    @Test (dependsOnMethods = "addProducts")
    public void checkout(ITestContext iTestContext){
        page = cartPage;
        Assert.assertTrue(page.isAt());
        infoPage = cartPage.checkoutInfo();
        confirmationPage = infoPage.shipInfo(user1);
        Assert.assertTrue(confirmationPage.validation(iTestContext));
        finishPage = confirmationPage.pay();
        Assert.assertTrue(finishPage.validation());
    }

}

