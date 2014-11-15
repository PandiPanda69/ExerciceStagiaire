package com.sopra.factory;

import java.util.Random;

import com.sopra.bean.Sweet;

/**
 * Factory for building random sweet instances  
 * @author smeriot
 */
public class SweetFactory {

	private static Random random = new Random();
	
	/**
	 * Build a new random sweet instance
	 * @return A {@link Sweet} instance
	 */
	public static Sweet newSweet() {
		return new Sweet(random.nextInt(325) / 100f);
	}
}
