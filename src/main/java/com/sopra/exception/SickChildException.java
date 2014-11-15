package com.sopra.exception;

/**
 * Exception raised when a child fall in a coma because of too much sugar.
 * @author smeriot
 */
public class SickChildException extends Exception {

	private static final long serialVersionUID = 5924812509958309657L;
	
	public SickChildException(final String msg) {
		super(msg);
	}
}
