package com.csc;

public class Cafe extends FoodJoint {

	public Cafe(FoodPurchaseTransaction transaction, int locationId) {
		super(transaction, locationId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createTransaction() {
		// TODO creates the transaction by setting status and the pickup location.

	}

	@Override
	public void displayItems() {
		// TODO display items that are for a cafe

	}

}
