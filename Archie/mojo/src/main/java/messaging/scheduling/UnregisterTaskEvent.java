package messaging.scheduling;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command UnregisterTaskCommand
 *@author Design detail addin
 *@version 1.0
 */
public class UnregisterTaskEvent extends RequestEvent {
    public UnregisterTaskEvent() { }

    public String getTaskName(){
            return this.taskName;
        }

    public void setTaskName(String taskName){
		this.taskName = taskName;
    }

    private String taskName;
}
