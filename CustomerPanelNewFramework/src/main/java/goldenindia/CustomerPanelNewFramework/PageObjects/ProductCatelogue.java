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

		int count = 0;
		System.out.println("Total Products in Customer Panel are :");
		for (WebElement webElement : productNames) {
			System.out.println(webElement.getText());
			count += 1;
		}
		System.out.println("Total Elements are  " + count);

		for (WebElement productName : productNames) {
			if (productName.getText().contains(newValue)) {
				scrollingToParticularElementUsingJavascriptExecutor(productName);
				clickingOnParticularElementUsingJavascriptExecutor(productName);
				addToCartBtn.click();
			} else if (productName.getText() != newValue) {
				scrollingToParticularElementUsingJavascriptExecutor(productName);
				clickingOnParticularElementUsingJavascriptExecutor(productName);
				addToCartBtn.click();
				Thread.sleep(2000);
				break;
			}
		}

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
