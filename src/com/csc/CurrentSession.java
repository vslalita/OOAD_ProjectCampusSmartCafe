package com.csc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrentSession {
	//currentUser holds the information of the member who is currently logged in
	private static  User currentUser;
	private static CurrentSession currentSession=null;
	private CurrentSession(User user){
		currentUser=user;
	}

	public static CurrentSession getMemberInstance(String cardNumber){
		User user=null;
		if(currentSession==null){
			Statement userQueryStatement;
			try {
				userQueryStatement = DatabaseConnection.connectionRequest().createStatement();
				String userQuery="Select * from user where card_number='"+cardNumber+"'";
				ResultSet userQueryResult=userQueryStatement.executeQuery(userQuery);
				while(userQueryResult.next()){
					if(cardNumber.equals(userQueryResult.getString("card_number"))){
						int id=userQueryResult.getInt("id");
						String firstName=userQueryResult.getString("first_name");
						String lastName=userQueryResult.getString("last_name");
						String userCardNumber=cardNumber;
						user=new User(id,firstName,lastName,userCardNumber);
					}
				} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentSession=new CurrentSession(user);
			return currentSession;
		}
		else{
			currentUser=user;
			return currentSession;
		}
	}

	public static User getCurrentUser(){
		if(currentSession!=null && currentUser!=null){
			return currentUser;
		}
		else return null;
	}

	public static void resetCurrentMember(){
		currentUser=null;
	}
}

