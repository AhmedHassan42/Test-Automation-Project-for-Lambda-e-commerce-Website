package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06ProductPage {
    private final WebDriver driver;
    static float Productprice=0;
    private final By Availability= By.xpath("//span[contains(@class,'badge-success')]");
    private final By BuyNowButton= By.xpath("//button[contains(@class,'btn-buynow')]");
    private final By AddtocartButton= By.xpath("//div[@id='entry_216842']//button[contains(@class,'btn-cart')]");
    private final By ProductPriceLoc= By.xpath("//h3[@data-update='price']");
    public P06ProductPage(WebDriver driver) {
        this.driver=driver;
    }

    public P07CheckOutPage ClickonBuynow(){
        String InStock= Utility.getText(driver,Availability);
        if (InStock.equals("In Stock")){
            Utility.clickOnElement(driver,BuyNowButton);
        }else{
            driver.navigate().back();
            LogsUtil.info("The product is out of stock");
        }
        return new P07CheckOutPage(driver);
    }

    public String GetproductPrice(){
        String Fulltext= Utility.getText(driver,ProductPriceLoc);
        Productprice= Float.parseFloat(Fulltext.replace("$",""));
        LogsUtil.info("Product price: "+ Productprice);
        return String.valueOf(ProductPriceLoc);
    }
}
