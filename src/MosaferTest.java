import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MosaferTest {

	static String URL = "https://www.almosafer.com/en";
	static WebDriver driver = new ChromeDriver();
	SoftAssert softAssert = new SoftAssert();

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
		Assert.assertEquals(isDisplayed, "false", "Test of Qitaf Logo is displayed");

	}

	@Test(priority = 1)
	public void checkDepartureDate() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);
		driver.get("https://www.almosafer.com/" + webLang[randomNum]);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

		LocalDate currentDate = LocalDate.now();

		LocalDate expectedDepartureDate = currentDate.plusDays(1);
		String expectedDepartureDateFormatted = expectedDepartureDate.format(formatter);

		WebElement actualDepartureDate = driver.findElement(By.className("sc-lnrBVv")).findElement(By.className("sc-fvLVrH"));
		String actualDepartureDay = actualDepartureDate.getText();
		String actualDepartureDayFormatted = actualDepartureDay.formatted(formatter);
		Assert.assertEquals(actualDepartureDayFormatted, expectedDepartureDateFormatted, "Checks Departure Date");

	}

	@Test(priority = 1)
	public void checkReturnDate() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);
		driver.get("https://www.almosafer.com/" + webLang[randomNum]);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

		LocalDate currenDate = LocalDate.now();

		LocalDate expectedReturnDate = currenDate.plusDays(2);
		String expectedReturnDateFormatted = expectedReturnDate.format(formatter);

		WebElement actualReturnDate = driver.findElement(By.className("sc-bYnzgO")).findElement(By.className("sc-fvLVrH"));
		String actualReturnDay = actualReturnDate.getText();
		String actualReturnDayFormatted = actualReturnDay.formatted(formatter);
		Assert.assertEquals(actualReturnDayFormatted, expectedReturnDateFormatted, "Checks Return Date");
		
//		softAssert.assertEquals(actualReturnDayFormatted, expectedReturnDateFormatted); //Soft Assertion 

	}

	@Test(priority = 1)
	public void randomWebsiteLang() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);
		driver.get("https://www.almosafer.com/" + webLang[randomNum]);
		String siteLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(siteLang, webLang[randomNum], "Test the lang of the Site");

	}

	@Test(priority = 1)
	public void hotelLocation() {

		String[] webLang = { "en", "ar" };
		String[] enLocation = { "Dubai", "Jeddah", "Riyadh" };
		String[] arLocation = { "دبي", "جدة" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);
		int randomEngLoc = rand.nextInt(enLocation.length);
		int randomArLoc = rand.nextInt(arLocation.length);
		driver.get("https://www.almosafer.com/" + webLang[randomNum]);
		String siteLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).click();

		if (siteLang == "en") {

			driver.findElement(By.className("phbroq-2")).sendKeys(enLocation[randomEngLoc]);
			driver.findElement(By.cssSelector(
					".phbroq-5.dbvRBC.AutoComplete__ListItem.AutoComplete__ListItem--highlighted.AutoComplete__ListItem"))
					.click();

		} else if (siteLang == "ar") {

			driver.findElement(By.className("phbroq-2")).sendKeys(arLocation[randomArLoc]);
			driver.findElement(By.cssSelector(
					".phbroq-5.dbvRBC.AutoComplete__ListItem.AutoComplete__ListItem--highlighted.AutoComplete__ListItem"))
					.click();

		}

	}

	@AfterTest
	public void postTest() {

	}
}
