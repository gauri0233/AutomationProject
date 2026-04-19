package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SlowDown;

public class cartItem {

    private WebDriver driver;

    private By cartIcon       = By.id("shopping_cart_container");
    private By titleLocator   = By.cssSelector("span.title");
    private By checkoutButton = By.id("checkout");

    public cartItem(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Locating cart icon on top-right of page...");
        SlowDown.pause(1);
        System.out.println("[DONE] Cart icon found.");

        System.out.println("[STEP] Clicking on cart icon to open the cart...");
        SlowDown.pause(2);
        driver.findElement(cartIcon).click();
        System.out.println("[DONE] Cart icon clicked. Navigating to Cart page...");
        SlowDown.pause(2);
    }

    public boolean isLoaded() {
        System.out.println("[STEP] Verifying Cart page is loaded...");
        boolean loaded = driver.getCurrentUrl().contains("cart.html")
                && driver.findElement(titleLocator).getText().equals("Your Cart");

        if (loaded) {
            System.out.println("[DONE] Cart page loaded successfully!");
            System.out.println("[INFO] Current URL: " + driver.getCurrentUrl());
        } else {
            System.out.println("[FAIL] Cart page did NOT load correctly.");
        }
        SlowDown.pause(2);
        return loaded;
    }

    public void clickCheckout() {
        System.out.println("[STEP] Locating CHECKOUT button on cart page...");
        SlowDown.pause(1);
        System.out.println("[DONE] Checkout button found.");

        System.out.println("[STEP] Clicking CHECKOUT button...");
        SlowDown.pause(2);
        driver.findElement(checkoutButton).click();
        System.out.println("[DONE] Checkout button clicked. Moving to checkout information page...");
        SlowDown.pause(2);
    }
}