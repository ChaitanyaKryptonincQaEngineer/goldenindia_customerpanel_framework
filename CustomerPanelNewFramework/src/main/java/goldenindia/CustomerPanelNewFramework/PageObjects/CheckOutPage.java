package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.CustomerPanelNewFramework.Utilities.CommonUtilities;

public class CheckOutPage extends CommonUtilities {

	public WebDriver driver;

	// Locator for delivery order type button
	@FindBy(xpath = "//button[@type=\"button\" and text()='Delivery']")
	WebElement deliveryOrderType;

	// Locator for pickup order type button
	@FindBy(xpath = "//button[@type=\"button\" and text()='Pickup']")
	WebElement pickUpOrderType;

	// Locator for delivery address input field
	@FindBy(xpath = "//input[@placeholder=\"Add your address  ... \"]")
	WebElement deliveryAddressInput;

	// Locator for date entry dropdown
	@FindBy(id = "react-select-2-placeholder")
	WebElement dateEntryDropdown;

	// Locator for time entry dropdown
	@FindBy(id = "react-select-3-placeholder")
	WebElement timeEntryDropdown;

	// Locator for minimum quantity error message
	@FindBy(css = "[class*=\"alert-danger\"]")
	WebElement minimumQuantityErrorMessage;

	// Locator for checkout button
	@FindBy(css = "[class*=\"btn-lg\"]")
	WebElement checkoutButton;

	// Locator for quantity increase icon
	@FindBy(xpath = "//*[local-name()=\"svg\" and @data-testid=\"AddCircleOutlineIcon\"]")
	WebElement quantityIncreaseIcon;

	// Constructor
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to select the order type (delivery/pickup)
	public PaymentPage selectOrderType() {
		try {
			String orderType = System.getProperty("orderType") != null ? System.getProperty("orderType")
					: getValueFromPropertyFile("orderType");
			if (orderType.equals("Delivery")) {
				System.out.println("Order Type: Delivery");
				enterDeliveryDetails();
			} else if (orderType.equals("Pickup")) {
				System.out.println("Order Type: Pick Up");
				enterPickUpDetails();
			}
		} catch (Exception e) {
			// Handle exception
		}
		// Initialize and return PaymentPage
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}

	// Method to enter delivery address
	public void enterDeliveryAddress() throws IOException, InterruptedException {
		String deliveryAddress = System.getProperty("deliveryAddress") != null ? System.getProperty("deliveryAddress")
				: getValueFromPropertyFile("deliveryAddress");
		deliveryAddressInput.sendKeys(deliveryAddress);
		Thread.sleep(2000);
		movingToParticularElementUsingActionClass(deliveryAddressInput).sendKeys(Keys.DOWN, Keys.ENTER).build()
				.perform();
	}

	public void enteringDateAndTime() throws InterruptedException {
		// SELECTING THE DATE FROM THE DROP DOWN
		movingToParticularElementUsingActionClass(dateEntryDropdown).click().build().perform();
		Thread.sleep(1000);
		movingToParticularElementUsingActionClass(dateEntryDropdown).sendKeys(Keys.ENTER).build().perform();

		// SELECTING THE TIME FROM THE DROP DOWN
		movingToParticularElementUsingActionClass(timeEntryDropdown).click().build().perform();
		Thread.sleep(1000);
		movingToParticularElementUsingActionClass(timeEntryDropdown).sendKeys(Keys.ENTER).build().perform();

	}

	// Method to click on delivery order type
	public void clickOnDelivery() {
		deliveryOrderType.click();
	}

	// Method to retrieve minimum order value
	public void retrieveMinimumOrderValue() throws InterruptedException {
		String extractedNumber = "";
		checkoutButton.click();
		waitForVisibility(minimumQuantityErrorMessage);
		String errorMessage = minimumQuantityErrorMessage.getText();
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
		Matcher matcher = pattern.matcher(errorMessage);
		if (matcher.find()) {
			extractedNumber = matcher.group();
			System.out.println("Extracted Number: " + extractedNumber);
		} else {
			System.out.println("No number found in the message.");
		}

		if (checkoutButton.getText().contains(extractedNumber)) {
			checkoutButton.click();
		} else {
			while (!checkoutButton.getText().contains(extractedNumber)) {
				quantityIncreaseIcon.click();
				Thread.sleep(1000);
				checkoutButton.click();
			}
		}
	}

	// Method to click on pickup order type
	public void clickOnPickUp() {
		pickUpOrderType.click();
	}

	// Method to enter delivery details
	public void enterDeliveryDetails() throws IOException, InterruptedException {
		clickOnCartIcon();
		clickOnDelivery();
		enterDeliveryAddress();
		enteringDateAndTime();
		retrieveMinimumOrderValue();
	}

	// Method to enter pickup details
	public void enterPickUpDetails() throws InterruptedException {
		clickOnCartIcon();
		clickOnPickUp();
		enteringDateAndTime();
		retrieveMinimumOrderValue();
	}
}
