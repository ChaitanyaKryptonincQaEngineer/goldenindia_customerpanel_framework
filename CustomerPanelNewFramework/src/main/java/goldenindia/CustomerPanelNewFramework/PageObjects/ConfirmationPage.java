package goldenindia.CustomerPanelNewFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

	public WebDriver driver;

	@FindBy(css = "[class*=\"header_success\"]")
	WebElement orderConfirmationMsg;

	@FindBy(css = "[class*=\"payment_order_id\"]")
	WebElement orderID;

	@FindBy(xpath = "//button[@type=\"button\"]//strong[text()=\"Download Receipt\"]")
	WebElement downloadReceiptBtn;

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void gettingOrderId() {

		String orderConfirmation = orderConfirmationMsg.getText();
		String arra[] = orderID.getText().split("#");
		System.out.println(orderConfirmation);
		System.out.println(arra[1]);
	}

	public void downloadingOrderPDF() {
		downloadReceiptBtn.click();
	}

}
