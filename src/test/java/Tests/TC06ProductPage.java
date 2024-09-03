package Tests;

import Pages.P02LoginPage;
import Pages.P03MyAccountPage;
import Pages.P05BrandProducts;
import Pages.P06ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

public class TC06ProductPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void BuyProduct() throws IOException {
        //Login
        new P02LoginPage(getDriver()).emailField(getJsonData("ValidLoginData","email"))
                .passwordField(getJsonData("ValidLoginData","password")).LoginButClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","MyAccount_URL"));
        //Redirected to HTC Products
        new P03MyAccountPage(getDriver()).ClickonHome().redirectedtobrandproducts();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","HTCProducts_URL"));
        //Select product
        new P05BrandProducts(getDriver()).SelectProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","HTCProduct1_URL"));
        //Check availability and BuyNow
        new P06ProductPage(getDriver()).ClickonBuynow();
    }

    @AfterMethod
    public void quit(){
        //quitDriver();
    }
}
