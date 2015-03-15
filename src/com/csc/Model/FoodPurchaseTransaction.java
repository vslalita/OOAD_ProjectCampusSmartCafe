package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.controller.CSCServiceContext;

public class FoodPurchaseTransaction {

	private int id;
	private ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
	private String status;
	private FoodJoint foodJoint;
	private String cardNumber;

	public FoodPurchaseTransaction(int id,ArrayList<FoodItem> foodItems,String cardNumber,FoodJoint foodJoint){
		this.id=id;
		this.foodItems=foodItems;
		this.cardNumber=cardNumber;
		this.foodJoint=foodJoint;
	}
	
	public FoodPurchaseTransaction(ArrayList<FoodItem> foodItems,String cardNumber,FoodJoint foodJoint){
		this.foodItems=foodItems;
		this.cardNumber=cardNumber;
		this.foodJoint=foodJoint;
	}
	
	public FoodPurchaseTransaction(){
		
	}
	//TODO Change this to a Service getOrderDetails
	public void setOrderDetails(int orderId) {
		this.id=orderId;
		try {
			Statement orderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String orderItemsQuery="Select * from food_order_transaction_lines where order_id="+orderId;
			ResultSet orderItemsQueryResult=orderQueryStatement.executeQuery(orderItemsQuery);
			while(orderItemsQueryResult.next()){
				FoodItem item=new FoodItem(orderItemsQueryResult.getInt("id"));
				this.foodItems.add(item);
			}
			String orderQuery="Select * from food_order_transaction where id="+orderId;
			ResultSet orderQueryResult=orderQueryStatement.executeQuery(orderQuery);
			while(orderQueryResult.next()){
				this.status=orderQueryResult.getString("status");
				this.cardNumber=orderQueryResult.getString("card_number");
				//TODO Prioirty High do you really need 
				this.foodJoint=CSCServiceContext.getFoodJointService().getFoodJointDetailsById(orderQueryResult.getInt("food_joint_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setStatus(String status){
		this.status=status;
	}

	public void setFoodJoint(FoodJoint foodJoint){
		this.foodJoint=foodJoint;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getCardNumber(){
		return this.cardNumber;
	}

	public FoodJoint getFoodJoint(){
		return this.foodJoint;
	}
	
	public void createTransaction(){
		try {
			Statement orderInsertionStatement=DatabaseConnection.connectionRequest().createStatement();
			int price=0;
			for(int i=0;i<foodItems.size();i++){
				price=price+foodItems.get(i).getPrice();
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal1 = Calendar.getInstance();
			String orderInsertionQuery="insert into food_order_transaction(food_joint_id,status,card_number,amount,created_on) values ("+this.foodJoint.getId()+",'"+this.status+"','"+this.cardNumber+"',"+price+",'"+dateFormat.format(cal1.getTime())+"')";
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


	public boolean update(){
		try {
			Statement updateOrderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String updateOrderQuery="update food_order_transaction set status='"+this.status+"' where id="+this.id;
			int updateOrderQueryResult=updateOrderQueryStatement.executeUpdate(updateOrderQuery);
			if(updateOrderQueryResult>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
      return false;
		
	}

}
