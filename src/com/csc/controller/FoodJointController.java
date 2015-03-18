package com.csc.controller;

import java.util.ArrayList;

import com.csc.CurrentSession;
import com.csc.model.FoodItem;
import com.csc.model.FoodJoint;

public class FoodJointController {
	
	public void selectFoodJoint(FoodJoint foodJoint){
		CurrentSession.getInstance().setCurrentFoodJoint(foodJoint);
	}
	
	public ArrayList<FoodItem> getFoodJointItems(){
		return CSCServiceContext.getFoodJointService().displayItems();
	}
	
	public boolean validateAndCreate(ArrayList<FoodItem> items){
		CurrentSession session=CurrentSession.getInstance();
		return CSCServiceContext.getFoodJointService().validateAndCreateOrder(items, session.getCurrentUser(), session.getCurrentFoodJoint());
	}
	
}
