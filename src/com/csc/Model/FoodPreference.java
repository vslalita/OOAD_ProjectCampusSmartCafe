package com.csc.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csc.CurrentSession;
import com.csc.DatabaseConnection;

public class FoodPreference {
   private String cardNumber;
   private int calories;
   
   public FoodPreference(String cardnumber,int calories){
	   this.calories=calories;
	   this.cardNumber=cardnumber;
   }
   
   public FoodPreference(String cardnumber){
	   this.cardNumber=cardnumber;
	   this.calories=getUserCalories();
   }
   
   public void save(int caloriesToBeAdded){
	   try {
		Statement preferenceStatement=DatabaseConnection.connectionRequest().createStatement();
		String preferenceQuery="Select count(*) from food_preferences where card_number='"+this.cardNumber+"'";
		ResultSet preferenceQueryResult=preferenceStatement.executeQuery(preferenceQuery);
		if(preferenceQueryResult.next()){
			if(preferenceQueryResult.getInt("count(*)")>0){
			    caloriesToBeAdded=this.calories+caloriesToBeAdded;
				String query="update food_preferences set calories="+caloriesToBeAdded+" where card_number='"+this.cardNumber+"'";
				preferenceStatement.executeUpdate(query);
			}
			else{
				String query="insert into food_preferences(card_number,calories,calories_remaining) values("+this.cardNumber+","+this.calories+","+this.calories+")";
				preferenceStatement.executeUpdate(query);
			}
		}
		updateRemainingCalories();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public void updateRemainingCalories(){
	   DateFormat dateFormatForCalories = new SimpleDateFormat("yyyy-MM-dd");
	   Date dateObj = new Date();
	   Statement updateProfileStatement;
	try {
		updateProfileStatement = DatabaseConnection.connectionRequest().createStatement();
		String orderItemsCaloriesQuery="Select fi.calories,fot.amount,fot.created_on "
				+ "                 from food_order_transaction fot,food_order_transaction_lines fotl, food_item fi "
				+ "                 where fotl.item_id=fi.id "
				+ "                   and fot.id=fotl.order_id"
				+"                    and fot.card_number='"+this.cardNumber+"'"
				+ "                   and fot.created_on like '"+dateFormatForCalories.format(dateObj)+"%'";
		ResultSet orderItemsCaloriesResult=updateProfileStatement.executeQuery(orderItemsCaloriesQuery);
		int caloriesSpent=0;
		while(orderItemsCaloriesResult.next()){
			caloriesSpent=caloriesSpent+orderItemsCaloriesResult.getInt("calories");
	    }
		int userDailyIntakeCalories=getUserCalories();
		int caloriesRemaining=0;
		if(caloriesSpent==0){
			caloriesRemaining=userDailyIntakeCalories;
		}
		else if(caloriesSpent<=userDailyIntakeCalories){
			caloriesRemaining=userDailyIntakeCalories-caloriesSpent;
		}
		String query="update food_preferences set calories_remaining="+caloriesRemaining+" where card_number='"+CurrentSession.getCurrentUser().getCardNumber()+"'";
		updateProfileStatement.executeUpdate(query);
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
   }
   
   public int getUserCalories(){
	   int dailyIntakeCalories=0;
	   try {
		Statement getCaloriesStatement=DatabaseConnection.connectionRequest().createStatement();
		String getCaloriesQuery="Select calories from food_preferences where card_number='"+CurrentSession.getCurrentUser().getCardNumber()+"'";
		ResultSet getCaloriesResult=getCaloriesStatement.executeQuery(getCaloriesQuery);
		while(getCaloriesResult.next()){
			dailyIntakeCalories=getCaloriesResult.getInt("calories");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return dailyIntakeCalories;
   }
   
   public int getRemainingCalories(){
	   int remainingCalories=0;
	   try {
			Statement getRemainingCaloriesStatement=DatabaseConnection.connectionRequest().createStatement();
			String getRemainingCaloriesQuery="Select calories_remaining from food_preferences where card_number='"+CurrentSession.getCurrentUser().getCardNumber()+"'";
			ResultSet getRemainingCaloriesResult=getRemainingCaloriesStatement.executeQuery(getRemainingCaloriesQuery);
			while(getRemainingCaloriesResult.next()){
				remainingCalories=getRemainingCaloriesResult.getInt("calories_remaining");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return remainingCalories;
   }
	
}
