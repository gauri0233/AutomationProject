package tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Inventory;
import pages.Login;

import java.util.List;

public class cartTest extends BaseTest {

    // Scenario 2: add two products, verify cart badge
    @Test
    public void addTwoProducts_CartBadgeUpdates() {
        Login loginPage = new Login(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventory inventoryPage = new Inventory(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded");

        inventoryPage.addFirstNProductsToCart(2);

        int badgeCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, 2, "Cart badge should show 2 items");
    }

    // Scenario 5: sort by price low->high, check first three
    @Test
    public void sortByPriceLowToHigh_VerifyFirstThreeAscending() {
        Login loginPage = new Login(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventory inventoryPage = new Inventory(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded");

        inventoryPage.sortByPriceLowToHigh();

        List<Double> firstThreePrices = inventoryPage.getFirstNPrices(3);
        Assert.assertEquals(firstThreePrices.size(), 3, "Should get three prices");

        Assert.assertTrue(firstThreePrices.get(0) <= firstThreePrices.get(1),
                "First price should be <= second price");
        Assert.assertTrue(firstThreePrices.get(1) <= firstThreePrices.get(2),
                "Second price should be <= third price");
    }
}