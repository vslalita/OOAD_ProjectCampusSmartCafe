package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.Model.FoodItem;
import com.csc.Model.FoodPurchaseTransaction;

public class Cafe extends FoodJoint {

	public Cafe(FoodPurchaseTransaction transaction, int locationId) {
		super(transaction, locationId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createTransaction(ArrayList<FoodItem> foodItems) {
		// TODO creates the transaction by setting status and the pickup location.
		FoodPurchaseTransaction transaction=new FoodPurchaseTransaction(foodItems,CurrentSession.getCurrentUser().getCardNumber(),this.locationId);
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
				FoodItem item=new FoodItem(id,itemName,price,calories);
				foodItems.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodItems;
	}
}
