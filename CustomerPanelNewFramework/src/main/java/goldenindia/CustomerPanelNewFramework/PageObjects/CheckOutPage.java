package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	public WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectingParticularOrderType(String name) {
		try {
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customerPanel.properties");
			properties.load(fileInputStream);

			String orderType = System.getProperty("orderType") != null ? System.getProperty("OrderType")
					: properties.getProperty("orderType");

			if (orderType.equals("Delivery")) {
				System.out.println("Order Type is Delivery");
			} else if (orderType.equals("Pick up")) {
				System.out.println("Order Type is Pick Up");
			}
		} catch (Exception e) {

		}
	}

	public void enteringDeliveryDetails() {

	}

	public void enteringPickUpDetails() {

	}
}
