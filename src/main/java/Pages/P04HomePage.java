package Pages;

import Utilities.Utility;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class P04HomePage {
    private final WebDriver driver;
    private final By hovertomenu= By.xpath("//li[contains(@class,'mega')]//a[contains(@class,'dropdown-toggle') and (@role=\"button\")]");
    private final By Categories= By.xpath("//ul[contains(@class,'flex-column')]//li[(@class='nav-item')]/a[contains(@class,'nav-link')]");
    private final By Brandname= By.cssSelector("h1.h4");
    private final By HTCProducts= By.xpath("(//ul[contains(@class,'flex-column')]//li[(@class='nav-item')]/a[contains(@class,'nav-link')])[2]");
    private final By AppleProducts= By.xpath("(//ul[contains(@class,'flex-column')]//li[(@class='nav-item')]/a[contains(@class,'nav-link')])[1]");
    private static List<WebElement> allbrands;

    public P04HomePage(WebDriver driver) {
        this.driver= driver;
    }
    public void clickonbrand(){
            allbrands= driver.findElements(Categories);
            //new Actions(driver).moveToElement(Utility.BytoWebelement(driver,hovertomenu)).perform();
            try {
                for(WebElement elements : allbrands){
                new Actions(driver).moveToElement(Utility.BytoWebelement(driver,hovertomenu)).perform();
                String brandcategory = elements.getText();
                elements.click();
                String brandName = driver.findElement(Brandname).getText();
                System.out.println(brandName);
                driver.navigate().back();
                }

            }catch (Exception E){
                E.printStackTrace();
            }
    }
    public P05BrandProducts redirectedtobrandproducts(){
        new Actions(driver).moveToElement(Utility.BytoWebelement(driver,hovertomenu)).perform();
        Utility.clickOnElement(driver,HTCProducts);
        return new P05BrandProducts(driver);

    }
    public P05BrandProducts redirectedtoAppleproducts(){
        new Actions(driver).moveToElement(Utility.BytoWebelement(driver,hovertomenu)).perform();
        Utility.clickOnElement(driver,AppleProducts);
        return new P05BrandProducts(driver);

    }

}
