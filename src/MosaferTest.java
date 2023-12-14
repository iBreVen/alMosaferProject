import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.get(URL);
		driver.findElement(By.className("cta__saudi")).click();
		driver.manage().window().maximize();

	}

	@Test(enabled = false)
	public void testLanguage() {

		String language = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(language, "en", "Test to see if the language is English.");

	}

	@Test(enabled = false)
	public void testCurrency() {

		WebElement currency = driver.findElement(By.className("sc-dRFtgE"));
		String actualCurrency = currency.getText();
		Assert.assertEquals(actualCurrency, "SAR", "Test to see if the currency is SAR.");

	}

	@Test(enabled = false)
	public void contactNumber() {

		WebElement contactNumber = driver.findElement(By.className("sc-hUfwpO"));
		String actualContactNumber = contactNumber.getText();
		Assert.assertEquals(actualContactNumber, "+966554400000", "Test if the Contact Number is correct.");

	}

	@Test(enabled = false)
	public void QitafLogo() {

		WebElement footer = driver.findElement(By.tagName("footer"));
		boolean qitafLogoIsDisplayed = footer.findElement(By.className("sc-dznXNo")).isDisplayed();
		Assert.assertTrue(qitafLogoIsDisplayed, "Test of Qitaf Logo is displayed");

	}

	@Test(enabled = false)
	public void checkHotelIsNotSelected() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String isDisplayed = hotelTab.getAttribute("aria-selected");
		Assert.assertEquals(isDisplayed, "false", "Test of Qitaf Logo is displayed");

	}

	@Test(priority = 1, enabled = false)
	public void checkDepartureDate() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);

		driver.get("https://www.almosafer.com/" + webLang[randomNum]);
		String siteLang = driver.findElement(By.tagName("html")).getAttribute("lang");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

		LocalDate currentDay = LocalDate.now();

		LocalDate expectedDepartureDay = currentDay.plusDays(1);

		String expectedDepartureDateFormatted = expectedDepartureDay.format(formatter);

		String actualDepartureDay = driver.findElement(By.className("sc-lnrBVv")).findElement(By.className("sc-fvLVrH"))
				.getText();

		String actualDepartureDayofWeek = driver.findElement(By.className("sc-lnrBVv"))
				.findElement(By.className("sc-eSePXt")).getText();

		String actualDepartureMonth = driver.findElement(By.className("sc-lnrBVv"))
				.findElement(By.className("sc-hvvHee")).getText();

		String actualDepartureDayFormatted = actualDepartureDay.formatted(formatter);
		Assert.assertEquals(actualDepartureDayFormatted, expectedDepartureDateFormatted, "Checks Departure Day");

		if (siteLang.equals("en")) {

			String expectedDepartureDayofWeekEN = expectedDepartureDay.getDayOfWeek().getDisplayName(TextStyle.FULL,
					Locale.ENGLISH);
			String expectedDepartureMonthEN = expectedDepartureDay.getMonth().getDisplayName(TextStyle.FULL,
					Locale.ENGLISH);

			Assert.assertEquals(actualDepartureDayofWeek, expectedDepartureDayofWeekEN, "Checks Departure Day of Week");

			Assert.assertEquals(actualDepartureMonth, expectedDepartureMonthEN, "Checks Departure Month of Year");

		} else if (siteLang.equals("ar")) {

			String expectedDepartureDayofWeekAR = expectedDepartureDay.getDayOfWeek().getDisplayName(TextStyle.FULL,
					new Locale("ar", "SA", "POSIX"));

			String expectedDepartureMonthAR = expectedDepartureDay.getMonth().getDisplayName(TextStyle.FULL,
					new Locale("ar", "SA", "POSIX"));

			Assert.assertEquals(actualDepartureDayofWeek, expectedDepartureDayofWeekAR,
					"Checks Departure Day of Week AR");

			Assert.assertEquals(actualDepartureMonth, expectedDepartureMonthAR, "Checks Departure Month of Year AR");

		}

	}

	@Test(priority = 1, enabled = false)
	public void checkReturnDate() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);

		driver.get("https://www.almosafer.com/" + webLang[randomNum]);
		String siteLang = driver.findElement(By.tagName("html")).getAttribute("lang");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

		LocalDate currentDate = LocalDate.now();

		LocalDate expectedReturnDay = currentDate.plusDays(2);
		String expectedReturnDateFormatted = expectedReturnDay.format(formatter);

		String actualReturnDay = driver.findElement(By.className("sc-bYnzgO")).findElement(By.className("sc-fvLVrH"))
				.getText();

		String actualReturnDayofWeek = driver.findElement(By.className("sc-bYnzgO"))
				.findElement(By.className("sc-eSePXt")).getText();

		String actualReturnMonth = driver.findElement(By.className("sc-bYnzgO")).findElement(By.className("sc-hvvHee"))
				.getText();

		String actualReturnDayFormatted = actualReturnDay.formatted(formatter);
		Assert.assertEquals(actualReturnDayFormatted, expectedReturnDateFormatted, "Checks Return Day");

		if (siteLang.equals("en")) {

			String expectedReturnDayofWeekEN = expectedReturnDay.getDayOfWeek().getDisplayName(TextStyle.FULL,
					Locale.ENGLISH);
			String expectedReturnMonthEN = expectedReturnDay.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

			Assert.assertEquals(actualReturnDayofWeek, expectedReturnDayofWeekEN, "Checks Departure Day of Week");

			Assert.assertEquals(actualReturnMonth, expectedReturnMonthEN, "Checks Departure Month of Year");

		} else if (siteLang.equals("ar")) {

			String expectedReturnDayofWeekAR = expectedReturnDay.getDayOfWeek().getDisplayName(TextStyle.FULL,
					new Locale("ar", "SA", "POSIX"));

			String expectedReturnMonthAR = expectedReturnDay.getMonth().getDisplayName(TextStyle.FULL,
					new Locale("ar", "SA", "POSIX"));

			Assert.assertEquals(actualReturnDayofWeek, expectedReturnDayofWeekAR, "Checks Departure Day of Week AR");

			Assert.assertEquals(actualReturnMonth, expectedReturnMonthAR, "Checks Departure Month of Year AR");

		}

	}

	@Test(priority = 1, enabled = false)
	public void randomWebsiteLang() {

		String[] webLang = { "en", "ar" };
		Random rand = new Random();
		int randomNum = rand.nextInt(webLang.length);
		driver.get("https://www.almosafer.com/" + webLang[randomNum]);
		String siteLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(siteLang, webLang[randomNum], "Test the lang of the Site");

	}

	@Test(priority = 2)
	public void hotelLocation() throws InterruptedException {

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

		if (siteLang.contains("en")) {

			driver.findElement(By.className("phbroq-1")).findElement(By.className("phbroq-2"))
					.sendKeys(enLocation[randomEngLoc]);

			WebElement resList = driver.findElement(By.className("tln3e3-1"));
			Select resSelect = new Select(resList);
			int randRes = rand.nextInt(2);
			resSelect.selectByIndex(randRes);

			driver.findElement(By.className("sc-1vkdpp9-6")).click();
			Thread.sleep(3000);

			String searchResults = driver.findElement(By.className("sc-cClmTo")).getText();
			Assert.assertEquals(searchResults.contains("properties found"), true);

		} else if (siteLang.contains("ar")) {

			driver.findElement(By.className("phbroq-1")).findElement(By.className("phbroq-2"))
					.sendKeys(arLocation[randomArLoc]);

			WebElement resList = driver.findElement(By.className("tln3e3-1"));
			Select resSelect = new Select(resList);
			int randRes = rand.nextInt(2);
			resSelect.selectByIndex(randRes);

			driver.findElement(By.className("sc-1vkdpp9-6")).click();
			Thread.sleep(3000);

			String searchResults = driver.findElement(By.className("sc-cClmTo")).getText();
			Assert.assertEquals(searchResults.contains("عقار وجدنا"), true);

		}
		
		driver.findElement(By.className("eSXwxY")).click();
		WebElement rightSection = driver.findElement(By.xpath("//div[@class='sc-htpNat KtFsv col-9']"));
		List<WebElement> Prices = rightSection.findElements(By.className("Price__Value"));


		int lowestPrice = 0;
		int highestPrice = 0;

		for (int i = 0; i < Prices.size(); i++) {

			lowestPrice = Integer.parseInt(Prices.get(0).getText().replace(",", ""));

			highestPrice = Integer.parseInt(Prices.get(Prices.size() - 1).getText().replace(",", ""));
			System.out.println(lowestPrice + " this is the lowest price ");
			System.out.println(highestPrice + " this is the highest price ");
			Assert.assertEquals(lowestPrice < highestPrice, true);

		}

	}

	@AfterTest
	public void postTest() {

	}
}
