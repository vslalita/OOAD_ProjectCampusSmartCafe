package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.csc.DatabaseConnection;

public class FoodJoint {

	private int id;
    //TODO include property name
	protected String location;
	private int xPosition;
	private int yposition;
	
	public FoodJoint(int id,String location,int xPosition,int yPosition){
    	this.id=id;
    	this.location=location;
    	this.xPosition=xPosition;
    	this.yposition=yPosition;
    }
	
	public int getId(){
    	return this.id;
    }
    
    //change to get location by id
    private void getLocation(int id) {
		// TODO Auto-generated method stub
    	Statement foodJointsStatement;
		try {
			foodJointsStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodJointsQuery="Select * from food_joint where id="+id;
			ResultSet foodJointsQueryResult=foodJointsStatement.executeQuery(foodJointsQuery);
			while(foodJointsQueryResult.next()){
				this.location=foodJointsQueryResult.getString("location_address");
				this.xPosition=foodJointsQueryResult.getInt("x_position");
				this.yposition=foodJointsQueryResult.getInt("y_position");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLocation(){
    	return this.location;
    }
	
	
	
}
