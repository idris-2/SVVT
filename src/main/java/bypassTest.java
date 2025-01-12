
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
/*
Tried to bypass cloudflare, but it didn't work
*/
public class bypassTest {

    private WebDriver driver;
    private int maxRetries;
    private boolean log;

    public bypassTest(WebDriver driver, int maxRetries, boolean log) {
        this.driver = driver;
        this.maxRetries = maxRetries;
        this.log = log;
    }

    private WebElement searchRecursivelyShadowRootWithIframe(WebElement ele) {
        if (ele.getShadowRoot() != null) {
            WebElement shadowRootChild = ele.getShadowRoot().findElement(By.tagName("iframe"));
            if (shadowRootChild != null) {
                return shadowRootChild;
            }
        } else {
            for (WebElement child : ele.findElements(By.xpath("./*"))) {
                WebElement result = searchRecursivelyShadowRootWithIframe(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private WebElement searchRecursivelyShadowRootWithCfInput(WebElement ele) {
        if (ele.getShadowRoot() != null) {
            WebElement shadowRootInput = ele.getShadowRoot().findElement(By.cssSelector("input"));
            if (shadowRootInput != null) {
                return shadowRootInput;
            }
        } else {
            for (WebElement child : ele.findElements(By.xpath("./*"))) {
                WebElement result = searchRecursivelyShadowRootWithCfInput(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private WebElement locateCfButton() {
        WebElement button = null;
        try {
            // Find all input elements
            java.util.List<WebElement> eles = driver.findElements(By.tagName("input"));
            for (WebElement ele : eles) {
                String name = ele.getAttribute("name");
                String type = ele.getAttribute("type");
                if (name != null && type != null) {
                    if (name.contains("turnstile") && type.equals("hidden")) {
                        button = ele.findElement(By.xpath("./..")).getShadowRoot().findElement(By.tagName("body")).getShadowRoot().findElement(By.tagName("input"));
                        break;
                    }
                }
            }

            if (button == null) {
                logMessage("Basic search failed. Searching for button recursively.");
                WebElement body = driver.findElement(By.tagName("body"));
                WebElement iframe = searchRecursivelyShadowRootWithIframe(body);
                if (iframe != null) {
                    button = searchRecursivelyShadowRootWithCfInput(iframe.findElement(By.tagName("body")));
                } else {
                    logMessage("Iframe not found. Button search failed.");
                }
            }
        } catch (Exception e) {
            logMessage("Error locating the button: " + e.getMessage());
        }
        return button;
    }

    private void logMessage(String message) {
        if (log) {
            System.out.println(message);
        }
    }

    private void clickVerificationButton() {
        try {
            WebElement button = locateCfButton();
            if (button != null) {
                logMessage("Verification button found. Attempting to click.");
                button.click();
            } else {
                logMessage("Verification button not found.");
            }
        } catch (Exception e) {
            logMessage("Error clicking verification button: " + e.getMessage());
        }
    }

    private boolean isBypassed() {
        try {
            String title = driver.getTitle().toLowerCase();
            return !title.contains("just a moment");
        } catch (Exception e) {
            logMessage("Error checking page title: " + e.getMessage());
            return false;
        }
    }

    public void bypass() {
        int tryCount = 0;

        while (!isBypassed()) {
            if (maxRetries > 0 && tryCount >= maxRetries) {
                logMessage("Exceeded maximum retries. Bypass failed.");
                break;
            }

            logMessage("Attempt " + (tryCount + 1) + ": Verification page detected. Trying to bypass...");
            clickVerificationButton();

            tryCount++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (isBypassed()) {
            logMessage("Bypass successful.");
        } else {
            logMessage("Bypass failed.");
        }
    }

    public static void main(String[] args) {
        // Example usage
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\AppData\\Roaming\\undetected_chromedriver\\undetected_chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://korpa.ba/kategorije/57");

        bypassTest bypasser = new bypassTest(driver, 5, true);
        bypasser.bypass();
    }
}
