package com.csc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.csc.CurrentSession;
import com.csc.DatabaseConnection;
import com.csc.ObservableObserver.IObservable;
import com.csc.ObservableObserver.IObserver;

public class User implements IObservable{
	private int id;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private int expenses;
	private int expenses_remaining;
	ArrayList<IObserver> observers=new ArrayList<IObserver>();

	public User(int id,String firstName,String lastName, String cardNumber){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.cardNumber=cardNumber;
	}

	public User(){
	}

	public void addObservers(IObserver observer){
		observers.add(observer);
	}
	
	public User setUserDetailsByCardNumber(String cardNumber){
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

	
	public int getRemainingExpenses(){
		updateRemainingExpenses();
		return this.expenses_remaining;
	}
	
	public int getExpenses() {
		return expenses;
	}

	public void setExpenses(int expenses){
		this.expenses=expenses;
	}
	

	public void updateExpenses(){
		try {
			Statement updateExpenseStatement=DatabaseConnection.connectionRequest().createStatement();
			String updateExpenseQuery="update user set expenses="+this.expenses+" where card_number="+this.cardNumber;
			updateExpenseStatement.executeUpdate(updateExpenseQuery);
			updateRemainingExpenses();
		} catch (SQLException e) {
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
					+ "                 where fot.created_on like '"+dateFormatForExpenses.format(dateObj)+"%'"
							+ "           and fot.card_number="+this.cardNumber;
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
			String query="update user set expenses_remaining="+fundsRemaining+" where card_number='"+this.cardNumber+"'";
			this.expenses_remaining=fundsRemaining;
			updateProfileStatement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		if(observers.size()>0){
			for(int i=0;i<observers.size();i++){
				observers.get(i).updateComponents();
			}
		}
	}

}
