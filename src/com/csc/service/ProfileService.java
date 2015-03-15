package com.csc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.csc.CSCApplicationContext;
import com.csc.CurrentSession;
import com.csc.DatabaseConnection;

public class ProfileService {
	//TODO add it in user class
	public PieDataset getDietaryStatistics(){
		DateFormat dateFormatForCalories = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -7);
		DefaultPieDataset result = new DefaultPieDataset();
		String cardNumber=CurrentSession.getInstance().getCurrentUser().getCardNumber();
		try {
			Statement caloriesPerDayQueryStatement=DatabaseConnection.connectionRequest().createStatement();
		    String caloriesPerDayQuery="Select sum(fi.calories) calories,fot.created_on "
		    		+ "from food_order_transaction fot, "
		    		+      "food_order_transaction_lines fotl,"
		    		+      "food_item fi "
		    		+ "where fot.id=fotl.order_id"
		    		+ "  and fotl.item_id=fi.id"
		    		+ "  and fot.card_number='"+cardNumber+"'"
		    		+ "  and fot.created_on <= '"+dateFormatForCalories.format(cal1.getTime())+"' "
		    	    + "  and fot.created_on >= '"+dateFormatForCalories.format(cal2.getTime())+"'"
		    	    + " group by created_on";        
		    ResultSet caloriesPerDayQueryResult=caloriesPerDayQueryStatement.executeQuery(caloriesPerDayQuery);
		    while(caloriesPerDayQueryResult.next()){
		    	result.setValue(dateFormatForCalories.format(caloriesPerDayQueryResult.getDate("created_on", cal1)), caloriesPerDayQueryResult.getInt("calories"));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public CategoryDataset getExpensesStatics(){
		DateFormat dateFormatForCalories = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -30);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String cardNumber=CurrentSession.getInstance().getCurrentUser().getCardNumber();
		try {
			Statement expenseRetrievalQueryStatement=DatabaseConnection.connectionRequest().createStatement();
			String expenseRetrievalQuery="Select sum(amount) amount,created_on "
					+ "from food_order_transaction "
					+ "where created_on >= '"+dateFormatForCalories.format(cal2.getTime())+"' "
					+ "and created_on <= '"+dateFormatForCalories.format(cal1.getTime())+"' "
					+ "and card_number='"+cardNumber+"' "
					+ "group by created_on ";
			ResultSet expenseRetrievalQueryResult=expenseRetrievalQueryStatement.executeQuery(expenseRetrievalQuery);
			while(expenseRetrievalQueryResult.next()){
				dataset.addValue(expenseRetrievalQueryResult.getInt("amount"), dateFormatForCalories.format(expenseRetrievalQueryResult.getDate("created_on")), "expenses");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataset;
	}
	
	//TODO remove main
	public static void main(String[] args){
		ProfileService p=new ProfileService();
		p.getDietaryStatistics();
	}

}


