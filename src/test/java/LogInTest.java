import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LogInTest extends BaseClass{
    public void sendLogInDetails(String email, String password){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputEmail\"]"))).sendKeys(email);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputPassword\"]"))).sendKeys(password);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target\"]/button"))).click();
    }
    @Test
    @Order(1)
    public void LogInNavTest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[2]/a"))).click();
        Thread.sleep(4000);
        String cloudFlare = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"KMfH6\"]"))).getText();
        assertEquals("Verify you are human by completing the action below.",cloudFlare);
        Thread.sleep(3000);
    }
    @Test
    @Order(2)
    public void logInDirect() throws InterruptedException {
        webDriver.get(baseUrl + "/login");
        // We can get tempMail from registration class, but for simplicity:
        RegistrationTest lol = new RegistrationTest();
        System.out.println(lol.getTempMail());

        sendLogInDetails("temp@mail.com", "12345678");
        Thread.sleep(3000);
        assertNotEquals(baseUrl, (baseUrl + "/login"));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[3]/a"))).click();
        String accName = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div[1]/div/div/div/div/div/h6"))).getText();
        String accPhone = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div[1]/div/div/div/div/div/p[1]"))).getText();
        String accEmail = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div[1]/div/div/div/div/div/p[2]"))).getText();

        assertEquals("Sulejman", accName);
        assertEquals("555555555", accPhone);
        assertEquals("temp@mail.com", accEmail);

        Thread.sleep(5000);
    }
}
