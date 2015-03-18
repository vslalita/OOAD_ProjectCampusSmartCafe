package com.csc.controller;

import com.csc.CurrentSession;
import com.csc.model.FoodJoint;
import com.csc.model.FoodPurchaseTransaction;
import com.csc.service.FoodJointService;

public class FoodJointController {
	
	public void selectFoodJoint(FoodJoint foodJoint){
		CurrentSession.getInstance().setCurrentFoodJoint(foodJoint);
	}
	public FoodPurchaseTransaction getFoodPurchaseTransactionDetails(int orderTransactionId){
		FoodJointService foodJointService = CSCServiceContext.getFoodJointService();
		return foodJointService.getFoodPurchaseTransactionDetails(orderTransactionId);	
	}
	
	public ArrayList<FoodItem> getFoodJointItems(){
		return CSCServiceContext.getFoodJointService().displayItems();
	}
}
