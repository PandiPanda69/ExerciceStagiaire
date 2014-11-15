package com.sopra.bean;

/**
 * Bean simulating a child requesting a sweet. 
 * @author smeriot
 */
public class Request {

	private final Child target;
	private final Sweet sweet;
	
	public Request(final Child target, final Sweet sweet) {
		this.target = target;
		this.sweet = sweet;
	}

	public Child getTarget() {
		return target;
	}

	public Sweet getSweet() {
		return sweet;
	}

	@Override
	public String toString() {
		return "Request [target=" + target + ", sweet=" + sweet + "]";
	}
}
