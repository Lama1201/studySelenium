package reviewJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class reviewArrayList {
	public static void main(String[] args) {
		// Convert Array thành arrayList
		String[] items = {"computer","smart watch", "smart phone"};
		List<String> itemList = Arrays.asList(items);
		System.out.println(itemList);
		System.out.println("-------------------");
		
		// Sử dụng class Arraylist để tạo mảng động
		ArrayList<String> name = new ArrayList<String>();
		name.add("Bao");
		name.add("Toan");
		name.add("Anh");
		name.add("Tuan");
		name.add("Khiem");
		name.add("Phuong");
		System.out.println("Tat ca ten trong danh sach: " +name);
		System.out.println("-------------------");
			
		// Lấy giá trị tại 1 vị trí
		System.out.println(name.get(1)+ " se bi xoa khoi danh sach");
				
		// Xóa mảng tại 1 vị trí
		name.remove(1);
		System.out.println("Ten con lai trong danh sach: " +name);
		System.out.println("-------------------");
		
		// Xóa mảng theo giá trị
		System.out.println("Tuan se bi xoa khoi danh sach");
		name.remove(String.valueOf("Tuan"));
		System.out.println("Danh sach con lai: " +name);
		System.out.println("-------------------");
		
		// Gán các số ngẫu nhiên
		ArrayList<Integer> number = new ArrayList<Integer>();
		/*number.add((int)(Math.random()*100));
		number.add((int)(Math.random()*100));
		number.add((int)(Math.random()*100));
		number.add((int)(Math.random()*100));
		number.add((int)(Math.random()*100));*/
		int max = (int)(Math.random()*10);
		for(int i=0; i < max; i++) {
			number.add((int)(Math.random()*100));
		}
		System.out.println("All values in array: " +number);		
		System.out.println("-------------------");
		
		// Xóa phần tử có giá trị nhỏ hơn 50
		for(int i = 0; i < number.size(); i++) {
			
			if(number.get(i) < 50){
				number.remove(Integer.valueOf(number.get(i)));
				i--;
			}			
		}
		System.out.println("Value greater than 50: "+number);
		System.out.println("-------------------");
		
		// Kiểm tra giá trị có nằm trong mảng hay ko
		if(name.contains("Phuong")) {
			System.out.println("Phuong is in the list");
		}
		else {
			System.out.println("Phuong is not in the list");
		}
	}
}
