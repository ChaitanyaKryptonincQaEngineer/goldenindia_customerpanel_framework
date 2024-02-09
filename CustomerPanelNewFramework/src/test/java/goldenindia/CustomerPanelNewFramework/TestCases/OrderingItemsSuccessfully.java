package goldenindia.CustomerPanelNewFramework.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import goldenindia.CustomerPanelNewFramework.Base.CustomerPanelBaseTest;
import goldenindia.CustomerPanelNewFramework.PageObjects.CheckOutPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.ConfirmationPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.PaymentPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.ProductCatalog;

public class OrderingItemsSuccessfully extends CustomerPanelBaseTest {

	@Test(dataProvider = "enteringCustomerDetails", groups = "Regression")
	public void placing_the_order_by_adding_only_one_product(HashMap<String, String> input)
			throws IOException, InterruptedException {
		ProductCatalog productPage = homePage.navigateToBranch();
		CheckOutPage checkOutPage = productPage.addSingleProductToCart();
		PaymentPage paymentPage = checkOutPage.selectOrderType();
		ConfirmationPage confirmationPage = paymentPage.completingPaymentDetails(input.get("name"), input.get("email"),
				input.get("number"));
		confirmationPage.getOrderID();
		confirmationPage.downloadOrderReceipt();

	}

	@DataProvider
	public Object[][] enteringCustomerDetails() throws IOException {

		List<HashMap<String, String>> data = gettingDataFromJSONFile(System.getProperty("user.dir")
				+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customer.json");
		return new Object[][] { { data.get(0) } };
	}
}
