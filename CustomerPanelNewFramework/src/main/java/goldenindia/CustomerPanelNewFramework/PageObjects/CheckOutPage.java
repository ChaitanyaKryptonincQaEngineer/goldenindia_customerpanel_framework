package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.CustomerPanelNewFramework.Utilities.BoilerPlateCode;

public class CheckOutPage extends BoilerPlateCode {

	public WebDriver driver;

	@FindBy(xpath = "//button[@type=\"button\" and text()='Delivery']")
	WebElement deliveryOrderType;

	@FindBy(xpath = "//button[@type=\"button\" and text()='Pickup']")
	WebElement pickUpOrderType;

	@FindBy(xpath = "//input[@placeholder=\"Add your address  ... \"]")
	WebElement deliveryAddressLabel;

	@FindBy(id = "react-select-2-placeholder")
	WebElement dateEntryLabel;

	@FindBy(id = "react-select-3-placeholder")
	WebElement timeEntryLabel;

	@FindBy(css = "[class*=\"alert-danger\"]")
	WebElement minimumQuantityErrorMessage;

	@FindBy(css = "[class*=\"btn-lg\"]")
	WebElement checkoutBtn;

	@FindBy(xpath = "//*[local-name()=\"svg\" and @data-testid=\"AddCircleOutlineIcon\"]")
	WebElement quantityIncreaseIcon;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public PaymentPage selectingParticularOrderType() {
		try {
			String orderType = System.getProperty("orderType") != null ? System.getProperty("orderType")
					: gettingValueFromPropertyFile("orderType");
			if (orderType.equals("Delivery")) {
				System.out.println("Order Type is Delivery");
				enteringDeliveryDetails();
			} else if (orderType.equals("Pickup")) {
				System.out.println("Order Type is Pick Up");
				enteringPickUpDetails();
			}
		} catch (Exception e) {

		}
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}

	public void enteringDeliveryAddress() throws IOException, InterruptedException {
		String deliveryAddress = System.getProperty("deliveryAddress") != null ? System.getProperty("deliveryAddress")
				: gettingValueFromPropertyFile("deliveryAddress");
		deliveryAddressLabel.sendKeys(deliveryAddress);
		Thread.sleep(2000);
		movingToParticularElementUsingActionClass(deliveryAddressLabel).sendKeys(Keys.DOWN, Keys.ENTER).build()
				.perform();
	}

	public void enteringDateAndTime() throws InterruptedException {
		// SELECTING THE DATE FROM THE DROP DOWN
		movingToParticularElementUsingActionClass(dateEntryLabel).click().build().perform();
		Thread.sleep(1000);
		movingToParticularElementUsingActionClass(dateEntryLabel).sendKeys(Keys.ENTER).build().perform();

		// SELECTING THE TIME FROM THE DROP DOWN
		movingToParticularElementUsingActionClass(timeEntryLabel).click().build().perform();
		Thread.sleep(1000);
		movingToParticularElementUsingActionClass(timeEntryLabel).sendKeys(Keys.ENTER).build().perform();

	}

	public void clickingOnDelivery() {
		deliveryOrderType.click();
	}

	public void gettingMinimumOrderValue() throws InterruptedException {
		String extractedNumber = "";
		checkoutBtn.click();
		super.visibilityOfWebElement(minimumQuantityErrorMessage);
		String errorMessage = minimumQuantityErrorMessage.getText();
		Pattern pattern = Pattern.compile("\\b\\d+\\b");
		Matcher matcher = pattern.matcher(errorMessage);
		if (matcher.find()) {
			extractedNumber = matcher.group();
			System.out.println("Extracted Number: " + extractedNumber);
		} else {
			System.out.println("No number found in the message.");
		}

		if (checkoutBtn.getText().contains(extractedNumber)) {
			checkoutBtn.click();
		} else {
			while (!checkoutBtn.getText().contains(extractedNumber)) {
				quantityIncreaseIcon.click();
				Thread.sleep(1000);
				checkoutBtn.click();
			}
		}
	}

	public void clickingOnPickUp() {
		pickUpOrderType.click();
	}

	public void enteringDeliveryDetails() throws IOException, InterruptedException {
		super.clickingOnCartIcon();
		this.clickingOnDelivery();
		this.enteringDeliveryAddress();
		this.enteringDateAndTime();
		this.gettingMinimumOrderValue();
	}

	public void enteringPickUpDetails() throws InterruptedException {
		super.clickingOnCartIcon();
		this.clickingOnPickUp();
		this.enteringDateAndTime();
		this.gettingMinimumOrderValue();

	}
}
