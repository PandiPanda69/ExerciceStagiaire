package com.sopra.factory;

import java.util.Random;

import com.sopra.bean.Child;

/**
 * Factory for building random child instance. <br/>
 * <b>Note</b>: Two children cannot have the same name. 
 * @author smeriot
 *
 */
public class ChildFactory {

	public static Random random = new Random();
	public static String[] names = {"Alice", "Bob", "Charlie", "Denis", "Eric", "Fiora", "Gilbert", "Hans", "Irène", "Jacob", "Kelly", "Mathieu", "Nina", "Orianne", "Pierre", "Quentin", "Rémy", "Sandra", "Tommy", "Ulysse", "Victoria", "Warwick", "Xavier", "Yves", "Zoé"};
	public static boolean[] pick = new boolean[names.length];
	
	/**
	 * Create a new random child instance.
	 * @return A {link Child} instance
	 */
	public static Child newChild() {
		
		int namePick;
		
		do {
			namePick = random.nextInt(names.length);
		} while(pick[namePick]);
		
		pick[namePick] = true;
		return new Child(names[namePick], 2.0f + Math.abs((random.nextInt()%1000)/100.0f));
	}
}
