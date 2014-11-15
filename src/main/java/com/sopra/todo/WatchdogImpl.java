package com.sopra.todo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sopra.bean.AnswerEnum;
import com.sopra.bean.Child;
import com.sopra.bean.Sweet;

public class WatchdogImpl implements Watchdog {

	private Map<String, Float> cache = new HashMap<>();
	private Lock lock = new ReentrantLock();
	
	@Override
	public AnswerEnum onNewChildAskForSweet(Child child, Sweet sweet) {

		// If already locked, ask the child to wait until the current one leaves the queue.
		if(!lock.tryLock()) {
			return AnswerEnum.WAIT;
		}
		
		float qty;
		String name = child.getName();
		
		if(!cache.containsKey(name)) {
			qty = sweet.getSugarQuantity();
		} else {
			qty = cache.get(name) + sweet.getSugarQuantity();
		}
		
		if(qty >= child.getSugarLimitBeforeComa()) {
			return AnswerEnum.DENY;
		}
		
		cache.put(name, qty);
		return AnswerEnum.ALLOW;
	}

	@Override
	public void onChildLeaving(Child child) {
		// Release the lock so you can handle a new request.
		lock.unlock();
	}
}
