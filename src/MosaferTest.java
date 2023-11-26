import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MosaferTest {
	
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
		Assert.assertEquals(language, "en", "Test to see if the language is English.");

	}

	@Test()
	public void testCurrency() {

		WebElement currency = driver.findElement(By.className("sc-dRFtgE"));
		String actualCurrency = currency.getText();
		Assert.assertEquals(actualCurrency, "SAR", "Test to see if the currency is SAR.");

	}

	@Test()
	public void contactNumber() {

		WebElement contactNumber = driver.findElement(By.className("sc-hUfwpO"));
		String actualContactNumber = contactNumber.getText();
		Assert.assertEquals(actualContactNumber, "+966554400000", "Test if the Contact Number is correct.");

	}

	@Test()
	public void QitafLogo() {

		WebElement footer = driver.findElement(By.tagName("footer"));
		boolean qitafLogoIsDisplayed = footer.findElement(By.className("sc-dznXNo")).isDisplayed();
		Assert.assertTrue(qitafLogoIsDisplayed, "Test of Qitaf Logo is displayed");

	}

	@Test()
	public void checkHotelIsNotSelected() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String isDisplayed = hotelTab.getAttribute("aria-selected");
		Assert.assertEquals(isDisplayed, "false","Test of Qitaf Logo is displayed");
		
	}

	@Test()
	public void checkDeparturedate() {
		
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(1);
		String futureDay = String.valueOf(futureDate.getDayOfMonth());
		
		WebElement departureDate = driver.findElement(By.className("sc-lnrBVv")).findElement(By.className("sc-fvLVrH"));
		String departureDay = departureDate.getText();
		Assert.assertEquals(departureDay, futureDay);
				
	}

	@Test()
public void checkReturnDate() {
		
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(2);
		String futureDay = String.valueOf(futureDate.getDayOfMonth());
		
		WebElement reutrnDate = driver.findElement(By.className("sc-bYnzgO")).findElement(By.className("sc-fvLVrH"));
		String reutrnDay = reutrnDate.getText();
		Assert.assertEquals(reutrnDay, futureDay);
		
	}
	
	@AfterTest
	public void postTest() {

	}
}
