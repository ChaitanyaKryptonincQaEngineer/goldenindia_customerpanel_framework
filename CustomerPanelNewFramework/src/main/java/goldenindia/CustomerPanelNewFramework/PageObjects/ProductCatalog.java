package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.CustomerPanelNewFramework.Utilities.CommonUtilities;

public class ProductCatalog extends CommonUtilities {

    WebDriver driver;

    // Constructor
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators for product names
    @FindBy(css = "[class*=\"slider_title\"]")
    List<WebElement> productNames;

    // Locator for add to cart button
    @FindBy(xpath = "(//button[@type=\"button\"])[12]")
    WebElement addToCartButton;

    // Method to add a single product to the cart
    public CheckOutPage addSingleProductToCart() throws IOException, InterruptedException {
        String selectedProduct = System.getProperty("productName") != null ? System.getProperty("productName")
                : getValueFromPropertyFile("productName");

        System.out.println("Selected Product: " + selectedProduct);

        // Printing all product names for debugging
        for (WebElement productName : productNames) {
            System.out.println("Available Product: " + productName.getText());
        }

        // Loop through product names to find the selected product
        for (WebElement productName : productNames) {
            if (productName.getText().equals(selectedProduct)) {
                System.out.println("Selected product found.");
                scrollToElementUsingJavascript(productName);
                clickElementUsingJavascript(productName);
                addToCartButton.click();
                break;
            } else {
                // If the selected product is not found, add the first product to the cart
                scrollToElementUsingJavascript(productNames.get(0));
                clickElementUsingJavascript(productNames.get(0));
                addToCartButton.click();
                break;
            }
        }

        // Initialize and return the CheckOutPage
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }
}
