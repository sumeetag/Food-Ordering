package com.codragon.restuarant;

public class OrderModel {
	
	String food, nof, q;
	
	//boolean selected;

	// int value; /* 0 -> checkbox disable, 1 -> checkbox enable */

	OrderModel(String food, String nof, String q) {
		this.food = food;
		this.nof = nof;
		this.q = q;
		
	}

	public String getFood() {
		return this.food;
	}

	public String getQ() {
		return this.q;
	}

	
	public String getNof() {
		return this.nof;
	}
	
	
	public void setFood(String food) {
	    this.food = food;
	  }
	
	public void setQ(String q) {
	    this.q = q;
	  }
	
	public void setNof(String nof) {
	    this.nof = nof;
	  }

}
