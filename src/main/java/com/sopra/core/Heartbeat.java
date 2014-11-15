package com.sopra.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sopra.bean.Child;
import com.sopra.bean.Request;
import com.sopra.bean.Sweet;
import com.sopra.factory.ChildFactory;
import com.sopra.factory.SweetFactory;
import com.sopra.todo.Watchdog;
import com.sopra.todo.WatchdogImpl;

public class Heartbeat {

	private Child[] children;
	private Sweet[] sweets;
	
	private final int threadCount;
	
	public Heartbeat(final int childCount, final int sweetCount, final int threadCount) {
		
		children = new Child[childCount];
		sweets = new Sweet[sweetCount];
		
		this.threadCount = threadCount;
		
		populateChildren();
		populateSweets();
	}
	
	private void populateChildren() {
		for(int i = 0; i < children.length; ++i) {
			children[i] = ChildFactory.newChild();
		}
	}
	
	private void populateSweets() {
		for(int i = 0; i < sweets.length; ++i) {
			sweets[i] = SweetFactory.newSweet();
		}
	}
	
	private ConcurrentLinkedQueue<Request> populateRequests() {
		ConcurrentLinkedQueue<Request> requestsQueue = new ConcurrentLinkedQueue<>();
		
		Random random = new Random();
		for(Sweet sweet : sweets) {
			Child target = children[random.nextInt(children.length)];
			
			requestsQueue.add(new Request(target, sweet));
		}
		
		return requestsQueue;
	}
	
	private List<HeartbeatThread> populateThreads(final Watchdog watchdog, final ConcurrentLinkedQueue<Request> requestsQueue) {
		List<HeartbeatThread> threads = new ArrayList<>();
		
		for(int i = 0; i < threadCount; ++i) {
			threads.add(new HeartbeatThread(requestsQueue, watchdog));
		}
		
		return threads;
	}
	
	public void start() {
		
		Watchdog watchdog = new WatchdogImpl();
		ConcurrentLinkedQueue<Request> requestsQueue = populateRequests();
		
		List<HeartbeatThread> threads = populateThreads(watchdog, requestsQueue);
		for(HeartbeatThread thread : threads) {
			thread.start();
		}
		
		// And now, wait threads end
		for(HeartbeatThread thread : threads) {
			try {
				thread.join();
			}
			catch(InterruptedException ex) {
				System.err.println("Already interrupted.");
			}
		}
		
		displayStats();
	}
	
	private void displayStats() {
		int count = 0;
		
		StringBuilder sb = new StringBuilder()
		.append("**************************************\n")
		.append("*             Résultats              *\n");
		
		for(Child current : children) {
			if(!current.isStillAlive()) {
				count++;
			}
		}
		
		if(count == 0) {
			sb.append("* Félicitations ! Vous avez réussi ! *\n");
		} else {
			sb.append("*       ").append(count).append(" enfants sont malades.      *\n");
		}
		
		sb.append("**************************************");
		
		if(count == 0) {
			System.out.println(sb);
		} else {
			System.err.println(sb);
		}
	}
}
