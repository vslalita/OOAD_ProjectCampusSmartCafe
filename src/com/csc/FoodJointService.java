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
    private int id;
    //TODO include property name
	protected String location;
	private String name;
	private int xPosition;
	private int yposition;
	private boolean isCafe;
	private boolean isVendingMachine;
    
    public FoodJointService(int id,String name,String location,int xPosition,int yPosition,boolean isCafe,boolean isVendingMachine){
    	this.id=id;
    	this.name=name;
    	this.location=location;
    	this.xPosition=xPosition;
    	this.yposition=yPosition;
    	this.isCafe=isCafe;
    	this.isVendingMachine=isVendingMachine;
    }
    
    
    public String getName(){
    	return this.name;
    }
    public FoodJointService(){
    	
    }
    
    public FoodJointService(int id){
    	//remove this constructor
    	this.id=id;
    	this.getLocation(id);
    	
    }
    
    public int getId(){
    	return this.id;
    }
    
    public boolean getIsVendingMachine(){
    	return this.isVendingMachine;
    }
    
    
    public boolean getIsCafe(){
    	return this.isCafe;
    }
    
    public int getXPostion(){
    	return this.xPosition;
    }
    
    public int getYPostion(){
    	return this.yposition;
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
			int amount=0;
			for(int i=0;i<items.size();i++){
				calories=calories+items.get(i).getCalories();
				amount=amount+items.get(i).getPrice();
			}
			
			int expenses=CurrentSession.getCurrentUser().getExpenses();
			int availableBalance=CurrentSession.getCurrentUser().getRemainingExpenses();
			FoodPreference caloriePreference=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
			int userDailyIntakeCalories=caloriePreference.getUserCalories();
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
		FoodPreference preferencesForValidation=new FoodPreference(CurrentSession.getCurrentUser().getCardNumber());
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getCurrentUser().updateRemainingExpenses();
		
	}
}
