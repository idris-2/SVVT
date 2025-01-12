import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class cartTest extends BaseClass {
    public void getToFoodPage() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tab-Sarajevo\"]/section[1]/div/div[3]/div/div/div[1]/div/div[6]/div/div"))).click();
        Thread.sleep(2000);
    }

    @Test
    @Order(1)
    public void addToCart() throws InterruptedException {
        // Navigate to the food page for the restaurant
        webDriver.get(baseUrl + "/partner/montana-otoka");
        // getToFoodPage();     // Uncomment to get a random food page, for our test tho im just using a page i know hase dodatci
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='d-none d-sm-inline' and text()='Dodaj']"))).click();
        Thread.sleep(2000);

        // List to store food names dynamically
        List<WebElement> checkboxes;
        List<String> labels = new ArrayList<>();
        try {
            // Check if "Dodatci" modal is shown
            String dodatci = webDriver.findElement(By.xpath("//*[@id=\"addonsModal\"]/div/div/div[1]/h4")).getText();
            checkboxes = webDriver.findElements(By.className("addon_ch"));

            // If add-ons exist, click the first one and store the label text
            if (!checkboxes.isEmpty()) {
                for (WebElement checkbox : checkboxes) {
                    WebElement label = checkbox.findElement(By.xpath("following-sibling::label"));
                    labels.add(label.getText());
                }

                // Click the first checkbox (you can modify this if needed to select others)
                checkboxes.get(0).click(); // Select the first checkbox
                Thread.sleep(2000);
            }

            // Click on the button to add the item to the cart
            webDriver.findElement(By.xpath("//*[@id=\"addCustomizedProduct\"]")).click();
        } catch (Exception e) {
            // If no "Dodatci" are found, print message
            System.out.println("No Dodatci!");
        }

        // Now, assert that the food was added to the cart
        verifyFoodAddedToCart(labels);
    }

    // Method to verify that the correct food was added to the cart
    private void verifyFoodAddedToCart(List<String> labels) throws InterruptedException {
        // Wait until the cart is visible and grab the order details
        String orderDetails = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_design\"]/div/div/div/p"))).getText();

        // Check if the order contains the expected food label (from add-ons or directly)
        if (!labels.isEmpty()) {
            boolean containsFood = orderDetails.contains(labels.get(0)); // Checking the first label if add-ons were present
            assertTrue(containsFood, "The correct food with add-on should be in the cart");
            System.out.println("Order details: " + orderDetails);
        } else {
            // In case no add-ons, verify the base food name (this part might need customization depending on your page structure)
            String baseFoodName = webDriver.findElement(By.xpath("//div[contains(@class, 'gold-members') and @data-name]")).getAttribute("data-name");
            boolean containsBaseFood = orderDetails.contains(baseFoodName);
            assertTrue(containsBaseFood, "The base food should be in the cart");
            System.out.println("Order details: " + orderDetails);
        }

        // Delay to allow for visual inspection
        Thread.sleep(5000);
    }
}
