package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05BrandProducts {
    private final WebDriver driver;
    private final By InStockButton= By.cssSelector("[for='mz-fss-1--1'].custom-control-label");
    private final By SelectProduct=By.xpath("(//div[contains(@class,'active')]/img[@class='lazy-load'])[1]");
    private final By FilterButton=By.xpath("//a[contains(@class,'btn-inline')]");
    public P05BrandProducts(WebDriver driver) {
        this.driver = driver;
    }
    public P05BrandProducts SelectFilterSection(){
        Utility.clickOnElement(driver,FilterButton);
        return this;
    }
    public P05BrandProducts SelectInStock(){
        /*new Actions(driver).scrollToElement(Utility.BytoWebelement(driver,InStockButton)).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(InStockButton));*/
        Utility.ScrollToElement(driver,InStockButton);
        Utility.clickOnElement(driver,InStockButton);
        return this;

    }
    public P06ProductPage SelectProduct(){
        Utility.clickOnElement(driver,SelectProduct);
        return new P06ProductPage(driver);

    }
}
