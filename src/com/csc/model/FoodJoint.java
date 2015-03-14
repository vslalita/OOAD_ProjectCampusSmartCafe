package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.DatabaseConnection;

public class FoodJoint {
	private int id;
    //TODO include property name
	protected String location;
	private String name;
	private int xPosition;
	private int yposition;
	private boolean isCafe;
	private boolean isVendingMachine;
    
    public FoodJoint(int id,String name,String location,int xPosition,int yPosition,boolean isCafe,boolean isVendingMachine){
    	this.id=id;
    	this.name=name;
    	this.location=location;
    	this.xPosition=xPosition;
    	this.yposition=yPosition;
    	this.isCafe=isCafe;
    	this.isVendingMachine=isVendingMachine;
    }
    
    public FoodJoint(){
    	
    }
    
    
    public int getId(){
    	return this.id;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public boolean getIsVendingMachine(){
    	return this.isVendingMachine;
    }
    
    
    public boolean getIsCafe(){
    	return this.isCafe;
    }
    
    public int getXPostion(){
    	return this.xPosition;
    }
    
    public int getYPostion(){
    	return this.yposition;
    }

    public String getLocation(){
    	return this.location;
    }
    
    public void setFoodJointDetailsById(int id) {
		// TODO Auto-generated method stub
    	
    	Statement foodJointsStatement;
		try {
			foodJointsStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodJointsQuery="Select * from food_joint where id="+id;
			ResultSet foodJointsQueryResult=foodJointsStatement.executeQuery(foodJointsQuery);
			while(foodJointsQueryResult.next()){
				this.id=id;
				this.name=foodJointsQueryResult.getString("name");
				this.location=foodJointsQueryResult.getString("location_address");
				this.xPosition=foodJointsQueryResult.getInt("x_position");
				this.yposition=foodJointsQueryResult.getInt("y_position");
				this.isCafe=foodJointsQueryResult.getBoolean("is_cafe");
				this.isVendingMachine=foodJointsQueryResult.getBoolean("is_vending_machine");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
   
    
}
