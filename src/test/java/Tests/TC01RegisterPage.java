package Tests;

import Pages.P01RegisterPage;
import Utilities.DataUtil;
import Utilities.Utility;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtil.getJsonData;
import static Utilities.DataUtil.getPropertyValue;

public class TC01RegisterPage {
    @Parameters(value = "browser")
    @BeforeMethod
    public void setup(@Optional("edge") String browser) throws IOException {
        SetupDriver(browser);
        getDriver().get(DataUtil.getPropertyValue("environment","Register_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void ValidRegisterTC() throws IOException {
        new P01RegisterPage(getDriver()).firstnameField(getJsonData("ValidRegisterData","firstname") )
                .lastnameField(getJsonData("ValidRegisterData","lastname"))
                .emailField(getJsonData("ValidRegisterData","email") + Utility.getTimeStamp())
                .TelephoneField(getJsonData("ValidRegisterData","telephone"))
                .passwordField(getJsonData("ValidRegisterData","password"))
                .confirmPasswordField(getJsonData("ValidRegisterData","password"))
                .ClickonChecker().ClickonContinue();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","RegisteredSuccess_URL"));

    }
    @Test
    public void InValidRegisterTC() throws IOException {
        new P01RegisterPage(getDriver()).firstnameField(getJsonData("ValidRegisterData","firstname") )
                .lastnameField(getJsonData("ValidRegisterData","lastname"))
                .emailField(getJsonData("ValidRegisterData","email"))
                .TelephoneField(getJsonData("ValidRegisterData","telephone"))
                .passwordField(getJsonData("ValidRegisterData","password"))
                .confirmPasswordField(getJsonData("ValidRegisterData","password"))
                .ClickonChecker().ClickonContinue();
        Assert.assertEquals(getDriver().getCurrentUrl(),getPropertyValue("environment","Register_URL"));
        Assert.assertTrue(new P01RegisterPage(getDriver()).CheckErrorMessageVisibility());

    }



    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
