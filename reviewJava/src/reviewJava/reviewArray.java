package reviewJava;

public class reviewArray {

	public static void main(String[] args) {
		// Array 
		// Khai báo và gán giá trị theo từng bước
		int[] year = new int[5]; 
		year[0] = 1984;
		year[1]= 1997;
		year[2] = 2003;
		year[3] = 2006;
		year[4]= 2009;
		
		// Khai báo và gán giá trị đồng thời
		int[] year2 = {1983,2018,2020,2021};
		String[] name = {"An","Tuan","Hai","Phuong"};
		
		// In giá trị tại 1 vị trí trong mảng
		System.out.println(year[2]);
		System.out.println(year2[0]);
		
		// In mảng cách cổ điển
		for(int i = 0; i < year.length; i++ ) {
			System.out.println(year[i]);
		}
		
		for(int i = 0; i < name.length; i++ ) {
			System.out.println(name[i]);
		}
		
		// In mảng cách ngắn gọn
		for(String n: name ) {
			System.out.println(n);
		}		
		for(int y: year ) {
			System.out.println(y);
		}
		
		// In mảng theo vị trí chẵn
		for(int i = 0; i < name.length; i++ ) {
			if (i%2 == 0) {
				System.out.println(name[i]);
			}
		}		
		
		// Phân biệt các giá trị chẵn lẻ
		for(int y: year ) {
			if(y%2 == 1) {
				System.out.println(y + " nam le");
			}
			else {
				System.out.println(y + " nam chan");
			}
		}	
		
		// Tìm 1 giá trị trong mảng
		for(String n: name) {
			if (n == "Hai") {
				System.out.println(n + " is in the list");
				break;
			}
			else {
				System.out.println("searching");
			}
		}		

	}

}
