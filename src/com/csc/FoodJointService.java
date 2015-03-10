package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csc.model.FoodItem;
import com.csc.model.FoodPreference;
import com.csc.model.FoodPurchaseTransaction;

abstract public class FoodJointService {
    
    public FoodJointService(){
    	
    }
    
    
    
	
	protected abstract void createTransaction(ArrayList<FoodItem> foodItems);
	
	//called by gui
	public abstract ArrayList<FoodItem> displayItems();
    
    //Template pattern
	public boolean validateAndCreateTransaction(ArrayList<FoodItem> foodItems){
		if(validateOrder(foodItems)){
			createTransaction( foodItems);
			updateProfile();
			return true;
		}
		else{
			return false;
		}
	}
		
	private boolean validateOrder(ArrayList<FoodItem> items){
		//TODO validate against preferences calories and expenses etc
		updateProfile();
		if(items!=null&&items.size()>0){
			int calories=0;
			for(int i=0;i<items.size();i++){
				calories=calories+items.get(i).getCalories();
			}
			
			FoodPreference caloriePreference=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
			int userDailyIntakeCalories=caloriePreference.getUserCalories();
			int remainingIntakeCalories=caloriePreference.getRemainingCalories();
			if((remainingIntakeCalories>0)&&userDailyIntakeCalories>0){
				if(calories<=remainingIntakeCalories&&calories<=userDailyIntakeCalories){
					return true;
				}
			}
		}
		return false;
	}
	
	private void updateProfile(){
		FoodPreference preferencesForValidation=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getCurrentUser().updateRemainingExpenses();
		
	}
}
