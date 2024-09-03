package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01RegisterPage {
    private final WebDriver driver;
    private final By Firstname= By.id("input-firstname");
    private final By Lastname= By.id("input-lastname");
    private final By email= By.id("input-email");
    private final By Telephone= By.id("input-telephone");
    private final By password= By.id("input-password");
    private final By confirmPassword= By.id("input-confirm");
    private final By readandagreechecker= By.cssSelector("label[for=input-agree]");
    private final By continueButton= By.cssSelector("input[value=Continue]");
    private final By WarningMessage=By.xpath("//div[contains(@class,'alert-danger')]");



    public P01RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public P01RegisterPage firstnameField(String firstnametext){
        Utility.SendData(driver,Firstname,firstnametext);
        return this;
    }
    public P01RegisterPage lastnameField(String lastnametext){
        Utility.SendData(driver,Lastname,lastnametext);
        return this;
    }public P01RegisterPage emailField(String emailtext){
        Utility.SendData(driver,email,emailtext);
        LogsUtil.info(emailtext);
        return this;
    }public P01RegisterPage TelephoneField(String telephoneNo){
        Utility.SendData(driver,Telephone,telephoneNo);
        return this;
    }public P01RegisterPage passwordField(String passwordText){
        Utility.SendData(driver,password,passwordText);
        return this;
    }
    public P01RegisterPage confirmPasswordField(String confirmpasstext){
        Utility.SendData(driver,confirmPassword,confirmpasstext);
        return this;
    }
    public P01RegisterPage ClickonChecker(){
        Utility.clickOnElement(driver,readandagreechecker);
        return this;
    }
    public P04HomePage ClickonContinue(){
        Utility.clickOnElement(driver,continueButton);
        return new P04HomePage(driver);
    }
    public boolean CheckErrorMessageVisibility(){
        return driver.findElement(WarningMessage).isDisplayed();
    }
}
