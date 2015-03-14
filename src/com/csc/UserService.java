package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.csc.model.FoodPurchaseTransaction;
import com.csc.model.User;

public class UserService {

	
	public ArrayList<FoodPurchaseTransaction> getCurrentUserUnpickedOrders(){
		ArrayList<FoodPurchaseTransaction> unpickedOrders=new ArrayList<FoodPurchaseTransaction>();
		Statement unpickedOrdersStatement;
		try {
			unpickedOrdersStatement = DatabaseConnection.connectionRequest().createStatement();
			String unpickedOrdersQuery="Select * from food_order_transaction where status='Ordered' "
					+ "and card_number='"+CurrentSession.getCurrentUser().getCardNumber()+"'"
							+ "and food_joint_id="+CurrentSession.getMachine().getId();
			ResultSet unpickedOrdersQueryResult=unpickedOrdersStatement.executeQuery(unpickedOrdersQuery);
			while(unpickedOrdersQueryResult.next()){
				int id=unpickedOrdersQueryResult.getInt("id");
				FoodPurchaseTransaction order=new FoodPurchaseTransaction();
				order.setOrderDetails(id);
				//order.setStatus(unpickedOrdersQueryResult.getString("status"));
				unpickedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unpickedOrders;
	}
	
	
	public void updateExpenses(int expense,String action){
			User currentSessionUser=CurrentSession.getCurrentUser();
			int currentExpenses=CurrentSession.getCurrentUser().getExpenses();
			if(action.equals("add")){
				
				currentSessionUser.setExpenses(currentExpenses+expense);
			}
			if(action.equals("reduce")){
				int amountRemaining=CurrentSession.getCurrentUser().getRemainingExpenses();
				if(expense<CurrentSession.getCurrentUser().getExpenses() &&expense<amountRemaining){
					currentSessionUser.setExpenses(currentExpenses-expense);
				}
			}
			currentSessionUser.updateExpenses();
	}
	
	public boolean pickUpOrder(FoodPurchaseTransaction transaction){
		transaction.setStatus("Delivered");
		if(transaction.update()){
		  return true;	
		}
		else{
			return false;
		}
	}

	
}
