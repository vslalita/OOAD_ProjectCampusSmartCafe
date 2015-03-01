package com.csc;

public class FoodItem {
   private int id;
   private String itemName;
   private int price;
   private int calories;
   private boolean existsInVendingMachine;
   private boolean existsInCafe;
   
   public FoodItem(int id,String itemName, int price, int calories, boolean existsInVendingMachine, boolean existsInCafe ){
	   this.id=id;
	   this.itemName=itemName;
	   this.price=price;
	   this.calories=calories;
	   this.existsInVendingMachine=existsInVendingMachine;
	   this.existsInCafe=existsInCafe;
   }
 
}
