package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csc.Model.FoodItem;
import com.csc.Model.FoodPreference;
import com.csc.Model.FoodPurchaseTransaction;

abstract public class FoodJoint {
    private int id;
	protected String location;
	private int xPosition;
	private int yposition;
    
    public FoodJoint(int id,String location,int xPosition,int yPosition){
    	this.id=id;
    	this.location=location;
    	this.xPosition=xPosition;
    	this.yposition=yPosition;
    }
    
    public FoodJoint(){
    	
    }
    
    public FoodJoint(int id){
    	this.id=id;
    	getLocation(id);
    	
    }
    
    private void getLocation(int id) {
		// TODO Auto-generated method stub
    	Statement foodJointsStatement;
		try {
			foodJointsStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodJointsQuery="Select * from food_joint where id="+id;
			ResultSet foodJointsQueryResult=foodJointsStatement.executeQuery(foodJointsQuery);
			while(foodJointsQueryResult.next()){
				this.location=foodJointsQueryResult.getString("location_address");
				this.xPosition=foodJointsQueryResult.getInt("x_position");
				this.yposition=foodJointsQueryResult.getInt("y_position");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLocation(){
    	return this.location;
    }
    
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
	protected abstract void createTransaction(ArrayList<FoodItem> foodItems);
	public abstract ArrayList<FoodItem> displayItems();
	private void updateProfile(){
		FoodPreference preferencesForValidation=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getCurrentUser().updateRemainingExpenses();
		
	}
}
