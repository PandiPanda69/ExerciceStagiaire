package com.sopra.core;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sopra.bean.AnswerEnum;
import com.sopra.bean.Child;
import com.sopra.bean.Request;
import com.sopra.bean.Sweet;
import com.sopra.exception.SickChildException;
import com.sopra.todo.Watchdog;

public class HeartbeatThread extends Thread {

	private static Lock lock = new ReentrantLock();
	
	private final ConcurrentLinkedQueue<Request> requests;
	private final Watchdog watchdog;
	
	public HeartbeatThread(final ConcurrentLinkedQueue<Request> requests, final Watchdog watchdog) {
		this.requests = requests;
		this.watchdog = watchdog;
	}
	
	@Override
	public void run() {
		
		try {
			Random random = new Random();
			Request currentRequest;
			while((currentRequest = requests.poll()) != null) {
				
				Child target = currentRequest.getTarget();
				Sweet sweet = currentRequest.getSweet();
				
				// Ask watchdog what to do
				AnswerEnum answer = watchdog.onNewChildAskForSweet(target, sweet);
				
				if(!AnswerEnum.WAIT.equals(answer)) {

					// Check multi threading is ok.
					if(!lock.tryLock()) {
						if(target.isStillAlive()) {
							sweet = new Sweet(1000f); 
							System.err.println("Vous avez autorisé " + target.getName() + " à manger un bonbon qu'il n'aurait pas du en voulant répondre à plusieurs enfants à la fois.");
							eatSweet(target, sweet);
						}
						
						break;
					}
					
					if(AnswerEnum.ALLOW.equals(answer)) {
						eatSweet(target, sweet);
					}

					lock.unlock();
					watchdog.onChildLeaving(target);
				}
				else {
					requests.add(currentRequest);
				}

				// Sleep a little (threading execution shuffling)
				Thread.sleep(Math.abs(random.nextInt(10)));
			}
		}
		catch(SickChildException ex) {
			System.err.println(ex.getMessage());
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	private void eatSweet(final Child child, final Sweet sweet) throws SickChildException {
		synchronized (child) {
			child.eatSweet(sweet);
		}
	}
}
