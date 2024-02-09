package goldenindia.CustomerPanelNewFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    public WebDriver driver;

    // Locator for order confirmation message
    @FindBy(css = "[class*=\"header_success\"]")
    WebElement orderConfirmationMessage;

    // Locator for order ID
    @FindBy(css = "[class*=\"payment_order_id\"]")
    WebElement orderIDElement;

    // Locator for download receipt button
    @FindBy(xpath = "//button[@type=\"button\"]//strong[text()=\"Download Receipt\"]")
    WebElement downloadReceiptButton;

    // Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to retrieve order ID from the confirmation page
    public void getOrderID() {
        String orderConfirmation = orderConfirmationMessage.getText();
        String[] orderIdArray = orderIDElement.getText().split("#");
        System.out.println("Order Confirmation Message: " + orderConfirmation);
        System.out.println("Order ID: " + orderIdArray[1]);
    }

    // Method to download the order receipt
    public void downloadOrderReceipt() {
        downloadReceiptButton.click();
    }
}
