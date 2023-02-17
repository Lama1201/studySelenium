package studySelenium;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropdownHandling {

	public static void main(String[] args) throws InterruptedException {
		WebDriver chr1 = new ChromeDriver();
		chr1.get("https://rahulshettyacademy.com/dropdownsPractise/");
		chr1.manage().window().maximize();

		Country(chr1); // dynamic dropdown list
		Currency(chr1);// static dropdown list
		Passengers(chr1);
		FromTo(chr1); // xpath index hoặc xpath parent-child
		FlightDate(chr1, 2023, 5, 20);
		StartSearch(chr1);

		//chr1.quit();
	}

	public static void Currency(WebDriver chr) throws InterruptedException {

		// Tạo đối tượng có thể tái sử dụng
		// WebElement dropdownCurrency =
		// chr1.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		// Select selectedCurrency = new Select(dropdownCurrency);

		Select selectedValue = new Select(chr.findElement(By.id("ctl00_mainContent_DropDownListCurrency"))); // Tạo đối
																												// tượng
																												// trực
																												// tiếp
		selectedValue.selectByVisibleText("AED");// Chọn giá trị bằng text
		Thread.sleep(1000);
		selectedValue.selectByValue("INR"); // Chọn giá trị bằng value
		Thread.sleep(1000);
		selectedValue.selectByIndex(3); // Chọn giá trị bằng index
		Thread.sleep(1000);
		System.out.println("Currency: " + selectedValue.getFirstSelectedOption().getText());
	}

	public static void Passengers(WebDriver chr) throws InterruptedException {
		chr.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(1000);

		int passenger = (int) (Math.random() * (10 - 1) + 1);// lấy 1 số random trong khoảng max=6, min=1
		for (int i = 1; i < passenger; i++) {
			chr.findElement(By.id("hrefIncAdt")).click();
		}

		passenger = (int) (Math.random() * 4);
		for (int i = 0; i < passenger; i++) {
			chr.findElement(By.id("hrefIncChd")).click();
		}

		passenger = (int) (Math.random() * 2);
		for (int i = 0; i < passenger; i++) {
			chr.findElement(By.id("hrefIncInf")).click();
		}

		chr.findElement(By.id("btnclosepaxoption")).click();
		System.out.println("Passengers: " + chr.findElement(By.id("divpaxinfo")).getText());
		Thread.sleep(1000);
	}

	public static void FromTo(WebDriver chr) throws InterruptedException {

		String[] ListCity = { "ix", "at", "de", "sa", "go", "ai" };
		Random rd = new Random();
		int index = rd.nextInt(ListCity.length);// Tạo index random

		chr.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys(ListCity[index]);
		System.out.println("Keyword sent: " + ListCity[index]);
		Thread.sleep(1000);
		// Lấy hết các element có chứa value liên quan đến string nhập trong dynamic
		// list
		List<WebElement> options = chr.findElements(
				By.xpath("//a[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"
						+ ListCity[index] + "')]"));
		// Chọn ngẫu nhiên 1 giá trị trong các value lấy được
		index = rd.nextInt(options.size());
		options.get(index).click();
		System.out.println("Departure from: " + options.get(index).getText());

		// Chọn điểm đến ngẫu nhiên
		chr.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		Thread.sleep(1000);
		options = chr.findElements(By
				.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[string-length(@value) =3]"));
		index = rd.nextInt(options.size());
		options.get(index).click();
		System.out.println("Destination: " + options.get(index).getText());

		// chr.findElement(By.xpath("(//a[@value='JAI'])[2]")).click();// Khi có 2
		// element giống hệt nhau có thể dùng (xpath)[index] để gọi chính xác
		// có thể sử dụng xpath để trỏ tới parent element ngay sai đó trỏ tới child
		// element bằng xpath để trỏ chính xác thay vì dử dụng index
		// chr.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='JAI']")).click();
		// System.out.println("Fly from " +
		// chr.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).getAttribute("selectedtext")
		// +" to " +
		// chr.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).getAttribute("selectedtext"));
		// chr.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).click();
		Thread.sleep(2000);
	}

	public static void Country(WebDriver chr) throws InterruptedException {
		chr.findElement(By.id("autosuggest")).sendKeys("vi");
		Thread.sleep(1000);
		List<WebElement> options = chr.findElements(By.xpath("//li[@class='ui-menu-item']"));
		for (WebElement n : options) {
			if (n.getText().equalsIgnoreCase("Vietnam")) {
				System.out.println("Selected country: " + n.getText());
				n.click();
				break;
			}
		}
		Thread.sleep(1000);
	}

	public static void FlightDate(WebDriver chr, int y, int m, int d) throws InterruptedException {
		if (!chr.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isSelected()) {
			chr.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
		}
		// Kiểm tra khi select one way thì lịch chiều về bị ẩn đi
		// Assert.assertFalse(chr.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled());//
		// Cách này ko hoạt động vì thực tế chỉ có Ui thay đổi
		// Assert.assertEquals(chr.findElement(By.className("picker-second")).getAttribute("style"),
		// "display: block; opacity: 0.5;");// Dùng cách so sánh string
		Assert.assertTrue(chr.findElement(By.className("picker-second")).getAttribute("style").contains("0.5")); // Dùng
																													// cách
																													// kiểm
																													// tra
																													// string
																													// có
																													// tồn
																													// tại
																													// hay
																													// ko
		// Kiểm tra date picker đã mở sẵn chưa. Nếu chưa thì mở date picker
		if (!chr.findElement(By.id("ui-datepicker-div")).getCssValue("display").contains("block")) {
			chr.findElement(By.id("ctl00_mainContent_view_date1")).click();
			Thread.sleep(1000);
		}

		// Lấy năm đang chọn trên calendar
		int ay = Integer.valueOf(
				chr.findElement(By.xpath("//div[contains(@class,'ui-corner-left')]//span[@class='ui-datepicker-year']"))
						.getText());
		if (ay < y) {
			for (int i = ay; i <= (y - ay + 1) * 12; i++) {
				chr.findElement(By.xpath("//a[@title='Next']")).click();
			}
		}
		if (ay > y) {
			for (int i = y; i <= (ay - y + 1) * 12; i++) {
				chr.findElement(By.xpath("//a[@title='Prev']")).click();
			}
		}
		// Lấy tháng đang được chọn trên calendar
		int am = GetCurrentDate.GetMonthNumber(chr
				.findElement(By.xpath("//div[contains(@class,'ui-corner-left')]//span[@class='ui-datepicker-month']"))
				.getText());
		if (am < m) {
			for (int i = am; i <= (m - am + 1); i++) {
				chr.findElement(By.xpath("//a[@title='Next']")).click();
			}
		}
		if (am > m) {
			for (int i = m; i <= (am - m + 1); i++) {
				chr.findElement(By.xpath("//a[@title='Prev']")).click();
			}
		}
		Thread.sleep(1000);
		chr.findElement(By.xpath("//td[@data-month='" + Integer.toString(m - 1) + "']//a[contains(text(),'"
				+ Integer.toString(d) + "')]")).click();
		System.out.println("Flight date: " + chr.findElement(By.id("view_fulldate_id_1")).getText());
		Thread.sleep(1000);
	}

	public static void StartSearch(WebDriver chr) throws InterruptedException {
		chr.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		if (chr.findElement(By.id("view-origin-station")).getText().contains("Select")) {
			FromTo(chr);
		}
		System.out.println("We are searching for your flight. Please wait...");
	}

}
