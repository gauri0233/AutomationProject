package tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class checkoutTest extends BaseTest {

    // Scenario 3: full checkout, verify confirmation message
    @Test
    public void completeCheckout_EndToEnd_VerifyConfirmation() {
        Login loginPage = new Login(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventory inventoryPage = new Inventory(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded");

        inventoryPage.addFirstNProductsToCart(2);

        cartItem cartPage = new cartItem(driver);
        cartPage.openCart();
        Assert.assertTrue(cartPage.isLoaded(), "Cart page should be loaded");

        cartPage.clickCheckout();

        checkout infoPage = new checkout(driver);
        Assert.assertTrue(infoPage.isLoaded(), "Checkout information page should be loaded");

        infoPage.fillCustomerInformation("John", "Doe", "12345");
        infoPage.clickContinue();

        checkoutoverview overviewPage = new checkoutoverview(driver);
        Assert.assertTrue(overviewPage.isLoaded(), "Checkout overview page should be loaded");

        overviewPage.clickFinish();

        checkoutcomplete completePage = new checkoutcomplete(driver);
        Assert.assertTrue(completePage.isLoaded(), "Checkout complete page should be loaded");

        String message = completePage.getCompletionMessage();
        Assert.assertEquals(message, "Thank you for your order!",
                "Confirmation message should match");
    }
}