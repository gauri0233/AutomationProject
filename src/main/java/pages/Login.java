package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SlowDown;

public class Login {

    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton   = By.id("login-button");
    private By errorMessage  = By.cssSelector("h3[data-test='error']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Starting login process...");
        SlowDown.pause(1);

        System.out.println("[STEP] Clearing username field...");
        driver.findElement(usernameInput).clear();
        System.out.println("[DONE] Username field cleared.");
        SlowDown.pause(1);

        System.out.println("[STEP] Typing username: '" + username + "'");
        driver.findElement(usernameInput).sendKeys(username);
        System.out.println("[DONE] Username entered successfully.");
        SlowDown.pause(1);

        System.out.println("[STEP] Clearing password field...");
        driver.findElement(passwordInput).clear();
        System.out.println("[DONE] Password field cleared.");
        SlowDown.pause(1);

        System.out.println("[STEP] Typing password: '********' (hidden for security)");
        driver.findElement(passwordInput).sendKeys(password);
        System.out.println("[DONE] Password entered successfully.");
        SlowDown.pause(1);

        System.out.println("[STEP] Both fields filled. Ready to submit login form.");
        SlowDown.pause(1);

        System.out.println("[STEP] Clicking the LOGIN button...");
        driver.findElement(loginButton).click();
        System.out.println("[DONE] Login button clicked. Waiting for page response...");
        SlowDown.pause(2);
    }

    public boolean isErrorDisplayed() {
        boolean displayed = !driver.findElements(errorMessage).isEmpty();
        if (displayed) {
            System.out.println("[INFO] Error message element is visible on page.");
        } else {
            System.out.println("[INFO] No error message found on page.");
        }
        return displayed;
    }

    public String getErrorMessage() {
        System.out.println("[STEP] Reading error message text from page...");
        String msg = driver.findElement(errorMessage).getText();
        System.out.println("[DONE] Error message captured: \"" + msg + "\"");
        SlowDown.pause(2);
        return msg;
    }
}