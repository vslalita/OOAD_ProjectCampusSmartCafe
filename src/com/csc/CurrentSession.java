package com.csc;

import com.csc.model.FoodJoint;
import com.csc.model.User;

public class CurrentSession {
	
	private static CurrentSession instance;
	//currentUser-Holds the information of the member who is currently logged in
	private User currentUser;
	//TODO Why is this called currentMachine. Is cafe same as machine?
	private FoodJoint currentFoodJoint;
	
	private CurrentSession(){
		this.currentFoodJoint=null;
		this.currentUser=null;
	}
    
	public synchronized static CurrentSession getInstance(){
		if (instance ==null) {
			instance=new CurrentSession();
		}
		return instance;
	}
	public void reset(){
		instance=null;
	}

	public void setCurrentFoodJoint(FoodJoint joint){
		this.currentFoodJoint=joint;
	}
	
	public FoodJoint getCurrentFoodJoint(){
		return currentFoodJoint;
	}
	public void setCurrentUser(User user){
		this.currentUser=user;
	}
	public User getCurrentUser(){
		return this.currentUser;
	}		
}

