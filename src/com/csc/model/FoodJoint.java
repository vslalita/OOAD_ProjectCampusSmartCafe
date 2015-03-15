package com.csc.model;


public class FoodJoint {
	private int id;
	private String address;
	private String name;
	private int xPosition;
	private int yposition;
	private boolean isCafe;
	private boolean isVendingMachine;
    
    public FoodJoint(int id,String name,String location,int xPosition,int yPosition,boolean isCafe,boolean isVendingMachine){
    	this.id=id;
    	this.setName(name);
    	this.address=location;
    	this.xPosition=xPosition;
    	this.yposition=yPosition;
    	this.isCafe=isCafe;
    	this.isVendingMachine=isVendingMachine;
    }
    
    public FoodJoint(){
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return address;
	}

	public void setLocation(String location) {
		this.address = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yposition;
	}

	public void setYPosition(int yposition) {
		this.yposition = yposition;
	}

	public boolean isCafe() {
		return isCafe;
	}

	public void setCafe(boolean isCafe) {
		this.isCafe = isCafe;
	}

	public boolean isVendingMachine() {
		return isVendingMachine;
	}

	public void setVendingMachine(boolean isVendingMachine) {
		this.isVendingMachine = isVendingMachine;
	}
   
    
}
