package goldenindia.CustomerPanelNewFramework.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoilerPlateCode {

	WebDriver driver;

	public BoilerPlateCode(WebDriver driver) {
		this.driver = driver;
	}

	public void scrollingToParticularElementUsingJavascriptExecutor(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",ele);
	}
	
	public void clickingOnParticularElementUsingJavascriptExecutor(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()",ele);
	}

}