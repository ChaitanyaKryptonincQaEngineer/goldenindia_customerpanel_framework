package goldenindia.CustomerPanelNewFramework.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class*=\"header_name\"]")
	public List<WebElement> branchNameElements;

	@FindBy(css = "[class*=\"custom_hover\"]")
	public List<WebElement> orderNowBtn;

	@FindBy(css = "[class=\"sc-87747fd2-0 bkSLXW\"]")
	public WebElement branchWelcomeMsg;

	public ProductCatelogue customerClickingParticularBranch() throws IOException {

		try {

			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customerPanel.properties");
			properties.load(fileInputStream);

			String branchName = System.getProperty("branch") != null ? System.getProperty("branch")
					: properties.getProperty("branch");

			for (int i = 0; i < branchNameElements.size(); i++) {
				if (branchNameElements.get(i).getText().equals(branchName)) {
					orderNowBtn.get(i).click();
					break;
				}
			}
			System.out.println(branchName);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			driver.navigate().refresh();
			orderNowBtn.get(0).click();
		}
		System.out.println(branchWelcomeMsg.getText());
		ProductCatelogue productPage = new ProductCatelogue(driver);
		return productPage;
	}

	public void gotoURL() {
		driver.get("http://mcd.mypreview.xyz/");
	}
}
