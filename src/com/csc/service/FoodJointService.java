package com.csc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.model.FoodItem;
import com.csc.model.FoodJoint;
import com.csc.model.FoodPreference;
import com.csc.model.User;

abstract public class FoodJointService {
	
    public FoodJointService(){
    }
	
	public abstract ArrayList<FoodItem> displayItems();
    
   
	public FoodJoint getFoodJointDetailsById(int foodJointId) {
		// TODO Auto-generated method stub
    	
    	Statement foodJointsStatement;
    	FoodJoint foodJoint = null;
		try {
			foodJointsStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodJointsQuery="Select * from food_joint where id="+foodJointId;
			ResultSet foodJointsQueryResult=foodJointsStatement.executeQuery(foodJointsQuery);
			if(foodJointsQueryResult.next()){
				foodJoint=new FoodJoint();
				foodJoint.setId(foodJointId);
				foodJoint.setName(foodJointsQueryResult.getString("name"));
				foodJoint.setLocation(foodJointsQueryResult.getString("location_address"));
				foodJoint.setXPosition(foodJointsQueryResult.getInt("x_position"));
				foodJoint.setYPosition(foodJointsQueryResult.getInt("y_position"));
				foodJoint.setCafe(foodJointsQueryResult.getBoolean("is_cafe"));
				foodJoint.setVendingMachine(foodJointsQueryResult.getBoolean("is_vending_machine"));
			}
			return foodJoint;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return foodJoint;
		}
	}
	
	private boolean validateOrder(ArrayList<FoodItem> items){
		//TODO validate against preferences calories and expenses etc
		this.updateRemainingCaloriesAndExpenses();
		if(items!=null&&items.size()>0){
			int calories=0;
			int amount=0;
			FoodPreference fp=CurrentSession.getInstance().getCurrentUser().getFoodPreference();
			int expenses=CurrentSession.getInstance().getCurrentUser().getExpenses();
			int availableBalance=CurrentSession.getInstance().getCurrentUser().getRemainingExpenses();
			int userDailyIntakeCalories=fp.getCalories();
			int remainingIntakeCalories=fp.getRemainingCalories();
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
			if((remainingIntakeCalories>0)&&(userDailyIntakeCalories>0)&&(expenses>0)&&(availableBalance>0)){
				if((calories<=remainingIntakeCalories)&&(calories<=userDailyIntakeCalories)&&(amount<=availableBalance)&&(amount<=expenses)){
					return true;
				}
			}
		}
		return false;
	}
	
	protected abstract void createOrder(ArrayList<FoodItem> foodItems);

	//Template pattern
	public boolean validateAndCreateOrder(ArrayList<FoodItem> foodItems){
		if(this.validateOrder(foodItems)){
			this.createOrder( foodItems);
			this.updateRemainingCaloriesAndExpenses();
			return true;
		}
		else{
			return false;
		}
	}
	
	private void updateRemainingCaloriesAndExpenses(){
		FoodPreference preferencesForValidation = CurrentSession.getInstance().getCurrentUser().getFoodPreference();
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getInstance().getCurrentUser().updateRemainingExpenses();
		
	}
	
}
