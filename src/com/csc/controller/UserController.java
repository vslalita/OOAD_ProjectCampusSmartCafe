package com.csc.controller;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.model.FoodPreference;
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
		
		
}
