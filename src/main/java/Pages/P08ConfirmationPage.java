package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P08ConfirmationPage {
    static float totalprice= 0;
    private final WebDriver driver;
    private final By ConfirmButton= By.cssSelector("button#button-confirm");
    private final By ShippingAddress= By.xpath("//h4[text()='Shipping Address']//following::div[@class='card-body'][1]");
    private final By PaymentAddress= By.xpath("//h4[text()='Shipping Address']//preceding::div[@class='card-body']");
    private final By Subtotal= By.xpath("//tfoot/tr[1]/td[@class='text-right'][2]");
    private final By FlatShippingRate= By.xpath("//tfoot/tr[2]/td[@class='text-right'][2]");
    private final By Total= By.xpath("//tfoot/tr[3]/td[@class='text-right'][2]");


    public P08ConfirmationPage(WebDriver driver) {
        this.driver=driver;
    }
    public String GetTotalPrice(){
        String SubtotalText= Utility.getText(driver,Subtotal);
        String FlateShippingRateText = Utility.getText(driver,FlatShippingRate);
        totalprice = Float.parseFloat(SubtotalText.replace("$",""))
                + Float.parseFloat(FlateShippingRateText.replace("$",""));
        LogsUtil.info("total Price: "+ totalprice);
        return String.valueOf(totalprice);
    }
    public boolean ComparingBetweenTotalPrices(){
        return GetTotalPrice().equals(new P07CheckOutPage(driver).getTotalPrice());
    }

    public String GetshippingAddress(){
        return Utility.getText(driver,ShippingAddress);
    }
    public String GetPaymentAddress(){
        return Utility.getText(driver,PaymentAddress);
    }
    public boolean CompareShippingandPaymentAddress(){
        return GetPaymentAddress().equals(GetshippingAddress());
    }

    public P09FinishingOrder ConfirmationOrder(){
        Utility.clickOnElement(driver,ConfirmButton);
        return new P09FinishingOrder(driver);
    }

}
