package com.csc.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.csc.Cafe;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.FoodJoint;

public class FoodPurchaseTransaction {

	private int id;
	private ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
	private String status;
	private FoodJoint foodJoint;
	private String cardNumber;
	private Date date;

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
	
	public FoodPurchaseTransaction(int id){
		this.id=id;
		getOrderDetails(id);
	}

	private void getOrderDetails(int id2) {
		// TODO Auto-generated method stub
		try {
			Statement orderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String orderItemsQuery="Select * from food_order_transaction_lines where order_id="+id;
			ResultSet orderItemsQueryResult=orderQueryStatement.executeQuery(orderItemsQuery);
			while(orderItemsQueryResult.next()){
				FoodItem item=new FoodItem(orderItemsQueryResult.getInt("id"));
				this.foodItems.add(item);
			}
			String orderQuery="Select * from food_order_transaction where id="+id;
			ResultSet orderQueryResult=orderQueryStatement.executeQuery(orderQuery);
			while(orderQueryResult.next()){
				this.status=orderQueryResult.getString("status");
				this.cardNumber=orderQueryResult.getString("card_number");
				//TODO create nly cafe object.Check with this.
				this.foodJoint=new Cafe(orderQueryResult.getInt("food_joint_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public FoodJoint getFoodJoint(){
		return this.foodJoint;
	}
	
	public void createTransaction(){
		//TODO write functionality to insert data into table
		try {
			Statement orderInsertionStatement=DatabaseConnection.connectionRequest().createStatement();
			int price=0;
			for(int i=0;i<foodItems.size();i++){
				price=price+foodItems.get(i).getPrice();
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal1 = Calendar.getInstance();
			String orderInsertionQuery="insert into food_order_transaction(food_joint_id,status,card_number,amount,created_on) values ("+CurrentSession.getMachine().getId()+",'"+this.status+"','"+CurrentSession.getCurrentUser().getCardNumber()+"',"+price+",'"+dateFormat.format(cal1.getTime())+"')";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTransaction(){
		//TODO delete ordered items and transaction
	}

	public boolean update(){
		//TODO write query to update the order.
		try {
			Statement updateOrderQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String updateOrderQuery="update food_order_transaction set status='"+this.status+"' where id="+this.id;
			int updateOrderQueryResult=updateOrderQueryStatement.executeUpdate(updateOrderQuery);
			if(updateOrderQueryResult>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return false;
		
	}

}
