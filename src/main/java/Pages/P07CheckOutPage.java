package Pages;

import Utilities.LogsUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P07CheckOutPage {
    private final WebDriver driver;
    private final By firstname= By.id("input-payment-firstname");
    private final By lastname= By.id("input-payment-lastname");
    private final By company= By.id("input-payment-company");
    private final By Address1= By.id("input-payment-address-1");
    private final By Address2= By.id("input-payment-address-2");
    private final By city= By.id("input-payment-city");
    private final By postcode= By.id("input-payment-postcode");
    private final By CountryDropdown= By.id("input-payment-country");
    private final By StateDropdown= By.id("input-payment-zone");
    private final By DeliveryAndBilladdressCheckbox= By.cssSelector("div label.custom-control-label[for=input-shipping-address-same]");
    private final By ReadAndAgreeTermsCheckbox= By.cssSelector("div label.custom-control-label[for=input-agree]");
    private final By ContinueButton= By.cssSelector("button#button-save");
    private final By ClickOnAddnewAddress= By.cssSelector("div label.custom-control-label[for=input-payment-address-new]");
    private final By quantityofproduct= By.cssSelector("input[type=number]");
    private final By Updatequantityofproduct= By.xpath("//i[contains(@class,'fa-sync')]");
    private final By FlateRate1= By.xpath("//label[@for='input-shipping-method-flat.flat']");
    private final By FlateRate2= By.xpath("//table[@id]//tr[2]//td[@class='text-right'][1]//strong");
    private final By TotalPrice= By.xpath("//table[@id]//tr//td[text()='Total:']//following::strong");
    public P07CheckOutPage(WebDriver driver) {
    this.driver=driver;
    }
    public String getTotalPrice(){
        new WebDriverWait(driver,Duration.ofSeconds(5));
        String TotalPriceText= Utility.getText(driver,TotalPrice);
        LogsUtil.info(String.valueOf(Float.parseFloat(TotalPriceText.replace("$",""))));
        return String.valueOf(Float.parseFloat(TotalPriceText.replace("$","")));
    }
    public String FlateRateinMethod(){
        String fullText= Utility.getText(driver,FlateRate1);
        LogsUtil.info(String.valueOf(Float.parseFloat(fullText.replace("Flat Shipping Rate - $",""))));
        return String.valueOf(Float.parseFloat(fullText.replace("Flat Shipping Rate - $","")));
    }
    public String FlateRateInCard(){
        String fullText= Utility.getText(driver,FlateRate2);
        LogsUtil.info(String.valueOf(Float.parseFloat(fullText.replace("$",""))));
        return String.valueOf(Float.parseFloat(fullText.replace("$","")));
    }
    public boolean ComparingFlateRatePrice(){
        return FlateRateinMethod().equals(FlateRateInCard());
    }

    public P07CheckOutPage FirstName(String firstnametext){
        Utility.SendData(driver,firstname,firstnametext);
        return this;
    }
    public P07CheckOutPage LastName(String Lastnametext){
        Utility.SendData(driver,lastname,Lastnametext);
        return this;
    }
    public P07CheckOutPage Company(String companytext){
        Utility.SendData(driver,company,companytext);
        return this;
    }
    public P07CheckOutPage Address1(String Address1text){
        Utility.SendData(driver,Address1,Address1text);
        return this;
    }
    public P07CheckOutPage Address2(String Address2text){
        Utility.SendData(driver,Address2,Address2text);
        return this;
    }
    public P07CheckOutPage City(String CityText){
        Utility.SendData(driver,city,CityText);
        return this;
    }
    public P07CheckOutPage Postcode(String postcodetext){
        Utility.SendData(driver,postcode,postcodetext);
        return this;
    }
    public P07CheckOutPage CountryDropdown(String countrytext){
        new Select(Utility.BytoWebelement(driver,CountryDropdown)).selectByVisibleText(countrytext);
        return this;
    }
    public P07CheckOutPage StateDropdown(String statetext){
        new Select(Utility.BytoWebelement(driver,StateDropdown)).selectByVisibleText(statetext);
        return this;
    }
    public P08ConfirmationPage ClickContinue(){
        /*if(driver.findElement(DeliveryAndBilladdressCheckbox).isSelected()){*/
            Utility.clickOnElement(driver,ReadAndAgreeTermsCheckbox);
            Utility.clickOnElement(driver,ContinueButton);
            //System.out.println("Delievery and billing address are the same: " + driver.findElement(DeliveryAndBilladdressCheckbox).isSelected());
        /*}else {
            LogsUtil.info("Add your shipping address");
        }*/
            return new P08ConfirmationPage(driver);
    }
    public P07CheckOutPage clickonAddnewAddress(){
        try {
            Utility.clickOnElement(driver,ClickOnAddnewAddress);
        }catch (Exception e){
            e.getMessage();
        }
        return this;
    }
    public P07CheckOutPage EnterQuantityOfProduct(String quantitytext){
        driver.findElement(quantityofproduct).clear();
        Utility.SendData(driver,quantityofproduct,quantitytext);
        return this;
    }
    public P07CheckOutPage clickUpdateProduct(){
        Utility.clickOnElement(driver,Updatequantityofproduct);
        return this;
    }
}
