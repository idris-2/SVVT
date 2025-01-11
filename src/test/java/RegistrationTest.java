import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrationTest extends BaseClass{
    public void sendDetails(String ime, String email, String telefon, String password) throws InterruptedException {
        webDriver.get(baseUrl + "/registracija");
        // Ime
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputFullName\"]"))).sendKeys(ime);
        // Email
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputEmail\"]"))).sendKeys(email);
        // Telefon
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputPhone\"]"))).sendKeys(telefon);
        // Password
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inputPassword\"]"))).sendKeys(password);
        // Button push
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target\"]/button"))).click();
        Thread.sleep(3000);
    }
    @Test
    @Order(1)
    public void emptyFieldsTest() throws InterruptedException {
        sendDetails("", "", "", "");
        assertEquals((baseUrl + "/registracija"), webDriver.getCurrentUrl());
    }
    @Test
    @Order(3)
    public void badInformationFeedbackTest() throws InterruptedException {
        sendDetails("Random", "Junk@gmail", "Random", "Junk");
        String emailValid = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target\"]/div[2]/span/strong"))).getText();
        String phoneArea = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target\"]/div[3]/span/strong"))).getText();
        String passReq = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"target\"]/div[4]/span/strong"))).getText();
        assertEquals("email mora biti validna e-mail adresa.", emailValid);
        assertEquals("phone polje je obavezno.", phoneArea);
        assertEquals("password mora biti najkraće 6 karaktera.", passReq);
    }
    @Test
    @Order(2)
    public void badInformationTest() throws InterruptedException {
        // Invalid data format
        sendDetails("Random", "Junk", "Random", "Junk");
        assertEquals((baseUrl + "/registracija"), webDriver.getCurrentUrl());
    }
}