package com.sopra.todo;

import com.sopra.bean.AnswerEnum;
import com.sopra.bean.Child;
import com.sopra.bean.Sweet;

public interface Watchdog {

	/**
	 * Called each time a child comes to you to ask if he/she can eat this sweet.
	 * @param child 
	 * @param sweet The sweet the child wanna eat
	 * @return {@link AnswerEnum} telling your answer.
	 */
	AnswerEnum onNewChildAskForSweet(final Child child, final Sweet sweet);
	
	/**
	 * When the child leaves the queue right after having ate its sweet.
	 * @param child
	 */
	void onChildLeaving(final Child child);
}
