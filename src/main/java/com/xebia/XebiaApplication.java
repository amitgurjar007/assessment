package com.xebia;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xebia.model.Category;
import com.xebia.model.Product;
import com.xebia.model.User;

@SpringBootApplication
public class XebiaApplication {
	
	static final String GROCERY = "Grocery";
	static final String NON_GROCERY = "Non Grocery";

	public static void main(String[] args) {
		SpringApplication.run(XebiaApplication.class, args);
		
	
		
		List<Product> products = getProductList();
		
		User employee = new User("Amit", "14-01-2010", true, false, products);
		
		User affiliate = new User("Ashish","14-10-2018", false, true, products);
		User years = new User("Deepak","14-10-2017", false, false, products);
		User onlyone = new User("Abhishek","14-10-2017", false, true, products);
		
		//First Case : If the user is an employee of the store
		double totalbill = calculateBill(employee);
		System.out.println("Total payable bill of employee = "+totalbill);
		
		
		//Second Case : If the user is an affiliate of the store
		totalbill = calculateBill(affiliate);
		System.out.println("Total payable bill of affiliate = "+totalbill);
		
		//Third Case : If the user has been a customer for over 2 years
		totalbill = calculateBill(years);
		System.out.println("Total payable bill of user who has been a customer for over 2 years  = "+totalbill);
		
		//Fourth Case : A user can get only one of the percentage based discounts on a bill
		totalbill = calculateBill(onlyone);
		System.out.println("Total payable bill, a user can get only one of the percentage based discounts on a bill  = "+totalbill);
		
	}
	
	private static double calculateBill(User user) {
		List<Product> products = user.getProductList();
		double TotalCostForDiscount = 0;
		double TotalCostOfGrocery = 0;

		for (Product product : products) {
			
			//The percentage based discounts do not apply on groceries.
			if(product.getCategory().getName()==GROCERY) {
				TotalCostOfGrocery = TotalCostOfGrocery+product.getTotalPrice();
			}else {
				TotalCostForDiscount = TotalCostForDiscount+product.getTotalPrice();
			}
		}
		System.out.println("----------Invoice Report ----------");
		System.out.println("Total Cost For Discount = " +TotalCostForDiscount);
		System.out.println("Total Cost Of Grocery = " +TotalCostOfGrocery);
		
		long yearOfJoining = getNoOfJoiningYears(user.getJoinDate());
		//If the user is an employee of the store, he gets a 30% discount
		if(!user.isGetDiscount() && user.isEmployee()) {
			TotalCostForDiscount = (TotalCostForDiscount*30)/100;
			user.setGetDiscount(true);
			System.out.println("Total Cost with 30% discount because he is employee = " +TotalCostForDiscount);
		}
		
		//If the user is an affiliate of the store, he gets a 10% discount
		else if(!user.isGetDiscount() && user.isAffiliate()) {
			TotalCostForDiscount = (TotalCostForDiscount*10)/100;
			user.setGetDiscount(true);
			System.out.println("Total Cost with 10% discount because he is affiliate = " +TotalCostForDiscount);
		}
		//If the user has been a customer for over 2 years, he gets a 5% discount.
		else if(!user.isGetDiscount() && yearOfJoining>=2) {
			TotalCostForDiscount = (TotalCostForDiscount*5)/100;
			user.setGetDiscount(true);
			System.out.println("Total Cost with 5% discount because he has been for over 2 year = " +TotalCostForDiscount);
		}
		
		double totalCost = TotalCostForDiscount + TotalCostOfGrocery;
		System.out.println("total Cost without $5 discount ==== "+totalCost);
		//For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount)
		double discount =  (((long)totalCost/100)*5);
		System.out.println("You get $5 discount on every $ 100 on bill = " + discount);
		totalCost = totalCost-discount;
		
		return totalCost;
	}
	
	
	private static List<Product> getProductList() {
		
		Category groceryCategory = new Category(100,GROCERY);
		Category nonGroceryCategory = new Category(101,NON_GROCERY);
		
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
	
	private static long getNoOfJoiningYears(String joiningDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		long diffYears = 0;
		try {
			Date joindate = format.parse(joiningDate);
			Duration duration = Duration.between(joindate.toInstant(), Instant.now());
			diffYears = duration.toMillis()/(1000l * 60 * 60 * 24 * 365);
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return diffYears;
	}
	

}
