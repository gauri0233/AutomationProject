package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.SlowDown;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private WebDriver driver;

    private By titleLocator     = By.cssSelector("span.title");
    private By cartBadge        = By.cssSelector(".shopping_cart_badge");
    private By addToCartButtons = By.cssSelector(".inventory_item button.btn_inventory");
    private By sortDropdown     = By.cssSelector("select.product_sort_container");
    private By itemPrices       = By.cssSelector(".inventory_item .inventory_item_price");

    public Inventory(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        System.out.println("[STEP] Checking if Products (Inventory) page is loaded...");
        boolean urlOk    = driver.getCurrentUrl().contains("inventory.html");
        boolean titleOk  = driver.findElement(titleLocator).getText().equals("Products");
        boolean loaded   = urlOk && titleOk;

        if (loaded) {
            System.out.println("[DONE] Products page is loaded!");
            System.out.println("[INFO] Current URL : " + driver.getCurrentUrl());
            System.out.println("[INFO] Page Title  : " + driver.findElement(titleLocator).getText());
        } else {
            System.out.println("[FAIL] Products page did NOT load correctly.");
            System.out.println("[INFO] Current URL : " + driver.getCurrentUrl());
        }
        SlowDown.pause(2);
        return loaded;
    }

    public void addFirstNProductsToCart(int n) {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Preparing to add " + n + " product(s) to cart...");
        SlowDown.pause(1);

        List<WebElement> buttons = driver.findElements(addToCartButtons);
        System.out.println("[INFO] Total 'Add to Cart' buttons found on page: " + buttons.size());
        SlowDown.pause(1);

        for (int i = 0; i < n && i < buttons.size(); i++) {
            System.out.println("[STEP] Adding product " + (i + 1) + " of " + n + " to cart...");
            SlowDown.pause(2);
            buttons.get(i).click();
            System.out.println("[DONE] Product " + (i + 1) + " added to cart successfully!");
            SlowDown.pause(2);
        }
        System.out.println("[DONE] All " + n + " product(s) added to cart.");
    }

    public int getCartBadgeCount() {
        System.out.println("[STEP] Reading cart badge count...");
        if (driver.findElements(cartBadge).isEmpty()) {
            System.out.println("[INFO] Cart badge is not visible. Cart is empty (count = 0).");
            return 0;
        }
        int count = Integer.parseInt(driver.findElement(cartBadge).getText());
        System.out.println("[DONE] Cart badge count is: " + count);
        SlowDown.pause(2);
        return count;
    }

    public void sortByPriceLowToHigh() {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Locating the sort dropdown...");
        SlowDown.pause(1);
        System.out.println("[DONE] Sort dropdown found.");

        System.out.println("[STEP] Selecting option: 'Price (Low to High)'...");
        SlowDown.pause(2);
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("lohi");
        System.out.println("[DONE] Sort option selected: Price (Low to High).");

        System.out.println("[INFO] Products are now re-ordered by price ascending.");
        SlowDown.pause(2);
    }

    public List<Double> getFirstNPrices(int n) {
        System.out.println("[STEP] Reading the first " + n + " product prices from sorted list...");
        SlowDown.pause(1);

        List<WebElement> prices = driver.findElements(itemPrices);
        List<Double> result     = new ArrayList<>();

        for (int i = 0; i < n && i < prices.size(); i++) {
            String raw   = prices.get(i).getText().replace("$", "");
            double price = Double.parseDouble(raw);
            System.out.println("[INFO] Product " + (i + 1) + " price: $" + price);
            result.add(price);
            SlowDown.pause(1);
        }

        System.out.println("[DONE] Price reading complete. Prices collected: " + result);
        SlowDown.pause(1);
        return result;
    }
}