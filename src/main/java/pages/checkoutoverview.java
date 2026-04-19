package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutoverview {

    private WebDriver driver;

    private By titleLocator  = By.cssSelector("span.title");
    private By finishButton  = By.id("finish");

    public checkoutoverview(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("checkout-step-two.html")
                && driver.findElement(titleLocator).getText().equals("Checkout: Overview");
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}