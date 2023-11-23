import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MosaferTest extends Parameters {
	
	static String URL = "https://www.almosafer.com/en";
	static WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void preTest() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.get(URL);
		driver.findElement(By.className("cta__saudi")).click();
		driver.manage().window().maximize();

	}

	@Test()
	public void testLanguage() {

		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(language, "en");

	}

	@Test()
	public void testCurrency() {

		String currency = driver.findElement(By.className("sc-dRFtgE")).getText();
		Assert.assertEquals(currency, "SAR");

	}

	@AfterTest
	public void postTest() {

	}
}
