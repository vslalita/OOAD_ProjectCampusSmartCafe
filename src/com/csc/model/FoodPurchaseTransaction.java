package com.csc.model;

import java.util.ArrayList;

public class FoodPurchaseTransaction {

	private int id;
	private ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
	private String status;
	private int foodJointId;
	private String cardNumber;

	public FoodPurchaseTransaction(int id,ArrayList<FoodItem> foodItems,String cardNumber,int foodJointId){
		this.id=id;
		this.setFoodItems(foodItems);
		this.cardNumber=cardNumber;
		this.foodJointId=foodJointId;
	}
	
	public FoodPurchaseTransaction(ArrayList<FoodItem> foodItems,String cardNumber,int foodJointId){
		this.setFoodItems(foodItems);
		this.cardNumber=cardNumber;
		this.foodJointId=foodJointId;
	}
	
	public FoodPurchaseTransaction(){
		
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(ArrayList<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
    public String getStatus(){
    	return this.status;
    }
    
	public void setStatus(String status){
		this.status=status;
	}
	
	public void setCardNumber(String cardNumber){
		this.cardNumber=cardNumber;
	}
	
	public String getCardNumber(){
		return this.cardNumber;
	}

	public int getFoodJoint(){
		return this.foodJointId;
	}
	public void setFoodJoint(int foodJointId){
		this.foodJointId=foodJointId;
	}
	
}
