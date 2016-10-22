package com.codragon.restuarant;

public class RatingModel {
	
	String food;
	//boolean selected;

	// int value; /* 0 -> checkbox disable, 1 -> checkbox enable */

	RatingModel(String food) {
		this.food = food;
	}

	public String getFood() {
		return this.food;
	}

	
	
	public void setFood(String food) {
	    this.food = food;
	  }
	
	

}
