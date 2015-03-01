package com.csc;

public class UserFoodPreference {
    
	int cardNumber;
    int sodiumPercentage;
    int fatPercentage;
    int cholestrolPercentage;
    int caloriesIntake;
    
    
    public UserFoodPreference(int cardNumber,int sodiumPercentage,int fatPercentage,int cholestrolPercentage,int caloriesIntake){
    	this.cardNumber=cardNumber;
    	this.sodiumPercentage=sodiumPercentage;
    	this.fatPercentage=fatPercentage;
    	this.cholestrolPercentage=cholestrolPercentage;
    	this.caloriesIntake=caloriesIntake;
    }
    
    
    public void updatePreferences(){
    	//TODO update the table with respective percentages and have to be less than 100
    }
    
}
