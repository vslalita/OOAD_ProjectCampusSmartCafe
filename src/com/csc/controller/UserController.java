package com.csc.controller;

import java.util.ArrayList;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.model.FoodJoint;
import com.csc.model.FoodPreference;
import com.csc.model.FoodPurchaseTransaction;
import com.csc.model.User;

public class UserController {
	public UserController(){
		
	}
	//Checks if a registered user exists by this card number and sets the currentSession with this user information
	public boolean login(String cardNumber){
			User user = CSCServiceContext.getUserService().getUserDetailsByCardNumber(cardNumber);
			CurrentSession session=CurrentSession.getInstance();
			if (session==null) { 
				return false;
			}
			if (user==null) {
				return false;
			}
			if(user!=null && session!=null){
				session.setCurrentUser(user);
				return true;
			}
			return false;
		}
	
	
	//Update User Preferences
	public void updateFoodPreference(User user,int calories,String action,boolean lowSodium,boolean lowFat){
		CSCServiceContext.getUserService().updateFoodPreference(user,calories, action, lowSodium, lowFat);	
	}
	//TODO Priority High pass user object to updateExpenses etc 
	
	public void updateExpenses(User user,int expenses,String action){
		CSCServiceContext.getUserService().updateExpenses(user,expenses, action);
	}
	
	public boolean pickUpOrder(FoodPurchaseTransaction transaction){
		return CSCServiceContext.getUserService().pickUpOrder(transaction);
	}
	
	public ArrayList<FoodPurchaseTransaction> getUnpickedOrders(User user,FoodJoint foodJoint){
		return CSCServiceContext.getUserService().getUserUnpickedOrders(user,foodJoint);
	}
		
	public PieDataset getDietaryStatistics(User user){
		return CSCServiceContext.getProfileService().getDietaryStatistics(user);
	}
	
	public CategoryDataset getExpensesStatistics(User user){
		return CSCServiceContext.getProfileService().getExpensesStatics(user);
	}
		
}
