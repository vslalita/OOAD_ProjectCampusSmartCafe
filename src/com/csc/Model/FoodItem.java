package com.csc.Model;

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
}
