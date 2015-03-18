package com.csc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.controller.CSCServiceContext;
import com.csc.model.FoodPreference;
import com.csc.model.FoodPurchaseTransaction;
import com.csc.model.User;

public class UserService {
	
	public User getUserDetailsByCardNumber(String cardNumber){
		Statement userQueryStatement;
		User user = null;
		try{
			userQueryStatement = DatabaseConnection.connectionRequest().createStatement();
			String userQuery="Select * from user where card_number='"+cardNumber+"'";
			ResultSet userQueryResult=userQueryStatement.executeQuery(userQuery);
			user = new User();
			if(userQueryResult.next()){
				if(cardNumber.equals(userQueryResult.getString("card_number"))){
					user.setId(userQueryResult.getInt("id"));
					user.setFirstName(userQueryResult.getString("first_name"));
					user.setLastName(userQueryResult.getString("last_name"));
					FoodPreference foodPreference=this.getFoodPreferenceByCardNumber(cardNumber);
					user.setFoodPreference(foodPreference);
					user.setCardNumber(cardNumber);
					user.setExpenses(userQueryResult.getInt("expenses"));
				}
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	private FoodPreference getFoodPreferenceByCardNumber(String cardNumber){
		FoodPreference preference=null;
		Statement preferenceStatement;
		try {
			preferenceStatement = DatabaseConnection.connectionRequest().createStatement();
			String preferenceQuery="Select * from food_preferences where card_number='"+cardNumber+"'";
			ResultSet preferenceQueryResult=preferenceStatement.executeQuery(preferenceQuery);
			if(preferenceQueryResult.next()){
				preference=new FoodPreference();
				preference.setCardNumber(cardNumber);
				preference.setCalories(preferenceQueryResult.getInt("calories"));
				preference.setLowFat(preferenceQueryResult.getBoolean("low_fat"));
				preference.setLowSodium(preferenceQueryResult.getBoolean("low_sodium"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return preference;
	}
	public void updateFoodPreference(User user,int calories,String action,boolean lowSodium,boolean lowFat){
		FoodPreference foodPreference = user.getFoodPreference();
		foodPreference.save(calories,action,lowSodium,lowFat);
	}
	
	public ArrayList<FoodPurchaseTransaction> getCurrentUserUnpickedOrders(){
		ArrayList<FoodPurchaseTransaction> unpickedOrders=new ArrayList<FoodPurchaseTransaction>();
		Statement unpickedOrdersStatement;
		try {
			String cardNumber=CurrentSession.getInstance().getCurrentUser().getCardNumber();
			int foodJointId= CurrentSession.getInstance().getCurrentFoodJoint().getId();
			unpickedOrdersStatement = DatabaseConnection.connectionRequest().createStatement();
			String unpickedOrdersQuery="Select * from food_order_transaction where status='Ordered' "
					+ "and card_number='"+cardNumber+"'"
							+ "and food_joint_id="+foodJointId;
			ResultSet unpickedOrdersQueryResult=unpickedOrdersStatement.executeQuery(unpickedOrdersQuery);
			while(unpickedOrdersQueryResult.next()){
				int id=unpickedOrdersQueryResult.getInt("id");
				FoodJointService foodJointService = CSCServiceContext.getFoodJointService();
				FoodPurchaseTransaction order = foodJointService.getFoodPurchaseTransactionDetails(id);
				unpickedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unpickedOrders;
	}
	
	public void updateExpenses(int expense,String action){
			User currentSessionUser=CurrentSession.getInstance().getCurrentUser();
			int currentExpenses= currentSessionUser.getExpenses();
			if(action.equals("add")){
				
				currentSessionUser.setExpenses(currentExpenses+expense);
			}
			if(action.equals("reduce")){
				int amountRemaining=currentSessionUser.getRemainingExpenses();
				if(expense<currentSessionUser.getExpenses() &&expense<amountRemaining){
					currentSessionUser.setExpenses(currentExpenses-expense);
				}
			}
			currentSessionUser.updateExpenses();
	}
	
	public boolean pickUpOrder(FoodPurchaseTransaction transaction){
		transaction.setStatus("Delivered");
		return CSCServiceContext.getFoodJointService().updateTransaction(transaction);
	}
}
