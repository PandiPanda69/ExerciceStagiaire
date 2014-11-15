package com.sopra.bean;

public class Sweet {

	private final float sugarQuantity;
	
	public Sweet(float sugarQuantity) {
		this.sugarQuantity = sugarQuantity;
	}
	
	public float getSugarQuantity() {
		return sugarQuantity;
	}

	@Override
	public String toString() {
		return "Sweet [sugarQuantity=" + sugarQuantity + "]";
	}
}
