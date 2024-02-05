package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.CustomerPanelNewFramework.Utilities.BoilerPlateCode;

public class ProductCatelogue extends BoilerPlateCode {

	WebDriver driver;

	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[class*=\"slider_title\"]")
	List<WebElement> productNames;

	@FindBy(xpath = "(//button[@type=\"button\"])[12]")
	WebElement addToCartBtn;

	public CheckOutPage addingSingleProductToCart() throws IOException, InterruptedException {
		String newValue = System.getProperty("productName") != null ? System.getProperty("productName")
				: gettingValueFromPropertyFile("productName");

		System.out.println("Product Name is " + newValue);
		for (WebElement productName : productNames) {
		    if (productName.getText().equals(newValue)) {
		        System.out.println("Both are equal");
		        scrollingToParticularElementUsingJavascriptExecutor(productName);
		        clickingOnParticularElementUsingJavascriptExecutor(productName);
		        addToCartBtn.click();
		        break;
		    } else {
		        scrollingToParticularElementUsingJavascriptExecutor(productNames.get(0));
		        clickingOnParticularElementUsingJavascriptExecutor(productNames.get(0));
		    }
		}


		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
