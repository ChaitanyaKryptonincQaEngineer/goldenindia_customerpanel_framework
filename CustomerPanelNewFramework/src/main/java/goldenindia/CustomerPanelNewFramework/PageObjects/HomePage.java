package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators for branch names
    @FindBy(css = "[class*=\"header_name\"]")
    public List<WebElement> branchNameElements;

    // Locators for order now buttons
    @FindBy(css = "[class*=\"custom_hover\"]")
    public List<WebElement> orderNowButtons;

    // Locator for branch welcome message
    @FindBy(css = "[class=\"sc-87747fd2-0 bkSLXW\"]")
    public WebElement branchWelcomeMessage;

    // Method to navigate to a particular branch
    public ProductCatalog navigateToBranch() throws IOException {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                    + "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customerPanel.properties");
            properties.load(fileInputStream);

            String selectedBranch = System.getProperty("branch") != null ? System.getProperty("branch")
                    : properties.getProperty("branch");

            for (int i = 0; i < branchNameElements.size(); i++) {
                if (branchNameElements.get(i).getText().equals(selectedBranch)) {
                    orderNowButtons.get(i).click();
                    break;
                }
            }
            System.out.println("Selected Branch: " + selectedBranch);
        } catch (Exception e) {
            System.err.println("Error occurred while selecting branch: " + e.getMessage());
            driver.navigate().refresh();
            orderNowButtons.get(0).click(); // Clicking on the first order now button as fallback
        }
        System.out.println("Branch Welcome Message: " + branchWelcomeMessage.getText());
        // Initialize and return ProductCatalog page
        ProductCatalog productCatalogPage = new ProductCatalog(driver);
        return productCatalogPage;
    }

    // Method to navigate to the home page URL
    public void navigateToURL() {
        driver.get("http://mcd.mypreview.xyz/");
    }
}
