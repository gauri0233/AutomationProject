package tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Inventory;
import pages.Login;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "success"},
                {"locked_out_user", "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    // Data-driven login test (requirement: parameterised test)
    @Test(dataProvider = "loginData")
    public void loginScenarios(String username, String password, String expectedResult) {
        Login loginPage = new Login(driver);
        loginPage.login(username, password);

        if ("success".equals(expectedResult)) {
            Inventory inventoryPage = new Inventory(driver);
            Assert.assertTrue(inventoryPage.isLoaded(),
                    "Inventory page should be loaded for successful login");
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
            Assert.assertEquals(loginPage.getErrorMessage(), expectedResult);
        }
    }

    // Scenario 1: successful login explicitly
    @Test
    public void successfulLoginStandardUser_VerifyProductsPage() {
        Login loginPage = new Login(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventory inventoryPage = new Inventory(driver);
        Assert.assertTrue(inventoryPage.isLoaded(),
                "Products page should load after logging in as standard_user");
    }
}