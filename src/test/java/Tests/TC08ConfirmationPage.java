package Tests;

import Pages.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

public class TC08ConfirmationPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void ValidCheckoutData() throws IOException {
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
        new WebDriverWait(getDriver(),Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(getPropertyValue("environment","CheckOut_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","CheckOut_URL"));
        //Adding Billing and shipping Address and finished order
        new P07CheckOutPage(getDriver()).clickonAddnewAddress()
                .FirstName(getJsonData("ValidBillingAddress","firstname"))
                .LastName(getJsonData("ValidBillingAddress","lastname"))
                .Company(getJsonData("ValidBillingAddress","company"))
                .Address1(getJsonData("ValidBillingAddress","address1"))
                .Address2(getJsonData("ValidBillingAddress","address2"))
                .City(getJsonData("ValidBillingAddress","city"))
                .Postcode(getJsonData("ValidBillingAddress","postcode"))
                .CountryDropdown(getJsonData("ValidBillingAddress","country"))
                .StateDropdown(getJsonData("ValidBillingAddress","state"))
                .EnterQuantityOfProduct("1").clickUpdateProduct()
                .ClickContinue();
        new WebDriverWait(getDriver(),Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(getPropertyValue("environment","confirmationOrder_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","confirmationOrder_URL"));
        Assert.assertTrue(new P08ConfirmationPage(getDriver()).CompareShippingandPaymentAddress());
        new P08ConfirmationPage(getDriver()).ConfirmationOrder();
    }
    @Test
    public void VerifyProductPrice() throws IOException {
        //Login
        new P02LoginPage(getDriver()).emailField(getJsonData("ValidLoginData", "email"))
                .passwordField(getJsonData("ValidLoginData", "password")).LoginButClick();
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "MyAccount_URL"));
        //Redirected to HTC Products
        new P03MyAccountPage(getDriver()).ClickonHome().redirectedtobrandproducts();
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "HTCProducts_URL"));
        //Select product
        new P05BrandProducts(getDriver()).SelectProduct();
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "HTCProduct1_URL"));
        //Check availability and BuyNow
        new P06ProductPage(getDriver()).ClickonBuynow();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(getPropertyValue("environment", "CheckOut_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "CheckOut_URL"));
        new P07CheckOutPage(getDriver()).EnterQuantityOfProduct("1").clickUpdateProduct();
        //Adding Billing and shipping Address and finished order
        new P07CheckOutPage(getDriver()).clickonAddnewAddress()
                .FirstName(getJsonData("ValidBillingAddress", "firstname"))
                .LastName(getJsonData("ValidBillingAddress", "lastname"))
                .Company(getJsonData("ValidBillingAddress", "company"))
                .Address1(getJsonData("ValidBillingAddress", "address1"))
                .Address2(getJsonData("ValidBillingAddress", "address2"))
                .City(getJsonData("ValidBillingAddress", "city"))
                .Postcode(getJsonData("ValidBillingAddress", "postcode"))
                .CountryDropdown(getJsonData("ValidBillingAddress", "country"))
                .StateDropdown(getJsonData("ValidBillingAddress", "state"));
        String CheckoutTotalPrice= new P07CheckOutPage(getDriver()).getTotalPrice();
        new P07CheckOutPage(getDriver()).ClickContinue();
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(getPropertyValue("environment", "confirmationOrder_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "confirmationOrder_URL"));
        Assert.assertTrue(new P08ConfirmationPage(getDriver()).CompareShippingandPaymentAddress());
        Assert.assertEquals(new P08ConfirmationPage(getDriver()).GetTotalPrice(), CheckoutTotalPrice);
    }
    @AfterMethod
    public void quit(){
        //quitDriver();
    }
}
