package com.csc;

import java.util.ArrayList;

import com.csc.model.FoodItem;
import com.csc.model.FoodPreference;

abstract public class FoodJointService {
    
    public FoodJointService(){
    }
	
	protected abstract void createTransaction(ArrayList<FoodItem> foodItems);
	
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
			int amount=0;
			FoodPreference fp=new FoodPreference();
			fp.setPrefDeatilsByCardNumber(CurrentSession.getCurrentUser().getCardNumber());
			for(int i=0;i<items.size();i++){
				FoodItem item=items.get(i);
				if((fp.isLowFat()&&item.isLowFat())||(fp.isLowSodium()&&item.isLowSodium())||(!fp.isLowFat()&&!fp.isLowSodium())){
					calories=calories+items.get(i).getCalories();
					amount=amount+items.get(i).getPrice();
				}
				else{
					return false;
				}
				
			}
			
			int expenses=CurrentSession.getCurrentUser().getExpenses();
			int availableBalance=CurrentSession.getCurrentUser().getRemainingExpenses();
			FoodPreference caloriePreference=new FoodPreference();
			caloriePreference.setPrefDeatilsByCardNumber(CurrentSession.getCurrentUser().getCardNumber());
			int userDailyIntakeCalories=caloriePreference.getCalories();
			int remainingIntakeCalories=caloriePreference.getRemainingCalories();
			if((remainingIntakeCalories>0)&&(userDailyIntakeCalories>0)&&(expenses>0)&&(availableBalance>0)){
				if((calories<=remainingIntakeCalories)&&(calories<=userDailyIntakeCalories)&&(amount<=availableBalance)&&(amount<=expenses)){
					return true;
				}
			}
		}
		return false;
	}
	
	private void updateProfile(){
		FoodPreference preferencesForValidation=new FoodPreference();
		preferencesForValidation.setPrefDeatilsByCardNumber(CurrentSession.getCurrentUser().getCardNumber());
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getCurrentUser().updateRemainingExpenses();
		
	}
}
