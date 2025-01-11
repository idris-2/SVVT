import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest extends BaseClass{
    @Test
    public void LogInNavTest() throws InterruptedException {
        webDriver.get(baseUrl);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[2]/a"))).click();
        Thread.sleep(4000);
        String cloudFlare = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"KMfH6\"]"))).getText();
        assertEquals("Verify you are human by completing the action below.",cloudFlare);
        Thread.sleep(3000);
    }
}
