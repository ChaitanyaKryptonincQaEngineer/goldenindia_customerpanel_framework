package goldenindia.CustomerPanelNewFramework.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {

	public WebDriver driver;
	@FindBy(xpath = "//*[local-name()='svg' and @data-testid='shoppingBag']")
	WebElement cartIcon;

	public CommonUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public void scrollToElementUsingJavascript(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void clickElementUsingJavascript(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}
	
	public void waitForVisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void clickOnCartIcon() {
		cartIcon.click();

	}

	public String getValueFromPropertyFile(String name) throws IOException {
		String propValue = "";
		try {
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customerPanel.properties");
			properties.load(fileInputStream);
			propValue = properties.getProperty(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return propValue;

	}

	public Actions movingToParticularElementUsingActionClass(WebElement ele) {

		Actions action = new Actions(driver);
		return action.moveToElement(ele);
	}
	
	public void visibilityOfElements(List<WebElement> ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	


}