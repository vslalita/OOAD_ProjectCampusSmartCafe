package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;

public class FoodPreference {
	private String cardNumber;
	private int calories;
	private boolean lowSodium;
	private boolean lowFat;

	public FoodPreference(String cardnumber,int calories,boolean lowSodium,boolean lowFat){
		this.calories=calories;
		this.cardNumber=cardnumber;
		this.lowSodium=lowSodium;
		this.lowFat=lowFat;
	}

	public FoodPreference() {
		// TODO Auto-generated constructor stub
	}

	
	


	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public boolean isLowSodium() {
		return lowSodium;
	}

	public void setLowSodium(boolean lowSodium) {
		this.lowSodium = lowSodium;
	}

	public boolean isLowFat() {
		return lowFat;
	}

	public void setLowFat(boolean lowFat) {
		this.lowFat = lowFat;
	}

	public void save(int caloriesToBeModified,String action,boolean low_sodium,boolean low_fat){
		this.lowFat=low_fat;
		this.lowSodium=low_sodium;
		try {
			Statement preferenceStatement=DatabaseConnection.connectionRequest().createStatement();
			String preferenceQuery="Select count(*) from food_preferences where card_number='"+this.cardNumber+"'";
			ResultSet preferenceQueryResult=preferenceStatement.executeQuery(preferenceQuery);
			if(preferenceQueryResult.next()){
				if(preferenceQueryResult.getInt("count(*)")>0){
					if(action.equals("add")){
						this.calories=this.calories+caloriesToBeModified;
					}
					if(action.equals("reduce")){
						if(caloriesToBeModified<this.calories && caloriesToBeModified<=getRemainingCalories()){
							this.calories=this.calories-caloriesToBeModified;
						}
					}
					String query="update food_preferences set calories="+this.calories+", low_fat="+low_fat+", low_sodium="+low_sodium+" where card_number='"+this.cardNumber+"'";
					preferenceStatement.executeUpdate(query);
				}
				else{
					String query="insert into food_preferences(card_number,calories,calories_remaining,low_fat,low_sodium) values("+this.cardNumber+","+this.calories+","+this.calories+","+this.lowFat+","+this.lowSodium+")";
					preferenceStatement.executeUpdate(query);
				}
			}
			updateRemainingCalories();
			User currentUser = CurrentSession.getInstance().getCurrentUser();
			currentUser.setFoodPreference(this);
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
			int userDailyIntakeCalories=this.calories;
			int caloriesRemaining=0;
			if(caloriesSpent==0){
				caloriesRemaining=userDailyIntakeCalories;
			}
			else if(caloriesSpent<=userDailyIntakeCalories){
				caloriesRemaining=userDailyIntakeCalories-caloriesSpent;
			}
			String query="update food_preferences set calories_remaining="+caloriesRemaining+" where card_number='"+this.cardNumber+"'";
			updateProfileStatement.executeUpdate(query);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public int getRemainingCalories(){
		int remainingCalories=0;
		try {
			Statement getRemainingCaloriesStatement=DatabaseConnection.connectionRequest().createStatement();
			String getRemainingCaloriesQuery="Select calories_remaining from food_preferences where card_number='"+this.cardNumber+"'";
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
