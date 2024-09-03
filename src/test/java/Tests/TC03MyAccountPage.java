package Tests;

import Pages.P02LoginPage;
import Pages.P03MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.SetupDriver;
import static DriverFactory.DriverFactory.getDriver;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

public class TC03MyAccountPage {
    @BeforeMethod
    public void setup() throws IOException {
        SetupDriver(getPropertyValue("environment","browser"));
        getDriver().get(getPropertyValue("environment","Login_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void RedirectedToHome() throws IOException {
        new P02LoginPage(getDriver()).emailField(getJsonData("ValidLoginData","email"))
                .passwordField(getJsonData("ValidLoginData","password")).LoginButClick();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","MyAccount_URL"));
        new P03MyAccountPage(getDriver()).ClickonHome();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Home_URL"));
    }



    @AfterMethod
    public void quit(){
        //quitDriver();
    }
}
