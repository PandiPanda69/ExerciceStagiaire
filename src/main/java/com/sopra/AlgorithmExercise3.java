package com.sopra;

import com.sopra.core.Heartbeat;



public class AlgorithmExercise3 {

	public static final int MAX_CHILD_COUNT = 3;
	public static final int MAX_SWEET_COUNT = 25;
	
	public static final int THREAD_COUNT = 1;
	
	public static void main(String... args) {
		new Heartbeat(MAX_CHILD_COUNT, MAX_SWEET_COUNT, THREAD_COUNT).start();
	}
}
