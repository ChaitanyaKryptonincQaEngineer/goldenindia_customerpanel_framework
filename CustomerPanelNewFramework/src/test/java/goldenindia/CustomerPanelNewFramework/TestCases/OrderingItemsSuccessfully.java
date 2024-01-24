package goldenindia.CustomerPanelNewFramework.TestCases;

import java.io.IOException;

import org.testng.annotations.Test;
import goldenindia.CustomerPanelNewFramework.Base.CustomerPanelBaseTest;
import goldenindia.CustomerPanelNewFramework.PageObjects.CheckOutPage;
import goldenindia.CustomerPanelNewFramework.PageObjects.ProductCatelogue;

public class OrderingItemsSuccessfully extends CustomerPanelBaseTest {

	public static String productName = "Testing testing teew";

	@Test
	public void placing_the_order_by_adding_only_one_product() throws IOException {
		ProductCatelogue productPage = homePage.customerClickingParticularBranch();
		CheckOutPage checkOutPage = productPage.addingSingleProductToCart(productName);
		checkOutPage.selectingParticularOrderType("Delivery");
		
	}
}
