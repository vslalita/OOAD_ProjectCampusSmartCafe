package com.csc;

public class User {
    private int id;
	private String firstName;
	private String lastName;
	private String cardNumber;
	
	User(int id,String firstName,String lastName, String cardNumber){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.cardNumber=cardNumber;
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
	
	
}
