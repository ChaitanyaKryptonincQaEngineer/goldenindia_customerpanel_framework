package goldenindia.CustomerPanelNewFramework.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoilerPlateCode {

	public WebDriver driver;
	@FindBy(xpath = "//*[local-name()='svg' and @data-testid='shoppingBag']")
	WebElement cartIcon;

	public BoilerPlateCode(WebDriver driver) {
		this.driver = driver;
	}

	public void scrollingToParticularElementUsingJavascriptExecutor(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void clickingOnParticularElementUsingJavascriptExecutor(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}
	
	public void visibilityOfWebElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void clickingOnCartIcon() {
		cartIcon.click();

	}

	public String gettingValueFromPropertyFile(String name) throws IOException {
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

}