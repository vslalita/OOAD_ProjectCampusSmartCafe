package com.csc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.model.FoodItem;
import com.csc.model.FoodJoint;
import com.csc.model.FoodPreference;
import com.csc.model.FoodPurchaseTransaction;
import com.csc.model.User;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

abstract public class FoodJointService {
	
    public FoodJointService(){
    }
	
	public abstract ArrayList<FoodItem> displayItems();
    
   
	public FoodJoint getFoodJointDetailsById(int foodJointId) {
		// TODO Auto-generated method stub
    	
    	Statement foodJointsStatement;
    	FoodJoint foodJoint = null;
		try {
			foodJointsStatement = DatabaseConnection.connectionRequest().createStatement();
			String foodJointsQuery="Select * from food_joint where id="+foodJointId;
			ResultSet foodJointsQueryResult=foodJointsStatement.executeQuery(foodJointsQuery);
			if(foodJointsQueryResult.next()){
				foodJoint=new FoodJoint();
				foodJoint.setId(foodJointId);
				foodJoint.setName(foodJointsQueryResult.getString("name"));
				foodJoint.setLocation(foodJointsQueryResult.getString("location_address"));
				foodJoint.setXPosition(foodJointsQueryResult.getInt("x_position"));
				foodJoint.setYPosition(foodJointsQueryResult.getInt("y_position"));
				foodJoint.setCafe(foodJointsQueryResult.getBoolean("is_cafe"));
				foodJoint.setVendingMachine(foodJointsQueryResult.getBoolean("is_vending_machine"));
			}
			return foodJoint;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return foodJoint;
		}
	}
	
	private boolean validateOrder(ArrayList<FoodItem> items){
		//TODO validate against preferences calories and expenses etc
		if(items!=null&&items.size()>0){
			int calories=0;
			int amount=0;
			FoodPreference fp=CurrentSession.getInstance().getCurrentUser().getFoodPreference();
			int expenses=CurrentSession.getInstance().getCurrentUser().getExpenses();
			int availableBalance=CurrentSession.getInstance().getCurrentUser().getRemainingExpenses();
			int userDailyIntakeCalories=fp.getCalories();
			int remainingIntakeCalories=fp.getRemainingCalories();
			for(int i=0;i<items.size();i++){
				FoodItem item=items.get(i);
				if((fp.isLowFat()&&item.isLowFat())||(fp.isLowSodium()&&item.isLowSodium())||(!fp.isLowFat()&&!fp.isLowSodium())){
					calories=calories+items.get(i).getCalories();
					amount=amount+items.get(i).getPrice();
				}
				else{
					return false;
				}
				
			}
			if((remainingIntakeCalories>0)&&(userDailyIntakeCalories>0)&&(expenses>0)&&(availableBalance>0)){
				if((calories<=remainingIntakeCalories)&&(calories<=userDailyIntakeCalories)&&(amount<=availableBalance)&&(amount<=expenses)){
					return true;
				}
			}
		}
		System.out.println("No selection made. Please select at least one food item.");
		return false;
	}
	
	protected abstract void createOrder(User user,FoodJoint foodJoint,ArrayList<FoodItem> foodItems);

	//Template pattern
	public boolean validateAndCreateOrder(ArrayList<FoodItem> foodItems,User user,FoodJoint foodJoint){
		if(this.validateOrder(foodItems)){
			this.createOrder( user,foodJoint,foodItems);
			this.updateRemainingCaloriesAndExpenses(user);
			return true;
		}
		else{
			return false;
		}
	}
	//Creates and saves an entry for a transaction marked with the current timestamp.
	protected void createTransaction(FoodPurchaseTransaction transaction){
		try {
			ArrayList<FoodItem> foodItems = transaction.getFoodItems();
			Statement orderInsertionStatement=DatabaseConnection.connectionRequest().createStatement();
			int price=0;
			for(int i=0;i<foodItems.size();i++){
				price=price+foodItems.get(i).getPrice();
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal1 = Calendar.getInstance();
			String orderInsertionQuery="insert into food_order_transaction(food_joint_id,status,card_number,amount,created_on)"
					+ " values ("+transaction.getFoodJoint()+",'"+transaction.getStatus()+"','"+transaction.getCardNumber()+"',"+price+",'"+dateFormat.format(cal1.getTime())+"')";
			int insertionQueryResult=orderInsertionStatement.executeUpdate(orderInsertionQuery,Statement.RETURN_GENERATED_KEYS);
			if(insertionQueryResult>0){
				ResultSet rs=orderInsertionStatement.getGeneratedKeys();
				int orderId=0;
				while(rs.next()){
				 orderId=rs.getInt(1);
				}
				
				for(int i=0;i<foodItems.size();i++){
					String orderLinesInsertionQuery="insert into food_order_transaction_lines(order_id,item_id) values("+orderId+","+foodItems.get(i).getId()+")";
					orderInsertionStatement.executeUpdate(orderLinesInsertionQuery);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean updateTransaction(FoodPurchaseTransaction transaction){
		try {
			Statement updateOrderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String updateOrderQuery="update food_order_transaction set status='"+transaction.getStatus()+"' where id="+transaction.getId();
			int updateOrderQueryResult=updateOrderQueryStatement.executeUpdate(updateOrderQuery);
			if(updateOrderQueryResult>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
      return false;
		
	}
    public FoodPurchaseTransaction getFoodPurchaseTransactionDetails(int orderTransactionId) {	
		try {
			FoodPurchaseTransaction orderTransaction = new FoodPurchaseTransaction();
			orderTransaction.setId(orderTransactionId);
			Statement orderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String orderItemsQuery="Select * from food_order_transaction_lines where order_id="+orderTransactionId;
			ResultSet orderItemsQueryResult=orderQueryStatement.executeQuery(orderItemsQuery);
			while(orderItemsQueryResult.next()){
				FoodItem item=new FoodItem(orderItemsQueryResult.getInt("id"));
				orderTransaction.getFoodItems().add(item);
			}
			String orderQuery="Select * from food_order_transaction where id="+orderTransactionId;
			ResultSet orderQueryResult=orderQueryStatement.executeQuery(orderQuery);
			if(orderQueryResult.next()){
				orderTransaction.setStatus(orderQueryResult.getString("status"));
				orderTransaction.setCardNumber(orderQueryResult.getString("card_number"));
				//TODO Prioirty High do you really need 
				orderTransaction.setFoodJoint(orderQueryResult.getInt("food_joint_id"));
			}
			return orderTransaction;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	private void updateRemainingCaloriesAndExpenses(User user){
		FoodPreference preferencesForValidation = user.getFoodPreference();
		preferencesForValidation.updateRemainingCalories();
		CurrentSession.getInstance().getCurrentUser().updateRemainingExpenses();
		
	}
	
}
