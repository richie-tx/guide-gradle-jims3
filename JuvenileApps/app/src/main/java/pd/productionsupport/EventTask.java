package pd.productionsupport;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.scheduling.Task;

public class EventTask extends Task {

	private String taskStatus;
	private String eventName;
    
    /**
     * sets the tasks scheduler class name
     * 
     * @param taskStatus
     *            the name of the class
     */
    public void setTaskStatus(String taskStatus)
    {
        if (this.taskStatus == null || !this.taskStatus.equals(taskStatus))
        {
            markModified();
        }
        this.taskStatus = taskStatus;
    }

    /**
     * 
     */
    public String getTaskStatus()
    {
        fetch();
        return this.taskStatus;
    }
	
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		fetch();
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		 if (this.eventName == null || !this.eventName.equals(eventName))
	        {
	            markModified();
	        }
	        this.eventName = eventName;
	}

	/**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4177C29D03A9
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return (Iterator) home.findAll(event, EventTask.class);
    }
}
