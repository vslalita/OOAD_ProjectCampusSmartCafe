package com.csc;

import java.util.ArrayList;

abstract public class FoodJoint {
    protected FoodPurchaseTransaction transaction;
    private int locationId;
    
    public FoodJoint(FoodPurchaseTransaction transaction,int locationId){
    	this.transaction=transaction;
    	this.locationId=locationId;
    }
    
    public FoodJoint(){
    	
    }
    
    //Template pattern
	public boolean createandUpdate(){
		if(validateOrder()){
			createTransaction();
			updateProfile();
			return true;
		}
		else{
			return false;
		}
	}
	private boolean validateOrder(){
		//TODO validate against preferences calories and expenses etc
		return true;
	}
	protected abstract void createTransaction();
	public abstract ArrayList<FoodItem> displayItems();
	private void updateProfile(){
		//TODO code for updating expenses and dietary profile
		//TODO uses Profile class to update the profile.
	}
	
}
