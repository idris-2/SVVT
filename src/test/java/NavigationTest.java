import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NavigationTest extends BaseClass{
    @Test
    @Order(1)
    public void partnerTest(){
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[5]/div/div/div/div[2]/div[1]/div/div[2]/div/a"))).click();
        String onPage = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div[3]/div/div/div/div/h1"))).getText();
        assertEquals("Postanite korpa partner", onPage);
        assertEquals("https://korpa.ba/partner", webDriver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div[3]/div/div/div/div/a"))).click();
        assertEquals("https://korpa.ba/partner#apliciraj", webDriver.getCurrentUrl());
    }
    @Test
    @Order(2)
    public void partnerRequestTest(){
        // You should not be able to send a request with empty fields
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[5]/div/div/div/div[2]/div[1]/div/div[2]/div/a"))).click();
        String onPage = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div[3]/div/div/div/div/h1"))).getText();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div[3]/div/div/div/div/a"))).click();

        // Show network traffic and validate my suspicion
    }
    @Test
    @Order(3)
    public void kurirTest() throws InterruptedException {
        // Can't go further cuz CloudFlare
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[5]/div/div/div/div[2]/div[2]/div/div[2]/div/a"))).click();

        assertEquals(baseUrl + "/kurir", webDriver.getCurrentUrl());
        Thread.sleep(2000);
        //assertEquals();
    }
    @Test
    @Order(4)
    public void korpaTeamTest() throws InterruptedException {
        // Can't go further cuz CloudFlare
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[5]/div/div/div/div[2]/div[3]/div/div[2]/div/a"))).click();

        assertEquals((baseUrl + "/posao"), webDriver.getCurrentUrl());
        Thread.sleep(2000);
    }
    @Test
    @Order(5)
    public void restaurantFoodTest() throws InterruptedException {
        webDriver.get(baseUrl);
        String titleBeforeClick = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tab-Sarajevo\"]/section[1]/div/div[3]/div/div/div[1]/div/div[6]/div/div/div[2]/div/h6/a"))).getText();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tab-Sarajevo\"]/section[1]/div/div[3]/div/div/div[1]/div/div[6]/div/div"))).click();

        String titleAfterClick = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div[3]/div/div/div[1]/div/h1"))).getText();
        // Test correct page after click
        assertEquals(titleBeforeClick, titleAfterClick);
        // Test different page after click
        assertNotEquals(baseUrl, webDriver.getCurrentUrl());
        Thread.sleep(2000);
    }
}
/*
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v109.network.Network;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class NetworkTrafficTest {

    private WebDriver driver;
    private DevTools devTools;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // Set up CDP for capturing network traffic
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.of(100000), Optional.of(100000), Optional.of(100000)));

        // Listen to network events
        devTools.addListener(Network.requestWillBeSent(), request -> {
            System.out.println("Request URL: " + request.getRequest().getUrl());
            System.out.println("Request Payload: " + request.getRequest().getPostData());
        });
    }

    @Test
    public void testNetworkTraffic() {
        driver.get("https://example.com");

        // Perform some actions on the page that will trigger requests
        WebElement button = driver.findElement(By.id("myButton"));
        button.click();

        // You can add assertions here based on the network traffic captured
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

*/