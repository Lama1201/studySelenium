package reviewJava;

import java.time.YearMonth;

public class reviewMethod {

	public static void main(String[] args) {

		// Tạo đối tượng từ class khác
		//methodDemo d1= new methodDemo();
		//System.out.println(d1.getData());
		//d1.printdemo();
		// Gọi static method ko cần thông qua oblect
		//printdemo2();
		//System.out.println(d1.sum(34,5));

	//}
	
	// từ khóa static cho phép gọi ko cần thông qua class
	//private static void printdemo2() {
		//System.out.println("This is demo of static");
		System.out.print(YearMonth.now().getMonth().getValue());
	}

}
