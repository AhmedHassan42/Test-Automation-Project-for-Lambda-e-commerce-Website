package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03MyAccountPage {
    private final WebDriver driver;
    private final By HomePageButton= By.xpath("//span[contains(text(),\"Home\")]");

    public P03MyAccountPage(WebDriver driver){
        this.driver=driver;
    }

    public P04HomePage ClickonHome(){
        Utility.clickOnElement(driver,HomePageButton);
        return new P04HomePage(driver);

    }
}
