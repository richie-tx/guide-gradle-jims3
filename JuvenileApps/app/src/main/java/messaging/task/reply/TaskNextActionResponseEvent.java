/*
 * Created on Aug 04, 2008
 *
 */
package messaging.task.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class TaskNextActionResponseEvent extends ResponseEvent
{
	private String parentAction;
	private Collection nextActions;

	/**
	 * @return the nextActions
	 */
	public Collection getNextActions() {
		return nextActions;
	}

	/**
	 * @param nextActions the nextActions to set
	 */
	public void setNextActions(Collection nextActions) {
		this.nextActions = nextActions;
	}

	/**
	 * @return the parentAction
	 */
	public String getParentAction() {
		return parentAction;
	}

	/**
	 * @param parentAction the parentAction to set
	 */
	public void setParentAction(String parentAction) {
		this.parentAction = parentAction;
	}
}
