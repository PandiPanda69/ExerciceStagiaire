package com.sopra;

import com.sopra.core.Heartbeat;



public class AlgorithmExercise4 {

	public static final int MAX_CHILD_COUNT = 25;
	public static final int MAX_SWEET_COUNT = 1000;
	
	public static final int THREAD_COUNT = 10;
	
	public static void main(String... args) {
		new Heartbeat(MAX_CHILD_COUNT, MAX_SWEET_COUNT, THREAD_COUNT).start();
	}
}
