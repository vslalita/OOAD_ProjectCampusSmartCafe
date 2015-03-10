package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CSCApplication {
    //TODO add this method to FoodJoint
	public ArrayList<FoodJoint> getAllFoodJoints(){
		ArrayList<FoodJoint> foodJoints=new ArrayList<FoodJoint>();
		try {
			Statement foodJointsQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String foodJointQuery="Select * from food_joint";
			ResultSet foodJointQueryResult=foodJointsQueryStatement.executeQuery(foodJointQuery);
			while(foodJointQueryResult.next()){
				FoodJoint foodJoint;
				int id=foodJointQueryResult.getInt("id");
				String location=foodJointQueryResult.getString("location_address");
				int xPosition=foodJointQueryResult.getInt("x_position");
				int yPosition=foodJointQueryResult.getInt("y_position");
				if(foodJointQueryResult.getBoolean("is_vending_machine")){
					foodJoint=new VendingMachine(id,location,xPosition,yPosition);
					foodJoints.add(foodJoint);
				}
				if(foodJointQueryResult.getBoolean("is_cafe")){
					foodJoint=new Cafe(id,location,xPosition,yPosition);
					foodJoints.add(foodJoint);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodJoints;
	}
	
}
