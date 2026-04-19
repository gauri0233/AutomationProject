package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utils.SlowDown;

public class checkout {

    private WebDriver driver;
    private WebDriverWait wait;

    private By titleLocator    = By.cssSelector("span.title");
    private By firstNameInput  = By.id("first-name");
    private By lastNameInput   = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton  = By.id("continue");

    public checkout(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoaded() {
        System.out.println("[STEP] Verifying Checkout Information page is loaded...");
        boolean loaded = driver.getCurrentUrl().contains("checkout-step-one.html")
                && driver.findElement(titleLocator).getText().equals("Checkout: Your Information");

        if (loaded) {
            System.out.println("[DONE] Checkout: Your Information page loaded!");
            System.out.println("[INFO] Current URL: " + driver.getCurrentUrl());
        } else {
            System.out.println("[FAIL] Checkout Information page did NOT load.");
        }
        SlowDown.pause(2);
        return loaded;
    }

    public void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Filling in customer information form...");
        SlowDown.pause(1);

        System.out.println("[STEP] Entering First Name: '" + firstName + "'");
        driver.findElement(firstNameInput).sendKeys(firstName);
        System.out.println("[DONE] First Name entered.");
        SlowDown.pause(1);

        System.out.println("[STEP] Entering Last Name: '" + lastName + "'");
        driver.findElement(lastNameInput).sendKeys(lastName);
        System.out.println("[DONE] Last Name entered.");
        SlowDown.pause(1);

        System.out.println("[STEP] Entering Postal Code: '" + postalCode + "'");
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        System.out.println("[DONE] Postal Code entered.");
        SlowDown.pause(1);

        System.out.println("[DONE] All customer details filled.");
    }

    public void clickContinue() {
        System.out.println("[STEP] Clicking Continue button on Checkout: Your Information page...");
        // Wait until the button is clickable, then click
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        System.out.println("[DONE] Continue button clicked, navigating to Overview page.");
        SlowDown.pause(2);
    }
}