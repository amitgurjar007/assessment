package com.xebia.utilities;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.xebia.model.Product;
import com.xebia.model.User;

public class XebiaUtility {

	public static final String GROCERY = "Grocery";
	public static final String NON_GROCERY = "Non Grocery";
	
	/**
	 * This method calculate the bill of customer according their discount and return the amount of bill.
	 * @param user
	 * @return
	 */
	public static double calculateBill(User user) {
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
	

	/**
	 * This method calculate the joining time of customer.
	 * @param joiningDate
	 * @return
	 */
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
