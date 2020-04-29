package com.xebia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xebia.model.Category;
import com.xebia.model.Product;
import com.xebia.model.User;
import com.xebia.utilities.XebiaUtility;

@SpringBootApplication
public class XebiaApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(XebiaApplication.class, args);
		
	
		
		List<Product> products = getProductList();
		
		User employee = new User("Amit", "14-01-2010", true, false, products);
		
		User affiliate = new User("Ashish","14-10-2018", false, true, products);
		User years = new User("Deepak","14-10-2017", false, false, products);
		User onlyone = new User("Abhishek","14-10-2017", false, true, products);
		
		//First Case : If the user is an employee of the store
		double totalbill = XebiaUtility.calculateBill(employee);
		System.out.println("Total payable bill of employee = "+totalbill);
		
		
		//Second Case : If the user is an affiliate of the store
		totalbill = XebiaUtility.calculateBill(affiliate);
		System.out.println("Total payable bill of affiliate = "+totalbill);
		
		//Third Case : If the user has been a customer for over 2 years
		totalbill = XebiaUtility.calculateBill(years);
		System.out.println("Total payable bill of user who has been a customer for over 2 years  = "+totalbill);
		
		//Fourth Case : A user can get only one of the percentage based discounts on a bill
		totalbill = XebiaUtility.calculateBill(onlyone);
		System.out.println("Total payable bill, a user can get only one of the percentage based discounts on a bill  = "+totalbill);
		
	}
	
	
	 private static List<Product> getProductList() {
			
			Category groceryCategory = new Category(100,XebiaUtility.GROCERY);
			Category nonGroceryCategory = new Category(101,XebiaUtility.NON_GROCERY);
			
			Product tvproduct = new Product("TV", 15000,1,nonGroceryCategory);
			Product coolerproduct = new Product("Cooler", 31000,1,nonGroceryCategory);
			Product Gproduct = new Product("Apple", 15,100,groceryCategory);
			Product Gproduct2 = new Product("Orange", 11,100,groceryCategory);
			
			List<Product> list = new ArrayList<Product>();
			list.add(coolerproduct);
			list.add(tvproduct);
			list.add(Gproduct2);
			list.add(Gproduct);
			
			return list;
		}
	

}
