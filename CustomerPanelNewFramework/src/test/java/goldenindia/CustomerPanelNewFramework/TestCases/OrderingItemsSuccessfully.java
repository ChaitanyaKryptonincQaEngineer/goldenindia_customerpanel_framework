package goldenindia.CustomerPanelNewFramework.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import goldenindia.CustomerPanelNewFramework.Base.CustomerPanelBaseTest;
import goldenindia.CustomerPanelNewFramework.PageObjects.CheckOutPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.PaymentPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.ProductCatelogue;

public class OrderingItemsSuccessfully extends CustomerPanelBaseTest {

	@Test(dataProvider = "enteringCustomerDetails")
	public void placing_the_order_by_adding_only_one_product(HashMap<String, String> input)
			throws IOException, InterruptedException {
		ProductCatelogue productPage = homePage.customerClickingParticularBranch();
		CheckOutPage checkOutPage = productPage.addingSingleProductToCart();
		PaymentPage paymentPage = checkOutPage.selectingParticularOrderType();
		paymentPage.enteringCustomerDetails(input.get("name"), input.get("email"), input.get("number"));

	}

	@DataProvider
	public Object[][] enteringCustomerDetails() throws IOException {

		List<HashMap<String, String>> data = gettingDataFromJSONFile(System.getProperty("user.dir")
				+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customer.json");
		return new Object[][] { { data.get(0) } };
	}
}
