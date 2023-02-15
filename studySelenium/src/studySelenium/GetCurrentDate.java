package studySelenium;
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.Month;

public class GetCurrentDate {
	
	public static int GetYear() {
		int y = YearMonth.now().getYear();
		return y;		
	}
	
	public static int GetMonth() {
		int m = YearMonth.now().getMonth().getValue();
		return m;
	}
	public static int GetDay() {
		int d = LocalDate.now().getDayOfMonth();
		return d;
	}
	public static int GetMonthNumber(String monthName) {
	    return Month.valueOf(monthName.toUpperCase()).getValue();
	}
}
