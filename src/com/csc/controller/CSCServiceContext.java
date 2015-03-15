package com.csc.controller;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.service.CafeService;
import com.csc.service.FoodJointService;
import com.csc.service.ProfileService;
import com.csc.service.UserService;
import com.csc.service.VendingMachineService;

public class CSCServiceContext {
	
	private static UserService userService;
	private static FoodJointService foodJointService;
	private static ProfileService profileService;
	public static UserService getUserService(){
		if(userService==null){
			userService=new UserService();
		}
		return userService;
	}	

	//Factory Method Pattern
	public static FoodJointService getFoodJointService(){
		if(foodJointService==null){
			if(CurrentSession.getInstance().getCurrentFoodJoint().isVendingMachine()){
				foodJointService=new VendingMachineService();
			}
			else{
				foodJointService=new CafeService();
			}
		}
		return foodJointService;
	}
	
	
	public static ProfileService getProfileService(){
		if(profileService==null){
			profileService=new ProfileService();
		}
		return profileService;
	}

}
