package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutcomplete {

    private WebDriver driver;

    private By titleLocator     = By.cssSelector("span.title");
    private By completeHeader   = By.cssSelector(".complete-header");

    public checkoutcomplete(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("checkout-complete.html")
                && driver.findElement(titleLocator).getText().equals("Checkout: Complete!");
    }

    public String getCompletionMessage() {
        return driver.findElement(completeHeader).getText();
    }
}