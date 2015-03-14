package com.csc;

import com.csc.model.FoodJoint;
import com.csc.model.User;

public class CurrentSession {
	//currentUser holds the information of the member who is currently logged in
	private static  User currentUser;
	private static FoodJoint currentMachine;
	private static CurrentSession currentSession=null;
	private CurrentSession(User user){
		currentUser=user;
	}

	public static CurrentSession getMemberInstance(String cardNumber){
		User user=null;
		if(currentSession==null){
			user=new User();
			user.setUserDetailsByCardNumber(cardNumber);
			if(user.getId()>0){
				currentSession=new CurrentSession(user);
			}
			return currentSession;
		}
		else{
			currentUser=user;
			return currentSession;
		}
	}

	public static void setMachine(FoodJoint joint){
		if(currentMachine==null){
			currentMachine=joint;
		}
	}
	
	public static FoodJoint getMachine(){
		return currentMachine;
	}
	
	public static User getCurrentUser(){
		if(currentSession!=null && currentUser!=null){
			return currentUser;
		}
		else return null;
	}

	public static void resetCurrentMember(){
		currentUser=null;
	}
}

