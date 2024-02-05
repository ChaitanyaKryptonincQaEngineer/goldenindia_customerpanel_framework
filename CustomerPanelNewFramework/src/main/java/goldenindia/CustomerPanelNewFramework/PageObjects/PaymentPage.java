package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

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

	@FindBy(xpath = "//form/div[4]/div[2]/div/div[1]/label")
	WebElement onlinePayment;

	@FindBy(xpath = "//form/div[4]/div[2]/div/div[2]/label")
	WebElement CODPayment;

	@FindBy(xpath = "/html/body/div/div/div[1]/div/div/div[2]/div[1]/div/form/div[5]/div/div[4]/button")
	WebElement paymentSbmtBtn;

	@FindBy(xpath = "(//input[@type=\"checkbox\"])[1]")
	WebElement termsAndConditionsFirstOne;

	@FindBy(xpath = "(//input[@type=\"checkbox\"])[2]")
	WebElement termsAndConditionsSecondOne;

	@FindBy(css = "[class=\"col-md-12\"] [class*=\"text-center\"]")
	WebElement termsAndConditionsErrorMessage;

	@FindBy(xpath = "//button[@type=\"button\" and text()=\"Yes, Order it !\"]")
	WebElement orderConfirmationBtn;

	public void enteringCustomerDetails(String name, String email, String number) {
		customerName.sendKeys(name);
		customerEmail.sendKeys(email);
		customerPhoneNumber.sendKeys(number);
	}

	public void selectingPaymentType() throws IOException {

		List<WebElement> paymentNames = driver.findElements(By.xpath("//form/div[4]/div[2]/div/div/label"));
		for (WebElement paymentName : paymentNames) {
			if (paymentName.getText().equals(gettingValueFromPropertyFile("paymentType"))) {
				if (!CODPayment.isSelected()) {
					WebElement paymentSelection = driver.findElement(
							RelativeLocator.with(By.xpath("//input[@value=\"COD\"]")).toLeftOf(paymentName));
					paymentSelection.click();

				}
				System.out.println("You are using " + paymentName.getText());
			} else if (paymentName.getText().equals(gettingValueFromPropertyFile("paymentType"))) {
				if (!onlinePayment.isSelected()) {
					WebElement paymentSelection = driver.findElement(
							RelativeLocator.with(By.xpath("//input[@value=\"ONLINE\"]")).toLeftOf(paymentName));
					paymentSelection.click();
				}
				System.out.println("You are using " + paymentName.getText());
			} else {
				System.out.println("Invalid Payment Option");
			}
		}

	}

	public void clickingOnTermsAndConditions() throws InterruptedException {
		termsAndConditionsFirstOne.click();
	}

	public void clickingOnPayButton() throws InterruptedException {
		movingToParticularElementUsingActionClass(paymentSbmtBtn).click().build().perform();
		Thread.sleep(1000);
		try {
			if (termsAndConditionsErrorMessage.isDisplayed()) {
				if (termsAndConditionsFirstOne.isSelected()) {
					movingToParticularElementUsingActionClass(termsAndConditionsFirstOne).click().build().perform();
				}
				movingToParticularElementUsingActionClass(termsAndConditionsSecondOne).click().build().perform();
				Thread.sleep(1000);
				movingToParticularElementUsingActionClass(paymentSbmtBtn).click().build().perform();
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void clickingOnOrderConfirmationBtn() {
		System.out.println("You are clicking on " + orderConfirmationBtn.getText() + "button");
		movingToParticularElementUsingActionClass(orderConfirmationBtn).click().build().perform();
	}

	public ConfirmationPage completingPaymentDetails(String name, String email, String number)
			throws IOException, InterruptedException {
		this.enteringCustomerDetails(name, email, number);
		this.selectingPaymentType();
		this.clickingOnTermsAndConditions();
		this.clickingOnPayButton();
		this.clickingOnOrderConfirmationBtn();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);

		return confirmationPage;

	}

}
