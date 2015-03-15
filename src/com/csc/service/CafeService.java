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
import com.csc.model.FoodPurchaseTransaction;

public class CafeService extends FoodJointService {
	
	public CafeService(){

	}
	

	@Override
	protected void createOrder(ArrayList<FoodItem> foodItems) {
		// TODO creates the transaction by setting status and the pickup location.
	    String  cardNumber = CurrentSession.getInstance().getCurrentUser().getCardNumber();
	    FoodJoint currentFoodJoint = CurrentSession.getInstance().getCurrentFoodJoint();
		FoodPurchaseTransaction transaction=new FoodPurchaseTransaction(foodItems,
						cardNumber,currentFoodJoint);
		transaction.setStatus("Ordered");
		transaction.createTransaction();
	}
	
	@Override
	public ArrayList<FoodItem> displayItems() {
		// TODO display items that are for a cafe
		// TODO displays items that are to be displayed nly for a vending machine
		ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
		Statement foodItemsQueryStatement;
		try {
			foodItemsQueryStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodItemsQuery="Select * from food_item where is_cafe=true";
			ResultSet foodItemsQueryResult=foodItemsQueryStatement.executeQuery(foodItemsQuery);
			while(foodItemsQueryResult.next()){
				int id=foodItemsQueryResult.getInt("id");
				String itemName=foodItemsQueryResult.getString("name");
				int price =foodItemsQueryResult.getInt("price");
				int calories=foodItemsQueryResult.getInt("calories");
				boolean isLowFat=foodItemsQueryResult.getBoolean("low_fat");
				boolean isLowSodium=foodItemsQueryResult.getBoolean("low_sodium");
				FoodItem item=new FoodItem(id,itemName,price,calories,isLowSodium,isLowFat);
				foodItems.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodItems;
	}
}
