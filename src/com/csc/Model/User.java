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
	private FoodPreference foodPreference;
	private int expenses;
	private int expenses_remaining;
	private ArrayList<IObserver> observers=new ArrayList<IObserver>();

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


	//Getters
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
		return this.expenses_remaining;
	}
	
	public int getExpenses() {
		return expenses;
	}
	
	public FoodPreference getFoodPreference() {
		return foodPreference;
	}


	
	//Setters
	public void setExpenses(int expenses){
		this.expenses=expenses;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}

	public void setLastName(String lastName) {
		this.lastName=lastName;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber=cardNumber;
	}
	
	public void setFoodPreference(FoodPreference foodPreference) {
		this.foodPreference = foodPreference;
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
				fundsRemaining=this.getExpenses();
			}
			else if(amountSpentForTheCurrentMonth<=this.getExpenses()){
				fundsRemaining=this.getExpenses()-amountSpentForTheCurrentMonth;
			}
			String query="update user set expenses_remaining="+fundsRemaining+" where card_number='"+this.cardNumber+"'";
			this.expenses_remaining=fundsRemaining;
			updateProfileStatement.executeUpdate(query);
			notifyObservers();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		if(observers.size()>0){
			for(int i=0;i<observers.size();i++){
				observers.get(i).updateComponents();
			}
		}
	}

	
}
