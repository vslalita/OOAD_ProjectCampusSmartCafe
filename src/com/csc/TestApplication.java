package com.csc;

import java.util.ArrayList;

import com.csc.model.FoodItem;
import com.csc.model.User;

public class TestApplication {

	public static void main(String[] args){
		 //Test Case1: To check on create user for a session.
		 CurrentSession.getMemberInstance("12345");
		 User user=CurrentSession.getCurrentUser();
		 System.out.println("Current User Info: "+ user.getId()+"  "+user.getFirstName()+" "+user.getLastName()+" "+user.getCardNumber());
		 
		 //Test Case2: to check on display food items of a vending machine.
		 FoodJointService f=new VendingMachineService();
		 ArrayList<FoodItem> foodItems=f.displayItems();
		 for(int i=0;i<foodItems.size();i++){
			 FoodItem item=foodItems.get(i);
			 System.out.println("FoodItem: id- "+item.getId()+ " name- "+item.getItemName()+" Price- "+item.getPrice()+" Calories- "+item.getCalories());
		 }
		 
	}	
}
