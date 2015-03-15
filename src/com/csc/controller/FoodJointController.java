package com.csc.controller;

import com.csc.CurrentSession;
import com.csc.model.FoodJoint;

public class FoodJointController {
	
	public void selectFoodJoint(FoodJoint foodJoint){
		CurrentSession.getInstance().setCurrentFoodJoint(foodJoint);
	}

}
