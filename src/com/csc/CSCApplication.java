package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.model.FoodJoint;

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
				String name=foodJointQueryResult.getString("name");
				boolean isVendingMachine=foodJointQueryResult.getBoolean("is_vending_machine");
				boolean isCafe=foodJointQueryResult.getBoolean("is_cafe");
				//				if(foodJointQueryResult.getBoolean("is_vending_machine")){
				foodJoint=new FoodJoint(id,name,location,xPosition,yPosition,isCafe,isVendingMachine);
				foodJoints.add(foodJoint);
				//				}
				//				if(foodJointQueryResult.getBoolean("is_cafe")){
				//					foodJoint=new Cafe(id,name,location,xPosition,yPosition,isCafe,isVendingMachine);
				//					foodJoints.add(foodJoint);
				//				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodJoints;
	}

}
