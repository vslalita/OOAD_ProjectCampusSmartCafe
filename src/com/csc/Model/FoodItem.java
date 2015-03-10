package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.DatabaseConnection;

public class FoodItem {
	private int id;
	private String itemName;
	private int price;
	private int calories;
	//   private boolean existsInVendingMachine;
	//   private boolean existsInCafe;

	public FoodItem(int id,String itemName, int price, int calories){
		this.id=id;
		this.itemName=itemName;
		this.price=price;
		this.calories=calories;

	}
	
	public FoodItem(int id){
		this.id=id;
		getFoodItem(id);
	}

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public int getCalories() {
		return calories;
	}

	public int getId() {
		return id;
	}
	
	private void getFoodItem(int itemId) {
		// TODO Auto-generated method stub
		Statement foodItemQueryStatement;
		try {
			foodItemQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String foodItemQuery="Select * from food_item where id="+itemId;
			ResultSet foodItemQueryResult=foodItemQueryStatement.executeQuery(foodItemQuery);
			while(foodItemQueryResult.next()){
				this.itemName=foodItemQueryResult.getString("name");
				this.price =foodItemQueryResult.getInt("price");
				this.calories=foodItemQueryResult.getInt("calories");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
