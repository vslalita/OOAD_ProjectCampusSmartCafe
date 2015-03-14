package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CSCApplication {
    //TODO add this method to FoodJoint
	public ArrayList<FoodJointService> getAllFoodJoints(){
		ArrayList<FoodJointService> foodJoints=new ArrayList<FoodJointService>();
		try {
			Statement foodJointsQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String foodJointQuery="Select * from food_joint";
			ResultSet foodJointQueryResult=foodJointsQueryStatement.executeQuery(foodJointQuery);
			while(foodJointQueryResult.next()){
				FoodJointService foodJoint;
				int id=foodJointQueryResult.getInt("id");
				String location=foodJointQueryResult.getString("location_address");
				int xPosition=foodJointQueryResult.getInt("x_position");
				int yPosition=foodJointQueryResult.getInt("y_position");
				boolean isVendingMachine=foodJointQueryResult.getBoolean("is_vending_machine");
				boolean isCafe=foodJointQueryResult.getBoolean("is_cafe");
				if(foodJointQueryResult.getBoolean("is_vending_machine")){
					foodJoint=new VendingMachineService(id,location,xPosition,yPosition,isVendingMachine,isCafe);
					foodJoints.add(foodJoint);
				}
				if(foodJointQueryResult.getBoolean("is_cafe")){
					foodJoint=new Cafe(id,location,xPosition,yPosition,isVendingMachine,isCafe);
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
