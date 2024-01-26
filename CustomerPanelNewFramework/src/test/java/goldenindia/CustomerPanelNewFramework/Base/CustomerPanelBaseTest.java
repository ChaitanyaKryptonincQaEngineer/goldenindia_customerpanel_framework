package goldenindia.CustomerPanelNewFramework.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import goldenindia.CustomerPanelNewFramework.PageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerPanelBaseTest {

	public WebDriver driver;
	public HomePage homePage;

	public WebDriver driverIntialization() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\goldenindia\\CustomerPanelNewFramework\\TestData\\customerPanel.properties");
		prop.load(file);

		String browserValue = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browserValue");

		if (browserValue.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserValue.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserValue.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid Driver Value " + browserValue);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	public List<HashMap<String, String>> gettingDataFromJSONFile(String filePath) throws IOException {
		String jsonValues = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonValues,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	@BeforeTest(alwaysRun = true)
	public HomePage launchApplication() throws IOException {

		driver = driverIntialization();
		homePage = new HomePage(driver);
		homePage.gotoURL();
		return homePage;

	}

	@AfterTest(alwaysRun = true)
	public void treeDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
