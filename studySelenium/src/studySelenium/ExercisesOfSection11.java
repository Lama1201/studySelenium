package studySelenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExercisesOfSection11 {

	public static void main(String[] args) {
		WebDriver chr = new ChromeDriver();
		chr.get("https://qaclickacademy.com/practice.php");
		chr.manage().window().maximize();
		
		// 1. Tổng số link trong trang web
		System.out.println(chr.findElements(By.tagName("a")).size());
		
		// 2. Tổng số link nằm trong footer
		WebElement footer = chr.findElement(By.id("gf-BIG"));
		System.out.println(footer.findElements(By.tagName("a")).size());
		//System.out.println(chr.findElement(By.id("gf-BIG")).findElements(By.tagName("a")).size());
		
		// 3. Tổng số link nằm ở cột đầu tiên trong footer
		WebElement firstCol = footer.findElement(By.xpath("(//ul)[1]"));
		System.out.println(firstCol.findElements(By.tagName("a")).size());
		//System.out.println(chr.findElement(By.id("gf-BIG")).findElement(By.xpath("(//ul)[1]")).findElements(By.tagName("a")).size());
		
		// 4. Click vào từng link trong 3.
		//OpenAllLinks(firstCol);
		
		// 5. Lấy title của  tất cả các tabs đang mở
		//GetTitleAllWindows(chr);
				
		// 6. Đóng các tabs đã mở
		//CloseAllChildenWindows(chr);
				
		
		// 7. Cuộn đến vị trí element nào đó
		//((JavascriptExecutor) chr).executeScript("arguments[0].scrollIntoView(true);", chr.findElement(By.xpath("//div[@class='tableFixHead']")));
		
		// 8. Lấy giá trị từng hàng trong bảng
		//PrintValuesFromTable(chr.findElement(By.xpath("//div[@class='tableFixHead']")));
		//System.out.println(GetValuesFromTable3(chr.findElement(By.xpath("//div[@class='tableFixHead']"))));		
		
		// 9. Tính total Amount trong bảng và sau đó
		/*int total = TotalAmount(chr.findElement(By.xpath("//div[@class='tableFixHead']")));
		int getTotal = Integer.parseInt(chr.findElement(By.className("totalAmount")).getText().split(":")[1].trim());
		if(total == getTotal) {
			System.out.println("Total Amount is correct");
		}*/
		String total = Integer.toString(TotalAmount(chr.findElement(By.xpath("//div[@class='tableFixHead']"))));
		System.out.println("Total Anount is 296: "+chr.findElement(By.className("totalAmount")).getText().contains(total));
		
		chr.close();
	}
	// Chỉ in giá trị, ko cần return
	 public static void PrintValuesFromTable(WebElement table) {
		 List<WebElement> rows = table.findElements(By.xpath(".//tr"));
			for (WebElement r: rows) {
				if(r!=rows.get(0)) {
					System.out.println();
					List<WebElement> cols = r.findElements(By.tagName("td"));
					for(WebElement c: cols) {
						System.out.print(c.getText()+ " ");
					}				
				}					
					
			}
	 }	 
	 
	// Return giá trị sử dụng mảng 2 chiều
	public static String[][] GetValuesFromTable1(WebElement table) {
		 List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		 String[][] values = new String[rows.size()][rows.get(1).findElements(By.tagName("td")).size()];
		 for (int i=1; i< rows.size(); i++) {			
			 List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			 for(int j =0; j< cols.size();j++) {
				values[i][j]= cols.get(j).getText();	
			 }				
		 }
		 return values;
	}
	
	// Return giá trị sử dụng List
	public static List<List<String>> GetValuesFromTable2(WebElement table) {
	    List<List<String>> values = new ArrayList<>();
	    List<WebElement> rows = table.findElements(By.xpath(".//tr"));
	    for (WebElement r: rows) {
	        List<WebElement> cols = r.findElements(By.tagName("td"));
	        List<String> rowValues = new ArrayList<>();
	        for (WebElement c: cols) {
	            rowValues.add(c.getText());
	        }
	        values.add(rowValues);
	    }
	    return values;
	}

	public static void CloseAllChildenWindows(WebDriver chr) {
		ArrayList<String> tabs = new ArrayList<String>(chr.getWindowHandles());
		for(String n:tabs) {
			if( n != tabs.get(0)) {
				chr.switchTo().window(n);
				chr.close();
			}
		}
		chr.switchTo().window(tabs.get(0));
	}
	
	// Get title of all tabs
	public static void GetTitleAllWindows(WebDriver chr) {
		ArrayList<String> tabs = new ArrayList<String>(chr.getWindowHandles());
		for(String n: tabs) {
			chr.switchTo().window(n);
			System.out.println(chr.getTitle());
		}
	}
	
	// Click vào tất cả các link trong element và mở ở tab mới
	public static void OpenAllLinks(WebElement item) {
		List<WebElement> links = item.findElements(By.tagName("a"));
		//List<WebElement> links = chr.findElement(By.id("gf-BIG")).findElement(By.xpath("(//ul)[1]")).findElements(By.tagName("a"));		
		for(WebElement n: links) {
			String openLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
			n.sendKeys(openLink);
			//n.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		}
	}
	
	public static int TotalAmount(WebElement table) {
		List<WebElement> rows = table.findElements(By.xpath(".//td[4]"));
		int total = 0;
		for(WebElement r : rows) {
			total = total + Integer.parseInt(r.getText());			
		}
		return total;
	}
}
