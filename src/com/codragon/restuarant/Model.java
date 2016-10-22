package com.codragon.restuarant;

public class Model {
	String food, order, rating, price, nof, serves;
	boolean selected;

	// int value; /* 0 -> checkbox disable, 1 -> checkbox enable */

	Model(String food,String price) {
		this.food = food;
		this.price = price;
		selected = false;
	}

	public String getFood() {
		return this.food;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	public void setFood(String food) {
	    this.food = food;
	  }
	
	public void setPrice(String price) {
	    this.price = price;
	  }
	
	public boolean isSelected() {
	    return selected;
	  }

	  public void setSelected(boolean selected) {
	    this.selected = selected;
	  }

}
