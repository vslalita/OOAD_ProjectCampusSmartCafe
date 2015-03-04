package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendingMachine extends FoodJoint {

	public VendingMachine(FoodPurchaseTransaction transaction, int locationId) {
		super(transaction, locationId);
		// TODO Auto-generated constructor stub
	}
	
	public VendingMachine(){
		
	}

	@Override
	protected void createTransaction() {
		// TODO create a transaction. Implementation involves immediate delivery of the item.

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