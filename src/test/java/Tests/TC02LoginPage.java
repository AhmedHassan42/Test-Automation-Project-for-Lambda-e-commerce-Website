package Tests;

import Pages.P02LoginPage;
import Utilities.DataUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.SetupDriver;
import static DriverFactory.DriverFactory.getDriver;
import static Utilities.DataUtil.getJsonData;

public class TC02LoginPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(DataUtil.getPropertyValue("environment","browser"));
        getDriver().get(DataUtil.getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void ValidLoginTC() throws FileNotFoundException {
        new P02LoginPage(getDriver()).emailField(getJsonData("ValidLoginData","email"))
                .passwordField(getJsonData("ValidLoginData","password")).LoginButClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
    }

    @AfterMethod
    public void quit(){
        //quitDriver();
    }
}
