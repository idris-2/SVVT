import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
NOTES:
- Most of the website is captcha protected (CloudFlare), therefor I could not test many things
nor access them.
-
*/
public class BaseClass {
    protected static WebDriver webDriver;
    protected final static String baseUrl = "https://korpa.ba";
    protected static WebDriverWait webDriverWait;
    Actions action = new Actions(webDriver);
    JavascriptExecutor js = (JavascriptExecutor) webDriver;

    @BeforeAll
    public static void setUp() {
        // Setup path for my environment:       C:\\Users\\User\\AppData\\Roaming\\undetected_chromedriver\\undetected_chromedriver.exe
        // Chromedriver that comes with repo    chromedriver-win64/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\AppData\\Roaming\\undetected_chromedriver\\undetected_chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

/*
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
*/