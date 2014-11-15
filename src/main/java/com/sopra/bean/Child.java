package com.sopra.bean;

import com.sopra.exception.SickChildException;

public class Child {

	private final String name;
	private final float sugarLimit;
	
	private boolean stillAlive;
	
	private float sugarAte;
	
	public Child(final String name, final float sugarLimit) {
		this.name = name;
		this.sugarLimit = sugarLimit;
		this.sugarAte = .0f;
		
		this.stillAlive = true;
	}
	
	/**
	 * Let the child eats the sweet.
	 * @param sweet The sweet
	 * @throws SickChildException Raised if this child ate too much sugar.
	 */
	public void eatSweet(final Sweet sweet) throws SickChildException {
		if(!stillAlive) {
			return;
		}
		
		sugarAte += sweet.getSugarQuantity();
		
		if(sugarAte > sugarLimit) {
			this.stillAlive = false;
			throw new SickChildException(new StringBuffer(name).append(" est tombé malade et vomit partout à présent.").toString());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public float getSugarLimitBeforeComa() {
		return sugarLimit;
	}
	
	public boolean isStillAlive() {
		return stillAlive;
	}
	
	@Override
	public String toString() {
		return "Child [name=" + name + ", sugarLimit=" + sugarLimit + ", sugarAte=" + sugarAte + "]";
	}
}
