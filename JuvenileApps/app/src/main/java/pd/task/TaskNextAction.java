/*
 * Created on August 04, 2008
 *
 */
package pd.task;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury
 *  
 */
public class TaskNextAction extends PersistentObject
{
    private String topic;
    private String parentAction;
    private String action;
    
	/**
	 * @return the action
	 */
	public String getAction() {
		fetch();
		return action;
	}
	/**
	 * @return the parentAction
	 */
	public String getParentAction() {
		fetch();
		return parentAction;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		fetch();
		return topic;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		if (this.action == null || !this.action.equals(action))
        {
            markModified();
        }
        this.action = action;
	}
	/**
	 * @param parentAction the parentAction to set
	 */
	public void setParentAction(String parentAction) {
		if (this.parentAction == null || !this.parentAction.equals(parentAction))
        {
            markModified();
        }
        this.parentAction = parentAction;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		if (this.topic == null || !this.topic.equals(topic))
        {
            markModified();
        }
        this.topic = topic;
	}
	public static Iterator findAll() {
		return new Home().findAll(TaskNextAction.class);
	}
}
