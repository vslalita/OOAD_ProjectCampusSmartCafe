package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.controller.FoodJointController;
import com.csc.controller.UserController;
import com.csc.model.FoodJoint;
import com.csc.service.CafeService;
import com.csc.service.FoodJointService;
import com.csc.service.ProfileService;
import com.csc.service.UserService;
import com.csc.service.VendingMachineService;

public class CSCApplicationContext {
	//TODO add this method to FoodJoint
	//TODO Can move this to the FoodJointService. Think about it?
    private static UserController userController;
	private static FoodJointController foodJointController;

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
	
	public static CurrentSession getCurrentSession(){
		return CurrentSession.getInstance();
	}
	
	
	public static UserController getUserController(){
		if(userController==null){
			userController=new UserController();
		}
		return userController;
	}	
	
	public static FoodJointController getFoodJointController(){
		
		if(foodJointController==null){
			foodJointController=new FoodJointController();
		}
		return foodJointController;
	}	

}
