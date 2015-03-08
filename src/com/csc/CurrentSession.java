package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.Model.User;

public class CurrentSession {
	//currentUser holds the information of the member who is currently logged in
	private static  User currentUser;
	public static FoodJoint currentMachine=new VendingMachine();
	private static CurrentSession currentSession=null;
	private CurrentSession(User user){
		currentUser=user;
	}

	public static CurrentSession getMemberInstance(String cardNumber){
		User user=null;
		if(currentSession==null){
			user=new User();
			user.getUserByCardNumber(cardNumber);
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

