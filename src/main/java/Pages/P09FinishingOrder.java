package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P09FinishingOrder {
    private final WebDriver driver;
    private final By OrderedSuccess= By.cssSelector("div h1");

    public P09FinishingOrder(WebDriver driver) {
        this.driver=driver;
    }

    public boolean CheckVisibilityOfOrderMessage(){
        return driver.findElement(OrderedSuccess).isDisplayed();
    }
}
