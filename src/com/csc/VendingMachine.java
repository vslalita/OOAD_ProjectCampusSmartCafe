package com.csc;

public class VendingMachine extends FoodJoint {

	public VendingMachine(FoodPurchaseTransaction transaction, int locationId) {
		super(transaction, locationId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createTransaction() {
		// TODO create a transaction. Implementation involves immediate delivery of the item.

	}

	@Override
	public void displayItems() {
		// TODO displays items that are to be displayed nly for a vending machine

	}

}
