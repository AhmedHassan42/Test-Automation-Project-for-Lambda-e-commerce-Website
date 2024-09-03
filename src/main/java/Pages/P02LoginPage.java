package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02LoginPage {
    private final WebDriver driver;
    private final By email=By.id("input-email");
    private final By password=By.id("input-password");
    private final By LoginButton=By.cssSelector("input[value=Login]");

    public P02LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P02LoginPage emailField(String emailtext){
        Utility.SendData(driver,email,emailtext);
        return this;

    }
    public P02LoginPage passwordField(String passwordtext){
        Utility.SendData(driver,password,passwordtext);
        return this;

    }
    public P03MyAccountPage LoginButClick(){
        Utility.clickOnElement(driver,LoginButton);
        return new P03MyAccountPage(driver);
    }
}
