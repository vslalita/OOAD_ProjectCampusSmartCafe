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
import com.csc.model.User;

public class VendingMachineService extends FoodJointService {

	
	public VendingMachineService(){
	}
	
	@Override
	protected void createOrder(User user,FoodJoint foodJoint,ArrayList<FoodItem> foodItems) {
		// TODO create a transaction. Implementation involves immediate delivery of the item.
			User currentUser=user;
			FoodPurchaseTransaction transaction=new FoodPurchaseTransaction(foodItems,
					user.getCardNumber(),foodJoint.getId());
			transaction.setStatus("Delivered");
			super.createTransaction(transaction);
	}

	@Override
	public ArrayList<FoodItem> displayItems() {
		// TODO displays items that are to be displayed nly for a vending machine
		ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
		Statement foodItemsQueryStatement;
		try {
			foodItemsQueryStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodItemsQuery="Select * from food_item where is_vending_machine=true";
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
