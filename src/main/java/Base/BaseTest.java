package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.SlowDown;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected final String baseUrl = "https://www.saucedemo.com/";

    @BeforeMethod
    public void setUp() {
        System.out.println("========================================");
        System.out.println("  TEST STARTING...");
        System.out.println("========================================");

        System.out.println("[STEP] Setting up ChromeDriver using WebDriverManager...");
        WebDriverManager.chromedriver().setup();
        System.out.println("[DONE] ChromeDriver setup complete.");

        // === Chrome options (including popup removal) ===
        ChromeOptions options = new ChromeOptions();

        // your old behavior
        options.addArguments("--start-maximized");

        // new: run clean and disable popups
        options.addArguments("--incognito");              // prevents stored credentials popup
        options.addArguments("--disable-notifications");  // blocks site notifications

        // Disable Chrome password manager completely
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        // optional: also block notification prompts at prefs level
        // prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);
        // ===============================================

        System.out.println("[STEP] Launching Chrome browser...");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("[DONE] Chrome browser launched and maximized.");

        System.out.println("[STEP] Navigating to SauceDemo: " + baseUrl);
        driver.get(baseUrl);
        System.out.println("[DONE] SauceDemo website loaded successfully.");
        SlowDown.pause(2);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("----------------------------------------");
        System.out.println("[STEP] Test completed. Pausing before closing browser...");
        SlowDown.pause(3);
        System.out.println("[STEP] Closing browser...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("[DONE] Browser closed.");
        System.out.println("========================================");
        System.out.println("  TEST FINISHED.");
        System.out.println("========================================\n");
    }
}