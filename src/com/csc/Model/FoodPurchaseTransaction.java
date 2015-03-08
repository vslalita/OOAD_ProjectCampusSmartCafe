package com.csc.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.UserFoodPreference;

public class FoodPurchaseTransaction {

	private int id;
	private ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
	private String status;
	private int locationId;
	private String cardNumber;
	private Date date;

	public FoodPurchaseTransaction(int id,ArrayList<FoodItem> foodItems,String cardNumber,int locationId){
		this.id=id;
		this.foodItems=foodItems;
		this.cardNumber=cardNumber;
		this.locationId=locationId;
	}
	
	public FoodPurchaseTransaction(ArrayList<FoodItem> foodItems,String cardNumber,int locationId){
		this.foodItems=foodItems;
		this.cardNumber=cardNumber;
		this.locationId=locationId;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public void setLocation(int locationId){
		this.locationId=locationId;
	}

	public void createTransaction(){
		//TODO write functionality to insert data into table
		try {
			Statement orderInsertionStatement=DatabaseConnection.connectionRequest().createStatement();
			int price=0;
			for(int i=0;i<foodItems.size();i++){
				price=price+foodItems.get(i).getPrice();
			}
			String orderInsertionQuery="insert into food_order_transaction(food_joint_id,status,card_number,amount) values (1,'"+this.status+"','"+CurrentSession.getCurrentUser().getCardNumber()+"',"+price+")";
			int insertionQueryResult=orderInsertionStatement.executeUpdate(orderInsertionQuery,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=orderInsertionStatement.getGeneratedKeys();
			int orderId=0;
			while(rs.next()){
			 orderId=rs.getInt(1);
			}
			
			for(int i=0;i<foodItems.size();i++){
				String orderLinesInsertionQuery="insert into food_order_transaction_lines(order_id,item_id) values("+orderId+","+foodItems.get(i).getId()+")";
				orderInsertionStatement.executeUpdate(orderLinesInsertionQuery);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTransaction(){
		//TODO delete ordered items and transaction
	}

	public void update(){
		//TODO write query to update the order.
	}

}
