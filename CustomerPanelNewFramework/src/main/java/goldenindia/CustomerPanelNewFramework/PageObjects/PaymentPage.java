package goldenindia.CustomerPanelNewFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import goldenindia.CustomerPanelNewFramework.Utilities.BoilerPlateCode;

public class PaymentPage extends BoilerPlateCode {

	public WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder=\"Enter your full name *\"]")
	WebElement customerName;

	@FindBy(xpath = "//input[@placeholder=\"Enter your email *\"]")
	WebElement customerEmail;

	@FindBy(xpath = "//input[@placeholder=\"Enter your phone *\"]")
	WebElement customerPhoneNumber;

	public void enteringCustomerDetails(String name, String email, String number) {
		customerName.sendKeys(name);
		customerEmail.sendKeys(email);
		customerPhoneNumber.sendKeys(number);
	}

}
