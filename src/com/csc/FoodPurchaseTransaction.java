package com.csc;

import java.sql.Date;
import java.util.ArrayList;

enum Status{
	Ordered,
	Delivered
};

public class FoodPurchaseTransaction {
   
   private int id;
   private ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
   private ArrayList<UserFoodPreference> foodPreferences=new ArrayList<UserFoodPreference>();
   private Status status;
   private int locationId;
   private int cardNumber;
   private Date date;
   
   public FoodPurchaseTransaction(ArrayList<FoodItem> foodItems,ArrayList<UserFoodPreference> foodPreferences,int cardNumber){
	   this.foodItems=foodItems;
	   this.foodPreferences=foodPreferences;
	   this.cardNumber=cardNumber;
   }
   
   public void setStatus(Status status){
	   this.status=status;
   }
   
   public void setLocation(int locationId){
	   this.locationId=locationId;
   }
   
   public void createTransaction(){
	   //TODO write functionality to insert data into table
   }
   
   public void deleteTransaction(){
	   //TODO delete ordered items and transaction
   }
   
}
