package com.csc.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csc.CurrentSession;
import com.csc.DatabaseConnection;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private int expenses;

	public User(int id,String firstName,String lastName, String cardNumber){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.cardNumber=cardNumber;
	}

	public User(){
		
	}

	public User getUserByCardNumber(String cardNumber){
		Statement userQueryStatement;
		try{
			userQueryStatement = DatabaseConnection.connectionRequest().createStatement();
			String userQuery="Select * from user where card_number='"+cardNumber+"'";
			ResultSet userQueryResult=userQueryStatement.executeQuery(userQuery);
			while(userQueryResult.next()){
				if(cardNumber.equals(userQueryResult.getString("card_number"))){
					this.id=userQueryResult.getInt("id");
					this.firstName=userQueryResult.getString("first_name");
					this.lastName=userQueryResult.getString("last_name");
					this.cardNumber=cardNumber;
					int expenses=userQueryResult.getInt("expenses");
					setExpenses(expenses);
				}
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}

	public int getId(){
		return this.id;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getCardNumber(){
		return this.cardNumber;
	}

	public ArrayList<FoodPurchaseTransaction> getUnpickedOrders(){
		ArrayList<FoodPurchaseTransaction> unpickedOrders=new ArrayList<FoodPurchaseTransaction>();
		Statement unpickedOrdersStatement;
		try {
			unpickedOrdersStatement = DatabaseConnection.connectionRequest().createStatement();
			//TODO include condition for date and time of the 
			String unpickedOrdersQuery="Select * from food_order_transaction where status='Ordered'";
			ResultSet unpickedOrdersQueryResult=unpickedOrdersStatement.executeQuery(unpickedOrdersQuery);
			while(unpickedOrdersQueryResult.next()){
				int id=unpickedOrdersQueryResult.getInt("id");
				ArrayList<FoodItem> unpickedOrderedItems=getOrderItems(id);
				String cardNumber=unpickedOrdersQueryResult.getString("card_number");
				int locationId=unpickedOrdersQueryResult.getInt("location_id");
				FoodPurchaseTransaction order=new FoodPurchaseTransaction(id,unpickedOrderedItems,cardNumber,locationId);
				//order.setStatus(unpickedOrdersQueryResult.getString("status"));
				unpickedOrders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unpickedOrders;
	}

	private ArrayList<FoodItem> getOrderItems(int orderId){
		ArrayList<FoodItem> foodItem =new ArrayList<FoodItem>();
		Statement orderFoodItemStatement;
		try {
			orderFoodItemStatement= DatabaseConnection.connectionRequest().createStatement();
			String orderFoodItemQuery="Select * from food_order_transaction_lines where order_id="+orderId;
			ResultSet orderFoodItemQueryResult=orderFoodItemStatement.executeQuery(orderFoodItemQuery);
			while(orderFoodItemQueryResult.next()){
				FoodItem item=getFoodItem(orderFoodItemQueryResult.getInt("item_id"));
				foodItem.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foodItem;
	}

	private FoodItem getFoodItem(int itemId) {
		// TODO Auto-generated method stub
		FoodItem item=null;
		Statement foodItemQueryStatement;
		try {
			foodItemQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String foodItemQuery="Select * from food_item where id="+itemId;
			ResultSet foodItemQueryResult=foodItemQueryStatement.executeQuery(foodItemQuery);
			while(foodItemQueryResult.next()){
				int id=foodItemQueryResult.getInt("id");
				String itemName=foodItemQueryResult.getString("name");
				int price =foodItemQueryResult.getInt("price");
				int calories=foodItemQueryResult.getInt("calories");
				item=new FoodItem(id,itemName,price,calories);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	public void pickUpOrder(FoodPurchaseTransaction transaction){
		transaction.setStatus("Delivered");
		transaction.update();
	}

	public void updateExpenses(int expense){
		try {
			this.expenses=expense;
			Statement updateExpenseStatement=DatabaseConnection.connectionRequest().createStatement();
			String updateExpenseQuery="update user set expenses="+expense+" where card_number="+this.cardNumber;
			updateExpenseStatement.executeUpdate(updateExpenseQuery);
			updateRemainingExpenses();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateRemainingExpenses(){
		DateFormat dateFormatForExpenses = new SimpleDateFormat("yyyy-MM");
		Date dateObj = new Date();
		int amountSpentForTheCurrentMonth=0;
		Statement updateProfileStatement;
		try {
			updateProfileStatement = DatabaseConnection.connectionRequest().createStatement();
			String ordersAmountQuery="Select fot.amount,fot.created_on "
					+ "                 from food_order_transaction fot"
					+ "                 where fot.created_on like '"+dateFormatForExpenses.format(dateObj)+"%'";
			ResultSet ordersAmountQueryResult=updateProfileStatement.executeQuery(ordersAmountQuery);
			while(ordersAmountQueryResult.next()){
				amountSpentForTheCurrentMonth=amountSpentForTheCurrentMonth+ordersAmountQueryResult.getInt("amount");
			}

			int fundsRemaining=0;
			if(amountSpentForTheCurrentMonth==0){
				fundsRemaining=CurrentSession.getCurrentUser().getExpenses();
			}
			else if(amountSpentForTheCurrentMonth<=CurrentSession.getCurrentUser().getExpenses()){
				fundsRemaining=CurrentSession.getCurrentUser().getExpenses()-amountSpentForTheCurrentMonth;
			}

			String query="update user set expenses_remaining="+fundsRemaining+" where card_number='"+CurrentSession.getCurrentUser().getCardNumber()+"'";
			updateProfileStatement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public int getExpenses() {
		return expenses;
	}

	public void setExpenses(int expenses){
		this.expenses=expenses;
	}
}
