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

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class interestingFunctionality {
    private static WebDriver webDriver;
    private static String baseUrl = "https://www.korpa.ba/";
    private static WebDriverWait webDriverWait;


    @BeforeAll
    public static void setUp() {
        // Setup path for my environment:       C:\\Users\\User\\AppData\\Roaming\\undetected_chromedriver\\undetected_chromedriver.exe
        // Chromedriver that comes with repo    chromedriver-win64/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(100));
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void feature1() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(5000);
    }
    @Test
    public void feature2() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(5000);
    }
    @Test
    public void feature3() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(5000);
    }
    @Test
    public void feature4() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(5000);
    }
    @Test
    public void feature5() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(5000);
    }
}
